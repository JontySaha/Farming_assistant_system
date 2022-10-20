package com.cg.farming.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.farming.entity.Supplier;

@Repository
public interface ISupplierRepo extends JpaRepository<Supplier,Integer>{

	@Query(value="select * from supplier inner join users on supplier.id=users.id where users.username=:username", nativeQuery=true)
	Supplier viewSupplierByUsername(@Param("username") String username);

}
