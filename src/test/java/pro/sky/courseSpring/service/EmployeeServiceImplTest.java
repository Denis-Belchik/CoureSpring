package pro.sky.courseSpring.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pro.sky.courseSpring.exeptions.EmployeeAlreadyAddedException;
import pro.sky.courseSpring.exeptions.EmployeeNotFoundException;
import pro.sky.courseSpring.model.Employee;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeServiceImplTest {

    private final EmployeeServiceMapImpl employeeService = new EmployeeServiceMapImpl();
    private Map<String, Employee> employees;

    static Stream<Arguments> employeeNotInMap() {
        return Stream.of(
                Arguments.of("Denisq", "7tj", 5200, 1),
                Arguments.of("asfasdv", "gfk", 5100, 2),
                Arguments.of("Desvisq", "Belhikq", 5400, 3),
                Arguments.of("Fedgjnor", "Fe67dorov", 100, 4)
        );
    }

    static Stream<Arguments> employeeInMap() {
        return Stream.of(
                Arguments.of("Denis", "Belchik", 500, 1),
                Arguments.of("Sara", "Ivanov", 5400, 2),
                Arguments.of("Ivan", "Petrov", 300, 3),
                Arguments.of("Fedor", "Fedorov", 100, 4)
        );
    }

    @BeforeEach
    void init() {
        this.employees = new HashMap<>();
        this.employees.put("DenisBelchik", new Employee("Denis", "Belchik", 500, 1));
        this.employees.put("SaraIvanov", new Employee("Sara", "Ivanov", 5400, 2));
        this.employees.put("IvanPetrov", new Employee("Ivan", "Petrov", 300, 3));
        this.employees.put("FedorFedorov", new Employee("Fedor", "Fedorov", 100, 4));

        employeeService.addEmployee("Denis", "Belchik", 500, 1);
        employeeService.addEmployee("Sara", "Ivanov", 5400, 2);
        employeeService.addEmployee("Ivan", "Petrov", 300, 3);
        employeeService.addEmployee("Fedor", "Fedorov", 100, 4);
    }

    @ParameterizedTest
    @MethodSource("employeeNotInMap")
    public void addEmployeePositiveResult(String name, String surname, Integer salary, Integer department) {
        Employee actual = employeeService.addEmployee(name, surname, salary, department);
        Employee expected = new Employee(name, surname, salary, department);
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("employeeInMap")
    public void addEmployeeNegativeResult(String name, String surname, Integer salary, Integer department) {
        assertThrows(EmployeeAlreadyAddedException.class,
                () -> employeeService.addEmployee(name, surname, salary, department));
    }

    @ParameterizedTest
    @MethodSource("employeeNotInMap")
    public void addEmployeeInMapPositiveResult(String name, String surname, Integer salary, Integer department) {
        employeeService.addEmployee(name, surname, salary, department);
        Employee employee = new Employee(name, surname, salary, department);
        employees.put(name + surname, employee);
        assertIterableEquals(employees.values(), employeeService.getAllEmployee());
    }

    @ParameterizedTest
    @MethodSource("employeeInMap")
    public void removeEmployeePositiveResult(String name, String surname, Integer salary, Integer department) {
        Employee actual = employeeService.removeEmployee(name, surname, salary, department);
        Employee expected = new Employee(name, surname, salary, department);
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("employeeNotInMap")
    public void removeEmployeeNegativeResult(String name, String surname, Integer salary, Integer department) {
        assertThrows(EmployeeNotFoundException.class,
                () -> employeeService.removeEmployee(name, surname, salary, department));
    }

    @ParameterizedTest
    @MethodSource("employeeInMap")
    public void removeEmployeeInMapPositiveResult(String name, String surname, Integer salary, Integer department) {
        employeeService.removeEmployee(name, surname, salary, department);
        Employee employee = new Employee(name, surname, salary, department);
        employees.remove(name + surname, employee);
        assertIterableEquals(employees.values(), employeeService.getAllEmployee());
    }

    @ParameterizedTest
    @MethodSource("employeeInMap")
    public void findEmployeePositiveResult(String name, String surname, Integer salary, Integer department) {
        Employee actual = employeeService.findEmployee(name, surname, salary, department);
        Employee expected = new Employee(name, surname, salary, department);
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("employeeNotInMap")
    public void findEmployeeNegativeResult(String name, String surname, Integer salary, Integer department) {
        assertThrows(EmployeeNotFoundException.class,
                () -> employeeService.findEmployee(name, surname, salary, department));
    }

    @Test
    public void getAllEmployee() {
        assertIterableEquals(employees.values(), employeeService.getAllEmployee());
    }

}
