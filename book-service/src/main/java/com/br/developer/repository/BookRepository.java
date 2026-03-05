package com.br.developer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.developer.model.Book;

public interface BookRepository extends JpaRepository<Book, Long>{

	
}
