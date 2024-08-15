package com.society.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.society.exception.InvalidPaymentException;
import com.society.exception.PaymentNotFoundException;
import com.society.model.Bill;
import com.society.model.Payment;
import com.society.service.BillService;
import com.society.service.PaymentService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users/payments")
@CrossOrigin(origins = "http://localhost:4200")  
public class PaymentController {

    @Autowired
    private PaymentService paymentService;
    
    @Autowired
    private BillService billService;


    @GetMapping
    public List<Payment> getAllPayments() {
        return paymentService.getAllPayments();
    }

//    @GetMapping("/{id}")
//    public Payment getPaymentById(@PathVariable Long id) {
//    	Payment payment = paymentService.getPaymentById(id);
//        if (payment == null) {
//            throw new PaymentNotFoundException("Payment not found with id: " + id);
//        }
//        return payment;
//    }
    @GetMapping("/getByUserID/{userId}")
    public List<Payment> getPaymentsByUserId(@PathVariable Long userId) {
       List<Bill> bills= billService.getBillsByUserId(userId) ;
       List<Payment> p=new ArrayList<>();
       for(Bill b:bills) {
    	 p.add ( b.getPayments());
       }
        return p;
    }


    @PostMapping("/{billId}")
    public Payment createPayment(@PathVariable Long billId, @RequestBody Payment payment) {
    	 if (payment.getAmount() <= 0) {
             throw new InvalidPaymentException("Payment amount must be greater than zero.");
         }
        
        return paymentService.createPayment(billId, payment);
    }
    
    @GetMapping("/{billId}")
    public List<Payment> getPaymentsByBillId(@PathVariable Long billId) {
       List<Payment> payments=paymentService.getPaymentsByBillId(billId);
        if (payments.isEmpty()) {
            throw new PaymentNotFoundException("No payments found for bill with id: " + billId);
        }
        return payments;
    }
  
    
}
