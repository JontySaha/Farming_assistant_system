package com.cg.farming.service;

import com.cg.farming.entity.User;
import com.cg.farming.exception.UserNotFoundException;

public interface IUserService {
	User updateUser(int id,User user) throws UserNotFoundException;
	User deleteUser(int id) throws UserNotFoundException; 
}
