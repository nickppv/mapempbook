package pro.sky.mapempbook;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface IEmployeeService {
    Collection<Employee> displayAllEmployees();
    Employee addEmployer(String firstName, String lastName, int department, int salary);
    Employee removeEmployer(String firstName, String lastName, int department, int salary);
    Employee findEmployer(String firstName, String lastName, int department, int salary);
    Employee getEmpWithMinSalaryInSomeDepartment(int department);
    Employee getEmpWithMaxSalaryInSomeDepartment(int department);
    List<Employee> getEmpInSomeDepartment(int department);
    Map<Integer, List<Employee>> getEmpSeparateByDepartment();
}
