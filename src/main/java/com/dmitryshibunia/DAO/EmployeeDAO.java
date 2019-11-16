package com.dmitryshibunia.DAO;

import com.dmitryshibunia.exception.RecordNotFoundException;
import com.dmitryshibunia.model.Employee;
import com.dmitryshibunia.repository.EmployeeRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class EmployeeDAO {

    private EmployeeRepository repository;
    private final Logger LOGGER = LogManager.getLogger();

    @Autowired
    public EmployeeDAO(EmployeeRepository repository){
        this.repository = repository;
    }

    public List<Employee> getAllEmployees() {
        LOGGER.info("Call getAllEmployees() method of EmployeeDAO");
        return repository.findAll();
    }

    public Employee getEmployeeById(Long id) throws RecordNotFoundException {
        LOGGER.info("Call getEmployeeById() method of EmployeeDAO" + id);
        Optional<Employee> employee = repository.findById(id);

        if(employee.isPresent()) {
            return employee.get();
        } else {
            throw new RecordNotFoundException("No employee record exist for given id");
        }
    }

    public int addEmployee(Employee employee){
        LOGGER.info("Call addEmployee() method of EmployeeDAO" + employee);
        Employee addedEmployee= repository.save(employee);
        return addedEmployee.getId().intValue();
    }

    public Employee updateEmployee(Employee employee) throws RecordNotFoundException {
        LOGGER.info("Call updateEmployee() method of EmployeeDAO" + employee);
        Optional<Employee> existingEmployee = repository.findById(employee.getId());

        if(existingEmployee.isPresent())
        {
            employee.setId(existingEmployee.get().getId());
            employee = repository.save(employee);
            return employee;
        } else {
            throw new RecordNotFoundException("No employee record exist for given id");
        }
    }

    public void deleteEmployee(Long id) throws RecordNotFoundException {
        LOGGER.info("Call deleteEmployee() method of EmployeeDAO" + id);
        Optional<Employee> employee = repository.findById(id);

        if(employee.isPresent())
        {
            repository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No employee record exist for given id");
        }
    }
}
