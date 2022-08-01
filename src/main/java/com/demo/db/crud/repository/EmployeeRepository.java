package com.demo.db.crud.repository;

import com.demo.db.crud.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    @Query(value = "SELECT * from employee where emp_id=?1", nativeQuery = true)
    Employee findByEmpId(int empId);
}
