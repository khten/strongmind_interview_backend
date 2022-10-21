package DTO;

public class PizzaDTO {

	private String name;
	private String toppings;
	
	public PizzaDTO(String name, String toppings) {
		this.name = name;
		this.toppings = toppings;
	}
	
	public String getName() {
		return name;
	}
	
	public String getToppings() {
		return toppings;
	}
}
