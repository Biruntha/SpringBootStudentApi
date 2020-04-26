package com.example.demo.DAO;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Model.Student;

@Repository
public interface StudentDao extends MongoRepository<Student, String> {
	Optional<Student> findByStudentNumber(String studentNumber);

	Optional<Student> findByEmail(String email);
    
    //sorts students according to their GPA.
    List<Student> findAllByOrderByGpa();
}
