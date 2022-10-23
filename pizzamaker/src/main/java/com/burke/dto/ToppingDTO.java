package com.burke.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class ToppingDTO {

	private int id;
	
	private String name;
	
	public String getName() {
		return this.name;
	}
	
	public int getId() {
		return this.id;
	}
}
