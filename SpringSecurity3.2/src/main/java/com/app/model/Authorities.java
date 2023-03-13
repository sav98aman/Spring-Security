package com.app.model;


import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Authorities {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer authoritesID;
	
	private String authoritesName;
	
	@ManyToMany(mappedBy = "authorites")
	private Set<Employee> employee;
}
	
