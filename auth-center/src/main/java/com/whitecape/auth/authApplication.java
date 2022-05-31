package com.whitecape.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient


public class authApplication {
/*
	 @Autowired
	    @Qualifier("kafkaCustomerListener")
	    private ConsumerFactory consumerFactory;

	    @Bean
	    public ConcurrentKafkaListenerContainerFactory<Integer, String> kafkaListenerContainerFactory() {
	        ConcurrentKafkaListenerContainerFactory<Integer, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
	        factory.setConsumerFactory(consumerFactory);
	        return factory;
	    }
	    
*/
	    
	public static void main(String[] args) {
		SpringApplication.run(authApplication.class, args);
	}

}