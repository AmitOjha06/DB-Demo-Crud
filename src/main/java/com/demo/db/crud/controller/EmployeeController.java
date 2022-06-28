package com.demo.db.crud.controller;

import com.demo.db.crud.beans.EmployeeRequest;
import com.demo.db.crud.beans.Response;
import com.demo.db.crud.service.EmployeeService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/db-demo/employee")
@Api(tags = "Employee-Controller")

public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/all")
    public ResponseEntity<Response> getAllEmployees(){
        Response response = employeeService.getAllEmployees();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/emp-id")
    public ResponseEntity<Response> getEmployeeById(@RequestParam(name = "Emp-Id") int empId){
        Response response = employeeService.getEmployeeById(empId);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Response> addEmployee(@RequestBody EmployeeRequest employeeRequest){
        Response response = employeeService.addEmployee(employeeRequest);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PutMapping("/update/emp-id")
    public ResponseEntity<Response> updateEmployee(@RequestParam(name = "Emp-Id") int empId, @RequestBody EmployeeRequest employeeRequest){
        Response response = employeeService.updateEmployee(empId,employeeRequest);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @DeleteMapping("/delete/emp-id")
    public ResponseEntity<Response> addEmployee(@RequestParam(name = "Emp-Id") int empId){
        Response response = employeeService.deleteEmployee(empId);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
