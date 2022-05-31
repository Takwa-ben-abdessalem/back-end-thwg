package com.whitecape.events;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient


public class Application {

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
		SpringApplication.run(Application.class, args);
	}
}