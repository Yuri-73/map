package com.example.map;

import java.util.Objects;

public class Employee {
    private String firstName;
    private String lastName;
    private String passport;
    private Integer salary;

    public Employee(String firstName, String lastName, String passport, Integer salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.passport = passport;
        this.salary = salary;
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

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public String getPassport() {
        return passport;
    }

    @Override
    public String toString() {
        return "Сотрудник: " + firstName + " " + lastName + ", паспорт номер: " + passport + ", оклад: " + salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return firstName.equals(employee.firstName) && lastName.equals(employee.lastName) && passport.equals(employee.passport) && salary.equals(employee.salary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, passport, salary);
    }
}

