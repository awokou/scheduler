package com.server.scheduler.services.impl;

import com.server.scheduler.dto.EmployeeDTO;
import com.server.scheduler.exception.ResourceNotFoundException;
import com.server.scheduler.mapper.EmployeeMapper;
import com.server.scheduler.model.Employee;
import com.server.scheduler.repository.EmployeeRepository;
import com.server.scheduler.services.EmployeeService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    @Override
    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {
        checkIfEmployeeExists(employeeDTO);
        Employee employee = employeeMapper.mapToEmployee(employeeDTO);
        if (employee == null) {
            throw new IllegalArgumentException("employee must not be null");
        }

        Employee savedEmployee = employeeRepository.save(employee);
        return employeeMapper.mapToEmployeeDTO(savedEmployee);
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        return employeeRepository.findAll()
                .stream().map(employeeMapper::mapToEmployeeDTO)
                .toList();
    }

    @Override
    public EmployeeDTO getEmployeeById(long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee is not exists with given id : "+id));
        return employeeMapper.mapToEmployeeDTO(employee);
    }

    @Override
    public EmployeeDTO updateEmployee(EmployeeDTO employeeDTO, long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Employee is not exists with given id:" +id));

        if (employeeDTO == null){
            throw new IllegalArgumentException("employeeDTO must not be null");
        }

        employee.setLastName(employeeDTO.getLastName());
        employee.setFirstName(employeeDTO.getFirstName());
        employee.setEmail(employeeDTO.getEmail());
        employee.setDateBirth(employeeDTO.getDateBirth());
        employee.setPhone(employeeDTO.getPhone());

        Employee updateEmployee = employeeRepository.save(employee);

        return employeeMapper.mapToEmployeeDTO(updateEmployee);
    }

    @Override
    public List<EmployeeDTO> getEmployeeByEmail(String email) {
        return employeeRepository.findByEmail(email)
                .stream().map(employeeMapper::mapToEmployeeDTO)
                .toList();
    }

    @Override
    public void deleteEmployeeById(long id) {
        employeeRepository.deleteById(id);
    }

    /**
     * check if employee already exist in database before save it.
     * @param employeeDTO The new employee information request
     */
    private void checkIfEmployeeExists(EmployeeDTO employeeDTO) {
        Boolean employeeExists = employeeRepository.existsByEmail(employeeDTO.getEmail());
        if(Boolean.TRUE.equals(employeeExists)) {
            throw new ResourceNotFoundException(String.format("Email  %s is already in use", employeeDTO.getEmail()));
        }
    }
}
