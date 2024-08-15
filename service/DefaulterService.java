package com.society.service;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.society.model.Defaulter;
import com.society.repository.DefaulterRepository;

import java.util.List;
@Service
//@RequiredArgsConstructor
public class DefaulterService {
@Autowired
    private DefaulterRepository defaulterRepository;
    public ResponseEntity<?> postDefaulter(Defaulter defaulter) {
        try {
            System.out.println(defaulter.getAmountDue());
            System.out.println(defaulter.getFine());
            System.out.println(defaulter.getDueDate());
            System.out.println(defaulter.getFamilyName());
            Defaulter save = defaulterRepository.save(defaulter);
            return  new ResponseEntity<>(save, HttpStatus.OK);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return new ResponseEntity<>("Not Saved", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> getAllDefaulters() {
        try {
            List<Defaulter> defaulters = defaulterRepository.findAll();
            return new ResponseEntity<>(defaulters, HttpStatus.OK);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return new ResponseEntity<>("Unable to retrieve defaulters", HttpStatus.BAD_REQUEST);
        }
    }

        public Defaulter updateDefaulter(Defaulter defaulter){
            return defaulterRepository.save(defaulter);
        }
    public ResponseEntity<?> getDefaulterById(Long id) {
        try {
            Defaulter defaulter = defaulterRepository.findById(id).orElse(null);
            if (defaulter != null) {
                return new ResponseEntity<>(defaulter, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Defaulter not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return new ResponseEntity<>("Unable to retrieve defaulter", HttpStatus.BAD_REQUEST);
        }
    }
    public ResponseEntity<?> updateDefaulter(Long id,Defaulter defaulter) {
        try {
            Defaulter existingdefaulter = defaulterRepository.findById(id).orElse(null);
            if(existingdefaulter==null){
                return ResponseEntity.notFound().build();
             }else{
                existingdefaulter.setFamilyName(defaulter.getFamilyName());
                existingdefaulter.setAmountDue(defaulter.getAmountDue());
                existingdefaulter.setDueDate(defaulter.getDueDate());
                existingdefaulter.setFine(defaulter.getFine());
                Defaulter updateddefaulter=updateDefaulter(existingdefaulter);
                return new ResponseEntity<>(updateddefaulter, HttpStatus.OK);
            }
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return new ResponseEntity<>("Unable to retrieve defaulter", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> deleteDefaulter(Long id){
        try {
          defaulterRepository.deleteById(id);
         return new ResponseEntity<>("OK", HttpStatus.OK);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
 
