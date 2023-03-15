package com.app.config.filter;

import java.io.IOException;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.app.config.Authentication.CustomeAuthentication;
import com.app.config.managers.CustomAuthneticationManager;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.var;

@Component
@AllArgsConstructor
public class CustomAuthenticationFilter extends OncePerRequestFilter{
	
	private final CustomAuthneticationManager customeAuthneticationManager;
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String key=String.valueOf(request.getHeader("key"));
		CustomeAuthentication ca=new CustomeAuthentication(true, key);
		
		//create an authentication object which is not yet authenticated
		// delegate the authentication object to the manager
		var a=customeAuthneticationManager.authenticate(ca);
		if (a.isAuthenticated()) {
			SecurityContextHolder.getContext().setAuthentication(a);
			filterChain.doFilter(request, response);//only when authentication work
		}
		
	}

	

}
