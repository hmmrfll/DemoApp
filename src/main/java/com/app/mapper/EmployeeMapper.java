package com.app.mapper;

import com.app.dto.EmployeeDto;
import com.app.entity.Employee;

public class EmployeeMapper {
    public static EmployeeDto MapToEMployeeDto(Employee employee){
        return new EmployeeDto(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getVacancy()
        );
    }

    public static Employee MapToEmployee(EmployeeDto employeeDto){
        return new Employee(
                employeeDto.getId(),
                employeeDto.getFirstName(),
                employeeDto.getLastName(),
                employeeDto.getEmail(),
                employeeDto.getVacancy()
        );
    }
}
