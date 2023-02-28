package qld.mock.vaccination.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import qld.mock.vaccination.entities.Employee;
import qld.mock.vaccination.entities.VaccineType;
import qld.mock.vaccination.repositories.EmployeeRepositories;
import qld.mock.vaccination.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeRepositories employeeRepositories;

	@Override
	public Page<Employee> getListEmployee(Integer pageNo, Integer pageSize) {
		// TODO Auto-generated method stub
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		return employeeRepositories.findAll(pageable);
	}

	@Override
	public Page<Employee> getListEmployeeByKeyWord(Integer pageNo, Integer pageSize, String keyWord) {
		// TODO Auto-generated method stub
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		List<Employee> employees = employeeRepositories.Search(keyWord);
		int total = employees.size();
		int start = (int) pageable.getOffset();
		int end = Math.min((start + pageable.getPageSize()), total);

		List<Employee> output = new ArrayList<>();

		if (start <= end) {
		    output = employees.subList(start, end);
		}

		return new PageImpl<>(
		    output,
		    pageable,
		    total
		);
	}

	@Override
	public Employee getById(String Id) {
		try {
			return employeeRepositories.findById(Id).get();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Boolean createEmployee(Employee employee) {
		return employeeRepositories.save(employee) != null;
	}

	@Override
	public Boolean deleteEmployee(List<String> ids) {
		// TODO Auto-generated method stub
		try {
			employeeRepositories.deleteAllById(ids);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
		}
		return false;
	}

}
