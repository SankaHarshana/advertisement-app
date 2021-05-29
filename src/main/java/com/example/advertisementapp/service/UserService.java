package com.example.advertisementapp.service;

import com.example.advertisementapp.Exception.UserException;
import com.example.advertisementapp.dto.UserDto;

public interface UserService {

    public UserDto createUser(UserDto userDto) throws UserException;

    public UserDto login(UserDto userDto) throws UserException;


}
