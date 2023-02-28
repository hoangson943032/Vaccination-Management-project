package qld.mock.vaccination.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import qld.mock.vaccination.entities.Employee;


@Repository
public interface EmployeeRepositories extends JpaRepository<Employee, String> {
	
	@Query("SELECT e FROM Employee e WHERE e.name LIKE %?1%")
	public List<Employee> Search(String keyWord);

}
