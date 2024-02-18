package com.app.service.impl;
import com.app.dto.EmployeeDto;
import com.app.entity.Employee;
import com.app.exeption.ResourceNotFoundException;
import com.app.mapper.EmployeeMapper;
import com.app.repository.EmployeeRepository;
import com.app.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.MapToEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.MapToEMployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto findEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() ->
                new ResourceNotFoundException("Employee not found with id: " + employeeId));
        return EmployeeMapper.MapToEMployeeDto(employee);
    }

    @Override
    public List<EmployeeDto> findAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map(EmployeeMapper::MapToEMployeeDto).collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updateEmployee) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() ->
                new ResourceNotFoundException("Employee not found with id: " + employeeId));
        if(updateEmployee.getFirstName() != null){
            employee.setFirstName(updateEmployee.getFirstName());
        }
        if(updateEmployee.getLastName() != null){
            employee.setLastName(updateEmployee.getLastName());
        }
        if(updateEmployee.getEmail() != null){
            employee.setEmail(updateEmployee.getEmail());
        }
        if(updateEmployee.getVacancy() != null){
            employee.setVacancy(updateEmployee.getVacancy());
        }
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.MapToEMployeeDto(savedEmployee);
    }

    @Override
    public void deleteEmployee(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() ->
                new ResourceNotFoundException("Employee not found with id: " + employeeId));
        employeeRepository.delete(employee);
    }


}
