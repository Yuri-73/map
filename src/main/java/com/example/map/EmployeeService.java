package com.example.map;

public interface EmployeeService {

    void addPerson(String key, Employee person);

    void removePerson(String key);

    String findPerson(String key);


    String maxSalaryPerson();

    String minSalaryPerson();

    String averageSalaryPerson();

    String listPerson();


    String indexingSalary(String passport, Integer persent);
}
