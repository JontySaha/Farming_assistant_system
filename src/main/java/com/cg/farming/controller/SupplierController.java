package com.cg.farming.controller;

import java.util.Collections;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cg.farming.dto.LoginDto;
import com.cg.farming.dto.SignUpDto;
import com.cg.farming.entity.Advertisement;
import com.cg.farming.entity.Role;
import com.cg.farming.entity.Supplier;
import com.cg.farming.entity.User;
import com.cg.farming.exception.AdvertisementNotFoundException;
import com.cg.farming.exception.SupplierNotFoundException;
import com.cg.farming.repo.IRoleRepo;
import com.cg.farming.repo.IUserRepo;
import com.cg.farming.service.AdvertisementServiceImpl;
import com.cg.farming.service.SupplierServiceImpl;
import com.cg.farming.service.UserServiceImpl;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/supplier")
public class SupplierController {
	
	@Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private IUserRepo userRepository;

    @Autowired
    private IRoleRepo roleRepository;
    
    @Autowired
   	UserServiceImpl userService;
    
    @Autowired
    SupplierServiceImpl supplierService;
    
    @Autowired
	AdvertisementServiceImpl advService;  
    
    @RequestMapping(value="/signup", method = RequestMethod.POST)
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpDto signUpDto){

        // add check for username exists in a DB
        if(userRepository.existsByUsername(signUpDto.getUsername())){
            return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
        }      
        
        Supplier supplier=new Supplier();
        supplier.setName(signUpDto.getName());
        supplier.setAddress(signUpDto.getAddress());
        supplier.setPhoneNo(signUpDto.getPhoneNo());
        
        // create user object
        User user = new User();
        user.setUsername(signUpDto.getUsername());
        user.setPassword(signUpDto.getPassword());
        Role roles = roleRepository.findByName("ROLE_SUPPLIER").get();
        user.setRoles(Collections.singleton(roles));  
        user.setSupplier(supplier);
        supplier.setUser(user);
        userRepository.save(user);
        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);

    }
    
    @RequestMapping(value="/signin", method = RequestMethod.POST)
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginDto loginDto){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsername(), loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity<>(authentication.getAuthorities(), HttpStatus.OK);
    }
    
    @RequestMapping(value="/logout", method=RequestMethod.GET)  
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {  
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();  
        if (auth != null){      
           new SecurityContextLogoutHandler().logout(request, response, auth);  
        }  
         return "Logout Successfully";  
     }  
    
    @PreAuthorize("hasRole('ROLE_SUPPLIER')")
    @RequestMapping(value="/updateDetails/{id}", method = RequestMethod.PUT)
    ResponseEntity<Supplier> updateSupplier(@Valid @PathVariable("id") int supplierId, @RequestBody Supplier supplier) throws SupplierNotFoundException {
    	Supplier updatedSupplier = supplierService.updateSupplier(supplierId, supplier);
		return new ResponseEntity<>(updatedSupplier, HttpStatus.OK); // 200 Ok
	}
    
    @PreAuthorize("hasRole('ROLE_SUPPLIER')")
    @RequestMapping(value="/deleteDetails/{id}", method = RequestMethod.DELETE)
    ResponseEntity<Supplier> deleteSupplier(@PathVariable("id") int supplierId) throws SupplierNotFoundException {
    	Supplier supplier = supplierService.deleteSupplier(supplierId);
		return new ResponseEntity<>(supplier, HttpStatus.OK); // 200 Ok
	}
    
    @PreAuthorize("hasRole('ROLE_SUPPLIER')")
    @RequestMapping(value="/addAdvertisement", method = RequestMethod.POST)
	ResponseEntity<Advertisement> addAdvertisement(@RequestBody Advertisement advt) {
		Advertisement newAdvt = advService.addAdvertisement(advt);
		return new ResponseEntity<>(newAdvt, HttpStatus.CREATED); // 201 created 
	}
	
    @PreAuthorize("hasRole('ROLE_SUPPLIER')")
    @RequestMapping(value="/updateAdvertisement/{advId}", method = RequestMethod.PUT)
	ResponseEntity<Advertisement> updateAdvertisement(@Valid @PathVariable("advId") int advId, @RequestBody Advertisement advt) throws AdvertisementNotFoundException {
		Advertisement updatedAdvt = advService.updateAdvertisement(advId, advt);
		return new ResponseEntity<>(updatedAdvt, HttpStatus.OK); // 200 Ok
	}
	
    @PreAuthorize("hasRole('ROLE_SUPPLIER')")
    @RequestMapping(value="/deleteAdvertisement/{advId}", method = RequestMethod.DELETE)
	ResponseEntity<Advertisement> deleteAdvertisement(@PathVariable("advId") int advId) throws AdvertisementNotFoundException{
		Advertisement adv = advService.deleteAdvertisement(advId);
		return new ResponseEntity<>(adv, HttpStatus.OK); //200 ok
	}
}
