package com.example.student.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.student.entity.Student;
import com.example.student.entity.StudentDepartmentWrapper;
import com.example.student.service.StudentService;

@RestController
public class StudentController {

	private static final Logger LOG = LoggerFactory.getLogger(StudentController.class);
	
	@Autowired
	private StudentService studentService;

	@PostMapping("/student")
	public Student saveStudent(@RequestBody Student student) {
		LOG.info("Inside StudentController :: saveStudent");
		return studentService.saveStudent(student);
	}

	/*@GetMapping("student/{id}")
	public Student getStudentById(@PathVariable("id") Long id) {
		return studentService.getStudentById(id);
	}*/
	
	@GetMapping("student/{id}")
	public StudentDepartmentWrapper getStudentById(@PathVariable("id") Long id) {
		LOG.info("Inside StudentController :: getStudentById");
		return studentService.getStudentById(id);
	}
}
