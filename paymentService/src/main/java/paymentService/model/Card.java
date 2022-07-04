package paymentService.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "card")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Card {

	  @Id
	  private String id;
	  private String cardNumber;
	  //private long CardMonthExpiration;
	  //private long CardYearExpiration;
	  //private long CVC;
	  private double amount;
	  private String currency;
	  
	  

	
	
}
