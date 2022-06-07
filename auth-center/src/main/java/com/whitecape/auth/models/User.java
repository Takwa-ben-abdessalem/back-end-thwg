package com.whitecape.auth.models;

import org.springframework.data.mongodb.core.mapping.Document;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import paymentService.model.Card;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
@Document(collection = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
  @Id
  private String id;
  @NotBlank
  @Size(max = 20)
  private String username;
  @NotBlank
  @Size(max = 50)
  @Email
  private String email;
  @JsonIgnore

  @NotBlank
  @Size(max = 120)
  private String password;
  @DBRef
  private Set<Role> roles = new HashSet<>();
  @JsonIgnore

  @DBRef
  private List<Event> EventAdded = new ArrayList<>();
  @JsonIgnore

  @DBRef

  private List<Event> EventPartcipated = new ArrayList<>();
  @DBRef
  private Event LastEventAdded;
  
  private String FirstName;
  private String LastName;
  private String phoneNumber;
  private Binary picture  ;  
  

  


  
  public User(String username, String email, String password) {
    this.username = username;
    this.email = email;
    this.password = password;
  } 
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public String getUsername() {
    return username;
  }
  public void setUsername(String username) {
    this.username = username;
  }
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
  }
  public Set<Role> getRoles() {
    return roles;
  }
 
  public void setRoles(Set<Role> roles) {
    this.roles = roles;
  }
 

  @Override
  public String toString() {
      return "User{" +
              "id=" + id +
              ", username='" + username + '\'' +
                
              ", email='" + email + '\'' +
               
              '}';
  }
}