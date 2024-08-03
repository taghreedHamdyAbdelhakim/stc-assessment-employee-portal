package com.stc.employeeservice.repository;


import com.stc.employeeservice.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Page<Employee> findAllByOrderByIdDesc(Pageable pageable);
    Page<Employee> findAllByOrderByIdAsc(Pageable pageable);
    Page<Employee> findAllByOrderByFirstNameDesc(Pageable pageable);
    Page<Employee> findAllByOrderByFirstNameAsc(Pageable pageable);
    Page<Employee> findAllByOrderByLastNameDesc(Pageable pageable);
    Page<Employee> findAllByOrderByLastNameAsc(Pageable pageable);
    Page<Employee> findAllByOrderBySalaryDesc(Pageable pageable);
    Page<Employee> findAllByOrderBySalaryAsc(Pageable pageable);

}
