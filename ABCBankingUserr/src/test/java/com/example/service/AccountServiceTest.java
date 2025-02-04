package com.example.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.model.Account;
import com.example.repo.AccountRepo;

import org.mockito.InjectMocks;
import org.mockito.Mock;


@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {
	
	@Mock
	private AccountRepo ar;
	
	@InjectMocks
	private AccountService as;
	
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
	void testRead() {
		List<Account> Customers=Arrays.asList(acc1,acc2);
		when(ar.findAll()).thenReturn(Customers);
		
		List<Account> result=as.getAccount();
		assertEquals(2,result.size());
		assertEquals("Account A",result.get(0).getAccountHolderName());
		assertEquals("Account B",result.get(1).getAccountHolderName());
		
	}
	
	@Test
	void testReadOne() {
		when(ar.findById((long) 1)).thenReturn(Optional.of(acc1));
		Optional<Account> result=as.getAccount((long)1);
		assertTrue(result.isPresent());
		assertEquals("Account A", result.get().getAccountHolderName());
	}
	
	@Test
    public void testDeposit() {
        // Arrange
        //  Long accountId = 1L;
        //double depositAmount = 50.0;
        when(ar.findById((long)1)).thenReturn(Optional.of(acc1));
        when(ar.save(any(Account.class))).thenAnswer(i -> i.getArguments()[0]);

        // Act
        Account updatedAccount = as.deposit((long)1, 50.0);

        // Assert
        assertNotNull(updatedAccount);
        assertEquals(20050.0, updatedAccount.getBalance());
        verify(ar, times(1)).findById((long)1);
        verify(ar, times(1)).save(acc1);
    }
}
