package com.br.developer.config;

import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.License;

@OpenAPIDefinition(info = @Info(title = "Book Microservice",
		version = "V1",
		description = "Documentation of Book Microservice API"))
public class OpenApiConfiguration {

	@Bean
	public OpenAPI custonOpenApi() {
		return new OpenAPI()
				.components(new Components())
				.info(new io.swagger.v3.oas.models.info.Info()
						.title("Book Microservice API")
						.version("V1")
						.license(new License().name("Apache 2.0").url("")));
	}
}
