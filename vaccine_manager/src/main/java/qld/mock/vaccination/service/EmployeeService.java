package qld.mock.vaccination.service;

import java.util.List;

import org.springframework.data.domain.Page;


import qld.mock.vaccination.entities.Employee;





public interface EmployeeService {
	
	Page<Employee> getListEmployee(Integer pageNo, Integer pageSize);
	
	Page<Employee> getListEmployeeByKeyWord(Integer pageNo, Integer pageSize, String keyWord);
	
	Employee getById(String Id);
	
	Boolean createEmployee(Employee employee);
	
	Boolean deleteEmployee(List<String> ids);

}
