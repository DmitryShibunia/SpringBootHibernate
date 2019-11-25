package com.dmitryshibunia.controller;

import com.dmitryshibunia.exception.RecordNotFoundException;
import com.dmitryshibunia.model.Employee;
import com.dmitryshibunia.service.EmployeeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RequestMapping("/employees")
@RestController
public class EmployeeController {

    private EmployeeService employeeService;
    private final Logger LOGGER = LogManager.getLogger();

    @Autowired
    EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        LOGGER.info("Call getAllEmployees() method of EmployeeController");
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id) throws RecordNotFoundException {
        LOGGER.info("Call getEmployeeById() method of EmployeeController");
        return employeeService.getEmployeeById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer addEmployee(@RequestBody Employee employee){
        LOGGER.info("Call addEmployee() method of EmployeeController");
        return employeeService.addEmployee(employee);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Employee updateTeam(@RequestBody Employee employee) throws RecordNotFoundException {
        LOGGER.info("Call updateTeam() method of EmployeeController");
        return employeeService.updateEmployee(employee);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteEmployee(@PathVariable Long id) throws RecordNotFoundException {
        LOGGER.info("Call deleteEmployee() method of EmployeeController");
        employeeService.deleteEmployee(id);
    }

    @PatchMapping(value = "/{filterFieldName}/{filterFieldValue}/{fieldToChangeName}/{fieldToChangeValue}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void patchEmployees(@PathVariable String filterFieldName,
                               @PathVariable String filterFieldValue,
                               @PathVariable String fieldToChangeName,
                               @PathVariable String fieldToChangeValue) {
        LOGGER.info("Call patchEmployees() method for employees with field {} = {}" , filterFieldName, filterFieldValue);
        employeeService.patchEmployees(filterFieldName, fieldToChangeName, filterFieldValue, fieldToChangeValue);
    }
}
