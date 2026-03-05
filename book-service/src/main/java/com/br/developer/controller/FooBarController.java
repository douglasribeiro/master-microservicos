package com.br.developer.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "Foo Bar Endepoint")
@RestController
@RequestMapping("/book-service")
@RequiredArgsConstructor
public class FooBarController {
	
	private Logger logger = LoggerFactory.getLogger(FooBarController.class);
	
	@GetMapping("/foo-bar")
	//@Retry(name = "default")
	//@Retry(name = "foo-bar")
	//@Retry(name = "foo-bar", fallbackMethod = "fallbackMethod")
	@CircuitBreaker(name = "foo-bar", fallbackMethod = "fallbackMethod")
	//@RateLimiter(name = "default")
	//@Bulkhead(name = "default")
	public String fooBar() {
		logger.info("Request to foobar is received");
		//var response = new RestTemplate().getForEntity("http://localhost:8080/foo-bar", String.class);
		//return response.getBody();
		return "Foo Bar!!!!!";
	}
	
	public String fallbackMethod(Exception ex) {
		return "fallbackMethod foo-bar";
	}
}
