package com.demo.db.crud.controller;

import com.demo.db.crud.beans.EmployeeRequest;
import com.demo.db.crud.beans.Response;
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
        when(employeeService.getAllEmployees()).thenReturn(new Response());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/db-demo/employee/all");
        MockMvcBuilders.standaloneSetup(employeeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testGetEmployeeById() throws Exception {
        when(employeeService.getEmployeeById(anyInt())).thenReturn(new Response());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/db-demo/employee/emp-id");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("Emp-Id", String.valueOf(1));
        MockMvcBuilders.standaloneSetup(employeeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testDeleteEmployee() throws Exception {
        when(employeeService.deleteEmployee(anyInt())).thenReturn(new Response());
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/api/db-demo/employee/delete/emp-id");
        MockHttpServletRequestBuilder requestBuilder = deleteResult.param("Emp-Id", String.valueOf(1));
        MockMvcBuilders.standaloneSetup(employeeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testAddEmployee() throws Exception {
        when(employeeService.addEmployee(any())).thenReturn(new Response());

        EmployeeRequest employeeRequest = new EmployeeRequest();
        employeeRequest.setDesignation("Designation");
        employeeRequest.setEmpId(123);
        employeeRequest.setEmpName("Emp Name");
        employeeRequest.setSalary(10.0d);
        String content = (new ObjectMapper()).writeValueAsString(employeeRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/db-demo/employee/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(employeeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testUpdateEmployee() throws Exception {
        when(employeeService.updateEmployee(anyInt(), any())).thenReturn(new Response());

        EmployeeRequest employeeRequest = new EmployeeRequest();
        employeeRequest.setDesignation("Designation");
        employeeRequest.setEmpId(123);
        employeeRequest.setEmpName("Emp Name");
        employeeRequest.setSalary(10.0d);
        String content = (new ObjectMapper()).writeValueAsString(employeeRequest);
        MockHttpServletRequestBuilder putResult = MockMvcRequestBuilders.put("/api/db-demo/employee/update/emp-id");
        MockHttpServletRequestBuilder requestBuilder = putResult.param("Emp-Id", String.valueOf(1))
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(employeeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().is(200));
    }
}

