package com.demo.db.crud.service;

import com.demo.db.crud.beans.EmployeeRequest;
import com.demo.db.crud.beans.Response;
import com.demo.db.crud.model.Employee;
import com.demo.db.crud.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Response getAllEmployees() {
        List<Employee> employeeList = new ArrayList<>();
        employeeList = employeeRepository.findAll();
        if (employeeList.isEmpty()) {
            log.error("EmployeeController-> EmployeeServiceImpl-> getAllEmployee()-> Not able to get details from database");
            return new Response(HttpStatus.NOT_FOUND.value(), "No Details Found", null);
        }
        log.info("EmployeeController-> EmployeeServiceImpl-> getAllEmployee()-> response-> {}", employeeList);
        return new Response(HttpStatus.OK.value(), "Successfully getting all details", employeeList);
    }

    @Override
    public Response getEmployeeById(int empId) {
        Employee employee = new Employee();
        employee = employeeRepository.findByEmpId(empId);
        if (employee == null) {
            log.error("EmployeeController-> EmployeeServiceImpl-> getEmployeeById()-> Not able to get details from database");
            return new Response(HttpStatus.NOT_FOUND.value(), "Employee details not found for empId " + empId, null);
        }
        log.info("EmployeeController-> EmployeeServiceImpl-> getEmployeeById()-> response-> {}", employee);
        return new Response(HttpStatus.OK.value(), "Successfully getting the details", employee);
    }

    @Override
    public Response addEmployee(EmployeeRequest employeeRequest) {
        Employee existingEmployee = employeeRepository.findByEmpId(employeeRequest.getEmpId());
        if (existingEmployee == null) {
            Employee employee = new Employee();
            employee.setEmpId(employeeRequest.getEmpId());
            employee.setEmpName(employeeRequest.getEmpName());
            employee.setDesignation(employeeRequest.getDesignation());
            employee.setSalary(employeeRequest.getSalary());
            employeeRepository.save(employee);
            log.info("EmployeeController-> EmployeeServiceImpl-> addEmployee()-> response-> {}", employee);
            return new Response(HttpStatus.CREATED.value(), "Successfully Added the details", employee);
        }
        return new Response(HttpStatus.OK.value(), "Details already present", existingEmployee);
    }

    @Override
    public Response updateEmployee(int empId, EmployeeRequest employeeRequest) {
        Employee existingEmployee = new Employee();
        existingEmployee = employeeRepository.findByEmpId(empId);
        if (existingEmployee == null) {
            log.error("EmployeeController-> EmployeeServiceImpl-> updateEmployee()-> No details found for updation");
            return new Response(HttpStatus.NOT_FOUND.value(), "Employee details not found for empId " + empId, null);
        }
        existingEmployee.setDesignation(employeeRequest.getDesignation());
        existingEmployee.setSalary(employeeRequest.getSalary());
        employeeRepository.save(existingEmployee);
        log.info("EmployeeController-> EmployeeServiceImpl-> updateEmployee()-> response-> {}", existingEmployee);
        return new Response(HttpStatus.OK.value(), "Successfully Updated the details", existingEmployee);
    }

    @Override
    public Response deleteEmployee(int empId) {
        Employee existingEmployee = new Employee();
        existingEmployee = employeeRepository.findByEmpId(empId);
        if (existingEmployee == null) {
            log.error("EmployeeController-> EmployeeServiceImpl-> deleteEmployee()-> No details found for deletion");
            return new Response(HttpStatus.NOT_FOUND.value(), "Employee details not found for deletion", null);
        }
        employeeRepository.delete(existingEmployee);
        log.info("EmployeeController-> EmployeeServiceImpl-> deleteEmployee()-> Success");
        return new Response(HttpStatus.OK.value(), "Successfully delete the details", null);
    }
}
