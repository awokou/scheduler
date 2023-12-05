package com.server.scheduler.services;

import com.server.scheduler.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeService {

    EmployeeDTO saveEmployee(EmployeeDTO employeeDTO);
    List<EmployeeDTO> getAllEmployees();
    EmployeeDTO getEmployeeById(long id);
    EmployeeDTO updateEmployee(EmployeeDTO employeeDTO, long id);
    List<EmployeeDTO> getEmployeeByEmail(String email);
    void deleteEmployeeById(long id);
}
