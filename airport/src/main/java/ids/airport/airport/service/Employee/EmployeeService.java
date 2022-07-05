package ids.airport.airport.service.Employee;

import ids.airport.airport.model.Employee;

import java.util.List;

public interface EmployeeService {

    Employee findById(long employeeId);

    List<Employee> findAll();

    void deleteLanguage(long employeeId);

    Employee updateLanguage(Employee employee);
}
