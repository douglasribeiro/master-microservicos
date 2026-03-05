package com.br.developer.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.developer.dto.Exchange;
import com.br.developer.environment.InstanceInformationService;
import com.br.developer.model.Book;
import com.br.developer.proxy.ExchangeProxy;
import com.br.developer.repository.BookRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "Book endpoint")
@RestController
@RequestMapping("/book-service")
@RequiredArgsConstructor
public class BookController {

	private final InstanceInformationService informationService;
	private final BookRepository repository; 
	private final ExchangeProxy proxy;
	
	@Operation(summary = "Find a specific book by your ID")
	@GetMapping(value = "/{id}/{currency}", produces =
	MediaType.APPLICATION_JSON_VALUE) public Book findBook(
		@PathVariable("id") Long id,
		@PathVariable("currency") String currency) 
	{
		String port = informationService.retriveServerPort(); 
		Book book = repository.findById(id).orElseThrow();
		
		Exchange exchange = proxy.getExchange(book.getPrice(), "USD", currency);
		
		book.setEnvironment("Book por " + port + " Exchange port" + exchange.getEnvironment()); 
		book.setCurrency(currency);
		book.setPrice(exchange.getConvertedValue()); 
		return book;
		
	}
	
	
//	@GetMapping(value = "/{id}/{currency}", produces = MediaType.APPLICATION_JSON_VALUE)
//	public Book findBook(
//			@PathVariable("id") Long id,
//			@PathVariable("currency") String currency) {
//		String port = informationService.retriveServerPort();
//		return new Book(
//				1L,
//				"Nigel Poulton",
//				"Docker Deep Driver",
//				new Date(),
//				15.8,
//				"BRL",
//				port
//				);
//				
//	}
	
	/*
	 * @GetMapping(value = "/{id}/{currency}", produces =
	 * MediaType.APPLICATION_JSON_VALUE) public Book findBook(
	 * 
	 * @PathVariable("id") Long id,
	 * 
	 * @PathVariable("currency") String currency) { String port =
	 * informationService.retriveServerPort(); Book book =
	 * repository.findById(id).orElseThrow();
	 * 
	 * HashMap<String, String> params = new HashMap<>(); params.put("amount",
	 * book.getPrice().toString()); params.put("from", "USD"); params.put("to",
	 * currency);
	 * 
	 * var response = new RestTemplate().getForEntity(
	 * "http://localhost:8000/exchange-service/{amount}/{from}/{to}",
	 * Exchange.class, params); Exchange exchange = response.getBody();
	 * 
	 * book.setEnvironment(port); book.setCurrency(currency);
	 * book.setPrice(exchange.getConvertedValue()); return book;
	 * 
	 * }
	 */
}
