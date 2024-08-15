package com.society.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.society.model.Defaulter;
import com.society.service.DefaulterService;

import java.util.List;

//@RestController

@Controller
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:4200")
public class DefaulterController {

    @Autowired
    private DefaulterService defaulterService;

    @PostMapping("/defaulter")
    public ResponseEntity<?> postDefualter(@RequestBody Defaulter defaulter){

        return defaulterService.postDefaulter(defaulter);
    }


    @GetMapping("/getAllDefaulters")
    public ResponseEntity<?> getAllDefaulters() {
        return defaulterService.getAllDefaulters();
    }

    @GetMapping("/defaulter/{id}")
    public ResponseEntity<?> getDefaulterById(@PathVariable Long id) {
//        Defualter defualter = defualterService.getDefualterById(id);
        return defaulterService.getDefaulterById(id);

//        if (defualter == null) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(defualter);
    }

    @PutMapping("/defaulter/{id}")
    public ResponseEntity<?> updateDefaulter(@PathVariable Long id, @RequestBody Defaulter defaulter){
//        Defualter existingdefualter=defualterService.getDefualterById(id);
//        if(existingdefualter==null){
//            return ResponseEntity.notFound().build();
//        }
//        existingdefualter.setFamilyName(defualter.getFamilyName());
//        existingdefualter.setAmountDue(defualter.getAmountDue());
//        existingdefualter.setDueDate(defualter.getDueDate());
//        existingdefualter.setFine(defualter.getFine());
//        Defualter updateddefualter=defualterService.updateDefualter(existingdefualter);
//        return ResponseEntity.ok(updateddefualter);

        return defaulterService.updateDefaulter(id,defaulter);
    }


    @DeleteMapping("/defaulter/{id}")
    public ResponseEntity<?> deleteDefaulter(@PathVariable Long id) {
        return defaulterService.deleteDefaulter(id);
    }

}
