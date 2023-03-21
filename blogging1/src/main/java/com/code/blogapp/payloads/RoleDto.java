package com.code.blogapp.payloads;

import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data

public class RoleDto {

	private int id;
	private String name;
}
