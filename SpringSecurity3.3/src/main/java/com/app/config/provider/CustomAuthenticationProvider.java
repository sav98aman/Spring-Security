package com.app.config.provider;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import com.app.config.Authentication.CustomeAuthentication;


@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Value("${our.very.very.very.secret.key}")
	private String key;
	
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// TODO Auto-generated method stub
		CustomeAuthentication ca=(CustomeAuthentication) authentication;
		
		String headerkey=ca.getKey();
		if(key.equals(headerkey)) {
			CustomeAuthentication result=new CustomeAuthentication(false, headerkey);
			return result;
		}else {
			throw new BadCredentialsException( "Oh No !");
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return CustomeAuthentication.class.equals(authentication);
	}

}
