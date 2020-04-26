package com.example.demo.Model;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "students")
public class Student {
	@Id
    private String id;
	
	@NotEmpty(message = "Name must not be empty")
    private String name;
	
    @NotEmpty(message = "Student Number must not be empty/null")
    private String studentNumber;
    
    @NotEmpty(message = "Email must not be empty")
    @Email(message = "Email should be a valid email")
    private String email;
    private List<String> courseList;
    
    @Max(value = 4, message = "GPA must be greater than 4")
    @Min(value = 0, message = "GPA must be greater than 0")
    private Float gpa;
    
	public Student(String id, String name, String studentNumber, String email, List<String> courseList, Float gpa) {
		super();
		this.id = id;
		this.name = name;
		this.studentNumber = studentNumber;
		this.email = email;
		this.courseList = courseList;
		this.gpa = gpa;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStudentNumber() {
		return studentNumber;
	}
	public void setStudentNumber(String studentNumber) {
		this.studentNumber = studentNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<String> getCourseList() {
		return courseList;
	}
	public void setCourseList(List<String> courseList) {
		this.courseList = courseList;
	}
	public Float getGpa() {
		return gpa;
	}
	public void setGpa(float gpa) {
		this.gpa = gpa;
	}
}
