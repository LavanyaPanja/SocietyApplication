package com.society.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.society.model.Complaint;
import com.society.model.User;
import com.society.repository.ComplaintRepository;
import com.society.repository.UserRepository;

@Service
public class ComplaintService {
	
	 @Autowired
	    private ComplaintRepository complaintRepository;
	 @Autowired
	    private UserRepository userRepository;

	    public List<Complaint> getAllComplaints() {
	    	
	        return complaintRepository.findAll();
	    }

	    public Optional<Complaint> getComplaintById(Long id) {
	        return complaintRepository.findById(id);
	    }
	    public List<Complaint> getAllComplaintsWithUsers() {
	        return complaintRepository.findAllWithUser();
	    }
	    public Complaint saveComplaint(Long userId,Complaint complaint) {
	    	User user=userRepository.findById(userId).orElseThrow(() -> new RuntimeException("user not found"));
	    	complaint.setUser(user);
	        return complaintRepository.save(complaint);
	    }
	    public Complaint saveComplaint(Complaint complaint) {
	        return complaintRepository.save(complaint);
	    }
	    public void deleteComplaintById(Long id) {
	        complaintRepository.deleteById(id);
	    }
	    public List<Complaint> getComplaintsByUserId(Long userId) {
	        return complaintRepository.findByUserId(userId);
	    }
}
