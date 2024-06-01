package pro.sky.mapempbook;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping(path = "/employee")
public class EmployeeController {

    private final EmployeeServiceImpl employeeServiceImpl;

    public EmployeeController(EmployeeServiceImpl employeeServiceImpl) {
        this.employeeServiceImpl = employeeServiceImpl;
    }

    @GetMapping
    public Collection<Employee> displayAllEmployees() {
        return employeeServiceImpl.displayAllEmployees();
    }

    @GetMapping(path = "/add")
    public Employee addEmployer(@RequestParam(value = "firstName", required = false) String firstName,
                                @RequestParam(value = "lastName", required = false) String lastName,
                                @RequestParam(value = "department", required = false) int department,
                                @RequestParam(value = "salary", required = false) int salary) {
        return employeeServiceImpl.addEmployer(firstName, lastName, department, salary);
    }

    @GetMapping(path = "/remove")
    public Employee removeEmployer(@RequestParam(value = "firstName", required = false) String firstName,
                                   @RequestParam(value = "lastName", required = false) String lastName,
                                   @RequestParam(value = "department", required = false) int department,
                                   @RequestParam(value = "salary", required = false) int salary) {
        return employeeServiceImpl.removeEmployer(firstName, lastName, department, salary);

    }

    @GetMapping(path = "/find")
    public Employee findEmployer(@RequestParam(value = "firstName", required = false) String firstName,
                                 @RequestParam(value = "lastName", required = false) String lastName,
                                 @RequestParam(value = "department", required = false) int department,
                                 @RequestParam(value = "salary", required = false) int salary) {
        return employeeServiceImpl.findEmployer(firstName, lastName, department, salary);

    }
}