package com.celestial.pingme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class PingmeApplication 
{
	@Bean
	public WebClient.Builder webClientBuilder() 
	{
		return WebClient.builder();
	}
	
	public static void main(String[] args) 
	{
		SpringApplication.run(PingmeApplication.class, args);
	}

}
