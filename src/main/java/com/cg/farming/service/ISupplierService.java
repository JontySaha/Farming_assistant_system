package com.cg.farming.service;

import com.cg.farming.entity.Supplier;
import com.cg.farming.exception.SupplierNotFoundException;

public interface ISupplierService {
	Supplier updateSupplier(int id,Supplier supplier) throws SupplierNotFoundException;
	Supplier deleteSupplier(int id) throws SupplierNotFoundException; 
}
