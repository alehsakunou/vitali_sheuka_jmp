package mentoring.controller;

import mentoring.dao.UserRepository;
import mentoring.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/mentoring/user")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private final UserRepository userRepository;

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    public UserController(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<Object> create(@RequestParam(value = "name", required = true) String name,
                                                       @RequestParam(value = "street", required = true) String street)
    {
        User user = new User(name, street);
        userRepository.save(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<Object> find(@RequestParam(value = "id", required = true) String id) {
        User user = userRepository.findOne(Long.parseLong(id));
        return new ResponseEntity<>(user, HttpStatus.FOUND);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<Object> delete(@RequestParam(value = "id", required = true) String id) {
        User user = userRepository.findOne(Long.parseLong(id));
        userRepository.delete(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Modifying(clearAutomatically = true)
    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<Object> update(@RequestParam(value = "id", required = true) String id,
                                                       @RequestParam(value = "name", required = true) String name,
                                                       @RequestParam(value = "street", required = true) String street
                                                       )
    {
        User user = userRepository.findOne(Long.parseLong(id));
        user.setName(name);
        user.setStreet(street);
        userRepository.save(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<Object> list() {
        List<User> users = userRepository.findAll();
        return new ResponseEntity<>(users, HttpStatus.FOUND);
    }

}
