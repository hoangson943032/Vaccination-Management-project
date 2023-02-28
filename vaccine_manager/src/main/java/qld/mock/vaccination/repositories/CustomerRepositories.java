package qld.mock.vaccination.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import qld.mock.vaccination.entities.Customer;


@Repository
public interface CustomerRepositories extends JpaRepository<Customer, Integer>{
	
	@Query("SELECT c FROM Customer c WHERE c.fullName LIKE %?1%")
	public List<Customer> Search(String keyWord);

}
