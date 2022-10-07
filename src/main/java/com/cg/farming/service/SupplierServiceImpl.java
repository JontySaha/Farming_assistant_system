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
	public Supplier updateSupplier(int id, Supplier supplier) throws SupplierNotFoundException {
		Optional<Supplier> supplierOpt = supplierRepository.findById(id);

		if (supplierOpt.isPresent()) {
			Supplier supplierDetails = supplierOpt.get();
			supplierDetails.setName(supplier.getName());
			supplierDetails.setAddress(supplier.getAddress());
			supplierDetails.setPhoneNo(supplier.getPhoneNo());
		    return supplierRepository.save(supplierDetails);
		} else {
			throw new SupplierNotFoundException("Supplier not found with given id: " + id);
		}
	}

	@Override
	public Supplier deleteSupplier(int id) throws SupplierNotFoundException {
		Optional<Supplier> supplierOpt = supplierRepository.findById(id);
		if (supplierOpt.isPresent()) {
			Supplier supplierDetails = supplierOpt.get();
			supplierRepository.deleteById(id);
			return supplierDetails;
		} else {
			throw new SupplierNotFoundException("Supplier not found with given id: " + id);
		}
	}

}
