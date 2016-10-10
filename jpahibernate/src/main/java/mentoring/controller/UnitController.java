package mentoring.controller;

import mentoring.dao.UnitRepository;
import mentoring.model.Employee;
import mentoring.model.Unit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;

/**
 * Created by Vitali_Sheuka on 10/9/2016.
 */
@RestController
@RequestMapping("/mentoring/unit")
public class UnitController {

    private final UnitRepository unitRepository;

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    public UnitController(final UnitRepository unitRepository) {
        this.unitRepository = unitRepository;
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<Object> create(@RequestParam(value = "name", required = true) String name) {
        Unit unit = new Unit();
        unit.setName(name);
        unitRepository.save(unit);
        return new ResponseEntity<>(unit, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<Object> find(@RequestParam(value = "id", required = true) String id) {
        Unit unit = unitRepository.findOne(Long.parseLong(id));
        return new ResponseEntity<>(unit, HttpStatus.FOUND);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<Object> delete(@RequestParam(value = "id", required = true) String id) {
        Unit unit = unitRepository.findOne(Long.parseLong(id));
        for (Employee employee: unit.getEmployees()) {
            employee.setUnit(null);
        }
        unit.setEmployees(null);
        unitRepository.delete(unit);
        return new ResponseEntity<>("OK",HttpStatus.OK);
    }

    @Modifying(clearAutomatically = true)
    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<Object> update(@RequestParam(value = "id", required = true) String id,
                                                       @RequestParam(value = "name", required = true) String name) {
        Unit unit = unitRepository.findOne(Long.parseLong(id));
        unit.setName(name);
        unitRepository.save(unit);
        return new ResponseEntity<>(unit, HttpStatus.OK);
    }
}
