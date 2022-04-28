package com.bms.authserver.controller;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
//import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.bms.authserver.dao.CustomerCredentialsRepository;
import com.bms.authserver.models.CustomerCredentials;
import com.bms.authserver.pojo.LoginRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@AutoConfigureMockMvc(addFilters =false)
//@RunWith(SpringJUnit4ClassRunner.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = LoginController.class)
public class LoginControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	CustomerCredentialsRepository customerCredentialsRepository;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@InjectMocks
	LoginController loginController;
	
	@Mock
	CustomerCredentials customerCredentials;
	
	@Mock
	LoginRequest loginRequest;
	
	@Test
	public void setUp() throws Exception {
		LoginRequest loginRequest=new LoginRequest("Rahul2022","Pass@390");
		CustomerCredentials customerCredentials=new CustomerCredentials("Rahul2022","Pass@390");
		
		
		when(customerCredentialsRepository.findByUsername(loginRequest.getUsername())).thenReturn(customerCredentials);
		this.mockMvc.perform(post("user/login")
		.contentType(MediaType.APPLICATION_JSON)
		.content(objectMapper.writeValueAsString(loginRequest)))
		.andExpect(status().isOk())
		.andExpect(( content().contentType(MediaType.APPLICATION_JSON)))
		.andExpect((jsonPath("$.httpStatus").value("OK")))
		.andExpect((jsonPath("$.response").value("Up-Running")));
	}

//	@Test
//	
//	public void createCustomerLogin() throws Exception {
//	String uri = "/login";
//	LoginRequest loginRequest = new LoginRequest();
//	loginRequest.setUsername("Rahul2022");
//	loginRequest.setPassword("Pass@2022");
//	String inputJson = super.mapToJson(loginRequest);
//	MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
//	.contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
//	int status = mvcResult.getResponse().getStatus();
//	assertEquals(200, status);
//	String content = mvcResult.getResponse().getContentAsString();
//	assertEquals(content, "Login successfully");
//
//}
//	
//	@Test
//	public void checkByUsername() throws Exception {
//	String uri = "/login";
//	LoginRequest loginRequest = new LoginRequest();
//	loginRequest.setUsername("jack2022");
//	loginRequest.setPassword("Pass@390");
//	String inputJson = super.mapToJson(loginRequest);
//	System.out.println("MVC obj "+mvc);
//	MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
//	.contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
//	int status = mvcResult.getResponse().getStatus();
//	assertEquals(400, status);
//	String content = mvcResult.getResponse().getContentAsString();
//	assertEquals(content, "Incorrect username");
//
//}
//	
//	@Test
//	public void checkPassword() throws Exception {
//	String uri = "/login";
//	LoginRequest loginRequest = new LoginRequest();
//	loginRequest.setUsername("Rahul2022");
//	loginRequest.setPassword("Pass000");
//	String inputJson = super.mapToJson(loginRequest);
//	MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
//	.contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
//	int status = mvcResult.getResponse().getStatus();
//	assertEquals(400, status);
//	String content = mvcResult.getResponse().getContentAsString();
//	assertEquals(content, "Incorrect password");
//
//}
//	
//	@Test
//	public void LogoutUser() throws Exception {
//	   String uri = "/logout";
//	   LoginRequest loginRequest = new LoginRequest();
//		loginRequest.setUsername("Rahul2022");
//		String inputJson = super.mapToJson(loginRequest);
//	   MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
//	      .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
//	   
//	   int status = mvcResult.getResponse().getStatus();
//	   assertEquals(200, status);
//	   String content = mvcResult.getResponse().getContentAsString();
//	   assertEquals(200, "Logout successfully");
//	  
//	}



}
