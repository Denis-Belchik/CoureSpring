package pro.sky.courseSpring.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.courseSpring.exeptions.NullDepartmentException;
import pro.sky.courseSpring.model.Employee;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class Department2ServiceImplTest {

    @InjectMocks
    private Department2ServiceImpl department2Service;

    private Map<String, Employee> employees;

    @Mock
    private EmployeeServiceMapImpl employeeServiceMapMock;

    @BeforeEach
    void init() {
        this.employees = new HashMap<>();
        this.employees.put("DenisBelchik", new Employee("Denis", "Belchik", 500, 1));
        this.employees.put("SaraIvanov", new Employee("Sara", "Ivanov", 5400, 2));
        this.employees.put("IvanPetrov", new Employee("Ivan", "Petrov", 300, 2));
        this.employees.put("FedorFedorov", new Employee("Fedor", "Fedorov", 100, 2));
    }

    private static Stream<Arguments> positiveSum() {
        return Stream.of(
                Arguments.of(1, 500),
                Arguments.of(2, 5800),
                Arguments.of(3, -1)
        );
    }

    private static Stream<Arguments> positiveMax() {
        return Stream.of(
                Arguments.of(1, 500),
                Arguments.of(2, 5400),
                Arguments.of(3, -1)
        );
    }

    private static Stream<Arguments> positiveMin() {
        return Stream.of(
                Arguments.of(1, 500),
                Arguments.of(2, 100),
                Arguments.of(3, -1)
        );
    }

    @ParameterizedTest
    @MethodSource("positiveSum")
    public void sumPositiveTest(Integer department, Integer sum) {
        when(employeeServiceMapMock.getAllEmployee()).thenReturn(employees.values());
        assertEquals(sum, department2Service.sum(department));
    }

    @Test
    public void sumNullDepartmentThenThrowNullDepartmentException() {
        assertThrows(NullDepartmentException.class, () -> department2Service.sum(null));
    }

    @ParameterizedTest
    @MethodSource("positiveMax")
    public void maxPositiveTest(Integer department, Integer max) {
        when(employeeServiceMapMock.getAllEmployee()).thenReturn(employees.values());
        assertEquals(max, department2Service.max(department));
    }

    @Test
    public void maxNullDepartmentThenThrowNullDepartmentException() {
        assertThrows(NullDepartmentException.class, () -> department2Service.max(null));
    }

    @ParameterizedTest
    @MethodSource("positiveMin")
    public void minPositiveTest(Integer department, Integer min) {
        when(employeeServiceMapMock.getAllEmployee()).thenReturn(employees.values());
        assertEquals(min, department2Service.min(department));
    }

    @Test
    public void minNullDepartmentThenThrowNullDepartmentException() {
        assertThrows(NullDepartmentException.class, () -> department2Service.min(null));
    }

    @Test
    public void employeesPositive() {
        when(employeeServiceMapMock.getAllEmployee()).thenReturn(employees.values());
        List<Employee> expected = List.of(new Employee("Sara", "Ivanov", 5400, 2),
                new Employee("Ivan", "Petrov", 300, 2),
                new Employee("Fedor", "Fedorov", 100, 2));

        assertIterableEquals(expected, department2Service.employees(2));
    }

    @Test
    public void employeesUnknownDepartment() {
        when(employeeServiceMapMock.getAllEmployee()).thenReturn(employees.values());
        List<Employee> expected = List.of();
        assertIterableEquals(expected, department2Service.employees(20));
    }

    @Test
    public void employeesNullDepartment(){
        assertThrows(NullDepartmentException.class, () -> department2Service.employees(null));
    }
}
