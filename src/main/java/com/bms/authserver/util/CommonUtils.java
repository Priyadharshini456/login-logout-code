package com.bms.authserver.util;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

public class CommonUtils {

	public static  String bcryptPasswordEncoder(String password) {
		String  originalPassword = "password";
		String passwordHash = BCrypt.hashpw(originalPassword, BCrypt.gensalt(12));

		//boolean matched = BCrypt.checkpw(originalPassword, generatedSecuredPasswordHash);
		return passwordHash;
	}
	
	
	public static String passwordEncoder(String password) {

		String pepper = "pepper"; // secret key used by password encoding
		int iterations = 200000;  // number of hash iteration
		int hashWidth = 256;      // hash width in bits

		Pbkdf2PasswordEncoder pbkdf2PasswordEncoder =new Pbkdf2PasswordEncoder(pepper, iterations, hashWidth);
		pbkdf2PasswordEncoder.setEncodeHashAsBase64(true);
		String encodedPassword = pbkdf2PasswordEncoder.encode(password);
		
		//boolean ismatch = pbkdf2PasswordEncoder.matches(password, encodedPassword);
		
		return encodedPassword;
	}
	
	
	




	  }
	

