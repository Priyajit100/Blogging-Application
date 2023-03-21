package com.code.blogapp.services;

import java.util.List;

import com.code.blogapp.payloads.UserDto;

public interface UserService {
UserDto registerNewUser(UserDto user);
UserDto create(UserDto user);
UserDto update(UserDto user,Integer userId);
UserDto get(Integer userId);
List<UserDto>getAllUsers();
void delete(Integer userId);


}
