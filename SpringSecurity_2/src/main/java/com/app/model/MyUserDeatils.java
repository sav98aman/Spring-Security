package com.app.model;

import java.util.ArrayList;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class MyUserDeatils implements UserDetails{
	
	
	
	private Employee employee;
	
	
	public MyUserDeatils() {
		
	}
	public MyUserDeatils(Employee empDB) {
		this.employee=empDB;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		Collection<GrantedAuthority> authorities=new ArrayList<>();
		SimpleGrantedAuthority roleauthorites=new SimpleGrantedAuthority(employee.getRole());
		authorities.add(roleauthorites);
		return authorities;
	
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return employee.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return employee.getUserName();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
