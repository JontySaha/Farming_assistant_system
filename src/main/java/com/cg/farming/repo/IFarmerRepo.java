package com.cg.farming.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.farming.entity.Farmer;

@Repository
public interface IFarmerRepo extends JpaRepository<Farmer,Integer>{
	
	Farmer getIdByFarmerId(@Param("id") int id);
}
