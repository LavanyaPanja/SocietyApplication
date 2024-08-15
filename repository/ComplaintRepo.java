package com.society.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.society.model.Complaint;

@Repository
public interface ComplaintRepository extends JpaRepository<Complaint, Long>{
	List<Complaint> findByUserId(Long userId);
	
	@Query("SELECT c FROM Complaint c JOIN FETCH c.user")
    List<Complaint> findAllWithUser();
}
