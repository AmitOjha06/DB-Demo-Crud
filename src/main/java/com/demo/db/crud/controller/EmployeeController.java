package com.demo.db.crud.controller;

import com.demo.db.crud.beans.EmployeeRequest;
import com.demo.db.crud.beans.Response;
import com.demo.db.crud.service.EmployeeService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/db-demo/employee")
@OpenAPIDefinition(info = @Info(title = "Employee Controller", description = "Employee details Rest API",
        termsOfService = "Gemini Demo Terms&Services",
        contact = @Contact(name = "Amit Ojha", url = "localhost:8080", email = "amit.ojha@geminisolutions.com"),
        license = @License(name = "Apache 2.0", url = "license@apache.com")))
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Operation(summary = "Get All employee list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Response.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content)})
    @GetMapping("/all")
    public ResponseEntity<Response> getAllEmployees() {
        Response response = employeeService.getAllEmployees();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Get employee by Emp-Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Response.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content)})
    @GetMapping("/emp-id")
    public ResponseEntity<Response> getEmployeeById(@Parameter(description = "id of employee to be searched")
            @RequestParam(name = "Emp-Id") int empId) {
        Response response = employeeService.getEmployeeById(empId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Add employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Response.class))}),
            @ApiResponse(responseCode = "201", description = "Created",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content)})
    @PostMapping("/add")
    public ResponseEntity<Response> addEmployee(@RequestBody EmployeeRequest employeeRequest) {
        Response response = employeeService.addEmployee(employeeRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Update employee by Emp-Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Response.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content)})
    @PutMapping("/update/emp-id")
    public ResponseEntity<Response> updateEmployee(@Parameter(description = "id of employee to be searched")
                                                       @RequestParam(name = "Emp-Id") int empId, @RequestBody EmployeeRequest employeeRequest) {
        Response response = employeeService.updateEmployee(empId, employeeRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Delete employee by Emp-Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Response.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content)})
    @DeleteMapping("/delete/emp-id")
    public ResponseEntity<Response> deleteEmployee(@Parameter(description = "id of employee to be searched")
                                                       @RequestParam(name = "Emp-Id") int empId) {
        Response response = employeeService.deleteEmployee(empId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
