package com.cg.farming.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.farming.entity.Supplier;

@Repository
public interface ISupplierRepo extends JpaRepository<Supplier,Integer>{

}
