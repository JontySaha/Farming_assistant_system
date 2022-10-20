package com.cg.farming.service;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.farming.entity.Farmer;
import com.cg.farming.entity.User;
import com.cg.farming.exception.FarmerNotFoundException;
import com.cg.farming.repo.IFarmerRepo;

@Service
public class FarmerServiceImpl implements IFarmerService {

	@Autowired
	IFarmerRepo farmerRepository;
	
	private static Logger logger = LogManager.getLogger();

	@Override
	public Farmer viewFarmerByUsername(String username) {
		// TODO Auto-generated method stub
		return farmerRepository.viewFarmerByUsername(username);
	}
}
