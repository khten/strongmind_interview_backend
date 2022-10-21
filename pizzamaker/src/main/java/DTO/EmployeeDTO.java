package DTO;

import com.burke.pizzamaker.enums.Role;

public class EmployeeDTO {

	
	private String name;
	private Role role;
	
	public EmployeeDTO(String name, Role role) {
          this.name = name;
          this.role = role;
	}

	public String getName() {
		return name;
	}

	public Role getRole() {
		return role;
	}


}
