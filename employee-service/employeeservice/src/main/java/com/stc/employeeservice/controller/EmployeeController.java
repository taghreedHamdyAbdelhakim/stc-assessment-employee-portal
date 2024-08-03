package com.stc.employeeservice.controller;

import com.stc.employeeservice.dtos.EmployeeDto;

import com.stc.employeeservice.exception.ORBadRequestException;
import com.stc.employeeservice.exception.ORNotFoundException;
import com.stc.employeeservice.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;


@Tag(name = "Employee")
@RestController
@RequestMapping("/api/v1/employees")
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeController {


    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    @Operation(summary = "Get all employees", description = "Retrieve a list of all employees.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of employees retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "No Employees are found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public Page<EmployeeDto> getAllEmployees(   @RequestParam(value = "page" ,required = false , defaultValue = "1") Integer pageNumber,
                                                 @RequestParam(value= "size",required = false , defaultValue = "10") Integer pageSize,
                                                 @RequestParam(value = "sort", required = false) String sort,
                                                 @RequestParam(value = "dir", required = false) String sortDir) throws ORNotFoundException {
        Page<EmployeeDto> employeePage = employeeService.getAllEmployees(pageNumber, pageSize, sort, sortDir);
        if (employeePage.getTotalElements() == 0) throw new ORNotFoundException("No Employees are found");
        return employeePage;
    }


    @Operation(summary = "Get employee by id", description = "Get employee by id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get employee by id successfully"),
            @ApiResponse(responseCode = "404", description = "No Employee is found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{id}")
    public EmployeeDto getEmployeeById(@PathVariable Long id) throws ORNotFoundException {
        EmployeeDto employee = employeeService.getEmployeeById(id);
        if (employee == null) throw new ORNotFoundException("No employee is found");
        return employee ;
    }
    @Operation(summary = "create new employee", description = "create new employee.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "create new employee successfully"),
            @ApiResponse(responseCode = "404", description = "wrong employee body"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public EmployeeDto createEmployee(@RequestBody EmployeeDto employeeDto) {
        try {
            return employeeService.saveEmployee(employeeDto);
        }catch (Exception e) {
            throw new ORBadRequestException(e.getMessage());
        }
    }
    @Operation(summary = "Update employee by id", description = "Update employee by id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "create new employee successfully"),
            @ApiResponse(responseCode = "404", description = "wrong employee body"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping("/{id}")
    public EmployeeDto updateEmployee(@PathVariable Long id, @RequestBody EmployeeDto employeeDto) {
        employeeDto.setId(id);
        try{
        return employeeService.saveEmployee(employeeDto);
        }catch (Exception e) {
            throw new ORBadRequestException(e.getMessage());
        }
    }
    @Operation(summary = "Delete employee by id", description = "Delete employee by id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "create new employee successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
    }
}
