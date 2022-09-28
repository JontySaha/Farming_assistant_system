package com.cg.farming.service;

import java.util.List;

import com.cg.farming.entity.Complaint;
import com.cg.farming.exception.ComplaintNotFoundException;

public interface IComplaintService {
	Complaint addComplaint(Complaint comp);
	List<Complaint> getAllComplaint();
	Complaint resolveComplaint(int complaintId) throws ComplaintNotFoundException;
}