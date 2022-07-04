package com.whitecape.auth.payload;

import java.util.List;
import org.bson.types.Binary;

import lombok.Data;

@Data
public class JwtResponse {
	//private String token;
    private String accessToken;

	private String type = "Bearer";
	private String id;
	private String username;
	private String email;
	private List<String> roles;
	private Binary picture;

	public JwtResponse(String accessToken, String id, String username, String email, Binary picture, List<String> roles) {
		this.accessToken = accessToken;
		this.id = id;
		this.username = username;
		this.email = email;
		this.picture = picture;

		this.roles = roles;
	}

	

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getTokenType() {
		return type;
	}

	public void setTokenType(String tokenType) {
		this.type = tokenType;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<String> getRoles() {
		return roles;
	}
}
