package com.whitecape.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.ReactiveDiscoveryClient;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.cloud.gateway.discovery.DiscoveryLocatorProperties;
import org.springframework.context.annotation.Bean;



@SpringBootApplication
public class ThwgGatwayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ThwgGatwayApplication.class, args);
	}
	 @Bean
	 public DiscoveryClientRouteDefinitionLocator discoveryClientRouteLocator(ReactiveDiscoveryClient rdc, DiscoveryLocatorProperties dlp) {
	 
	        return new DiscoveryClientRouteDefinitionLocator(rdc,dlp);
}
}