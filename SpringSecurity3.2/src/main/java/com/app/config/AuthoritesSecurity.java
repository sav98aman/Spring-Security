package com.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;

import com.app.model.Authorities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class AuthoritesSecurity implements GrantedAuthority {

	
	private Authorities authorites;
	
	
	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return authorites.getAuthoritesName();
	}

}
