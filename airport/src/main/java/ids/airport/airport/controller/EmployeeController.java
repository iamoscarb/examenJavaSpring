package ids.airport.airport.controller;

import ids.airport.airport.exception.Mensaje;
import ids.airport.airport.model.Employee;
import ids.airport.airport.service.Employee.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    //petición tipo GET
    @GetMapping("/listEmployees")
    public ResponseEntity<?> getAllEmployees(){
        List<Employee> lista = employeeService.findAll();
        if(lista.isEmpty()){
            return new ResponseEntity<>(new Mensaje("No employees to show"), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok().body(employeeService.findAll());
    }

    //petición tipo GET
    @GetMapping("/findEmployee/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable long id) {
        return ResponseEntity.ok().body(employeeService.findById(id));
    }

    //petición PUT
    @PutMapping("/updateEmployee/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable long id, @RequestBody Employee employee){
        employee.setId(id);
        return ResponseEntity.ok().body(this.employeeService.updateEmployee(employee));
    }

    @DeleteMapping("/deleteEmployee/{id}")
    public HttpStatus deleteEmployee(@PathVariable long id){
        this.employeeService.deleteEmployee(id);
        return HttpStatus.OK;
    }
}
