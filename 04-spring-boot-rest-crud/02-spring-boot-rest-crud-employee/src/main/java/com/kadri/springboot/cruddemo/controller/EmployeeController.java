package com.kadri.springboot.cruddemo.controller;

import com.kadri.springboot.cruddemo.entity.Employee;
import com.kadri.springboot.cruddemo.errors.EmployeeNotFoundException;
import com.kadri.springboot.cruddemo.service.EmployeeServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    private EmployeeServiceImpl employeeService;

    public EmployeeController (EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    @GetMapping("/employees/{id}")
    public Employee findById(@PathVariable int id) {
        Employee employee = employeeService.findById(id);
        if(employee == null) {
            throw new EmployeeNotFoundException("Employee with id " + id + " not found");
        }else{
            return employee;
        }
    }

    @PostMapping("/employees")
    public Employee save(Employee employee) {
        if(employeeService.findById(employee.getId()) == null) {
            return employeeService.save(employee);
        }else{
            throw new RuntimeException("Employee with id " + employee.getId() + " already exists");
        }
        /*
        * @RequestBody Employee employee
        * employee.setId(0);
        * Employee dbEmployee = employeeService.save(employee);
        * return dbEmployee;
        * */
    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee) {
        Employee dbEmployee = employeeService.findById(employee.getId());
        if(dbEmployee == null) {
            throw new RuntimeException("Employee with id " + employee.getId() + " not found");
        }else{
            return employeeService.save(employee);
        }
    }

    @DeleteMapping("/employees/{id}")
    public void deleteById(@PathVariable int id) {
        if (employeeService.findById(id) == null) {
            throw new RuntimeException("Employee with id " + id + " not found");
        } else {
            employeeService.deleteById(id);
        }
    }
}
