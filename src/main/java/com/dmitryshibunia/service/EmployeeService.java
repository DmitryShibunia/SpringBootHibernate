package com.dmitryshibunia.service;

import com.dmitryshibunia.exception.RecordNotFoundException;
import com.dmitryshibunia.model.Employee;
import com.dmitryshibunia.model.Message;
import com.dmitryshibunia.repository.EmployeeRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private EmployeeRepository employeeDAO;
    private JmsTemplate jmsTemplate;
    private final Logger LOGGER = LogManager.getLogger();

    @Autowired
    public EmployeeService(EmployeeRepository repository, JmsTemplate jmsTemplate){
        this.employeeDAO = repository;
        this.jmsTemplate = jmsTemplate;
    }

    public List<Employee> getAllEmployees() {
        LOGGER.info("Call getAllEmployees() method of EmployeeService");
        return employeeDAO.findAll();
    }

    public Employee getEmployeeById(Long id) throws RecordNotFoundException {
        LOGGER.info("Call getEmployeeById() method of EmployeeService" + id);
        Optional<Employee> employee = employeeDAO.findById(id);

        if(employee.isPresent()) {
            return employee.get();
        } else {
            throw new RecordNotFoundException("No employee record exist for given id");
        }
    }

    public int addEmployee(Employee employee){
        LOGGER.info("Call addEmployee() method of EmployeeService" + employee);
        Employee addedEmployee= employeeDAO.save(employee);
        return addedEmployee.getId().intValue();
    }

    public Employee updateEmployee(Employee employee) throws RecordNotFoundException {
        LOGGER.info("Call updateEmployee() method of EmployeeService" + employee);
        Optional<Employee> existingEmployee = employeeDAO.findById(employee.getId());

        if(existingEmployee.isPresent())
        {
            employee.setId(existingEmployee.get().getId());
            employee = employeeDAO.save(employee);
            return employee;
        } else {
            throw new RecordNotFoundException("No employee record exist for given id");
        }
    }

    public void deleteEmployee(Long id) throws RecordNotFoundException {
        LOGGER.info("Call deleteEmployee() method of EmployeeService" + id);
        Optional<Employee> employee = employeeDAO.findById(id);

        if(employee.isPresent())
        {
            employeeDAO.deleteById(id);
        } else {
            throw new RecordNotFoundException("No employee record exist for given id");
        }
    }

    public void patchEmployees(String filterFieldName, String fieldToChangeName, String filterFieldValue, String fieldToChangeValue){
        LOGGER.info("Call patchEmployees() method for employees with field {} = {}" , filterFieldName, filterFieldValue);
        jmsTemplate.convertAndSend("mailbox", new Message(filterFieldName, fieldToChangeName, filterFieldValue, fieldToChangeValue));
    }

}
