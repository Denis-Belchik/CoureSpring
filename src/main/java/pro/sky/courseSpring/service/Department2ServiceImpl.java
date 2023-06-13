package pro.sky.courseSpring.service;

import org.springframework.stereotype.Service;
import pro.sky.courseSpring.exeptions.NullDepartmentException;
import pro.sky.courseSpring.model.Employee;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class Department2ServiceImpl implements Department2Service {

    private final EmployeeService employeeService;

    public Department2ServiceImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public Integer sum(Integer department) {
        if (department == null)
            throw new NullDepartmentException();
        return employeeService.getAllEmployee().stream()
                .filter(imp -> imp.getDepartment() == department)
                .map(imp -> imp.getSalary())
                .reduce((accum, imp) -> accum + imp)
                .orElse(-1);
    }

    @Override
    public Integer max(Integer department) {
        if (department == null)
            throw new NullDepartmentException();
        return employeeService.getAllEmployee().stream()
                .filter(imp -> imp.getDepartment() == department)
                .map(imp -> imp.getSalary())
                .max((i, j) -> i.compareTo(j))
                .orElse(-1);
    }

    @Override
    public Integer min(Integer department) {
        if (department == null)
            throw new NullDepartmentException();
        return employeeService.getAllEmployee().stream()
                .filter(imp -> imp.getDepartment() == department)
                .map(imp -> imp.getSalary())
                .min((i, j) -> i.compareTo(j))
                .orElse(-1);
    }

    @Override
    public List<Employee> employees(Integer department) {
        if (department == null)
            throw new NullDepartmentException();
        return employeeService.getAllEmployee().stream()
                .filter(imp -> imp.getDepartment() == department)
                .collect(Collectors.toList());
    }

    @Override
    public Map<Integer, List<Employee>> employees() {
        return employeeService.getAllEmployee().stream()
                .collect(Collectors.groupingBy((imp) -> imp.getDepartment()));
    }
}
