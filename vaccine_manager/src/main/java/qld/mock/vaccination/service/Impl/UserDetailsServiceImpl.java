package qld.mock.vaccination.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import qld.mock.vaccination.Exception.NotFountException;
import qld.mock.vaccination.entities.CustomUserDetails;
import qld.mock.vaccination.entities.Users;
import qld.mock.vaccination.repositories.UserRepositories;




@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	private UserRepositories userRepositories;
	
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		Users users = userRepositories.findByUserName(username);
	
		if(users == null) {
			throw new NotFountException(username + "User not found!");
		}
		
		List<String> roleNames = new ArrayList<String>();
		roleNames.add(users.getRoles());
		
				
		
		List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
		if (roleNames != null) {
			for (String role : roleNames) {
				// ROLE_USER, ROLE_ADMIN,..
				GrantedAuthority authority = new SimpleGrantedAuthority(role);
				grantList.add(authority);
			}
		}
		UserDetails userDetails = (UserDetails) new CustomUserDetails(users.getUserName(), users.getPassword(), users.getEmail(), users.getUserID(), grantList);
		
		return userDetails;

	}
	
	
	
	

	
	

}
