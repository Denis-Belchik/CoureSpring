package pro.sky.courseSpring.controller;

import org.springframework.web.bind.annotation.*;
import pro.sky.courseSpring.model.Employee;
import pro.sky.courseSpring.service.Department2Service;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/department")
public class Department2Controller {

    private final Department2Service department2Service;

    public Department2Controller(Department2Service department2Service) {
        this.department2Service = department2Service;
    }

    @GetMapping("{id}/salary/sum")
    public Integer sum(@PathVariable Integer id){
        return department2Service.sum(id);
    }

    @GetMapping("{id}/salary/max")
    public Integer max(@PathVariable Integer id){
        return department2Service.max(id);
    }

    @GetMapping("{id}/salary/min")
    public Integer min(@PathVariable Integer id){
        return department2Service.min(id);
    }

    @GetMapping("{id}/employees")
    public List<Employee> employees(@PathVariable Integer id){
        return department2Service.employees(id);
    }

    @GetMapping("/employees")
    public Map<Integer, List<Employee>> employees(){
        return department2Service.employees();
    }

}
