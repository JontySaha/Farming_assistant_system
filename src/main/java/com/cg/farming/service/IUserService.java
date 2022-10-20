package com.cg.farming.service;

import com.cg.farming.entity.Farmer;
import com.cg.farming.entity.Supplier;
import com.cg.farming.entity.User;
import com.cg.farming.exception.UserNotFoundException;

public interface IUserService {
	User deleteUser(String username) throws UserNotFoundException;
	User updateFarmer(String username, Farmer farmer) throws UserNotFoundException;
	User updateSupplier(String username, Supplier supplier) throws UserNotFoundException;
}

