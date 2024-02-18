package com.app.controller;

import com.app.dto.EmployeeDto;
import com.app.service.EmployeeService;
import com.app.service.impl.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    @Autowired
    private EmployeeServiceImpl employeeService;

    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto){
        EmployeeDto employee = employeeService.createEmployee(employeeDto);
        return new ResponseEntity<>(employee ,HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<EmployeeDto> findEmployeeById(@PathVariable(value = "id") Long employeeId){
       EmployeeDto employee = employeeService.findEmployeeById(employeeId);
       return ResponseEntity.ok(employee);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAllEmployees(){
        List<EmployeeDto> employees = employeeService.findAllEmployees();
        return ResponseEntity.ok(employees);
    }

    @PutMapping("{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable(name = "id") Long employeeId,
                                                      @RequestBody EmployeeDto updateEmployee){
        EmployeeDto employee = employeeService.updateEmployee(employeeId, updateEmployee);
        return ResponseEntity.ok(employee);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable(name = "id") Long employeeId){
        employeeService.deleteEmployee(employeeId);
        return ResponseEntity.ok("Delete done successfully");
    }

}
