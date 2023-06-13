package pro.sky.courseSpring.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import pro.sky.courseSpring.exeptions.EmployeeAlreadyAddedException;
import pro.sky.courseSpring.exeptions.EmployeeNotFoundException;
import pro.sky.courseSpring.model.Employee;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Primary
@Service
public class EmployeeServiceMapImpl implements EmployeeService {
    private final Map<String, Employee> employees;

    public EmployeeServiceMapImpl() {
        this.employees = new HashMap<>();
//        this.employees.put("DenisBelchik", new Employee("Denis", "Belchik", 500, 1));
//        this.employees.put("SaraIvanov",new Employee("Sara", "Ivanov", 5400, 2));
//        this.employees.put("IvanPetrov",new Employee("Ivan", "Petrov", 300, 3));
//        this.employees.put("FedorFedorov",new Employee("Fedor", "Fedorov", 100, 4));
    }

    @Override
    public Employee addEmployee(String firstName, String lastName, int salary, int department) {
        Employee employee = new Employee(firstName, lastName, salary, department);

        String key = firstName + lastName;

        if (employees.containsKey(key)) {
            throw new EmployeeAlreadyAddedException("Сотрудник уже есть");
        }

        employees.put(key, employee);

        return employee;
    }

    @Override
    public Employee removeEmployee(String firstName, String lastName, int salary, int department) {
        Employee employee = new Employee(firstName, lastName, salary, department);

        String key = firstName + lastName;

        if (!employees.containsKey(key)) {
            throw new EmployeeNotFoundException("Сотрудника нет");
        }

        employees.remove(key);

        return employee;
    }

    @Override
    public Employee findEmployee(String firstName, String lastName, int salary, int department) {
        Employee employee = new Employee(firstName, lastName, salary, department);

        String key = firstName + lastName;

        if (!employees.containsKey(key)) {
            throw new EmployeeNotFoundException("Сотрудника нет");
        }

        return employee;
    }

    @Override
    public Collection<Employee> getAllEmployee() {
        return Collections.unmodifiableCollection(employees.values());
    }
}
