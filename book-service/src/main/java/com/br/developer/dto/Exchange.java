package com.br.developer.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Exchange implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;
	private String from;
	private String to;
	private BigDecimal conversionFactor;
	private Double convertedValue;
	private String environment;
}
