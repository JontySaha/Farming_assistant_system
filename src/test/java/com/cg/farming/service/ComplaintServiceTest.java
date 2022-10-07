package com.cg.farming.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.farming.entity.Complaint;

@SpringBootTest
class ComplaintServiceTest {

	@Autowired
	ComplaintServiceImpl compServ;

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

	// test addComplaint() method
		@Test
		void addComplaintTest(){
			Complaint comp = new Complaint();
			comp.setComplaint("sandipa");
			Complaint newComp= compServ.addComplaint(comp);
			assertEquals("sandipa",newComp.getComplaint());
			
		}
		
    //test getAllComplaint() method
		@Test
		void getAllComplaintTest() {
			List<Complaint> complaints = compServ.getAllComplaint();
			assertEquals(1, complaints.size());
			Complaint comp = complaints.get(0);
			assertEquals("sandipa", comp.getComplaint());
		}


}