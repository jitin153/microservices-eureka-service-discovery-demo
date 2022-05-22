package com.example.student.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.student.entity.Department;
import com.example.student.entity.Student;
import com.example.student.entity.StudentDepartmentWrapper;
import com.example.student.exception.RecordNotFoundException;
import com.example.student.repository.StudentRepository;

@Service
public class StudentService {
	
	private static final Logger LOG = LoggerFactory.getLogger(StudentService.class);
	
	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private RestTemplate restTemplate;

	@Value("${department-service.base-url}")
	private String deptServiceBaseUrl;

	public Student saveStudent(Student student) {
		LOG.info("Inside StudentService :: saveStudent");
		return studentRepository.save(student);
	}

	/*
	 * public Student getStudentById(Long id) { Optional<Student> result =
	 * studentRepository.findById(id); if(result.isPresent()) { return result.get();
	 * } throw new
	 * RecordNotFoundException("Student doesn't exist for the given id."); }
	 */

	public StudentDepartmentWrapper getStudentById(Long id) {
		LOG.info("Inside StudentService :: getStudentById");
		StudentDepartmentWrapper studentDepartmentWrapper = new StudentDepartmentWrapper();
		Optional<Student> result = studentRepository.findById(id);
		if (result.isPresent()) {
			Student student = result.get();
			studentDepartmentWrapper.setStudent(student);
			Department dept = restTemplate.getForObject(deptServiceBaseUrl + "/dept/" + student.getDepartmentId(),
					Department.class);
			studentDepartmentWrapper.setDepartment(dept);
			return studentDepartmentWrapper;
		}
		LOG.error("Student doesn't exist for the given id.");
		throw new RecordNotFoundException("Student doesn't exist for the given id.");
	}
}
