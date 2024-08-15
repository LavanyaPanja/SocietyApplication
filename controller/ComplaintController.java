package com.society.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.*;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.society.exception.ComplaintNotFoundException;
import com.society.exception.InvalidComplaintException;
import com.society.model.Complaint;
import com.society.service.ComplaintService;

@RestController
@RequestMapping("/users/complaints")
@CrossOrigin(origins = "http://localhost:4200")
public class ComplaintController {
	@Autowired
	private ComplaintService complaintService;
	@GetMapping
    public List<Complaint> getAllComplaints() {
	return	 complaintService.getAllComplaints(); // Assuming this returns List<Complaint>
		 

	}
	 
       
    

    @GetMapping("/{id}")
    public Optional<Complaint> getComplaintById(@PathVariable Long id) {
    	 Optional<Complaint> complaint = complaintService.getComplaintById(id);
         if (complaint.isEmpty()) {
             throw new ComplaintNotFoundException("Complaint not found with id: " + id);
         }
//         return ResponseEntity.ok(complaint.get());
        return complaintService.getComplaintById(id);
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Complaint>> getComplaintsByUserId(@PathVariable Long userId) {
        List<Complaint> complaints = complaintService.getComplaintsByUserId(userId);
        return new ResponseEntity<>(complaints, HttpStatus.OK);
    }
   
    @PostMapping("/{userId}")
    public Complaint addComplaint(@PathVariable Long userId,@RequestBody Complaint complaint) {
    	 validateComplaint(complaint);
         
        return complaintService.saveComplaint(userId,complaint);
    }
    @PutMapping("/{id}")
    public Complaint updateComplaint(@PathVariable Long id, @RequestBody Complaint complaint) {
    	
        complaint.setId(id);
        complaint.setUser(complaint.getUser());
        return complaintService.saveComplaint(complaint);
    }

    @DeleteMapping("/{id}")
    public void deleteComplaint(@PathVariable Long id) {
    	Optional<Complaint> complaint = complaintService.getComplaintById(id);
        if (complaint.isEmpty()) {
            throw new ComplaintNotFoundException("Complaint not found with id to delete: " + id);
        }
        complaintService.deleteComplaintById(id);
    }
    private void validateComplaint(Complaint complaint) {
        if (complaint.getDescription() == null || complaint.getDescription().isEmpty()) {
            throw new InvalidComplaintException("Complaint description cannot be empty");
        }
}
}
