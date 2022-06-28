package com.demo.db.crud.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ContextConfiguration(classes = {Employee.class})
@ExtendWith(SpringExtension.class)
public class EmployeeTest {

    @Test
    public void constructor() {
        Employee employee = new Employee();
        employee.setId(1);
        employee.setEmpId(11);
        employee.setEmpName("Emp Name");
        employee.setDesignation("Designation");
        employee.setSalary(20.0);
        assertEquals(1, employee.getId());
        assertEquals(11, employee.getEmpId());
        assertEquals("Emp Name", employee.getEmpName());
        assertEquals("Designation", employee.getDesignation());
        assertEquals(20.0, employee.getSalary());
    }

    @Test
    public void constructor1() {
        Employee employee = new Employee(1, 11, "Emp Name", "Designation", 20.0);
        assertEquals(1, employee.getId());
        assertEquals(11, employee.getEmpId());
        assertEquals("Emp Name", employee.getEmpName());
        assertEquals("Designation", employee.getDesignation());
        assertEquals(20.0, employee.getSalary());
    }
}
