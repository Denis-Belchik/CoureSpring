package pro.sky.courseSpring.service;

import pro.sky.courseSpring.model.Employee;

import java.util.Collection;
import java.util.List;

public interface DepartmentService {
    Employee maxSalary(int departmentId);
    Employee minSalary(int departmentId);
    Collection<Employee> getEmployeeToDepartment(int departmentId);
    Collection<List<Employee>> getEmployeeToDepartment();

}
