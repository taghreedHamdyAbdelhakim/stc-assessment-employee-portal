package com.stc.employeeservice.service;

import com.stc.employeeservice.dtos.EmployeeDto;
import com.stc.employeeservice.entity.Employee;
import com.stc.employeeservice.mapper.EmployeeMapper;
import com.stc.employeeservice.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;


import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
 class EmployeeServiceTest {
    @Mock
    private EmployeeRepository employeeRepository;
    @Mock
    private EmployeeMapper employeeMapper;
    @InjectMocks
    private EmployeeService employeeService;
    @Test
    void testGetAllEmployees(){

        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(1L);
        employeeDto.setFirstName("taghreed");
        employeeDto.setLastName("hamdy");
        employeeDto.setSalary(1000);
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setFirstName("taghreed");
        employee.setLastName("hamdy");
        employee.setSalary(1000);

        Page<EmployeeDto> employeeDtoPage = new PageImpl<>(List.of(employeeDto));
        Page<Employee> employeePage = new PageImpl<>(List.of(employee));
        Pageable pageable = PageRequest.of(0, 2, Sort.Direction.DESC,"salary");
        when(employeeRepository.findAll(pageable)).thenReturn(employeePage);
        when(employeeMapper.employeeEntityPageToEmployeeDtoPage(employeePage)).thenReturn(employeeDtoPage);
        Page<EmployeeDto> actualEmployeePage = employeeService.getAllEmployees(1, 2, "salary", "desc");
        verify(employeeMapper, times(1)).employeeEntityPageToEmployeeDtoPage(employeePage);
        assertNotNull(actualEmployeePage);
    }
    @Test
    void testGetEmployeeById(){

        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(1L);
        employeeDto.setFirstName("taghreed");
        employeeDto.setLastName("hamdy");
        employeeDto.setSalary(1000);
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setFirstName("taghreed");
        employee.setLastName("hamdy");
        employee.setSalary(1000);

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
        when(employeeMapper.employeeEntityToEmployeeDto(employee)).thenReturn(employeeDto);
        EmployeeDto actualEmployee = employeeService.getEmployeeById(1L);
        verify(employeeMapper, times(1)).employeeEntityToEmployeeDto(employee);
        assertNotNull(actualEmployee);
        assertEquals(actualEmployee.getId(), employeeDto.getId());
    }
    @Test
    void testDeleteEmployee()
    {

        employeeService.deleteEmployee(1L);
        verify(employeeRepository, times(1)).deleteById(1L);
    }

    @Test
    void testSaveEmployee(){
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setFirstName("taghreed");
        employeeDto.setLastName("hamdy");
        employeeDto.setSalary(1000);
        Employee employee = new Employee();
        //employee.setId(1L);
        employee.setFirstName("taghreed");
        employee.setLastName("hamdy");
        employee.setSalary(1000);
        when(employeeRepository.save(employee)).thenReturn(employee);
        when(employeeMapper.employeeEntityToEmployeeDto(employee)).thenReturn(employeeDto);
        when(employeeMapper.employeeDtoToEmployeeEntity(employeeDto)).thenReturn(employee);

        EmployeeDto actualEmployeeDto = employeeService.saveEmployee(employeeDto);

        assertNotNull(actualEmployeeDto);
        assertEquals(actualEmployeeDto.getId(), employeeDto.getId());
    }
}
