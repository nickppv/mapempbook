package pro.sky.mapempbook;

import java.util.Collection;

public interface IEmployeeService {
    Collection<Employee> displayAllEmployees();
    Employee addEmployer(String firstName, String lastName);
    Employee removeEmployer(String firstName, String lastName);
    Employee findEmployer(String firstName, String lastName);
}
