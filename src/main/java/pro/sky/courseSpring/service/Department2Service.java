package pro.sky.courseSpring.service;

import pro.sky.courseSpring.model.Employee;

import java.util.List;
import java.util.Map;

public interface Department2Service {
    Integer sum(Integer department);
    Integer max(Integer department);
    Integer min(Integer department);
    List<Employee> employees(Integer department);
    Map<Integer, List<Employee>> employees();
}
