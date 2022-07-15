/**
 * 
 */
package com.infosys.irs.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infosys.irs.entity.UserEntity;
import com.infosys.irs.exception.InvalidCredentialException;
import com.infosys.irs.model.Login;
import com.infosys.irs.model.User;
import com.infosys.irs.repository.UserRepository;


@Service
public class LoginService {
	@Autowired
	private UserRepository userRepository;	
	
	public User authenticateLogin(Login userLogin) throws InvalidCredentialException{
		UserEntity userentity = userRepository.findOne(userLogin.getUserName());
		User user=new User();
		user.setCity(userentity.getCity());
		user.setEmail(userentity.getEmail());
		user.setName(userentity.getName());
		user.setPassword(userentity.getPassword());
		user.setPhone(userentity.getPhone());
		user.setUserId(userentity.getUserId());
		
		if (user == null)
		{
			throw new InvalidCredentialException(
					"LoginService.INVALID_CREDENTIALS");
		}
		else if(!(user.getPassword().equals(userLogin.getPassword()))){
			throw new InvalidCredentialException(
					"LoginService.INVALID_CREDENTIALS");
		}
			return user;				
		}
	}
