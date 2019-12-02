package com.dmitryshibunia.controller;

import com.dmitryshibunia.exception.RecordNotFoundException;
import com.dmitryshibunia.model.Employee;
import com.dmitryshibunia.model.Message;
import com.dmitryshibunia.service.EmployeeService;
import io.swagger.annotations.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RequestMapping("/employees")
@RestController
@Api(value = "Employee Management System", description = "Operations pertaining to employee in Employee Management System")
@ApiResponses(value = {
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")

})
public class EmployeeController {

    private EmployeeService employeeService;
    private final Logger LOGGER = LogManager.getLogger();

    @Autowired
    EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @ApiOperation(value = "View a list of available employees", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list")
    })
    @GetMapping
    public List<Employee> getAllEmployees() {
        LOGGER.info("Call getAllEmployees() method of EmployeeController");
        return employeeService.getAllEmployees();
    }

    @ApiOperation(value = "Get an employee by id", response = Employee.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved employee")
    })
    @GetMapping("/{id}")
    public Employee getEmployeeById(
            @ApiParam(value = "Employee id from which employee object will retrieve", required = true)
            @PathVariable Long id) throws RecordNotFoundException {
        LOGGER.info("Call getEmployeeById() method of EmployeeController");
        return employeeService.getEmployeeById(id);
    }

    @ApiOperation(value = "Add an employee", response = Integer.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Id of successfully added employee")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer addEmployee(
            @ApiParam(value = "Employee entity store in database table", required = true)
            @RequestBody Employee employee){
        LOGGER.info("Call addEmployee() method of EmployeeController");
        return employeeService.addEmployee(employee);
    }

    @ApiOperation(value = "Update an employee", response = Employee.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Updated employee entity")
    })
    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Employee updateTeam(
            @ApiParam(value = "Employee entity store in database table", required = true)
            @RequestBody Employee employee) throws RecordNotFoundException {
        LOGGER.info("Call updateTeam() method of EmployeeController");
        return employeeService.updateEmployee(employee);
    }

    @ApiOperation(value = "Delete an employee by id")
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteEmployee(
            @ApiParam(value = "Employee entity store in database table", required = true)
            @PathVariable Long id) throws RecordNotFoundException {
        LOGGER.info("Call deleteEmployee() method of EmployeeController");
        employeeService.deleteEmployee(id);
    }

    @ApiOperation(value = "Change field 'fieldToChangeName' to 'fieldToChangeValue' for all employees with 'filterFieldName' = 'filterFieldValue'")
    @PatchMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void patchEmployees(
            @ApiParam(value = "2 pairs of parameters of query", required = true)
            @RequestBody Message message) throws NoSuchFieldException {
        LOGGER.info("Call patchEmployees() method for employees with field {} = {}" , message.getFilterFieldName(), message.getFilterFieldValue());
        employeeService.patchEmployees(message);
    }
}
