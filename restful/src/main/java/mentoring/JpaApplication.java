/*
 Create Rest Services for User.
 Implement Add, Update, Change, Delete. Also build the structure for list of users  and opportunity to select single user.
Close

 */
package mentoring;


import mentoring.dao.UserRepository;
import mentoring.model.User;
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
    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(JpaApplication.class, args);
    }

    @Override
    @Transactional
    public void run(String... strings) throws Exception {

        User user1 = new User("User 1", "Street 1");
        User user2 = new User("User 2", "Street 2");

        userRepository.save(new HashSet<User>(){{
            add(user1);
            add(user2);
        }});
    }
}
