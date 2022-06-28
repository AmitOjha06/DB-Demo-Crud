package com.demo.db.crud.service;

import com.demo.db.crud.beans.EmployeeRequest;
import com.demo.db.crud.beans.Response;
import com.demo.db.crud.model.Employee;
import com.demo.db.crud.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {EmployeeServiceImpl.class})
@ExtendWith(SpringExtension.class)
class EmployeeServiceImplTest {
    @MockBean
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeServiceImpl employeeServiceImpl;

    @Test
    void testGetAllEmployees() {
        when(employeeRepository.findAll()).thenReturn(new ArrayList<>());
        Response actualAllEmployees = employeeServiceImpl.getAllEmployees();
        assertNull(actualAllEmployees.getResult());
        assertEquals("No Details Found", actualAllEmployees.getStatusMessage());
        assertEquals(404, actualAllEmployees.getStatusCode());
    }

    @Test
    void testGetAllEmployees2() {
        Employee employee = new Employee();
        employee.setId(1);
        employee.setEmpId(123);
        employee.setEmpName("Name");
        employee.setDesignation("Designation");
        employee.setSalary(10.0d);
        ArrayList<Employee> employeeList = new ArrayList<>();
        employeeList.add(employee);
        when(employeeRepository.findAll()).thenReturn(employeeList);
        Response actualAllEmployees = employeeServiceImpl.getAllEmployees();
        assertEquals(employeeList, actualAllEmployees.getResult());
        assertEquals("Successfully getting all details", actualAllEmployees.getStatusMessage());
        assertEquals(200, actualAllEmployees.getStatusCode());
    }

    @Test
    void testGetEmployeeById() {
        when(employeeRepository.findByEmpId(anyInt())).thenReturn(null);
        Response actualAllEmployees = employeeServiceImpl.getEmployeeById(1);
        assertNull(actualAllEmployees.getResult());
        assertEquals("Employee details not found for empId 1", actualAllEmployees.getStatusMessage());
        assertEquals(404, actualAllEmployees.getStatusCode());
    }

    @Test
    void testGetEmployeeById1() {
        Employee employee = new Employee();
        employee.setDesignation("Designation");
        employee.setEmpId(123);
        employee.setEmpName("Emp Name");
        employee.setId(1);
        employee.setSalary(10.0d);
        when(employeeRepository.findByEmpId(anyInt())).thenReturn(employee);
        Response actualEmployeeById = employeeServiceImpl.getEmployeeById(123);
        assertSame(employee, actualEmployeeById.getResult());
        assertEquals("Successfully getting the details", actualEmployeeById.getStatusMessage());
        assertEquals(200, actualEmployeeById.getStatusCode());
    }

    @Test
    void testAddEmployee() {
        Employee employee = new Employee();
        employee.setDesignation("Designation");
        employee.setEmpId(123);
        employee.setEmpName("Emp Name");
        employee.setId(1);
        employee.setSalary(10.0d);
        when(employeeRepository.findByEmpId(anyInt())).thenReturn(employee);
        when(employeeRepository.save(any())).thenReturn(employee);
        Response actualAddEmployeeResult = employeeServiceImpl
                .addEmployee(new EmployeeRequest(123, "Emp Name", "Designation", 10.0d));
        assertSame(employee, actualAddEmployeeResult.getResult());
        assertEquals("Details already present", actualAddEmployeeResult.getStatusMessage());
        assertEquals(200, actualAddEmployeeResult.getStatusCode());
    }

    @Test
    void testAddEmployee1() {
        Employee employee = new Employee();
        employee.setId(1);
        employee.setEmpId(123);
        employee.setEmpName("Emp Name");
        employee.setDesignation("Designation");
        employee.setSalary(10.0d);
        when(employeeRepository.findByEmpId(anyInt())).thenReturn(null);
        when(employeeRepository.save(any())).thenReturn(employee);
        Response actualAllEmployees = employeeServiceImpl.addEmployee(new EmployeeRequest());
//        assertEquals(employee , actualAllEmployees.getResult());
        assertEquals("Successfully Added the details", actualAllEmployees.getStatusMessage());
        assertEquals(201, actualAllEmployees.getStatusCode());
    }

    @Test
    void testUpdateEmployee() {
        Employee employee = new Employee();
        employee.setDesignation("Designation");
        employee.setEmpId(123);
        employee.setEmpName("Emp Name");
        employee.setId(1);
        employee.setSalary(10.0d);

        when(employeeRepository.save(any())).thenReturn(employee);
        when(employeeRepository.findByEmpId(anyInt())).thenReturn(employee);
        Response actualUpdateEmployeeResult = employeeServiceImpl.updateEmployee(123,
                new EmployeeRequest(123, "Emp Name", "Designation", 10.0d));
        Object result = actualUpdateEmployeeResult.getResult();
        assertSame(employee, result);
        assertEquals("Successfully Updated the details", actualUpdateEmployeeResult.getStatusMessage());
        assertEquals(200, actualUpdateEmployeeResult.getStatusCode());
    }

    @Test
    void testUpdateEmployee1() {
        when(employeeRepository.findByEmpId(anyInt())).thenReturn(null);
        Response actualAllEmployees = employeeServiceImpl.updateEmployee(123, new EmployeeRequest());
        assertNull(actualAllEmployees.getResult());
        assertEquals("Employee details not found for empId 123", actualAllEmployees.getStatusMessage());
        assertEquals(404, actualAllEmployees.getStatusCode());
    }

    @Test
    void testDeleteEmployee() {
        Employee employee = new Employee();
        employee.setDesignation("Designation");
        employee.setEmpId(123);
        employee.setEmpName("Emp Name");
        employee.setId(1);
        employee.setSalary(10.0d);
        doNothing().when(employeeRepository).delete((Employee) any());
        when(employeeRepository.findByEmpId(anyInt())).thenReturn(employee);
        Response actualDeleteEmployeeResult = employeeServiceImpl.deleteEmployee(123);
        assertNull(actualDeleteEmployeeResult.getResult());
        assertEquals("Successfully delete the details", actualDeleteEmployeeResult.getStatusMessage());
        assertEquals(200, actualDeleteEmployeeResult.getStatusCode());
    }

    @Test
    void testDeleteEmployee1() {
        when(employeeRepository.findByEmpId(anyInt())).thenReturn(null);
        Response actualAllEmployees = employeeServiceImpl.deleteEmployee(123);
        assertNull(actualAllEmployees.getResult());
        assertEquals("Employee details not found for deletion", actualAllEmployees.getStatusMessage());
        assertEquals(404, actualAllEmployees.getStatusCode());
    }
}

