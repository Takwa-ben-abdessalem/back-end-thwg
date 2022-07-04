package paymentService.controller;

import java.util.List;



import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import paymentService.Exception.CartItemAlreadyExistsException;
import paymentService.model.Card;

import paymentService.repository.CardRepository;

@CrossOrigin(origins = "*", maxAge = 3600)

@RestController

@RequestMapping(value = "/api")

public class CardController {

	@Autowired
	CardRepository cardRepository;
	

	@GetMapping(value = "/all")
    @ResponseStatus(HttpStatus.OK)
	
	  public  Iterable<Card> getCards() {
			 return cardRepository.findAll();
	    }
		
		

		@GetMapping(value = "/card/{number}")
	    @ResponseStatus(HttpStatus.OK)

	    public  Card getCard(@PathVariable("number") String number) {
			 return cardRepository.findCardByCardNumber(number);
	    }
		
		
		
		@PostMapping(value = "/add/card")
	    @ResponseStatus(HttpStatus.OK)

	    public  String addCard(@RequestBody Card card ) {
	        for (Card item : cardRepository.findAll()) {  
	        	if (item.getCardNumber().equals(card.getCardNumber())) {
                throw new CartItemAlreadyExistsException(
                        "Card with number " + card.getCardNumber() + " already exists."
                );
            }
	        }
			Card insertedCard = cardRepository.insert(card);
			return insertedCard.getCardNumber() + " created successfully!\"" ;


			
}
		
		@PostMapping(value = "/card/amount/{number}/{amount}")
	    @ResponseStatus(HttpStatus.OK)

	    public  Card DecreaseCardAmount(@PathVariable("number") String number,@PathVariable ("amount") double amount ) {
			
			Card insertedCard =  cardRepository.findCardByCardNumber(number);
			double  amount2 = insertedCard.getAmount() ;
			insertedCard.setAmount(amount2 -amount)  ;
			cardRepository.save(insertedCard);
			return  insertedCard;


			
	    }
		
			


		 	
		}

