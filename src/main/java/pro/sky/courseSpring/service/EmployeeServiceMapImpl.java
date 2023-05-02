package pro.sky.courseSpring.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import pro.sky.courseSpring.exeptions.EmployeeAlreadyAddedException;
import pro.sky.courseSpring.exeptions.EmployeeNotFoundException;
import pro.sky.courseSpring.model.Employee;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Primary
@Service
public class EmployeeServiceMapImpl implements EmployeeService {
    private final Map<String, Employee> employees;

    public EmployeeServiceMapImpl() {
        this.employees = new HashMap<>();
    }

    @Override
    public Employee addEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);

        String key = firstName + lastName;

        if (employees.containsKey(key)) {
            throw new EmployeeAlreadyAddedException("Сотрудник уже есть");
        }

        employees.put(key, employee);

        return employee;
    }

    @Override
    public Employee removeEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);

        String key = firstName + lastName;

        if (!employees.containsKey(key)) {
            throw new EmployeeNotFoundException("Сотрудника нет");
        }


        employees.remove(key);

        return employee;
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);

        String key = firstName + lastName;

        if (!employees.containsKey(key)) {
            throw new EmployeeNotFoundException("Сотрудника нет");
        }

        return employee;
    }

    @Override
    public Collection<Employee> getAllEmployee() {
        Collection<Employee> employee = employees.values();
        return employee;
    }
}
