package com.cg.farming.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.farming.entity.Complaint;
import com.cg.farming.exception.ComplaintNotFoundException;
import com.cg.farming.repo.IComplaintRepo;

@Service
public class ComplaintServiceImpl implements IComplaintService {

	@Autowired
	IComplaintRepo compRepo;
	
	@Override
	public Complaint addComplaint(Complaint comp) {
		return compRepo.save(comp);
	}

	@Override
	public List<Complaint> getAllComplaint() {
		return compRepo.findAll();
	}

	@Override
	public Complaint resolveComplaint(int complaintId)throws ComplaintNotFoundException {
		Optional<Complaint> complaintDetails = compRepo.findById(complaintId);
		
		if (complaintDetails.isPresent()) {
			Complaint comp=complaintDetails.get();
			comp.setStatus(true);
			return compRepo.save(comp);
		} else {
			throw new ComplaintNotFoundException("Complaint not found with given id: " + complaintId);
		}
	}

}