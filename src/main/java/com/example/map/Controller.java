package com.example.map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/person") //Вывод общего адреса-строки в общий множитель
public class Controller {
    private final EmployeeService employeeService;

    public Controller(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/add") //Получает по указанному адресу в адресной строке поля кода, объекта: name и surname
    public String addPerson(@RequestParam(required = false) String key,
                            @RequestParam(required = false) String name,
                            @RequestParam(required = false) String surname,
                            @RequestParam(required = false) String passport,
                            @RequestParam(required = false) Integer salary) {

        Employee person = new Employee(name, surname, passport, salary); //Создаем внутренний объект с параметрами адресной строки, чтобы его можно было передать в качестве
        // параметра, а потом вывести в браузере
        try {
            employeeService.addPerson(key, person); //Вызов соответствующего метода в классе сервиса(импл)
            return person + " вошёл в список"; //Просто person передать нельзя, т.к. отдает метод - String
        } catch (EmployeeAlreadyAddedException | EmployeeIncorrectlyException e) {
            return e.getMessage();
        }

    }

    @GetMapping(path = "/remove")
    public String removePerson(
            @RequestParam(required = false) String key) {
        try {
            employeeService.removePerson(key);
        } catch (EmployeeAbsentException e) {
            return e.getMessage();
        }
        return "Сотрудник под кодом " + key + " из списка успешно удалён";
    }

    @GetMapping(path = "/find")
    public String findPerson(
            @RequestParam(required = false) String key) {
        try {
            return employeeService.findPerson(key);
        } catch (EmployeeAbsentException | EmployeeIncorrectlyException e) {
            return e.getMessage();
        }
    }
    @GetMapping(path = "/list")
    public String listAllEmployee() {
        return employeeService.listPerson();
    } //Вызываем и тут же получаем результат,
    //в котором распечатывается вся коллекция целиком за счёт toString в классе Employee

    @GetMapping(path = "/maxSalary")
    public String maxSalaryPerson() {
        return employeeService.maxSalaryPerson();
    }
    @GetMapping(path = "/minSalary")
    public String minSalaryPerson() {
        return employeeService.minSalaryPerson();
    }
    @GetMapping(path = "/average")
    public String averageSalaryPerson() {
        return employeeService.averageSalaryPerson();
    }
    @GetMapping(path = "/index")
    public String indexingSalary(@RequestParam(required = false) String passport, @RequestParam(required = false) Integer persent) {
        try {
            return employeeService.indexingSalary(passport, persent);
        } catch (EmployeeAbsentException e) {
            return e.getMessage();
        }
    }
}
