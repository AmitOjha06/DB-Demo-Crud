package com.demo.db.crud.beans;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ContextConfiguration(classes = {EmployeeRequest.class})
@ExtendWith(SpringExtension.class)
public class EmployeeRequestTest {

    @Test
    public void constructor() {
        EmployeeRequest employeeRequest = new EmployeeRequest();
        employeeRequest.setEmpId(1);
        employeeRequest.setEmpName("Emp Name");
        employeeRequest.setDesignation("Designation");
        employeeRequest.setSalary(20.0);
        assertEquals(1, employeeRequest.getEmpId());
        assertEquals("Emp Name", employeeRequest.getEmpName());
        assertEquals("Designation", employeeRequest.getDesignation());
        assertEquals(20.0, employeeRequest.getSalary());
    }

    @Test
    public void constructor1() {
        EmployeeRequest employeeRequest = new EmployeeRequest(1, "Emp Name", "Designation", 20.0);
        assertEquals(1, employeeRequest.getEmpId());
        assertEquals("Emp Name", employeeRequest.getEmpName());
        assertEquals("Designation", employeeRequest.getDesignation());
        assertEquals(20.0, employeeRequest.getSalary());
    }
}
