package com.example.map;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    Map<String, Employee> persons = new HashMap<>(Map.of
            ("123", new Employee("Сергей", "Решетов", "123", 50000),
                    "122", new Employee("Евгений", "Печёнкин", "122", 60000),
                    "131", new Employee("Олег", "Зубаиров", "131", 70000),
                    "321", new Employee("Алексей", "Бжозовский", "321", 80000),
                    "099", new Employee("Юрий", "Девятов", "099", 100000)));

    @Override
    public void addPerson(String key, Employee person) {
        if (person.getFirstName() == null || person.getLastName() == null || key == null || person.getPassport() == null || person.getSalary() == null) {
            throw new EmployeeIncorrectlyException("Сотрудник по коду или по параметрам вводится некорректно!");
        }
        if (person.getFirstName() == "" || person.getLastName() == "" || key == "" || person.getPassport() == "") {
            throw new EmployeeIncorrectlyException("Сотрудник по коду или по параметрам вводится пустым!");
        }
        if (persons.containsKey(key)) {
            throw new EmployeeAlreadyAddedException("Сотрудник по коду " + key + " уже имеется в списке!");
        }
        persons.put(key, person);
    }

    @Override
    public void removePerson(String key) {
        final Employee employee = persons.get(key);
        if (employee == null || persons.isEmpty()) {
            throw new EmployeeAbsentException("Сотрудник по коду " + key + " в списке не значится!");
        }
        persons.remove(key);
    }

    @Override
    public String findPerson(String key) {
        final Employee employee = persons.get(key);
        if (key == null || persons.isEmpty()) {
            throw new EmployeeIncorrectlyException("Сотрудник по коду вводится некорректно!");
        }
        if (employee == null) {
            throw new EmployeeAbsentException("Сотрудник по коду " + key + " в списке не значится!");
        }
        return "" + employee + " в списке найден";
    }

    @Override
    public String maxSalaryPerson() {
        int max = Integer.MIN_VALUE;
        Employee employee = new Employee("", "", "", 0);

        for (Employee person : persons.values()) {
            if (max < person.getSalary()) {
                max = person.getSalary();
                employee = persons.get(person.getPassport());
            }

        }
        return "" + employee + " имеет самую большую зарплату";
    }

    @Override
    public String minSalaryPerson() {
        int min = Integer.MAX_VALUE;
        Employee employee = new Employee("", "", "", 0);

        for (Employee person : persons.values()) {
            if (min > person.getSalary()) {
                min = person.getSalary();
                employee = persons.get(person.getPassport());
            }
        }
        return "" + employee + " имеет самую маленькую зарплату";
    }

    @Override
    public String averageSalaryPerson() {
        int sum = 0;
        int averageSalary;
        int score = 0;

        for (Employee person : persons.values()) {
            sum = sum + person.getSalary();
            score++;
        }
        return "Средняя зарплата сотрудников составляет: " + sum / score + ". Общая сумма зарплат всех сотрудников - " + sum + " рублей";
    }
    @Override
    public String indexingSalary(String passport, Integer persent) {

        Employee employee = new Employee("", "", "", 0);

        employee = persons.get(passport);
        if (employee == null || persons.isEmpty()) {
            throw new EmployeeAbsentException("Сотрудник по коду " + passport + " в списке не значится!");
        }

        employee.setSalary(employee.getSalary() + (employee.getSalary() / 100) * persent);
        return employee + " - зарплата проиндексирована на " + persent + " процентов";
    }
    @Override
    public String listPerson() {
        return persons.toString();
    }
}

