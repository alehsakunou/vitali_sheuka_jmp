package mentoring.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Vitali_Sheuka on 10/9/2016.
 * Relationship between Unit and Employee should be one-to-many
 */
@Entity
public class Unit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "unit", cascade = CascadeType.ALL)
    private Set<Employee> employees;

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

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }
}
