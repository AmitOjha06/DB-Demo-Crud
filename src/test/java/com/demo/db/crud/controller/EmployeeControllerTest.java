package com.demo.db.crud.controller;

import com.demo.db.crud.beans.EmployeeRequest;
import com.demo.db.crud.beans.Response;
import com.demo.db.crud.model.Employee;
import com.demo.db.crud.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {EmployeeController.class})
@ExtendWith(SpringExtension.class)
class EmployeeControllerTest {
    @Autowired
    private EmployeeController employeeController;

    @MockBean
    private EmployeeService employeeService;

    @Test
    void testGetAllEmployees() throws Exception {
        Response response = new Response();
        List<Employee> employeeList = new ArrayList<>();
        response.setStatusCode(200);
        response.setStatusMessage("Successfully getting all details");
        response.setResult(employeeList);
        when(employeeService.getAllEmployees()).thenReturn(new Response());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/db-demo/employee/all");
        MockMvcBuilders.standaloneSetup(employeeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
        assertEquals("Successfully getting all details", response.getStatusMessage());
        assertEquals(employeeList, response.getResult());
    }

    @Test
    void testGetEmployeeById() throws Exception {
        Employee employee = new Employee(1,101,"Amit","SE-2",40000.90);
        Response response = new Response();
        response.setStatusCode(200);
        response.setStatusMessage("Successfully getting the details");
        response.setResult(employee);
        when(employeeService.getEmployeeById(anyInt())).thenReturn(new Response());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/db-demo/employee/emp-id");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("Emp-Id", String.valueOf(1));
        MockMvcBuilders.standaloneSetup(employeeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().is(200));
        assertEquals("Successfully getting the details", response.getStatusMessage());
        assertEquals(employee, response.getResult());
    }

    @Test
    void testDeleteEmployee() throws Exception {
        Response response = new Response();
        response.setStatusMessage("Successfully delete the details");
        when(employeeService.deleteEmployee(anyInt())).thenReturn(new Response());
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/api/db-demo/employee/delete/emp-id");
        MockHttpServletRequestBuilder requestBuilder = deleteResult.param("Emp-Id", String.valueOf(1));
        MockMvcBuilders.standaloneSetup(employeeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().is(200));
        assertEquals("Successfully delete the details", response.getStatusMessage());
    }

    @Test
    void testAddEmployee() throws Exception {
        Response response = new Response();
        response.setStatusMessage("Successfully Added the details");
        when(employeeService.addEmployee(any())).thenReturn(new Response());

        EmployeeRequest employeeRequest = new EmployeeRequest();
        employeeRequest.setDesignation("Designation");
        employeeRequest.setEmpId(123);
        employeeRequest.setEmpName("Emp Name");
        employeeRequest.setSalary(10.0d);
        response.setResult(employeeRequest);
        String content = (new ObjectMapper()).writeValueAsString(employeeRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/db-demo/employee/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(employeeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().is(200));
        assertEquals("Successfully Added the details", response.getStatusMessage());
        assertEquals(employeeRequest, response.getResult());
    }

    @Test
    void testUpdateEmployee() throws Exception {
        Response response = new Response();
        response.setStatusCode(200);
        response.setStatusMessage("Successfully Updated the details");
        when(employeeService.updateEmployee(anyInt(), any())).thenReturn(new Response());

        EmployeeRequest employeeRequest = new EmployeeRequest();
        employeeRequest.setDesignation("Designation");
        employeeRequest.setEmpId(123);
        employeeRequest.setEmpName("Emp Name");
        employeeRequest.setSalary(10.0d);
        response.setResult(employeeRequest);
        String content = (new ObjectMapper()).writeValueAsString(employeeRequest);
        MockHttpServletRequestBuilder putResult = MockMvcRequestBuilders.put("/api/db-demo/employee/update/emp-id");
        MockHttpServletRequestBuilder requestBuilder = putResult.param("Emp-Id", String.valueOf(1))
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(employeeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().is(response.getStatusCode()));
        assertEquals("Successfully Updated the details", response.getStatusMessage());
        assertEquals(employeeRequest, response.getResult());
    }
}

