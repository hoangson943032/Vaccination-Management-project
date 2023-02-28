package qld.mock.vaccination.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import qld.mock.vaccination.entities.Customer;
import qld.mock.vaccination.entities.Employee;
import qld.mock.vaccination.repositories.CustomerRepositories;
import qld.mock.vaccination.repositories.UserRepositories;
import qld.mock.vaccination.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	private CustomerRepositories customerRepositories;

	@Override
	public Page<Customer> getListCustomer(Integer pageNo, Integer pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		return customerRepositories.findAll(pageable);
	}

	@Override
	public Page<Customer> getListCustomerByKeyWord(Integer pageNo, Integer pageSize, String keyWord) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		List<Customer> customers = customerRepositories.Search(keyWord);
		int total = customers.size();
		int start = (int) pageable.getOffset();
		int end = Math.min((start + pageable.getPageSize()), total);

		List<Customer> output = new ArrayList<>();

		if (start <= end) {
		    output = customers.subList(start, end);
		}

		return new PageImpl<>(
		    output,
		    pageable,
		    total
		);
	}

	@Override
	public Boolean createCustomer(Customer customer) {
		return customerRepositories.save(customer) != null;
	}

	@Override
	public Customer getById(Integer id) {
		// TODO Auto-generated method stub
		return customerRepositories.findById(id).get();
	}
	
	
	

}
