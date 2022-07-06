package ids.airport.airport.service.Employee;

import ids.airport.airport.model.Employee;

import java.util.List;

public interface EmployeeService {

    Employee findById(long employeeId);

    List<Employee> findAll();

    void deleteEmployee(long employeeId);

    Employee updateEmployee(Employee employee);
}
