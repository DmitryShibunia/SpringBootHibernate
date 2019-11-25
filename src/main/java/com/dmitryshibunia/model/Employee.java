package com.dmitryshibunia.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name="employee", schema = "public")
@ApiModel(description = "All details about the Employee. ")
public class Employee {

    @ApiModelProperty(notes = "The database generated employee ID")
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Long id;

    @ApiModelProperty(notes = "The employee first name")
    @Column(name = "first_name")
    private String firstName;

    @ApiModelProperty(notes = "The employee last name")
    @Column(name = "last_name")
    private String lastName;

    @ApiModelProperty(notes = "The department where employee works")
    @Column(name = "department_id")
    private Integer departmentId;

    @ApiModelProperty(notes = "The employee job title")
    @Column(name = "job_title")
    private String jobTitle;

    @ApiModelProperty(notes = "The employee gender")
    @Enumerated(EnumType.STRING)
    @Type(type = "com.dmitryshibunia.model.EnumTypePostgreSql")
    private Gender gender;

    @ApiModelProperty(notes = "The employee date of birth")
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    public Employee() {
    }

    public Employee(Long id, String firstName, String lastName, Integer departmentId, String jobTitle, Gender gender, LocalDate dateOfBirth) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.departmentId = departmentId;
        this.jobTitle = jobTitle;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id.equals(employee.id) &&
                firstName.equals(employee.firstName) &&
                lastName.equals(employee.lastName) &&
                departmentId.equals(employee.departmentId) &&
                jobTitle.equals(employee.jobTitle) &&
                gender == employee.gender &&
                dateOfBirth.equals(employee.dateOfBirth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, departmentId, jobTitle, gender, dateOfBirth);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", departmentId=" + departmentId +
                ", jobTitle='" + jobTitle + '\'' +
                ", gender=" + gender + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }
}
