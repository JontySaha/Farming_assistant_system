package com.cg.farming.service;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cg.farming.entity.Farmer;
import com.cg.farming.entity.Role;
import com.cg.farming.entity.Supplier;
import com.cg.farming.entity.User;
import com.cg.farming.exception.UserNotFoundException;
import com.cg.farming.repo.IFarmerRepo;
import com.cg.farming.repo.IUserRepo;

@Service
public class UserServiceImpl implements UserDetailsService,IUserService{
	
	private static Logger logger = LogManager.getLogger();
	
	@Autowired
	private IUserRepo userRepository;
	
	@Autowired
	IFarmerRepo farmerRepository;

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username)
				.orElseThrow(()->new UsernameNotFoundException("User not found with username:" + username));
				return new org.springframework.security.core.userdetails.User(user.getUsername(),
						user.getPassword(), mapRolesToAuthorities(user.getRoles()));
	}
	
	 private Collection< ? extends GrantedAuthority> mapRolesToAuthorities(Set<Role> roles){
	        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}

	@Override
	public User deleteUser(String username) throws UserNotFoundException {
		// TODO Auto-generated method stub
		Optional<User> userOpt = userRepository.findByUsername(username);

		if (userOpt.isPresent()) {
			User userDetails = userOpt.get();
			userRepository.deleteById(userDetails.getId());
			return userDetails;
			} else {
			throw new UserNotFoundException("Supplier not found with given id: " + username);
		}
	}
	
	@Override
	public User updateFarmer(String username, Farmer farmer) throws UserNotFoundException {
		
		Optional<User> user = userRepository.findByUsername(username);
		if (user.isPresent()) {
		User userDetails=user.get();
		Farmer farmerdetails=userDetails.getFarmer();
		farmerdetails.setName(farmer.getName());
		farmerdetails.setPhoneNo(farmer.getPhoneNo());
		farmerdetails.setAddress(farmer.getAddress());
	    //save the user class
	    return userRepository.save(userDetails);
		
		} else {
			throw new UserNotFoundException("User not found with given id: " + username);
		}
	}

	@Override
	public User updateSupplier(String username, Supplier supplier) throws UserNotFoundException {
		Optional<User> user = userRepository.findByUsername(username);
		if (user.isPresent()) {
		User userDetails=user.get();
		Supplier supplierdetails=userDetails.getSupplier();
		supplierdetails.setName(supplier.getName());
		supplierdetails.setPhoneNo(supplier.getPhoneNo());
		supplierdetails.setAddress(supplier.getAddress());
	    //save the user class
	    return userRepository.save(userDetails);
		
		} else {
			throw new UserNotFoundException("User not found with given id: " + username);
		}
	}

}
	
	
