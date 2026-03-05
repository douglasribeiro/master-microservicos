package com.br.developer.controller;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.developer.environment.InstanceInformationService;
import com.br.developer.model.Exchange;
import com.br.developer.repository.ExchangeRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "Exchange Endepoint")
@RestController
@RequestMapping("/exchange-service")
@RequiredArgsConstructor
public class ExchangeController {
	
	private final InstanceInformationService informationService;
	private final ExchangeRepository repository;
	private Logger logger = LoggerFactory.getLogger(ExchangeController.class);
	
	/*
	 * @GetMapping(value = "/{amount}/{from}/{to}", produces =
	 * MediaType.APPLICATION_JSON_VALUE) public Exchange getExchange(
	 * 
	 * @PathVariable BigDecimal amount,
	 * 
	 * @PathVariable String from,
	 * 
	 * @PathVariable String to) { return new Exchange(1L, from, to, BigDecimal.ONE,
	 * BigDecimal.ONE, "PORT " + informationService.retriveServerPort()); }
	 */

	@Operation(summary = "Find a specific book by your ID")
	@GetMapping(value = "/{amount}/{from}/{to}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Exchange getExchange(
			@PathVariable BigDecimal amount, 
			@PathVariable String from, 
			@PathVariable String to) {
		
		logger.info("get exchange is called with -> {}, {} and {}", amount, from, to);
		
		Exchange exchange = repository.findByFromAndTo(from, to);
		
		if(exchange == null) throw new RuntimeException("Currency Unsuported.");
		
		BigDecimal conversionFactor = exchange.getConversionFactor();
		BigDecimal convertedValue = conversionFactor.multiply(amount);
		exchange.setConvertedValue(convertedValue);
		exchange.setEnvironment("PORT " + informationService.retriveServerPort());
		return exchange;
	}
}
