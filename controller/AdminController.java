package com.society.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.society.exception.ComplaintNotFoundException;
import com.society.model.Bill;
import com.society.model.Complaint;
import com.society.model.contactForm;
import com.society.service.BillService;
import com.society.service.ComplaintService;
import com.society.service.ContactService;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:4200")
public class AdminController {

	
	@Autowired
	private ComplaintService complaintService;
	@Autowired
	private BillService billService;
	
	@Autowired
	private ContactService contactService;
	
	@GetMapping("/complaints")
    public List<Complaint> getAllComplaints() {
		
		
			return complaintService.getAllComplaints(); // Assuming this returns List<Complaint>
	}
    
	@GetMapping("/withUsers")
    public List<Complaint> getAllComplaintsWithUsers() {
        return complaintService.getAllComplaintsWithUsers();
    }
    @GetMapping("/complaints/{id}")
    public Optional<Complaint> getComplaintById(@PathVariable Long id) {
    	 Optional<Complaint> complaint = complaintService.getComplaintById(id);
         if (complaint.isEmpty()) {
             throw new ComplaintNotFoundException("Complaint not found with id: " + id);
         }
//         return ResponseEntity.ok(complaint.get());
        return complaint;
    }
   
    
    @PostMapping("/addbill/{userId}")
    public Bill addbill(@PathVariable Long userId,@RequestBody Bill bill) {
        return billService.saveBill(userId,bill);
    }
  @GetMapping("/allcontacts")
  public ResponseEntity<List<contactForm>> getAllContacts() {
      List<contactForm> contacts = contactService.getAllContacts();
      return ResponseEntity.status(HttpStatus.OK).body(contacts);
    }
}

