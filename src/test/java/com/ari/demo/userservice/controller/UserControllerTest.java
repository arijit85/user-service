package com.ari.demo.userservice.controller;

import java.util.Collections;

import org.assertj.core.util.Arrays;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.ari.demo.userservice.entity.User;
import com.ari.demo.userservice.repository.UserRepo;

@WebMvcTest(controllers=UserController.class)
@RunWith(SpringRunner.class)
public class UserControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UserRepo userRepo;
	
	@Test
	public void shouldGetUsers() throws Exception{
		User userOne = new User("Piyali", "Medya");
		Mockito.when(userRepo.findAll()).thenReturn(Collections.singletonList(userOne));
		mockMvc.perform( MockMvcRequestBuilders
			      .get("/users"))			      
			      .andExpect(MockMvcResultMatchers.status().isOk())
				  .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
				  .andExpect(MockMvcResultMatchers.jsonPath("$.[0].firstName", Matchers.is("Piyali")))
				  .andExpect(MockMvcResultMatchers.jsonPath("$.[0].lastName", Matchers.is("Medya")));
	}
	

}
