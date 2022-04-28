package com.bms.authserver.controller;

import java.util.Objects;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bms.authserver.dao.CustomerCredentialsRepository;
import com.bms.authserver.models.CustomerCredentials;

import com.bms.authserver.pojo.LoginRequest;
import com.bms.authserver.pojo.ResponseData;



//@CrossOrigin(origins = "https://localhost:9090")
@RestController
public class LoginController {
	
	@Autowired
	CustomerCredentialsRepository customerCredentialsrepository;
	
	//@PostConstruct
	public void postConstruct() {
		CustomerCredentials cutomerCredential  = new CustomerCredentials();
		cutomerCredential.setUsername("rahul2022");
		cutomerCredential.setPassword("Pass@390");	
		customerCredentialsrepository.save(cutomerCredential);
		}
	
	@PostMapping ("user/login")
	
	public <ResponseEntity>ResponseData login(@RequestBody LoginRequest loginRequest) { 
	CustomerCredentials customercredentials =customerCredentialsrepository.findByUsername(loginRequest.getUsername());
	
	if(Objects.nonNull(customercredentials)) {
		
		boolean matched = BCrypt.checkpw(loginRequest.getPassword(), customercredentials.getPassword());
		if(matched) {
			System.out.println("Login Successful");
			if(Objects.nonNull(customercredentials.getLoggedInKey())){
				
				ResponseData response =  new ResponseData(loginRequest.getUsername(),"Failure", 400, "user already logged in");
				return response;
			}
			else {
			
		customercredentials.setLoggedInKey(String.valueOf(Math.random()));
		customerCredentialsrepository.save(customercredentials);
			ResponseData response =  new ResponseData(loginRequest.getUsername(),"success", 200, "Login successful");
			return response;
			}
		}
		
	
		
	}
	ResponseData response =  new ResponseData("Failure", 400, "Invalid username and password");
	return response;
}

	
	@PostMapping ("user/userLogout")	
public <ResponseEntity>ResponseData logout(@RequestBody LoginRequest loginRequest) {
		CustomerCredentials customerCredential =  customerCredentialsrepository.findByUsername(loginRequest.getUsername());

		if(Objects.isNull(customerCredential)) {
			
			
			ResponseData response =  new ResponseData("Failure", 400, "no user present");
			return response;
			

		}
		if(Objects.nonNull(customerCredential.getLoggedInKey()))
		{
			customerCredential.setLoggedInKey(null);
			customerCredentialsrepository.save(customerCredential);
			
			ResponseData response =  new ResponseData(loginRequest.getUsername(),"success", 200, "Logout successful");
			return response;
			
			
		}
		else 
		{
			
			ResponseData response =  new ResponseData(loginRequest.getUsername(),"Failure", 400, "User not logged in, Please login!!");
			return response;
		}
		}


		
	} 