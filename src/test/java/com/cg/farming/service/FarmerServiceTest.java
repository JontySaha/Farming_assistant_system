package com.cg.farming.service;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.farming.entity.Farmer;
import com.cg.farming.exception.FarmerNotFoundException;

@SpringBootTest
class FarmerServiceTest {

	@Autowired
	FarmerServiceImpl farmServ;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		System.out.println("BeforeAll");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		System.out.println("AfterAll");
	}

	@BeforeEach
	void setUp() throws Exception {
		System.out.println("BeforeEach");
	}

	@AfterEach
	void tearDown() throws Exception {
		System.out.println("AfterEach");
	}
	
	// test deleteFarmer() method
		@Test
		void deleteFarmerTest() throws FarmerNotFoundException {
			Farmer farmer = farmServ.deleteFarmer(1);
			assertEquals("13456728", farmer.getPhoneNo());
		}
		
		// test updateFarmer() method
		@Test
		void updateUserTest() throws FarmerNotFoundException {
			Farmer farmer = new Farmer();
			farmer.setAddress("parkcircus");
			farmer.setPhoneNo("675432871");
	        farmer.setName("sandipa");
			Farmer updatedFarmer =farmServ.updateFarmer(2,farmer);
			
			assertEquals("parkcircus", updatedFarmer.getAddress());
			assertEquals("sandipa",updatedFarmer.getName());
			assertEquals("675432871",updatedFarmer.getPhoneNo());
		}

}
