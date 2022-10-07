package com.cg.farming.service;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.farming.entity.Farmer;
import com.cg.farming.exception.FarmerNotFoundException;
import com.cg.farming.repo.IFarmerRepo;

@Service
public class FarmerServiceImpl implements IFarmerService {

	@Autowired
	IFarmerRepo farmerRepository;
	
	private static Logger logger = LogManager.getLogger();
	
	@Override
	public Farmer updateFarmer(int id, Farmer farmer) throws FarmerNotFoundException {
		Optional<Farmer> farmerOpt = farmerRepository.findById(id);

		if (farmerOpt.isPresent()) {
			Farmer farmerDetails = farmerOpt.get();
			farmerDetails.setName(farmer.getName());
			farmerDetails.setAddress(farmer.getAddress());
			farmerDetails.setPhoneNo(farmer.getPhoneNo());
		    return farmerRepository.save(farmerDetails);
		} else {
			throw new FarmerNotFoundException("Farmer not found with given id: " + id);
		}
	}

	@Override
	public Farmer deleteFarmer(int id) throws FarmerNotFoundException {
		Optional<Farmer> farmerOpt = farmerRepository.findById(id);
		logger.info(farmerRepository.findById(id));
		if (farmerOpt.isPresent()) {
			Farmer farmerDetails = farmerOpt.get();
			logger.info(farmerDetails);
			farmerRepository.deleteById(id);
			return farmerDetails;
		} else {
			throw new FarmerNotFoundException("Farmer not found with given id: " + id);
		}
	}

}
