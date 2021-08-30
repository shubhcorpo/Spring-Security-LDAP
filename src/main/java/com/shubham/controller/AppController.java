package com.shubham.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/appcontroller/endpoint")
public class AppController {

	@GetMapping("/secure")
	public String secureMethod() {
	
		return "secureMethod or restend point is called";
	}
}
