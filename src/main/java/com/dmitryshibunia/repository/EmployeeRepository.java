package com.dmitryshibunia.repository;

import com.dmitryshibunia.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {


    @Modifying
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    @Query("update Employee set job_title = :fieldToChangeNameValue where first_name = :filterFieldNameValue")
    void update(@Param("filterFieldName") String filterFieldName,
                @Param("fieldToChangeName") String fieldToChangeName,
                @Param("filterFieldNameValue") String filterFieldNameValue,
                @Param("fieldToChangeNameValue") String fieldToChangeNameValue);


}

