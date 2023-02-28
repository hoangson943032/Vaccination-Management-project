package qld.mock.vaccination.controller;



import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import qld.mock.vaccination.dto.VaccineDTO;
import qld.mock.vaccination.dto.VaccineTypeDto;
import qld.mock.vaccination.entities.Vaccine;
import qld.mock.vaccination.entities.VaccineType;
import qld.mock.vaccination.service.VaccineTypeService;
import qld.mock.vaccination.utils.ControllerUtils;

@Controller
@RequestMapping("vaccine-type")
public class VaccineTypeController {
	
	@Autowired
	private VaccineTypeService vaccineTypeService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String getList(Model model, @RequestParam(value = "page", required=false,defaultValue="1") Integer page,
						@RequestParam(value = "size", required=false, defaultValue="5") Integer size) {
		
		Page<VaccineType> pageVaccine = vaccineTypeService.getListVaccineType(page - 1, size);
		
		model.addAttribute("pages", pageVaccine);
		
		int[] body = ControllerUtils.GenderPage(pageVaccine);
	    
	    model.addAttribute("body", body);
		

		return "vaccine-type/list";

		
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String SearchVaccineType(Model model, @RequestParam(value = "keyWord", required=false) String keyWord, 
			@RequestParam(value = "page", required=false,defaultValue="1") Integer page,
			@RequestParam(value = "size", required=false, defaultValue="5") Integer size) {
		
		Page<VaccineType> pageVaccine = vaccineTypeService.getListVaccineTypeByKeyWord(page - 1, size, keyWord);
		model.addAttribute("pages", pageVaccine);
		
		model.addAttribute("keyWord", keyWord);
		
		int[] body = ControllerUtils.GenderPage(pageVaccine);
	    
	    
	    model.addAttribute("body", body);
		
		
	    return "vaccine-type/list";

	}
	
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String createVaccine(Model model) {
		
		VaccineTypeDto vaccineTypeDto = new VaccineTypeDto();
		model.addAttribute("vaccineTypeDTO", vaccineTypeDto);
		
		

		return "vaccine-type/new";
		
		
	}
	
	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public String submitAddVaccine(@Valid @ModelAttribute("vaccineTypeDTO") VaccineTypeDto vaccineTypeDto, BindingResult bindingResult,
			RedirectAttributes redirAttrs, Model model) {
		
		if (bindingResult.hasErrors()) {
			return "vaccine-type/new";
        }
		
		if(vaccineTypeService.getById(vaccineTypeDto.getVaccineTypeId()) != null) {
			
			bindingResult.rejectValue("vaccineTypeId", "error.vaccineTypeDTO", "Id Already exist!");
			return "vaccine-type/new";
		}

		
		
		VaccineType vaccineType = modelMapper.map(vaccineTypeDto, VaccineType.class);
		vaccineType.setStatus(true);
		
		if(vaccineTypeService.createVaccineType(vaccineType)) {
			redirAttrs.addFlashAttribute("success", "Add successfully!");
		}
		else {
			redirAttrs.addFlashAttribute("error", "Add failure!");
		}
		
		
		return "redirect:/vaccine-type/new";
		
	}
	
	
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String updateVaccine(@RequestParam(value = "id", required = true) String id,
								@RequestParam(value = "reset", required = false, defaultValue = "false") Boolean reset, Model model) {
		
		VaccineTypeDto vaccineTypeDto = new VaccineTypeDto();
		
		if(reset == false) {
			VaccineType vaccineType = vaccineTypeService.getById(id);
			vaccineTypeDto = modelMapper.map(vaccineType, VaccineTypeDto.class);
		}
		else {
			vaccineTypeDto.setVaccineTypeId(id);
		}
		
		

		model.addAttribute("vaccineTypeDto", vaccineTypeDto);
		model.addAttribute("id", id);
		
		
		
		return "vaccine-type/update";
	}
	
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String SubmitUpdateVaccine(@RequestParam(value = "id", required = true) String id,
			@Valid @ModelAttribute("vaccineTypeDto") VaccineTypeDto vaccineTypeDto, BindingResult bindingResult, RedirectAttributes redirAttrs, Model model) {
		
		
		if (bindingResult.hasErrors()) {
			
			model.addAttribute("id", id);
			return "vaccine-type/update";
        }


		
		
		VaccineType vaccineType = modelMapper.map(vaccineTypeDto, VaccineType.class);
		
		if(vaccineTypeService.createVaccineType(vaccineType)) {
			redirAttrs.addFlashAttribute("success", "update successfully!");
		}
		else {
			redirAttrs.addFlashAttribute("error", "Update failure!");
		}
		
		
		return "redirect:/vaccine-type/list";
	}
	
	
	@RequestMapping(value = "/make-inactive", method = RequestMethod.GET)
	public String makeActive(@RequestParam(value = "ids", required=false) List<String> ids) {

		vaccineTypeService.MakeInactive(ids);
		
		return "redirect:/vaccine-type/list";
		
	}
	

}
