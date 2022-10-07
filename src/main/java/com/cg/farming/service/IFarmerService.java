package com.cg.farming.service;

import com.cg.farming.entity.Farmer;
import com.cg.farming.exception.FarmerNotFoundException;

public interface IFarmerService {
	Farmer updateFarmer(int id,Farmer farmer) throws FarmerNotFoundException;
	Farmer deleteFarmer(int id) throws FarmerNotFoundException; 
}
