package com.cg.farming.controller;

import java.util.Collections;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cg.farming.dto.LoginDto;
import com.cg.farming.dto.SignUpDto;
import com.cg.farming.entity.Advertisement;
import com.cg.farming.entity.Role;
import com.cg.farming.entity.User;
import com.cg.farming.exception.AdvertisementNotFoundException;
import com.cg.farming.repo.IRoleRepo;
import com.cg.farming.repo.IUserRepo;
import com.cg.farming.service.AdvertisementServiceImpl;

@RestController
@RequestMapping("/supplier")
public class SupplierController {
	
	@Autowired
    private AuthenticationManager authenticationManager;

	private static Logger logger = LogManager.getLogger();
	
    @Autowired
    private IUserRepo userRepository;

    @Autowired
    private IRoleRepo roleRepository;
    
    @Autowired
	AdvertisementServiceImpl advService;  
    
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignUpDto signUpDto){

        // add check for username exists in a DB
        if(userRepository.existsByUsername(signUpDto.getUsername())){
            return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
        }       

        // create user object
        User user = new User();
        user.setUsername(signUpDto.getUsername());
        user.setPassword(signUpDto.getPassword());
        user.setAddress(signUpDto.getAddress());
        user.setPhoneNo(signUpDto.getPhoneNo());
  //      user.setRole("ROLE_SUPPLIER");
       
        logger.info(user);

       Role roles = roleRepository.findByName("ROLE_SUPPLIER").get();
       user.setRoles(Collections.singleton(roles));
       
        userRepository.save(user);

        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);

    }
    
    @PostMapping("/signin")
    public ResponseEntity<String> authenticateUser(@RequestBody LoginDto loginDto){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsername(), loginDto.getPassword()));
        logger.info(authentication);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity<>("User signed-in successfully!.", HttpStatus.OK);
    }
    
    
    @PreAuthorize("hasRole('ROLE_SUPPLIER')")
    @RequestMapping(value="/add", method = RequestMethod.POST)
	ResponseEntity<Advertisement> addAdvertisement(@RequestBody Advertisement advt) {
		Advertisement newAdvt = advService.addAdvertisement(advt);
		return new ResponseEntity<>(newAdvt, HttpStatus.CREATED); // 201 created 
	}
	
    @PreAuthorize("hasRole('ROLE_SUPPLIER')")
    @RequestMapping(value="/update/{advId}", method = RequestMethod.PUT)
	ResponseEntity<Advertisement> updateAdvertisement(@PathVariable("advId") int advId, @RequestBody Advertisement advt) throws AdvertisementNotFoundException {
		Advertisement updatedAdvt = advService.updateAdvertisement(advId, advt);
		logger.info(updatedAdvt);
		return new ResponseEntity<>(updatedAdvt, HttpStatus.OK); // 200 Ok
	}
	
    @PreAuthorize("hasRole('ROLE_SUPPLIER')")
    @RequestMapping(value="/delete/{advId}", method = RequestMethod.DELETE)
	ResponseEntity<Advertisement> deleteAdvertisement(@PathVariable("advId") int advId) throws AdvertisementNotFoundException{
		Advertisement adv = advService.deleteAdvertisement(advId);
		return new ResponseEntity<>(adv, HttpStatus.OK); //200 ok
	}
}
