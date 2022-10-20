package com.cg.farming.controller;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

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
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cg.farming.dto.LoginDto;
import com.cg.farming.dto.SignUpDto;
import com.cg.farming.entity.Advertisement;
import com.cg.farming.entity.Complaint;
import com.cg.farming.entity.Farmer;
import com.cg.farming.entity.Role;
import com.cg.farming.entity.User;
import com.cg.farming.exception.AdvertisementNotFoundException;
import com.cg.farming.exception.FarmerNotFoundException;
import com.cg.farming.exception.UserNotFoundException;
import com.cg.farming.repo.IRoleRepo;
import com.cg.farming.repo.IUserRepo;
import com.cg.farming.service.AdvertisementServiceImpl;
import com.cg.farming.service.ComplaintServiceImpl;
import com.cg.farming.service.FarmerServiceImpl;
import com.cg.farming.service.UserServiceImpl;

@CrossOrigin("*")
@RestController
@RequestMapping("/farmer")
public class FarmerController {

	@Autowired
    private AuthenticationManager authenticationManager;

	private static Logger logger = LogManager.getLogger();
	
    @Autowired
    private IUserRepo userRepository;

    @Autowired
    private IRoleRepo roleRepository;
    
    @Autowired
	ComplaintServiceImpl compServ;
    
    @Autowired
	UserServiceImpl userService;
    
    @Autowired
    FarmerServiceImpl farmerService;
    
    @Autowired
	AdvertisementServiceImpl advService;
    
    @RequestMapping(value="/signup", method = RequestMethod.POST)
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpDto signUpDto){
        // add check for username exists in a DB
        if(userRepository.existsByUsername(signUpDto.getUsername())){
            return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
        }       
        
        Farmer farmer=new Farmer();
        farmer.setName(signUpDto.getName());
        farmer.setAddress(signUpDto.getAddress());
        farmer.setPhoneNo(signUpDto.getPhoneNo());
        
        // create user object
        User user = new User();
        user.setUsername(signUpDto.getUsername());
        user.setPassword(signUpDto.getPassword());
        Role roles = roleRepository.findByName("ROLE_FARMER").get();
        user.setRoles(Collections.singleton(roles));      
        user.setFarmer(farmer);
        farmer.setUser(user);
        userRepository.save(user);
        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);

    }
    
    @RequestMapping(value="/signin", method = RequestMethod.POST)
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginDto loginDto){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsername(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
       // logger.info(authentication);
        return new ResponseEntity<>(authentication, HttpStatus.OK);
    }
    
    @RequestMapping(value="/logout", method=RequestMethod.GET)  
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {  
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();  
        if (auth != null){      
           new SecurityContextLogoutHandler().logout(request, response, auth);  
        }  
         return "Logout Successfully";  
     }  
    
    
    @GetMapping("/viewFarmer/{username}")
	ResponseEntity<Farmer> viewFarmerByUsername(@PathVariable("username")String username) {
    	Farmer farmer = farmerService.viewFarmerByUsername(username);
    	logger.info((username));
		return new ResponseEntity<>(farmer, HttpStatus.OK);
	}
    
    //@PreAuthorize("hasRole('ROLE_FARMER')")
    @RequestMapping(value="/getAllAdvertisement", method = RequestMethod.GET)
    ResponseEntity<List<Advertisement>> getAllAdvertisement() {
		List<Advertisement> advertisements= advService.getAllAdvertisement();
		return new ResponseEntity<>(advertisements, HttpStatus.OK); // 200 ok
	}
    
    //@PreAuthorize("hasRole('ROLE_FARMER')")
    @RequestMapping(value="/addComplaint", method = RequestMethod.POST)
	public ResponseEntity<Complaint> addComplaint(@Valid @RequestBody Complaint complaint) {
		Complaint ck =compServ.addComplaint(complaint);
		return new ResponseEntity<>(ck, HttpStatus.CREATED); 
	}
    
    //@PreAuthorize("hasRole('ROLE_FARMER')")
    @RequestMapping(value="/statusAdvertisement/{id}", method = RequestMethod.POST)
    ResponseEntity<Advertisement> statusAdvertisement(@PathVariable("id") int advId) throws AdvertisementNotFoundException {
    	Advertisement statusAdv = advService.statusAdvertisement(advId);
		return new ResponseEntity<>(statusAdv, HttpStatus.OK); // 200 Ok
	}

}
