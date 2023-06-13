package pro.sky.courseSpring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.courseSpring.model.Employee;
import pro.sky.courseSpring.service.DepartmentService;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/max-salary")
    public Employee maxSalary(@RequestParam int departmentId){
        return departmentService.maxSalary(departmentId);
    }

    @GetMapping("/min-salary")
    public Employee minSalary(@RequestParam int departmentId){
        return departmentService.minSalary(departmentId);
    }

    @GetMapping(value = "/all", params = "departmentId")
    public Collection<Employee> getEmployeeToDepartment(@RequestParam int departmentId){
        return departmentService.getEmployeeToDepartment(departmentId);
    }

    @GetMapping(value = "/all")
    public Collection<List<Employee>> getEmployeeToDepartment(){
        return departmentService.getEmployeeToDepartment();
    }
}
