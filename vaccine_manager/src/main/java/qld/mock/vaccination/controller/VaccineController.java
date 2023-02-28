package qld.mock.vaccination.controller;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


import javax.validation.Valid;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import qld.mock.vaccination.dto.VaccineDTO;

import qld.mock.vaccination.entities.Vaccine;
import qld.mock.vaccination.entities.VaccineType;
import qld.mock.vaccination.service.VaccineService;
import qld.mock.vaccination.service.VaccineTypeService;
import qld.mock.vaccination.utils.ControllerUtils;

@Controller
@RequestMapping("vaccine-manager")
public class VaccineController {
	
	@Autowired
	private VaccineService vaccineService;
	
	@Autowired
	private VaccineTypeService vaccineTypeService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String getList(Model model, @RequestParam(value = "page", required=false,defaultValue="1") Integer page,
						@RequestParam(value = "size", required=false, defaultValue="5") Integer size) {
		
		Page<Vaccine> pageVaccine = vaccineService.getListVaccine(page - 1, size);
		
		model.addAttribute("pages", pageVaccine);
		
		int[] body = ControllerUtils.GenderPage(pageVaccine);
	    
	    model.addAttribute("body", body);
		

		return "vaccine/vaccine-list";

		
	}
	
	
	@RequestMapping(value = "/make-inactive", method = RequestMethod.GET)
	public String makeActive(@RequestParam(value = "ids", required=false) List<String> ids) {

	    vaccineService.MakeInactive(ids);
		
		return "redirect:/vaccine-manager/list";
		
	}
	
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String createVaccine(Model model) {
		
		VaccineDTO vaccineDTO = new VaccineDTO();
		model.addAttribute("vaccineDTO", vaccineDTO);
		
		List<VaccineType> vaccineTypes = vaccineTypeService.getAll();
		model.addAttribute("vaccineTypes", vaccineTypes);

		return "vaccine/create-vaccine";
		
		
	}
	
	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public String submitAddVaccine(@Valid @ModelAttribute("vaccineDTO") VaccineDTO vaccineDTO, BindingResult bindingResult,
			RedirectAttributes redirAttrs, Model model) {
		
		if (bindingResult.hasErrors()) {
			List<VaccineType> vaccineTypes = vaccineTypeService.getAll();
			model.addAttribute("vaccineTypes", vaccineTypes);
			return "vaccine/create-vaccine";
        }

		if(vaccineService.getById(vaccineDTO.getVaccineId()) != null) {
			List<VaccineType> vaccineTypes = vaccineTypeService.getAll();
			model.addAttribute("vaccineTypes", vaccineTypes);
			bindingResult.rejectValue("vaccineId", "error.vaccineDTO", "Id Already exist!");
			return "vaccine/create-vaccine";
		}
	
		if(vaccineService.getVaccineByVaccineName(vaccineDTO.getVaccineName()) != null) {
			List<VaccineType> vaccineTypes = vaccineTypeService.getAll();
			model.addAttribute("vaccineTypes", vaccineTypes);
			bindingResult.rejectValue("vaccineName", "error.vaccineDTO", "vaccine Name Already exist!");
			return "vaccine/create-vaccine";
		}
		
		if(vaccineDTO.getTimeBeginNextInjection().isAfter(vaccineDTO.getTimeEndNextInjection())) {
			List<VaccineType> vaccineTypes = vaccineTypeService.getAll();
			model.addAttribute("vaccineTypes", vaccineTypes);
			bindingResult.rejectValue("timeBeginNextInjection", "error.vaccineDTO", "begin date must be less than end date.!");
			return "vaccine/create-vaccine";
		}
		
		Vaccine vaccine = modelMapper.map(vaccineDTO, Vaccine.class);
		vaccine.setStatus(true);
		
		if(vaccineService.createVaccine(vaccine, vaccineDTO.getVaccineTypeName())) {
			redirAttrs.addFlashAttribute("success", "Create successfully!");
		}
		else {
			redirAttrs.addFlashAttribute("error", "Create failure!");
		}
		
		
		return "redirect:/vaccine-manager/new";
		
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String updateVaccine(@RequestParam(value = "id", required = true) String id,
								@RequestParam(value = "reset", required = false, defaultValue = "false") Boolean reset, Model model) {
		
		VaccineDTO vaccineDTO = new VaccineDTO();
		
		if(reset == false) {
			Vaccine vaccine = vaccineService.getById(id);
			vaccineDTO = modelMapper.map(vaccine, VaccineDTO.class);
			vaccineDTO.setVaccineTypeName(vaccine.getVaccineType().getVaccineTypeName());
		}
		else {
			vaccineDTO.setVaccineId(id);
		}
		
		

		model.addAttribute("vaccineDTO", vaccineDTO);
		model.addAttribute("id", id);
		
		List<VaccineType> vaccineTypes = vaccineTypeService.getAll();
		model.addAttribute("vaccineTypes", vaccineTypes);
		
		return "vaccine/update-vaccine";
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String SubmitUpdateVaccine(@RequestParam(value = "id", required = true) String id,
			@Valid @ModelAttribute("vaccineDTO") VaccineDTO vaccineDTO, BindingResult bindingResult, RedirectAttributes redirAttrs, Model model) {
		
		
		if (bindingResult.hasErrors()) {
			List<VaccineType> vaccineTypes = vaccineTypeService.getAll();
			model.addAttribute("vaccineTypes", vaccineTypes);
			model.addAttribute("id", id);
			return "vaccine/update-vaccine";
        }


		if(vaccineService.getVaccineByVaccineName(vaccineDTO.getVaccineName()) != null && 
				!vaccineService.getById(id).getVaccineName().equals(vaccineDTO.getVaccineName())) {
			List<VaccineType> vaccineTypes = vaccineTypeService.getAll();
			model.addAttribute("vaccineTypes", vaccineTypes);
			model.addAttribute("id", id);
			bindingResult.rejectValue("vaccineName", "error.vaccineDTO", "vaccine Name Already exist!");
			return "vaccine/update-vaccine";
		}
		
		if(vaccineDTO.getTimeBeginNextInjection().isAfter(vaccineDTO.getTimeEndNextInjection())) {
			List<VaccineType> vaccineTypes = vaccineTypeService.getAll();
			model.addAttribute("vaccineTypes", vaccineTypes);
			model.addAttribute("id", id);
			bindingResult.rejectValue("timeBeginNextInjection", "error.vaccineDTO", "begin date must be less than end date.!");
			return "vaccine/update-vaccine";
		}
		
		
		
		Vaccine vaccine = modelMapper.map(vaccineDTO, Vaccine.class);
		
		if(vaccineService.createVaccine(vaccine, vaccineDTO.getVaccineTypeName())) {
			redirAttrs.addFlashAttribute("success", "Update successfully!");
		}
		else {
			redirAttrs.addFlashAttribute("error", "Update failure!");
		}
		
		
		return "redirect:/vaccine-manager/list";
	}
	
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String SearchVaccine(Model model, @RequestParam(value = "keyWord", required=false) String keyWord, 
			@RequestParam(value = "page", required=false,defaultValue="1") Integer page,
			@RequestParam(value = "size", required=false, defaultValue="5") Integer size) {
		
		Page<Vaccine> pageVaccine = vaccineService.getListVaccineByKeyWord(page - 1, size, keyWord);
		model.addAttribute("pages", pageVaccine);
		
		model.addAttribute("keyWord", keyWord);
		
		int[] body = ControllerUtils.GenderPage(pageVaccine);
	    
	    
	    model.addAttribute("body", body);
		
		
		return "vaccine/vaccine-list";

	}
	
	@RequestMapping(value = "/import", method = RequestMethod.GET)
	public String importFile() {
		
		return "vaccine/import-file";
	}
	
	
	@RequestMapping(value = "/import", method = RequestMethod.POST)
	public String DoImportFile(@RequestParam("file") MultipartFile reapExcelDataFile, RedirectAttributes redirAttrs) throws IOException {
		List<VaccineDTO> vaccineDTOs = new ArrayList<>();
	    XSSFWorkbook workbook = new XSSFWorkbook(reapExcelDataFile.getInputStream());
	    XSSFSheet worksheet = workbook.getSheetAt(0);
	    
	    for(int i=1;i<worksheet.getPhysicalNumberOfRows() ;i++) {
	        VaccineDTO vaccineDTO = new VaccineDTO();
	            
	        XSSFRow row = worksheet.getRow(i);
	            
	        vaccineDTO.setVaccineId			(row.getCell(0).getStringCellValue());
	        vaccineDTO.setContraindication	(row.getCell(1).getStringCellValue());
	        vaccineDTO.setIndication		(row.getCell(2).getStringCellValue());
	        vaccineDTO.setNumberOfInjecttion((int)row.getCell(3).getNumericCellValue());
	        vaccineDTO.setOrigin			(row.getCell(4).getStringCellValue());

	        vaccineDTO.setTimeBeginNextInjection(LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(row.getCell(5).getDateCellValue())));
	        vaccineDTO.setTimeBeginNextInjection(LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(row.getCell(6).getDateCellValue())));
	        vaccineDTO.setUsage				(row.getCell(7).getStringCellValue());
	        vaccineDTO.setVaccineName		(row.getCell(8).getStringCellValue());
	        vaccineDTO.setStatus			(row.getCell(9).getBooleanCellValue());
	        vaccineDTO.setVaccineTypeName	(row.getCell(10).getStringCellValue());
	        
	        vaccineDTOs.add(vaccineDTO);
	
	    }
	    
	    List<Vaccine> vaccines = new ArrayList<Vaccine>();
	    
	    for(VaccineDTO vaccineDTO: vaccineDTOs) {
	    	
	    	Vaccine vaccine = modelMapper.map(vaccineDTO, Vaccine.class);
	    	VaccineType vaccineType = vaccineTypeService.getByVaccineTypeName(vaccineDTO.getVaccineTypeName());
	    	if(vaccineType == null) {
	    		 redirAttrs.addFlashAttribute("error", "Import failure!");
	    		 return "redirect:/vaccine-manager/import";
	    	}
	    	else {
				vaccine.setVaccineType(vaccineType);
				vaccines.add(vaccine);
			}
	    	
	    }
	    if(vaccineService.SaveAll(vaccines)) {
	    	redirAttrs.addFlashAttribute("success", "Update successfully!");
	    	return "redirect:/vaccine-manager/list";
	    }
	    
	    
	    redirAttrs.addFlashAttribute("error", "Update failure!");
	    return "redirect:/vaccine-manager/import";
		
	}
	
}
