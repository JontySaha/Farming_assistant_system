package com.cg.farming.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.farming.entity.Supplier;
import com.cg.farming.exception.SupplierNotFoundException;
import com.cg.farming.repo.ISupplierRepo;

@Service
public class SupplierServiceImpl implements ISupplierService {

	@Autowired
	ISupplierRepo supplierRepository;

	@Override
	public Supplier viewSupplierByUsername(String username) {
		// TODO Auto-generated method stub
		return supplierRepository.viewSupplierByUsername(username);
	}

}
