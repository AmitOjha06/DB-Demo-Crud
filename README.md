# DB-Demo-Crud-For-Employee

A brief description of this is, it has 4 APIs. Get, Post, Put
and Delete. These APIs are performing basic operation on an employee details stored in the mySql database.


## API Reference

#### Get All Employee List

```http
  GET:  /api/db-demo/employee/all
```

| Parameter | Type | Description                                 |
|:----------|:-----|:--------------------------------------------|
| `NA`      | `NA` | No parameter just return all employee data. |

#### Get Employee by Emp-id

```http
  GET:  /api/db-demo/employee/emp-id
```

| Parameter    | Type  | Description                   |
|:-------------|:------|:------------------------------|
| `Emp-Id`     | `int` | **Required***  Id of employee |

#### Add Employee Details


```http
  POST:  /api/db-demo/employee/add
```
It takes employee details as body parameter and add into database.

Employee details:

| Parameter       | Type     | Description                            |
|:----------------|:---------|:---------------------------------------|
| `empId`         | `int`    | **Required*** Id of employee.          |
| `empName`       | `string` | **Required*** Name of employee.        |
| `designation`   | `string` | **Required*** Designation of employee. |
| `salary`        | `double` | **Required*** Salary of employee.      |


#### Update Employee Details by Emp-id


```http
  PUT:  /api/db-demo/employee/update/emp-id
```
For fetching details we take emp-id as a parameter and find the details of employee and then update as per given updated values.

| Parameter | Type  | Description                    |
|:----------|:------|:-------------------------------|
| `Emp-Id`  | `int` | **Required***. Id of employee. |

It takes employee details which can be updated as body parameter for updating the details.

Employee details:

| Parameter     | Type     | Description                             |
|:--------------|:---------|:----------------------------------------|
| `designation` | `string` | **Required***. Designation of employee. |
| `salary`      | `double` | **Required***. Salary of employee.      |


#### Delete Employee Details by Emp-id


```http
  DELETE:  /api/db-demo/employee/delete/emp-id
```

| Parameter | Type  | Description                                  |
|:----------|:------|:---------------------------------------------|
| `Emp-Id`  | `int` | **Required***. Id of employee to be deleted. |




## Contributing

Contributions are always welcome!.

But this is a demo project for learning purpose, so it is making by @Amit Ojha. No Contribution is required.

