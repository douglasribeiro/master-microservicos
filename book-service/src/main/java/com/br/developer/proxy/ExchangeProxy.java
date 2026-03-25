	package com.br.developer.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.br.developer.dto.Exchange;


//@FeignClient(name = "exchange-service")
@FeignClient(name = "exchange-service", url = "${EXCHANGE_SERVCE_SERVICE_HOST:http://host.docker.internal}:8000")
public interface ExchangeProxy {

	@GetMapping(value = "/exchange-service/{amount}/{from}/{to}")
	public Exchange getExchange(
			@PathVariable Double amount, 
			@PathVariable String from, 
			@PathVariable String to); 
	
}
