package mentoring.controller;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import mentoring.dao.EmployeeRepository;
import mentoring.dao.ProjectRepository;
import mentoring.dao.UnitRepository;
import mentoring.model.Employee;
import mentoring.model.Personal;
import mentoring.model.Project;
import mentoring.model.Unit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Vitali_Sheuka on 10/9/2016.
 *
 Create Employee / Unit / Project
 Find employee / Unit / Project by id
 Delete employee / Unit / Project by id
 Update Employee / Unit / Project by id
 Add Employee to Unit by id’s
 Assign Employee for Project by id’s
 */
@RestController
@RequestMapping("/mentoring/employee")
public class EmployeeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

    private final EmployeeRepository employeeRepository;
    private final UnitRepository unitRepository;
    private final ProjectRepository projectRepository;

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    public EmployeeController(final EmployeeRepository employeeRepository, final UnitRepository unitRepository,
                              final ProjectRepository projectRepository) {
        this.employeeRepository = employeeRepository;
        this.unitRepository = unitRepository;
        this.projectRepository = projectRepository;
    }


//    @RequestMapping(value = "/create", method = RequestMethod.POST)
//    public @ResponseBody ResponseEntity<Object> create(@Valid @RequestBody(required = true) Employee employee,
//                                                       BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
//        }
//        employeeRepository.save(employee);
//        return new ResponseEntity<>(employee, HttpStatus.CREATED);
//    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<Object> create(@RequestParam(value = "name", required = true) String name,
                                                       @RequestParam(value = "surname", required = true) String surname,
                                                       @RequestParam(value = "street", required = true) String street) {

        Employee employee = new Employee(name, new HashSet<Project>(){});

        Employee.Address address = new Employee.Address();
        address.setStreet(street);
        employee.setAddress(address);

        Personal personal = new Personal();
        personal.setName(name);
        personal.setSurname(surname);

        employeeRepository.save(employee);
        return new ResponseEntity<>(employee, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<Object> find(@RequestParam(value = "id", required = true) String id) {
        Employee employee = employeeRepository.findOne(Long.parseLong(id));
        return new ResponseEntity<>(employee, HttpStatus.FOUND);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<Object> delete(@RequestParam(value = "id", required = true) String id) {
        Employee employee = employeeRepository.findOne(Long.parseLong(id));
        employee.setProjects(null);
        employeeRepository.delete(employee);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Modifying(clearAutomatically = true)
    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<Object> update(@RequestParam(value = "id", required = true) String id,
                                                       @RequestParam(value = "status", required = true) String status) {
        Employee employee = employeeRepository.findOne(Long.parseLong(id));
        employee.setEmployeeStatus(Employee.EmployeeStatus.valueOf(status));
        employeeRepository.save(employee);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<Object> add(@RequestParam(value = "employee_id", required = true) String employee_id, @RequestParam(value = "unit_id", required = true) String unit_id) {
        Employee employee = employeeRepository.findOne(Long.parseLong(employee_id));
        Unit unit = unitRepository.findOne(Long.parseLong(unit_id));
        employee.setUnit(unit);
        employeeRepository.save(employee);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @RequestMapping(value = "/assign", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<Object> assign(@RequestParam(value = "employee_id", required = true) String employee_id, @RequestParam(value = "project_id", required = true) String project_id) {
        Employee employee = employeeRepository.findOne(Long.parseLong(employee_id));
        Project project = projectRepository.findOne(Long.parseLong(project_id));
        employee.getProjects().add(project);
        employeeRepository.save(employee);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<Object> list() {
        List<Employee> employees = employeeRepository.findAll();
        return new ResponseEntity<>(employees, HttpStatus.FOUND);
    }

}
