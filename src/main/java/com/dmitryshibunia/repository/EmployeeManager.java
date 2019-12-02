package com.dmitryshibunia.repository;

import com.dmitryshibunia.model.Employee;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

@Repository
public class EmployeeManager {

    @PersistenceContext
    private EntityManager em;

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void updateEmployees(String filterFieldName, String fieldToChangeName, String filterFieldValue, String fieldToChangeValue) {
        CriteriaBuilder cb = this.em.getCriteriaBuilder();

        // create update
        CriteriaUpdate<Employee> update = cb.createCriteriaUpdate(Employee.class);

        // set the root class
        Root e = update.from(Employee.class);

        // set update and where clause
        update.set(fieldToChangeName, fieldToChangeValue);
        update.where(cb.equal(e.get(filterFieldName), filterFieldValue));

        this.em.createQuery(update).executeUpdate();
    }

}
