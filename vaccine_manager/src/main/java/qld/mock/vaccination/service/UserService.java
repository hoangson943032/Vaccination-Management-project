package qld.mock.vaccination.service;


import qld.mock.vaccination.entities.Users;

public interface UserService {
	
	Users getByUserName(String UserName);
	
	Users getByEmail(String email);
	
	Boolean createUser(Users users);


}
