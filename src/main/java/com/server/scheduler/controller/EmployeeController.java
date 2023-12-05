package com.server.scheduler.controller;

import java.util.List;

import com.server.scheduler.dto.EmployeeDTO;
import com.server.scheduler.services.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Tag(name = "Employee", description = "Employee APIs")
@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping("/employees")
    @Operation(
            summary = "Add new employee",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    )
            }
    )
    public ResponseEntity<EmployeeDTO> saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        return ResponseEntity.ok(employeeService.saveEmployee(employeeDTO));
    }

    @GetMapping("/employees")
    @Operation(
            summary = "List of employee",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    )
            }
    )
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @GetMapping("/employees/{id}")
    @Operation(
            summary = "List of employee by Id",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    )
            }
    )
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable("id") long employeeId) {
        return ResponseEntity.ok(employeeService.getEmployeeById(employeeId));
    }


    @PutMapping("/employees/{id}")
    @Operation(
            summary = "Update a employee by Id",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    )
            }
    )
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable("id") long id,@RequestBody EmployeeDTO employeeDTO) {
        return ResponseEntity.ok(employeeService.updateEmployee(employeeDTO, id));
    }

    @GetMapping("/employees/searchByEmail")
    @Operation(
            summary = "Search by email",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    )
            }
    )
    public List<EmployeeDTO> getEmployeeByEmail(@RequestParam String email){
        return employeeService.getEmployeeByEmail(email);
    }

    @DeleteMapping("/employees/{id}")
    @Operation(
            summary = "Delete a employee by Id",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    )
            }
    )
    public ResponseEntity<Void> deleteEmployeeById(@PathVariable("id") long id) {
        employeeService.deleteEmployeeById(id);
        return ResponseEntity.noContent().build();
    }
}
