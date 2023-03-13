package com.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/home")
public class Controller {
	
	@GetMapping
	public String welcome() {
		return "Welcome To This Page ";
	}
}
