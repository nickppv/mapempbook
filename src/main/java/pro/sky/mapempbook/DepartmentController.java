package pro.sky.mapempbook;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/department")
public class DepartmentController {
    private final EmployeeServiceImpl employeeServiceImpl;

    public DepartmentController(EmployeeServiceImpl employeeServiceImpl) {
        this.employeeServiceImpl = employeeServiceImpl;
    }


    @GetMapping(path = "/min-salary")
    public Employee minSalaryInSomeDepartment(
            @RequestParam(value = "department", required = false) int department) {
        return employeeServiceImpl.getEmpWithMinSalaryInSomeDepartment(department);
    }

    @GetMapping(path = "/max-salary")
    public Employee maxSalaryInSomeDepartment(
            @RequestParam(value = "department", required = false) int department) {
        return employeeServiceImpl.getEmpWithMaxSalaryInSomeDepartment(department);
    }


    @GetMapping(path = "/all")
    public List<Employee> allInSomeDepartment(
            @RequestParam(value = "department", required = false) int department) {
        return employeeServiceImpl.getEmpInSomeDepartment(department);
    }

    @GetMapping(path = "/all-separated")
    public Map<Integer, List<Employee>> allSeparateByDep(){
        return employeeServiceImpl.getEmpSeparateByDepartment();
    }


}
