package com.society.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.society.model.Invoice;
import com.society.repository.InvoiceRepository;

import java.io.IOException;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@RestController
@RequestMapping ("/admin/invoice")
@CrossOrigin(origins = "http://localhost:4200") 
public class InvoiceController {
    @Autowired
    private InvoiceRepository invoiceRepository;

    @PostMapping
    public ResponseEntity <Invoice> createNewInvoice(@RequestParam String discription,@RequestParam("displayPic")MultipartFile displayPic) throws IOException {
    	Invoice invoice = new Invoice();
        invoice.setDiscription(discription);
        invoice.setDisplayPic(displayPic.getBytes());
        invoiceRepository.save(invoice);
        invoice.setDisplayPic(null);
        return ResponseEntity.ok(invoice);
    }

    @GetMapping
    public ResponseEntity<List<Invoice>>getAllInvoice(){

        List<Invoice> invoiceList=invoiceRepository.findAll();
        return ResponseEntity.ok(invoiceList);

    }
}
