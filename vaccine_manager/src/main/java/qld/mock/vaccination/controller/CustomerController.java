package qld.mock.vaccination.controller;

import java.io.IOException;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
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

import qld.mock.vaccination.dto.CustomerDto;
import qld.mock.vaccination.dto.EmployeeDto;
import qld.mock.vaccination.dto.UserDto;
import qld.mock.vaccination.entities.Customer;
import qld.mock.vaccination.entities.Employee;
import qld.mock.vaccination.entities.Users;
import qld.mock.vaccination.entities.Vaccine;
import qld.mock.vaccination.service.CustomerService;
import qld.mock.vaccination.service.UserService;
import qld.mock.vaccination.utils.CaptchaUtil;
import qld.mock.vaccination.utils.ControllerUtils;
import qld.mock.vaccination.utils.FileUploadUtil;

@Controller
@RequestMapping("customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String getList(Model model, @RequestParam(value = "page", required=false,defaultValue="1") Integer page,
						@RequestParam(value = "size", required=false, defaultValue="5") Integer size) {
		
		Page<Customer> pageVaccine = customerService.getListCustomer(page - 1, size);
		
		model.addAttribute("pages", pageVaccine);
		
		int[] body = ControllerUtils.GenderPage(pageVaccine);
	    
	    model.addAttribute("body", body);
		

		return "customer/list";

		
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String Search(Model model, @RequestParam(value = "keyWord", required=false) String keyWord, 
			@RequestParam(value = "page", required=false,defaultValue="1") Integer page,
			@RequestParam(value = "size", required=false, defaultValue="5") Integer size) {
		
		Page<Customer> pageVaccine = customerService.getListCustomerByKeyWord(page - 1, size, keyWord);
		model.addAttribute("pages", pageVaccine);
		
		model.addAttribute("keyWord", keyWord);
		
		int[] body = ControllerUtils.GenderPage(pageVaccine);
	    
	    
	    model.addAttribute("body", body);
		
		
	    return "customer/list";


	}
	
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String createVaccine(Model model) {

		CustomerDto customerDto = new CustomerDto();
		
		UserDto userDto = new UserDto();
		
		CaptchaUtil.getCaptcha(userDto);
		
		model.addAttribute("customerDto", customerDto);
		
		model.addAttribute("userDto", userDto);
		

		return "customer/new";
	}
	
	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public String submitAdd(@Valid @ModelAttribute("customerDto") CustomerDto customerDto,
							BindingResult bindingResult,
							@Valid @ModelAttribute("userDto") UserDto userDto,
							BindingResult bindingResult1,
							RedirectAttributes redirAttrs, Model model) throws IOException {
		
		if (bindingResult.hasErrors() || bindingResult1.hasErrors()) {
			CaptchaUtil.getCaptcha(userDto);
			return "customer/new";
        }
		
		if(userService.getByUserName(userDto.getUserName()) != null) {
			bindingResult1.rejectValue("userName", "error.userDto", "User Name Already exist!");
			CaptchaUtil.getCaptcha(userDto);
			return "customer/new";
		}
		
		if(userService.getByEmail(userDto.getEmail()) != null) {
			bindingResult1.rejectValue("email", "error.userDto", "Email Already exist!");
			CaptchaUtil.getCaptcha(userDto);
			return "customer/new";
		}
		
		if(!userDto.getCaptcha().equals(userDto.getHiddenCaptcha())) {
			bindingResult1.rejectValue("captcha", "error.userDto", "Invalid Captcha!");
			CaptchaUtil.getCaptcha(userDto);
			return "customer/new";
		}
		
		if(!userDto.getPassword().equals(userDto.getPasswordConfirm())) {
			bindingResult1.rejectValue("passwordConfirm", "error.userDto", "passwordConfirm invalid!");
			CaptchaUtil.getCaptcha(userDto);
			return "customer/new";
		}
		
		Customer customer = modelMapper.map(customerDto, Customer.class);
		Users users = modelMapper.map(userDto, Users.class);
		users.setRoles("CUSTOMER");
		users.setPassword(passwordEncoder.encode(userDto.getPassword()));
		customer.setUsers(users);
		
		if(userService.createUser(users) && customerService.createCustomer(customer)) {
			redirAttrs.addFlashAttribute("success", "Add successfully!");
		}
		else {
			redirAttrs.addFlashAttribute("error", "Add failure!");
		}
		
		
		return "redirect:/customer/list";
	
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String update(@RequestParam(value = "id", required = true) Integer id,
								@RequestParam(value = "reset", required = false, defaultValue = "false") Boolean reset, Model model) {
		
		CustomerDto customerDto = new CustomerDto();
		
		UserDto userDto = new UserDto();
		
		if(reset == false) {
			Customer customer = customerService.getById(id);
			Users users = customer.getUsers();
			customerDto = modelMapper.map(customer, CustomerDto.class);
			
			userDto = modelMapper.map(users, UserDto.class);
			CaptchaUtil.getCaptcha(userDto);
		}
		
		
		

		model.addAttribute("customerDto", customerDto);
		model.addAttribute("userDto", userDto);
		model.addAttribute("id", id);
		
		
		
		return "customer/update";
	}
	
	
	
	
}
