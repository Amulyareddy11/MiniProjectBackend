package com.example.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

import com.example.model.Account;
import com.example.repo.AccountRepo;
import com.example.service.AccountService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(AccountController.class)
public class AccountControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private AccountService as;
	
	@MockBean
	private AccountRepo ar;
	
	@InjectMocks
	private AccountController cc;
	
	private Account acc1;
	private Account acc2;
	
	@BeforeEach
	void setUp()
	{
		acc1=new Account();
		acc1.setId((long)1);
		acc1.setAccountHolderName("Account A");
		acc1.setBalance(20000);
		
		acc2=new Account();
		acc2.setId((long)2);
		acc2.setAccountHolderName("Account B");
		acc2.setBalance(40000);
		
	}
	
	@Test
	void testRead() throws Exception {
		List<Account> Customers = Arrays.asList(acc1, acc2);
		when(as.getAccount()).thenReturn(Customers);
		
		mockMvc.perform(get("/acc/read"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$[0].accountHolderName").value("Account A"))
		.andExpect(jsonPath("$[1].accountHolderName").value("Account B"));
		
	}
	
	@Test
	void testreadOne() throws Exception {
		when(as.getAccount((long)1)).thenReturn(Optional.of(acc1));
		
		mockMvc.perform(get("/acc/read/1"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.accountHolderName").value("Account A"));
	}
	
	@Test
	void testAdd() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		String AccountJson = objectMapper.writeValueAsString(acc1);
		
		mockMvc.perform(post("/acc/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(AccountJson))
				.andExpect(status().isOk());
		verify(as).createAccount(any(Account.class));
	 }
 
}