package qld.mock.vaccination.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import qld.mock.vaccination.entities.Users;

@Repository
public interface UserRepositories extends JpaRepository<Users, Integer>{
	
	Users findByUserName(String userName);
	
	Users findByEmail(String email);

}
