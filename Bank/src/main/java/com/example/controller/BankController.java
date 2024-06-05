package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class BankController {
	@Autowired
    private RestTemplate restTemplate;
    
	@GetMapping("/home")
	public ModelAndView getDevices() {
	    ModelAndView modelAndView = new ModelAndView("index"); // Assuming "index.html" is in the default templates directory
	    return modelAndView;
	}
	
    @RequestMapping("/cust")
    public String getAdminData() {
        // Use service discovery to locate BankAdmin service
        String adminServiceUrl = "http://localhost:8888/cust/read";
        return restTemplate.getForObject(adminServiceUrl, String.class);
    }
    
    @RequestMapping("/acc")
    public String getUserData() {
        // Use service discovery to locate BankUser service
        String userServiceUrl = "http://localhost:8001/acc/read";
        return restTemplate.getForObject(userServiceUrl, String.class);
    }
		
	}

