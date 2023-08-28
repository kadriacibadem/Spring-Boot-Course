package com.luv2code.springboot.thymeleafdemo.controller;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import com.luv2code.springboot.thymeleafdemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
	private EmployeeService employeeService;
	// load employee data
	@Autowired
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}


	// add mapping for "/list"

	@GetMapping("/list")
	public String listEmployees(Model theModel) {

		List<Employee> theEmployees = employeeService.findAllByOrderByLastNameAsc();
		// add to the spring model
		theModel.addAttribute("employees", theEmployees);

		return "/employees/list-employees";
	}

	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		// create model attribute to bind form data
		Employee theEmployee = new Employee();
		// attribute ismi html sayfasındaki ile aynı olmalı
		theModel.addAttribute("employee", theEmployee);
		return "/employees/employee-form";
	}

	@PostMapping("/save")
	public String saveEmployee(@ModelAttribute("employee") Employee theEmployee) {
		// model attribute ile gelen employee nesnesini save ediyoruz
		// save the employee
		employeeService.save(theEmployee);
		// use a redirect to prevent duplicate submissions
		return "redirect:/employees/list";
	}

	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable("id")String id,Model theModel){
		Employee employee;
		employee = employeeService.findById(Integer.parseInt(id));
		theModel.addAttribute("employee",employee);
		return "/employees/employee-update-form";
	}


	@GetMapping("/delete/{id}")
	public String deleteEmployee(@PathVariable("id") String id) {
		// delete the employee
		employeeService.deleteById(Integer.parseInt(id));
		// use a redirect to /employees/list
		return "redirect:/employees/list";
	}



}









