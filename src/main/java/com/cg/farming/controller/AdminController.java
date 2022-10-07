package com.cg.farming.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cg.farming.dto.LoginDto;
import com.cg.farming.entity.Complaint;
import com.cg.farming.exception.ComplaintNotFoundException;
import com.cg.farming.service.ComplaintServiceImpl;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
    private AuthenticationManager authenticationManager;
	
	@Autowired
	ComplaintServiceImpl compServ;

    
	@RequestMapping(value="/signin", method = RequestMethod.POST)
    public ResponseEntity<String> authenticateUser(@Valid @RequestBody LoginDto loginDto){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsername(), loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity<>("User signed-in successfully!.", HttpStatus.OK);
    }
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)  
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {  
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();  
        if (auth != null){      
           new SecurityContextLogoutHandler().logout(request, response, auth);  
        }  
         return "Logout Successfully";  
    }  

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value="/getAllComplaint", method = RequestMethod.GET)
    public ResponseEntity<List<Complaint>> getAllComplaint() {
		List<Complaint> comp = compServ.getAllComplaint();
		return new ResponseEntity<>(comp, HttpStatus.OK);
	}
    
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value="/resolveComplaint/{id}", method = RequestMethod.POST)
    ResponseEntity<Complaint> resolveComplaint(@Valid @PathVariable("id") int complaintId) throws ComplaintNotFoundException {
    	Complaint resolvedComp = compServ.resolveComplaint(complaintId);
		return new ResponseEntity<>(resolvedComp, HttpStatus.OK); // 200 Ok
	}
}
