package qld.mock.vaccination.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import qld.mock.vaccination.dto.EmployeeDto;
import qld.mock.vaccination.entities.Employee;
import qld.mock.vaccination.service.EmployeeService;
import qld.mock.vaccination.utils.ControllerUtils;
import qld.mock.vaccination.utils.FileUploadUtil;

@Controller
@RequestMapping("employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String getList(Model model, @RequestParam(value = "page", required=false,defaultValue="1") Integer page,
						@RequestParam(value = "size", required=false, defaultValue="5") Integer size) {
		
		Page<Employee> pageVaccine = employeeService.getListEmployee(page - 1, size);
		
		model.addAttribute("pages", pageVaccine);
		
		int[] body = ControllerUtils.GenderPage(pageVaccine);
	    
	    model.addAttribute("body", body);
		

		return "employee/list";

		
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String SearchEmployee(Model model, @RequestParam(value = "keyWord", required=false) String keyWord, 
			@RequestParam(value = "page", required=false,defaultValue="1") Integer page,
			@RequestParam(value = "size", required=false, defaultValue="5") Integer size) {
		
		Page<Employee> pageVaccine = employeeService.getListEmployeeByKeyWord(page - 1, size, keyWord);
		model.addAttribute("pages", pageVaccine);
		
		model.addAttribute("keyWord", keyWord);
		
		int[] body = ControllerUtils.GenderPage(pageVaccine);
	    
	    
	    model.addAttribute("body", body);
		
		
	    return "employee/list";

	}
	
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String createVaccine(Model model) {

		EmployeeDto employeeDto = new EmployeeDto();
		employeeDto.setGender(1);
		
		model.addAttribute("employeeDto", employeeDto);
	
		return "employee/new";
	}
	
	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public String submitAddVaccine(@Valid @ModelAttribute("employeeDto") EmployeeDto employeeDto, BindingResult bindingResult,
			RedirectAttributes redirAttrs, @RequestParam("image") MultipartFile multipartFile, Model model) throws IOException {
		
		if (bindingResult.hasErrors()) {
			return "employee/new";
        }
		
		if(employeeService.getById(employeeDto.getEmployeeId()) != null) {
			
			bindingResult.rejectValue("employeeId", "error.employeeDto", "Id Already exist!");
			return "employee/new";
		}
		
		

		Employee employee = modelMapper.map(employeeDto, Employee.class);
		
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		
		employee.setImage(fileName);
		
		String uploadDir = "employee-photos/" + employee.getEmployeeId();
		 
        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
		
		if(employeeService.createEmployee(employee)) {
			redirAttrs.addFlashAttribute("success", "Add successfully!");
		}
		else {
			redirAttrs.addFlashAttribute("error", "Add failure!");
		}
		
		
		return "redirect:/employee/new";
		
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String updateEmployee(@RequestParam(value = "id", required = true) String id,
								@RequestParam(value = "reset", required = false, defaultValue = "false") Boolean reset, Model model) {
		
		EmployeeDto employeeDto = new EmployeeDto();
		
		if(reset == false) {
			Employee employee = employeeService.getById(id);
			employeeDto = modelMapper.map(employee, EmployeeDto.class);
		}
		else {
			employeeDto.setEmployeeId(id);
		}
		
		

		model.addAttribute("employeeDto", employeeDto);
		model.addAttribute("id", id);
		
		
		
		return "employee/update";
	}
	
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String SubmitUpdateVaccine(@RequestParam(value = "id", required = true) String id,
			@Valid @ModelAttribute("employeeDto") EmployeeDto employeeDto, BindingResult bindingResult, RedirectAttributes redirAttrs,
			@RequestParam("image") MultipartFile multipartFile, Model model) throws IOException {
		
		
		if (bindingResult.hasErrors()) {
			
			model.addAttribute("id", id);
			return "employee/update";
        }


		
		
		Employee employee = modelMapper.map(employeeDto, Employee.class);
		
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		
		employee.setImage(fileName);
		
		String uploadDir = "employee-photos/" + employee.getEmployeeId();
		 
        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
		
		if(employeeService.createEmployee(employee)) {
			redirAttrs.addFlashAttribute("success", "update successfully!");
		}
		else {
			redirAttrs.addFlashAttribute("error", "Update failure!");
		}
		
		
		return "redirect:/employee/list";
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String deleteEmployee(@RequestParam(value = "ids", required=false) List<String> ids, RedirectAttributes redirAttrs) {
		
		if(employeeService.deleteEmployee(ids)) {
			redirAttrs.addFlashAttribute("success", "Delete successfully!");
		}
		else {
			redirAttrs.addFlashAttribute("error", "Delete failure!");
		}
		
		return "redirect:/employee/list";
		
	}
	
	

}
