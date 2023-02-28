package qld.mock.vaccination.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import qld.mock.vaccination.entities.Users;
import qld.mock.vaccination.repositories.UserRepositories;
import qld.mock.vaccination.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepositories userRepositories;

	@Override
	public Users getByUserName(String UserName) {
		
		return userRepositories.findByUserName(UserName);
		
	
	}

	@Override
	public Users getByEmail(String email) {
		return userRepositories.findByEmail(email);
	}

	@Override
	public Boolean createUser(Users users) {
		return userRepositories.save(users) != null;
	}
	

}
