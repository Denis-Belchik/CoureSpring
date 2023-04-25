package pro.sky.courseSpring.service;

import org.springframework.stereotype.Service;
import pro.sky.courseSpring.exeptions.EmployeeAlreadyAddedException;
import pro.sky.courseSpring.exeptions.EmployeeNotFoundException;
import pro.sky.courseSpring.model.Employee;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class EmployeeServiceImp implements EmployeeService {
    private final List<Employee> employees;

    public EmployeeServiceImp() {
        this.employees = new ArrayList<>();
    }

    @Override
    public Employee addEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);

        if (employees.contains(employee)) {
            throw new EmployeeAlreadyAddedException("Сотрудник уже есть");
        }

        employees.add(employee);

        return employee;
    }

    @Override
    public Employee removeEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);

        if (!employees.contains(employee)) {
            throw new EmployeeNotFoundException("Сотрудника нет");
        }

        employees.remove(employee);

        return employee;
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);

        if (!employees.contains(employee)) {
            throw new EmployeeNotFoundException("Сотрудника нет");
        }

        return employee;
    }

    @Override
    public List<Employee> getAllEmployee() {
        return Collections.unmodifiableList(employees);
    }
}
