package com.code.blogapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.code.blogapp.payloads.ApiResponse;
import com.code.blogapp.payloads.UserDto;
import com.code.blogapp.services.UserService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
@SecurityRequirement(name="Bearer Authentication")
public class UserController {
	@Autowired
private UserService userService;
	@PostMapping("/")
	public ResponseEntity<UserDto> create(@Valid @RequestBody UserDto userDto){
		UserDto  createuserDto=this.userService.create(userDto);
		return new ResponseEntity<>(createuserDto, HttpStatus.CREATED);
	}
	
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> update(@Valid @RequestBody UserDto userDto, @PathVariable("userId") Integer uid){
		UserDto updateduser=this.userService.update(userDto,uid);
		return new ResponseEntity<>(updateduser,HttpStatus.OK);
	}
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponse>delete(@PathVariable("userId") Integer uid){
		this.userService.delete(uid);
		return new ResponseEntity(new ApiResponse("User deleted",true ),HttpStatus.OK);
	}
	@GetMapping("/")
	public ResponseEntity<List<UserDto>>get(){
		return new ResponseEntity(this.userService.getAllUsers(),HttpStatus.OK);	
	}
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto>getAll(@PathVariable Integer userId){
		return new ResponseEntity(this.userService.get(userId),HttpStatus.OK);	
	}
}
