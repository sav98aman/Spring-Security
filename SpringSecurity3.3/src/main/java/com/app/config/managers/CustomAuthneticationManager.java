package com.app.config.managers;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import com.app.config.provider.CustomAuthenticationProvider;
import lombok.AllArgsConstructor;


@Component
@AllArgsConstructor
public class CustomAuthneticationManager implements AuthenticationManager{

	private final CustomAuthenticationProvider customAuthenticationProvider;
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// TODO Auto-generated method stub
		if(customAuthenticationProvider.supports(authentication.getClass()) ) {
			return customAuthenticationProvider.authenticate(authentication);
		}else {
			throw new BadCredentialsException(" oh No !");
		}
	}
	
	
}
