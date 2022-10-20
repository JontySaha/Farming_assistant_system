package com.cg.farming.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cg.farming.entity.Advertisement;
import com.cg.farming.entity.Farmer;
import com.cg.farming.entity.Supplier;
import com.cg.farming.entity.User;
import com.cg.farming.exception.FarmerNotFoundException;
import com.cg.farming.exception.SupplierNotFoundException;
import com.cg.farming.exception.UserNotFoundException;
import com.cg.farming.repo.IUserRepo;
import com.cg.farming.service.UserServiceImpl;

@CrossOrigin("*")
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
    private IUserRepo userRepository;

	@Autowired
	UserServiceImpl userService;
	
	 @RequestMapping(value="/deleteUser/{username}", method = RequestMethod.DELETE)
	    ResponseEntity<User> deleteUser(@PathVariable("username") String  username) throws UserNotFoundException {
		 User user = userService.deleteUser(username);
			return new ResponseEntity<>(user, HttpStatus.OK); // 200 Ok
		}
	 
   @RequestMapping(value="/updateFarmer/{username}", method = RequestMethod.PATCH)
   ResponseEntity<User> updateFarmer(@PathVariable("username") String  username, @RequestBody Farmer farmer) throws UserNotFoundException {
	   User updatedUser = userService.updateFarmer(username, farmer);
		return new ResponseEntity<>(updatedUser, HttpStatus.OK); // 200 Ok
	}
   
   @RequestMapping(value="/updateSupplier/{username}", method = RequestMethod.PATCH)
   ResponseEntity<User> updateSupplier(@PathVariable("username") String  username, @RequestBody Supplier supplier) throws UserNotFoundException {
	   User updatedUser = userService.updateSupplier(username, supplier);
		return new ResponseEntity<>(updatedUser, HttpStatus.OK); // 200 Ok
	}
   
}
