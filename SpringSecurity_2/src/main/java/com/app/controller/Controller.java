package com.app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/masai")
public class Controller {
	

	// 
	@GetMapping(value = "/welcome")
	public String welcomeMsg() {
		return "Welcome Message Without Spring Security ";
	}
	
	@GetMapping(value = "/welcomeP")
	public ResponseEntity<String> welocmeMsgWithSpringSecurity() {
		return new ResponseEntity<String>("Welcome Message With Spring Security",HttpStatus.OK);
	}
	
	
}
