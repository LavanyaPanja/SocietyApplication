package com.society.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import com.society.exception.BillNotFoundException;
import com.society.exception.InvalidBillException;
import com.society.model.Bill;
import com.society.model.Complaint;
import com.society.service.BillService;
import com.society.service.UserService;

@RestController
@RequestMapping("/users/bills")
@CrossOrigin(origins = "http://localhost:4200")
 
public class BillController {

    @Autowired
    private BillService billService;
    @Autowired
    private UserService userService;

    @GetMapping
    public List<Bill> getAllBills() {
        return billService.getAllBills();
    }

    @GetMapping("/{id}")
    public Bill getBillById(@PathVariable Long id) {
    	Bill bill=billService.getBillById(id);
    	 if (bill == null) {
             throw new BillNotFoundException("Bill not found with id: " + id);
         }
        return bill;
    }

    @PostMapping
    public Bill saveBill(@RequestBody Bill bill) {
    	 if (bill.getAmount() <= 0) {
             throw new InvalidBillException("Bill amount must be greater than zero");
         }
    	 
    	
        return billService.saveBill(bill);
    }
    @PostMapping("/{userId}")
    public Bill addbill(@PathVariable Long userId,@RequestBody Bill bill) {
        return billService.saveBill(userId,bill);
    }

    @GetMapping("/paid/{userId}")
    public List<Bill> getPaidBill(@PathVariable Long userId) {
    	
    	return billService.getPaidBill(userId);
    	
    }
    
    @GetMapping("/unpaid/{userId}")
    public List<Bill> getUnPaidBill(@PathVariable Long userId) {
    	
    	return billService.getUnPaidBill(userId);
    	
    }
    
    @GetMapping("/{userId}")
    public ResponseEntity<Boolean> checkUserIdExists(@PathVariable Long userId) {
        boolean exists = userService.checkUserIdExists(userId);
        return ResponseEntity.ok(exists);
    }
    @GetMapping("/getByUserID/{userId}")
    public ResponseEntity<List<Bill>> getBillsByUserId(@PathVariable Long userId) {
        List<Bill> bills = billService.getBillsByUserId(userId);
        return ResponseEntity.ok().body(bills);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Bill> updateBill(@PathVariable Long id, @RequestBody Bill bill) {
        Bill updatedBill = billService.updateBill(id, bill);
        return ResponseEntity.ok(updatedBill);
    }
}
