package mentoring.controller;

import mentoring.dao.ProjectRepository;
import mentoring.model.Employee;
import mentoring.model.Project;
import mentoring.model.Unit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Vitali_Sheuka on 10/9/2016.
 */
@RestController
@RequestMapping("/mentoring/project")
public class ProjectController {

    private final ProjectRepository projectRepository;

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    public ProjectController(final ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<Object> create(@RequestParam(value = "name", required = true) String name) {
        Project project = new Project();
        project.setName(name);
        projectRepository.save(project);
        return new ResponseEntity<>(project, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<Object> find(@RequestParam(value = "id", required = true) String id) {
        Project project = projectRepository.findOne(Long.parseLong(id));
        return new ResponseEntity<>(project, HttpStatus.FOUND);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<Object> delete(@RequestParam(value = "id", required = true) String id) {
        Project project = projectRepository.findOne(Long.parseLong(id));
        for (Employee employee: project.getEmployees()) {
            employee.getProjects().remove(project);
        }
        project.setEmployees(null);
        projectRepository.delete(project);
        return new ResponseEntity<>("OK",HttpStatus.OK);
    }

    @Modifying(clearAutomatically = true)
    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<Object> update(@RequestParam(value = "id", required = true) String id,
                                                       @RequestParam(value = "name", required = true) String name) {
        Project project = projectRepository.findOne(Long.parseLong(id));
        project.setName(name);
        projectRepository.save(project);
        return new ResponseEntity<>(project, HttpStatus.OK);
    }
}
