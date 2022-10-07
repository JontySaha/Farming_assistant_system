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

import com.cg.farming.entity.Advertisement;
import com.cg.farming.exception.AdvertisementNotFoundException;

@SpringBootTest
class AdvertisementServiceTest {

	@Autowired
	AdvertisementServiceImpl advServ;
	
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

	// test addAdvertisement() method
	@Test
	void addAdvertisementTest(){
		Advertisement adv = new Advertisement();
		adv.setCropType("dal");
		adv.setPrice("60");
		adv.setQuantity(4);
		adv.setWeight("60");
		Advertisement newAdv= advServ.addAdvertisement(adv);
		assertEquals("dal",newAdv.getCropType());
		assertEquals("60",newAdv.getPrice());
		assertEquals(4,newAdv.getQuantity());
		assertEquals("60",newAdv.getWeight());
		
	}
	
	// test getAllAdvertisement() method
	@Test
	void getAllAdvertisementTest() {
		List<Advertisement> advertisements = advServ.getAllAdvertisement();
		assertEquals(4, advertisements.size());
		Advertisement adv = advertisements.get(0);
		assertEquals("dal", adv.getCropType());
	}
	
	//test deleteAdvertisement() method
	@Test
	void deleteAdvertisementTest() throws AdvertisementNotFoundException {
		Advertisement adv = advServ.deleteAdvertisement(6);
		assertEquals("60", adv.getPrice());
	}

}