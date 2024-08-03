package com.stc.employeeservice.mapper;

import com.stc.employeeservice.dtos.EmployeeDto;
import com.stc.employeeservice.entity.Employee;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    EmployeeDto employeeEntityToEmployeeDto (Employee employee);
    Employee employeeDtoToEmployeeEntity (EmployeeDto employeeDto);
    default Page<EmployeeDto> employeeEntityPageToEmployeeDtoPage(Page<Employee> employees) {
        return employees.map(this::employeeEntityToEmployeeDto);
    }
}
