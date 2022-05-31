package com.whitecape.auth.payload;

import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.mongodb.core.mapping.DBRef;

import com.whitecape.auth.models.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@AllArgsConstructor
@NoArgsConstructor
@Data
public class EventRequest {
	/*    private String title;

    private String price;

    private String image;
    private String type;
    private String link;*/
	
	  @NotBlank
	    @Size(min = 3, max = 20)
	    private String title;
	 
	    @NotBlank
	    @Size(max = 50)
	    @DBRef
	    private User organizer ;   
	    
	    private String price;
	    private String description;
	    private String place;

	    private String image;
	    private String type;
	    private String link;
	    
		

}
