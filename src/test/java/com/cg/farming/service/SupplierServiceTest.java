package com.cg.farming.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.farming.entity.Supplier;
import com.cg.farming.exception.SupplierNotFoundException;

@SpringBootTest
class SupplierServiceTest {
	
	@Autowired
	SupplierServiceImpl supServ;

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
	
	// test deleteSupplier() method
		@Test
		void deleteSupplierTest() throws SupplierNotFoundException {
			Supplier supplier = supServ.deleteSupplier(1);
			assertEquals("13456728", supplier.getPhoneNo());
		}
		
		// test updateSupplier() method
		@Test
		void updatdSupplierTest() throws SupplierNotFoundException {
			Supplier supplier= new Supplier();
			supplier.setAddress("parkcircus");
			supplier.setPhoneNo("675432871");
			supplier.setName("sandipa");
			Supplier updatedSupplier =supServ.updateSupplier(2, supplier);
			
			assertEquals("parkcircus", updatedSupplier.getAddress());
			assertEquals("sandipa",updatedSupplier.getName());
			assertEquals("675432871",updatedSupplier.getPhoneNo());
		}
}
