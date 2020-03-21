package com.ari.demo.userservice.integration;

import java.util.Collections;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.ari.demo.userservice.UserServiceApplication;
import com.ari.demo.userservice.builder.UserBuilder;
import com.ari.demo.userservice.entity.User;
import com.ari.demo.userservice.repository.UserRepo;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserServiceIntegrationTest {
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private UserRepo userRepo;
	
	@Before
	public void setUpDB(){
		User userOne = new UserBuilder().buildFirsName("Piyali").buildLastName("Medya").build();
		userRepo.save(userOne);
		User userTwo = new UserBuilder().buildFirsName("Arijit").buildLastName("Medya").build();
		userRepo.save(userTwo);
	}
	
	@After
	public void clearDB(){
		userRepo.deleteAll();
	}
	
	@Test
	public void shouldGetUsers() throws Exception{
		mockMvc.perform( MockMvcRequestBuilders
			      .get("/users"))			      
			      .andExpect(MockMvcResultMatchers.status().isOk())
				  .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
				  .andExpect(MockMvcResultMatchers.jsonPath("$.[0].firstName", Matchers.is("Piyali")))
				  .andExpect(MockMvcResultMatchers.jsonPath("$.[0].lastName", Matchers.is("Medya")))
				  .andExpect(MockMvcResultMatchers.jsonPath("$.[1].firstName", Matchers.is("Arijit")))
				  .andExpect(MockMvcResultMatchers.jsonPath("$.[1].lastName", Matchers.is("Medya")));
	}

}
