package com.example.department.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.department.entity.Department;
import com.example.department.excpetion.RecordNotFoundException;
import com.example.department.repository.DepartmentRepository;

@Service
public class DepartmentService {
	
	private static final Logger LOG = LoggerFactory.getLogger(DepartmentService.class);
	
	@Autowired
	private DepartmentRepository departmentRepository;

	public Department saveDepartment(Department department) {
		LOG.info("Inside DepartmentService :: saveDepartment");
		return departmentRepository.save(department);
	}

	public Department getDepartmentById(Long id) {
		LOG.info("Inside DepartmentService :: getDepartmentById");
		Optional<Department> result = departmentRepository.findById(id);
		if (result.isPresent()) {
			return result.get();
		}
		LOG.error("Department doesn't exist for the given id.");
		throw new RecordNotFoundException("Department doesn't exist for the given id.");
	}

}
