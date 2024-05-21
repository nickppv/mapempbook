package pro.sky.mapempbook;

import org.springframework.stereotype.Service;
import pro.sky.exceptions.EmployeeAlreadyAddedException;
import pro.sky.exceptions.EmployeeNotFoundException;
import pro.sky.exceptions.EmployeeStorageIsFullException;

import java.util.*;

@Service
public class EmployeeServiceImpl implements IEmployeeService {
    private final int MAX_EMPLOYEE_COUNT = 30;
    static int id = 0;

    Map<Employee, Integer> mapEmp = new HashMap<>();

    // метод для добавления сотрудника
    public Employee addEmployer(String firstName, String lastName) throws EmployeeStorageIsFullException, EmployeeAlreadyAddedException {
        Employee emp = new Employee(firstName, lastName);
        if (mapEmp.containsKey(emp)) {
            throw new EmployeeAlreadyAddedException("Такой работник уже есть в нашем хозяйстве.");
        } else if (mapEmp.size() >= MAX_EMPLOYEE_COUNT) {
            throw new EmployeeStorageIsFullException("Массив в работниками полностью заполнен.");
        } else {
            mapEmp.put(emp, id++);
            return emp;
        }
    }

    // метод для удаления сотрудника
    public Employee removeEmployer(String firstName, String lastName) {
        Employee emp = new Employee(firstName, lastName);
        if (mapEmp.containsKey(emp)) {
            mapEmp.remove(emp);
            return emp;
        }
        throw new EmployeeNotFoundException("Не найден работник для его утилизации.");
    }

    // метод для получения сотрудника
    public Employee findEmployer(String firstName, String lastName) {
        Employee emp = new Employee(firstName, lastName);
        if (mapEmp.containsKey(emp)) {
            return emp;
        } else {
            throw new EmployeeNotFoundException("Не найден работник с таким именем и фамилией.");
        }
    }

    // все сотрудники
    public Collection<Employee> displayAllEmployees() {
        return Collections.unmodifiableSet(mapEmp.keySet());
    }
}
