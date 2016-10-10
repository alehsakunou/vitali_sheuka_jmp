package mentoring.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @ManyToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    @JoinTable(name = "employee_project", joinColumns = @JoinColumn(name = "employee_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "project_id", referencedColumnName = "id"))
    private Set<Project> projects;

    /**
     * EmployeeStatus should be Enum type
     */
    public enum EmployeeStatus {
        FREE, PROJECT, MENTORING
    }

    @Column(name="status")
    @Enumerated(EnumType.STRING)
    private EmployeeStatus employeeStatus;

    @OneToOne(cascade = CascadeType.ALL)
    private Personal personal;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    @JoinColumn(name = "unit_id")
    private Unit unit;

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    /**
     * Address should be embedded to Employee object
     */
    public static class Address {
        public Address() {
        }

        private String street;

        public String getStreet() {
            return street;
        }

        public void setStreet(String street) {
            this.street = street;
        }
    }

    @Embedded
    private Address address;

    public Employee() {

    }

    public Personal getPersonal() {
        return personal;
    }

    public void setPersonal(Personal personal) {
        this.personal = personal;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public EmployeeStatus getEmployeeStatus() {
        return employeeStatus;
    }

    public void setEmployeeStatus(EmployeeStatus employeeStatus) {
        this.employeeStatus = employeeStatus;
    }

    public Employee(String name) {
        this.name = name;
    }

    public Employee(String name, Set<Project> projects){
        this.name = name;
        this.projects = projects;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }

    @Override
    public String toString() {
        String result = String.format(
                "Employee [id=%d, name='%s']%n",
                id, name);
        if (projects != null) {
            for(Project project : projects) {
                result += String.format(
                        "Project[id=%d, name='%s']%n",
                        project.getId(), project.getName());
            }
        }

        return result;
    }
}
