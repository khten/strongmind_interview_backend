package com.burke.pizzamaker.models;

import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter 
@Setter  @ToString 
@AllArgsConstructor
@NoArgsConstructor
public class Pizza {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, unique = true)
	private String name;
	
	@JsonIgnore
	@ElementCollection
	private List<Topping> toppings;

	@Override
	public int hashCode() {
		return Objects.hash(toppings.size());
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
		List<Topping> otherList = other.toppings;
		List<Topping> thisList = this.toppings;
		//both null return true;
		if(otherList == null && thisList == null) return true;
		//sizes unequal => return false
		if(otherList.size() != thisList.size()) return false;
		
		return (otherList != null && thisList != null && otherList.containsAll(thisList) &&
				thisList.containsAll(otherList));
	
	}
	
	
	
	
}
