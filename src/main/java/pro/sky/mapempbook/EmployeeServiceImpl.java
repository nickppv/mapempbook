package pro.sky.mapempbook;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import pro.sky.exceptions.EmployeeAlreadyAddedException;
import pro.sky.exceptions.EmployeeNotFoundException;
import pro.sky.exceptions.EmployeeStorageIsFullException;
import pro.sky.exceptions.ExtraLettersException;

import java.util.*;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

@Service
public class EmployeeServiceImpl implements IEmployeeService {
    private final int MAX_EMPLOYEE_COUNT = 30;
    static int id = 0;

    Map<String, Employee> mapEmp = new HashMap<>();

    @PostConstruct
    public void init() {
        mapEmp.put("Emp1", new Employee("l", "k", 3, 374));
        mapEmp.put("Emp2", new Employee("sd", "hj", 4, 290));
        mapEmp.put("Emp3", new Employee("d", "rt", 1, 104));
        mapEmp.put("Emp4", new Employee("fy", "qw", 3, 547));
        mapEmp.put("Emp5", new Employee("j", "po", 2, 370));
        mapEmp.put("Emp6", new Employee("f", "aj", 1, 164));
        mapEmp.put("Emp7", new Employee("q", "w", 3, 140));
        mapEmp.put("Emp8", new Employee("ai", "qk", 2, 501));
        mapEmp.put("Emp9", new Employee("rb", "j", 4, 167));
        mapEmp.put("Emp10", new Employee("zt", "dk", 3, 304));
        mapEmp.put("Emp12", new Employee("yg", "pf", 2, 165));
        mapEmp.put("Emp13", new Employee("lgg", "gh", 1, 314));
        mapEmp.put("Emp14", new Employee("pq", "ei", 4, 278));
        mapEmp.put("Emp15", new Employee("bv", "ert", 1, 664));
    }

    // метод для добавления сотрудника
    public Employee addEmployer(String firstName, String lastName, int department, int salary) throws EmployeeStorageIsFullException, EmployeeAlreadyAddedException {
        if (StringUtils.containsAny(firstName, "1234567890/*&%?()№@-+=!'\"_,.:;") ||
                StringUtils.containsAny(lastName, "1234567890/*&%?()№@-+=!'\"_,.:;")) {
            throw new ExtraLettersException("Введены запрещенные символы");
        }
        Employee emp = new Employee(firstName.substring(0, 1).toUpperCase() + firstName.substring(1),
                lastName.substring(0, 1).toUpperCase() + lastName.substring(1),
                department,
                salary);
        if (mapEmp.containsKey(emp.getFullName())) {
            throw new EmployeeAlreadyAddedException("Такой работник уже есть в нашем хозяйстве.");
        } else if (mapEmp.size() >= MAX_EMPLOYEE_COUNT) {
            throw new EmployeeStorageIsFullException("Массив в работниками полностью заполнен.");
        } else {
            mapEmp.put(emp.getFullName(), emp);
            id++;
            return emp;
        }
    }

    // метод для удаления сотрудника
    public Employee removeEmployer(String firstName, String lastName, int department, int salary) {
        if (StringUtils.containsAny(firstName, "1234567890/*&%?()№@-+=!'\"_,.:;") ||
                StringUtils.containsAny(lastName, "1234567890/*&%?()№@-+=!'\"_,.:;")) {
            throw new ExtraLettersException("Введены запрещенные символы");
        }
        Employee emp = new Employee(firstName, lastName, department, salary);
        if (mapEmp.containsKey(emp.getFullName())) {
            mapEmp.remove(emp.getFullName());
            id--;
            return emp;
        }
        throw new EmployeeNotFoundException("Не найден работник для его утилизации.");
    }

    // метод для получения сотрудника
    public Employee findEmployer(String firstName, String lastName, int department, int salary) {
        if (StringUtils.containsAny(firstName, "1234567890/*&%?()№@-+=!'\"_,.:;") ||
                StringUtils.containsAny(lastName, "1234567890/*&%?()№@-+=!'\"_,.:;")) {
            throw new ExtraLettersException("Введены запрещенные символы");
        }
        Employee emp = new Employee(firstName, lastName, department, salary);
        if (mapEmp.containsKey(emp.getFullName())) {
            return emp;
        } else {
            throw new EmployeeNotFoundException("Не найден работник с таким именем и фамилией.");
        }
    }

    // получаем сотрудника с минимальной зарплатой
    public Employee getEmpWithMinSalaryInSomeDepartment(int department) {
        return mapEmp.values().stream().
                filter(emp -> emp.getDepartment() == department).
                min(Comparator.comparing(Employee::getSalary)).orElse(null);
    }

    // получаем сотрудника с максимальной зарплатой
    public Employee getEmpWithMaxSalaryInSomeDepartment(int department) {
        return mapEmp.values().stream().
                filter(emp -> emp.getDepartment() == department).
                max(Comparator.comparing(Employee::getSalary)).orElse(null);
    }

    // получаем список сотрудников определенного отдела
    public List<Employee> getEmpInSomeDepartment(int department) {
        return mapEmp.values().stream().
                filter(emp -> emp.getDepartment() == department).toList();
    }

    // получаем сотрудников с группировкой по отделам
    public Map<Integer, List<Employee>> getEmpSeparateByDepartment() {
        return mapEmp.values().stream().collect(Collectors.groupingBy(Employee::getDepartment));
    }

    // все сотрудники
    public Collection<Employee> displayAllEmployees() {
        return Collections.unmodifiableCollection(mapEmp.values());
    }
}
