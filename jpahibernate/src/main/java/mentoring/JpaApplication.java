/*
 Create models: Employee, EmployeeStatus, Address, Personal, Project, Unit

 Take into account the following:

 EmployeeStatus should be Enum type
 Address should be embedded to Employee object
 Relationship between Employee and Personal should be one-to-one
 Relationship between Employee and Project should be many-to-many
 Relationship between Unit and Employee should be one-to-many
 Implement Service API which provides:

 Create Employee / Unit / Project
 Find employee / Unit / Project by id
 Delete employee / Unit / Project by id
 Update Employee / Unit / Project by id
 Add Employee to Unit by id’s
 Assign Employee for Project by id’s
 */
package mentoring;

import mentoring.dao.EmployeeRepository;
import mentoring.dao.ProjectRepository;
import mentoring.model.Employee;
import mentoring.model.Personal;
import mentoring.model.Project;
import mentoring.model.Unit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.transaction.Transactional;
import java.util.HashSet;

@SpringBootApplication
public class JpaApplication implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(JpaApplication.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ProjectRepository projectRepository;

    public static void main(String[] args) {
        SpringApplication.run(JpaApplication.class, args);
    }

    @Override
    @Transactional
    public void run(String... strings) throws Exception {

        Project projectA = new Project("Project A");
        Project projectB = new Project("Project B");
        Project projectC = new Project("Project C");

        Employee employee1 = new Employee("Employee A", new HashSet<Project>(){{
            add(projectA);
            add(projectB);
        }});
        employee1.setEmployeeStatus(Employee.EmployeeStatus.MENTORING);
        Employee.Address address = new Employee.Address();
        address.setStreet("Street 1");
        employee1.setAddress(address);
        Personal personal = new Personal();
        personal.setName("Name");
        personal.setSurname("Surname");

        Unit unit = new Unit();
        unit.setName("Unit 1");

        employee1.setUnit(unit);

        employeeRepository.save(new HashSet<Employee>(){{
            add(employee1);

            add(new Employee("Employee B", new HashSet<Project>(){{
                add(projectA);
                add(projectC);
            }}));
        }});


        for(Employee employee : employeeRepository.findAll()) {
            logger.info(employee.toString());
        }


        Employee employeeA = new Employee("Employee A");
        Employee employeeB = new Employee("Employee B");

        projectRepository.save(new HashSet<Project>() {{
            add(new Project("Project A", new HashSet<Employee>() {{
                add(employeeA);
                add(employeeB);
            }}));

            add(new Project("Project B", new HashSet<Employee>() {{
                add(employeeA);
                add(employeeB);
            }}));
        }});


        for(Project project : projectRepository.findAll()) {
            logger.info(project.toString());
        }
    }
}
