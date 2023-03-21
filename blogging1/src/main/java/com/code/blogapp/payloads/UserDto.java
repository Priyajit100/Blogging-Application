package com.code.blogapp.payloads;

import java.util.HashSet;
import java.util.Set;

import com.code.blogapp.entities.Role;
import com.code.blogapp.payloads.*;


import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import lombok.NoArgsConstructor;
import lombok.Setter;



@Setter
@Getter
public class UserDto {
	@Id
	private int id;

	@NotEmpty
	@Size(min = 4, message = "Username must be min of 4 characters !!")
	private String name;

	@Email(message = "Email address is not valid !!")
	@NotEmpty(message = "Email is required !!")
	private String email;

	@NotEmpty
	@Size(min = 3, max = 10, message = "Password must be min of 3 chars and max of 10 chars !!")


	private String password;

	@NotEmpty
	private String about;

	private Set<RoleDto> roles = new HashSet<>();

}
