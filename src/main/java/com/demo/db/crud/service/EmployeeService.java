package com.demo.db.crud.service;

import com.demo.db.crud.beans.EmployeeRequest;
import com.demo.db.crud.beans.Response;

public interface EmployeeService {
    Response getAllEmployees();

    Response getEmployeeById(int empId);

    Response addEmployee(EmployeeRequest employeeRequest);

    Response updateEmployee(int empId, EmployeeRequest employeeRequest);

    Response deleteEmployee(int empId);
}
