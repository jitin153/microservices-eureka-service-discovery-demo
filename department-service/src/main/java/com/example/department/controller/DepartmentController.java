package com.example.department.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.department.entity.Department;
import com.example.department.service.DepartmentService;

@RestController
public class DepartmentController {

	private static final Logger LOG = LoggerFactory.getLogger(DepartmentController.class);

	@Autowired
	private DepartmentService departmentService;

	@PostMapping("/dept")
	public Department saveDepartment(@RequestBody Department department) {
		LOG.info("Inside DepartmentController :: saveDepartment");
		return departmentService.saveDepartment(department);
	}

	@GetMapping("/dept/{id}")
	public Department getDepartmentById(@PathVariable("id") Long id) {
		LOG.info("Inside DepartmentController :: getDepartmentById");
		return departmentService.getDepartmentById(id);
	}
}
