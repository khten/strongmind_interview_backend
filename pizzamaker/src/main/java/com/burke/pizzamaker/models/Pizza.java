package com.burke.pizzamaker.models;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter 
@Setter  
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pizza_table")
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")


public class Pizza {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	
	@Column(nullable = false)
	private String name;



    private String toppings;

   
    
	@Override
	public int hashCode() {
		return Objects.hash(id, name, toppings);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pizza other = (Pizza) obj;
        
		return id == other.id || name.equals(other.name) || toppings.equals(other.toppings);
	}




}
