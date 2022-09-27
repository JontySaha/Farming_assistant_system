package com.cg.farming.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cg.farming.dto.LoginDto;
import com.cg.farming.entity.Complaint;
import com.cg.farming.service.ComplaintServiceImpl;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
    private AuthenticationManager authenticationManager;
	
	@Autowired
	ComplaintServiceImpl compServ;

	private static Logger logger = LogManager.getLogger();
    
    @PostMapping("/signin")
    public ResponseEntity<String> authenticateUser(@RequestBody LoginDto loginDto){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsername(), loginDto.getPassword()));
        logger.info(authentication);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity<>("User signed-in successfully!.", HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value="/getAllComplaint", method = RequestMethod.GET)
    public ResponseEntity<List<Complaint>> getAllComplaint() {
		List<Complaint> comp = compServ.getAllComplaint();
		return new ResponseEntity<>(comp, HttpStatus.OK);
	}
}
