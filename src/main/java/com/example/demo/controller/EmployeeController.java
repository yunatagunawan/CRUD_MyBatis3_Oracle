package com.example.demo.controller;

import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> getAll() {
        return employeeService.findAll();
    }

    @GetMapping("/{id}")
    public Employee getById(@PathVariable Long id) {
        return employeeService.findById(id);
    }

    @PostMapping
    public String create(@RequestBody Employee emp) {
        employeeService.insert(emp);
        return "Created";
    }

    @PutMapping
    public String update(@RequestBody Employee emp) {
        employeeService.update(emp);
        return "Updated";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        employeeService.delete(id);
        return "Deleted";
    }

    @DeleteMapping
    public String truncate() {
        employeeService.truncate();
        return "Truncated";
    }
}
