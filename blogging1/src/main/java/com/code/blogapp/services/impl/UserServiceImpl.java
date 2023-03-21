package com.code.blogapp.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.code.blogapp.config.AppConstants;
import com.code.blogapp.entities.Role;
import com.code.blogapp.entities.User;
import com.code.blogapp.exceptions.ResourceNotFoundException;
import com.code.blogapp.payloads.UserDto;
import com.code.blogapp.repositories.RoleRepo;
import com.code.blogapp.repositories.UserRepo;
import com.code.blogapp.services.UserService;


@Service
public class UserServiceImpl implements UserService {
	@Autowired
private UserRepo userRepo;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleRepo roleRepo;

	@Override
	public UserDto create(UserDto userDto) {
		User user =this.userDtoToUser(userDto);
		User saved=this.userRepo.save(user);// TODO Auto-generated method stub
		return this.userToDto(user);
	}

	@Override
	public UserDto update(UserDto userDto, Integer userId) {
		User user1 =this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));// TODO Auto-generated method stub
		user1.setName(userDto.getName());
		user1.setEmail(userDto.getEmail());
		user1.setPassword(userDto.getPassword());
		user1.setAbout(userDto.getAbout());
		User updated=this.userRepo.save(user1);
		return this.userToDto(updated);
	}

	@Override
	public UserDto get(Integer userId) {
		User user1 =this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
		// TODO Auto-generated method stub
		return this.userToDto(user1);
	}

	@Override
	public List<UserDto> getAllUsers() {
	List<User>users=	this.userRepo.findAll();
	List<UserDto>userDtos=users.stream().map(user->this.userToDto(user)).collect(Collectors.toList());
		// TODO Auto-generated method stub
		return userDtos;
	}

	@Override
	public void delete(Integer userId) {
		// TODO Auto-generated method stub
		User user1 =this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
	this.userRepo.delete(user1);
	}
	public User userDtoToUser(UserDto userDto) {
		User user=this.modelMapper.map(userDto, User.class);
	
		return user;
	}
	public UserDto userToDto(User user) {
		UserDto userdto=this.modelMapper.map(user, UserDto.class);
		
		
		
		
		return userdto;
	}

	@Override
	public UserDto registerNewUser(UserDto userDto) {

		User user = this.modelMapper.map(userDto, User.class);

		// encoded the password
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));

		// roles
		Role role = this.roleRepo.findById(AppConstants.NORMAL_USER).get();

		user.getRoles().add(role);

		User newUser = this.userRepo.save(user);

		return this.modelMapper.map(newUser, UserDto.class);
	}
}
