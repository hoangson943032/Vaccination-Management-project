package qld.mock.vaccination.service;

import org.springframework.data.domain.Page;

import qld.mock.vaccination.entities.Customer;
import qld.mock.vaccination.entities.Employee;



public interface CustomerService {
	
	Page<Customer> getListCustomer(Integer pageNo, Integer pageSize);
	
	Page<Customer> getListCustomerByKeyWord(Integer pageNo, Integer pageSize, String keyWord);
	
	Boolean createCustomer(Customer customer);
	
	Customer getById(Integer id);

}
