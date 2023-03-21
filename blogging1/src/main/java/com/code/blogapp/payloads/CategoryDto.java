package com.code.blogapp.payloads;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor

@Setter
@Getter
public class CategoryDto {
	@Id
	private int id;

	@NotEmpty
	@Size(min = 4, message = "Title must be min of 4 characters !!")
	private String title;

	@NotEmpty
	@Size(min = 10, message = "Description must be min of 10 characters !!")
	private String description;

	
}
