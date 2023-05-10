package pro.sky.courseSpring.service;

import org.springframework.stereotype.Service;
import pro.sky.courseSpring.exeptions.EmployeeNotFoundException;
import pro.sky.courseSpring.model.Employee;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private EmployeeServiceMapImpl employeeServiceMap;

    public DepartmentServiceImpl(EmployeeServiceMapImpl employeeServiceMap) {
        this.employeeServiceMap = employeeServiceMap;
    }

    @Override
    public Employee maxSalary(int departmentId) {
        return getEmployeeToDepartment(departmentId).stream()
                .max(Comparator.comparingInt(e -> e.getSalary()))
                .orElseThrow(() -> new EmployeeNotFoundException("Не найден сотрудник"));
    }

    @Override
    public Employee minSalary(int departmentId) {
        return getEmployeeToDepartment(departmentId).stream()
                .min(Comparator.comparingInt(e -> e.getSalary()))
                .orElseThrow(() -> new EmployeeNotFoundException("Не найден сотрудник"));
    }

    @Override
    public Collection<Employee> getEmployeeToDepartment(int departmentId) {
        return employeeServiceMap.getAllEmployee().stream()
                .filter(e -> e.getDepartment() == departmentId)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<List<Employee>> getEmployeeToDepartment() {
        return employeeServiceMap.getAllEmployee().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment)).values();
    }

}
