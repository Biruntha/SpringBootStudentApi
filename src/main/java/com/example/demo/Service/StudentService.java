package com.example.demo.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.DAO.StudentDao;
import com.example.demo.Exception.RecordNotFoundException;
import com.example.demo.Model.Student;

@Service
public class StudentService {
	
	@Autowired
	StudentDao studentDao;

	public ResponseEntity<List<Student>> getAllStudents() {
	    List<Student> students = new ArrayList<Student>();
	    studentDao.findAll().forEach(students::add);
	
	    if (students.isEmpty()) {
	      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    }
	    return new ResponseEntity<>(students, HttpStatus.OK);
	}

	public ResponseEntity<Student> findByStudentNumber(String studentNumber) {
		Optional<Student> studentData = studentDao.findByStudentNumber(studentNumber);

		if (studentData.isPresent()) {
			return new ResponseEntity<>(studentData.get(), HttpStatus.OK);
		} else {
			throw new RecordNotFoundException("Invalid Student Number : " + studentNumber);
		}
	}

	public ResponseEntity<Student> findByEmail(String email) {
		Optional<Student> studentData = studentDao.findByEmail(email);

		if (studentData.isPresent()) {
			return new ResponseEntity<>(studentData.get(), HttpStatus.OK);
		} else {
			throw new RecordNotFoundException("Invalid Email : " + email);
		}
	}

	public ResponseEntity<List<Student>> findAllByOrderByGpaDesc() {
	    List<Student> students = new ArrayList<Student>();
	    studentDao.findAllByOrderByGpa().forEach(students::add);
	
	    if (students.isEmpty()) {
	      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    }
	    return new ResponseEntity<>(students, HttpStatus.OK);
	}

	public ResponseEntity<Student> createStudent(Student student) {
		Student studentNew = studentDao.save(new Student(student.getId(), student.getName(), 
				student.getStudentNumber(), student.getEmail(), student.getCourseList(), student.getGpa()));
	    return new ResponseEntity<>(studentNew, HttpStatus.CREATED);
	}

	public ResponseEntity<HttpStatus> deleteStudent(String studentNumber) {
		Optional<Student> student = studentDao.findByStudentNumber(studentNumber);
		if (student.isPresent()) {
			studentDao.delete(student.get());
		    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			throw new RecordNotFoundException("Invalid Student Number : " + studentNumber);
		}
	}
}
