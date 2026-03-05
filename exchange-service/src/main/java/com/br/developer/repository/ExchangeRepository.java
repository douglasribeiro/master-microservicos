package com.br.developer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.developer.model.Exchange;

public interface ExchangeRepository extends JpaRepository<Exchange, Long>{

	Exchange findByFromAndTo(String from, String to);
	
}
