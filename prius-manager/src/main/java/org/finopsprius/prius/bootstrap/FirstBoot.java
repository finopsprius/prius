package org.finopsprius.prius.bootstrap;

import jakarta.transaction.Transactional;
import org.finopsprius.prius.entities.user.User;
import org.finopsprius.prius.entities.user.UserRepository;
import org.jboss.logging.Logger;

import java.util.Optional;

public class FirstBoot {
    private static final Logger LOGGER = Logger.getLogger("PriusLogger");

    @Transactional
    public static void initializeTables() {
        Optional<User> rootuser =
                User.find("#User.findByUsername", "root@root.com").firstResultOptional();

        if (rootuser.isEmpty()) {
            try {
                UserRepository userRepository = new UserRepository();
                User user = userRepository.add("root@root.com", "admin", "admin");
                LOGGER.info("User \"root@root.com\" has been created with ID: " + user.getId());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        Optional<User> testuser =
                User.find("#User.findByUsername", "test@test.com").firstResultOptional();
        if (testuser.isEmpty()) {
            try {
                UserRepository userRepository = new UserRepository();
                User user = userRepository.add("test@test.com", "user", "user");
                LOGGER.info("User \"test@test.com\" has been created with ID: " + user.getId());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
