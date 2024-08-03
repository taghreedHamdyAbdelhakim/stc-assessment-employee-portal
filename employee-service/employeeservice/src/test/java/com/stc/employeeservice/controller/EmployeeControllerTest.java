package com.stc.employeeservice.controller;

import com.stc.employeeservice.dtos.EmployeeDto;
import com.stc.employeeservice.exception.ORBadRequestException;
import com.stc.employeeservice.exception.ORNotFoundException;
import com.stc.employeeservice.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeControllerTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    @Test
    void testGetAllEmployees() throws ORNotFoundException {

        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(1L);
        employeeDto.setFirstName("taghreed");
        employeeDto.setLastName("hamdy");
        employeeDto.setSalary(1000);
        EmployeeDto employeeDto2 = new EmployeeDto();
        employeeDto2.setId(2L);
        employeeDto2.setFirstName("fn");
        employeeDto2.setLastName("ln");
        employeeDto2.setSalary(2000);
        Page<EmployeeDto> employeeDtoPage = new PageImpl<>(List.of(employeeDto, employeeDto2));
        when(employeeService.getAllEmployees(1, 2, "salary", "desc")).thenReturn(employeeDtoPage);

        Page<EmployeeDto> actualEmployeeDtoPage = employeeController.getAllEmployees(1, 2, "salary", "desc");
        verify(employeeService, times(1)).getAllEmployees(1, 2, "salary", "desc");
        assertNotNull(actualEmployeeDtoPage);
        assertEquals(actualEmployeeDtoPage.getTotalElements(), actualEmployeeDtoPage.getTotalElements());
    }

    @Test
    void testGetAllEmployeesNotFound() throws ORNotFoundException {

        Page<EmployeeDto> employeeDtoPage = new PageImpl<>(new ArrayList<>());
        when(employeeService.getAllEmployees(1, 2, "salary", "desc")).thenReturn(employeeDtoPage);


        ORNotFoundException employeesNotFoundExcepthion = assertThrows(ORNotFoundException.class, () -> {
            employeeController.getAllEmployees(1, 2, "salary", "desc");
        });

        verify(employeeService, times(1)).getAllEmployees(1, 2, "salary", "desc");
        assertEquals(employeesNotFoundExcepthion.getMessage(), "No Employees are found");
    }

    @Test
    void testCreateEmployee() {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setFirstName("taghreed");
        employeeDto.setLastName("hamdy");
        employeeDto.setSalary(1000);
        when(employeeService.saveEmployee(employeeDto)).thenReturn(employeeDto);
        EmployeeDto actualEmployeeDto = employeeController.createEmployee(employeeDto);
        verify(employeeService, times(1)).saveEmployee(employeeDto);
        assertNotNull(actualEmployeeDto);
        assertEquals(actualEmployeeDto.getId(), employeeDto.getId());

    }

    @Test
    void testCreateEmployeeBadRequest() {
        EmployeeDto employeeDto = new EmployeeDto();

        when(employeeService.saveEmployee(employeeDto)).thenThrow(new ORBadRequestException("wrong employee body"));
        ORBadRequestException employeeBadRequestExcepthion = assertThrows(ORBadRequestException.class, () -> {
            employeeController.createEmployee(employeeDto);
        });


        verify(employeeService, times(1)).saveEmployee(employeeDto);

        assertEquals(employeeBadRequestExcepthion.getMessage(), "wrong employee body");

    }

    @Test
    void testUpdateEmployee() {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(1L);
        employeeDto.setFirstName("taghreed");
        employeeDto.setLastName("hamdy");
        employeeDto.setSalary(1000);
        when(employeeService.saveEmployee(employeeDto)).thenReturn(employeeDto);
        EmployeeDto actualEmployeeDto = employeeController.updateEmployee(1L, employeeDto);
        verify(employeeService, times(1)).saveEmployee(employeeDto);
        assertNotNull(actualEmployeeDto);
        assertEquals(actualEmployeeDto.getId(), employeeDto.getId());

    }

    @Test
    void testUpdateEmployeeBadRequest() {
        EmployeeDto employeeDto = new EmployeeDto();

        when(employeeService.saveEmployee(employeeDto)).thenThrow(new ORBadRequestException("wrong employee body"));
        ORBadRequestException employeeBadRequestExcepthion = assertThrows(ORBadRequestException.class, () -> {
            employeeController.updateEmployee(1L, employeeDto);
        });

        verify(employeeService, times(1)).saveEmployee(employeeDto);

        assertEquals(employeeBadRequestExcepthion.getMessage(), "wrong employee body");

    }

    @Test
    void testGetEmployeeById() throws ORNotFoundException {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(1L);
        employeeDto.setFirstName("taghreed");
        employeeDto.setLastName("hamdy");
        employeeDto.setSalary(1000);
        when(employeeService.getEmployeeById(employeeDto.getId())).thenReturn(employeeDto);

        EmployeeDto actualEmployeeDto = employeeController.getEmployeeById(employeeDto.getId());


        verify(employeeService, times(1)).getEmployeeById(employeeDto.getId());
        assertNotNull(actualEmployeeDto);
        assertEquals(actualEmployeeDto.getId(), employeeDto.getId());


    }

    @Test
    void testGetEmployeeByIdNotFound() throws ORNotFoundException {

        when(employeeService.getEmployeeById(2L)).thenReturn(null);
        ORNotFoundException employeeNotFoundExcepthion = assertThrows(ORNotFoundException.class, () -> {
            employeeController.getEmployeeById(2L);
        });

        verify(employeeService, times(1)).getEmployeeById(2L);
        assertEquals(employeeNotFoundExcepthion.getMessage(), "No employee is found");


    }

    @Test
    void testDeleteEmployee() {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(1L);
        employeeDto.setFirstName("taghreed");
        employeeDto.setLastName("hamdy");
        employeeDto.setSalary(1000);
        employeeController.deleteEmployee(1L);
        verify(employeeService, times(1)).deleteEmployee(1L);

    }


}
