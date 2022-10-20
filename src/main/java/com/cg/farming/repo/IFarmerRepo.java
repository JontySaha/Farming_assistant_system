package com.cg.farming.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.farming.entity.Farmer;

@Repository
public interface IFarmerRepo extends JpaRepository<Farmer,Integer>{
	
	@Query(value="select * from farmer inner join users on farmer.id=users.id where users.username=:username", nativeQuery=true)
	 Farmer viewFarmerByUsername(@Param("username") String username);
	
}
