package com.stc.employeeservice.service;


import com.stc.employeeservice.dtos.EmployeeDto;

import com.stc.employeeservice.entity.Employee;
import com.stc.employeeservice.mapper.EmployeeMapper;
import com.stc.employeeservice.repository.EmployeeRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class EmployeeService {
    private final EmployeeMapper employeeMapper;

    private final EmployeeRepository employeeRepository;
    public EmployeeService(EmployeeMapper employeeMapper, EmployeeRepository employeeRepository) {
        this.employeeMapper = employeeMapper;
        this.employeeRepository = employeeRepository;
    }


    public EmployeeDto getEmployeeById(Long id) {
      return   employeeMapper.employeeEntityToEmployeeDto(employeeRepository.findById(id).orElse(null));

    }

    @Transactional
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        return employeeMapper.employeeEntityToEmployeeDto(employeeRepository.save(employeeMapper.employeeDtoToEmployeeEntity(employeeDto)));
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    public Page<EmployeeDto> getAllEmployees(Integer pageNumber, Integer pageSize, String sortColumn, String sortDirection) {

        if (pageNumber != 0) pageNumber = pageNumber - 1;
        String sortProperty = "id";
        Sort.Direction direction = Sort.Direction.DESC;
        if (sortColumn != null && !sortColumn.isEmpty()) {
            sortProperty = sortColumn;
        }
        if (sortDirection != null && sortDirection.equalsIgnoreCase("asc")) {
            direction = Sort.Direction.ASC;
        }
        Pageable pageable = PageRequest.of(pageNumber, pageSize, direction, sortProperty);
        Page<Employee> employeePage = employeeRepository.findAll(pageable);
        return employeeMapper.employeeEntityPageToEmployeeDtoPage(employeePage);
    }


}
