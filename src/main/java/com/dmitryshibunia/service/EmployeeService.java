package com.dmitryshibunia.service;

import com.dmitryshibunia.DAO.EmployeeDAO;
import com.dmitryshibunia.exception.RecordNotFoundException;
import com.dmitryshibunia.model.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private EmployeeDAO employeeDAO;
    private final Logger LOGGER = LogManager.getLogger();

    @Autowired
    public EmployeeService(EmployeeDAO employeeDAO){
        this.employeeDAO = employeeDAO;
    }

    public List<Employee> getAllEmployees()
    {
        LOGGER.info("Call getAllEmployees() method of EmployeeService");
        return employeeDAO.getAllEmployees();
    }

    public Employee getEmployeeById(Long id) throws RecordNotFoundException {
        LOGGER.info("Call getEmployeeById() method of EmployeeService");
        return employeeDAO.getEmployeeById(id);
    }

    public int addEmployee(Employee employee){
        LOGGER.info("Call addEmployee() method of EmployeeService");
        return employeeDAO.addEmployee(employee);
    }

    public Employee updateEmployee(Employee employee) throws RecordNotFoundException {
        LOGGER.info("Call updateEmployee() method of EmployeeService");
        return employeeDAO.updateEmployee(employee);
    }

    public void deleteEmployee(Long id) throws RecordNotFoundException {
        LOGGER.info("Call deleteEmployee() method of EmployeeService");
        employeeDAO.deleteEmployee(id);
    }
}
