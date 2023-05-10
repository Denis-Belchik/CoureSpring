package pro.sky.courseSpring.service;

import pro.sky.courseSpring.model.Employee;

import java.util.Collection;
import java.util.List;

public interface EmployeeService {

    Employee addEmployee(String firstName, String lastName, int salary, int department);

    Employee removeEmployee(String firstName, String lastName, int salary, int department);

    Employee findEmployee(String firstName, String lastName, int salary, int department);

    Collection<Employee> getAllEmployee();
}
