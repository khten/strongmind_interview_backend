package com.burke.pizzamaker.models;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter 
@Setter  
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pizza_table")
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
@ToString(exclude= {"toppings"})

public class Pizza {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	//TODO  check for unique names
	@Column(nullable = false, unique = true)
	private String name;

    //@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "pizza_topping",
	           joinColumns = @JoinColumn(name = "pizza_id", referencedColumnName = "id"),
	           inverseJoinColumns =  @JoinColumn(name = "topping_id", referencedColumnName = "id")

	)
	private List<Topping> toppings;

	@Override
	public int hashCode() {
		Collections.sort(toppings);
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
		Collections.sort(toppings);
		Collections.sort(other.toppings);

		return id == other.id && name.equals(other.name) && toppings.equals(other.toppings);
	}




}
