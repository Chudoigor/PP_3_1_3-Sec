package ru.kata.spring.boot_security.demo.initialization;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.annotation.PostConstruct;
import java.time.LocalDate;

@Component
public class InitDB {

    private final RoleService roleService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public InitDB(RoleService roleService, UserService userService, PasswordEncoder passwordEncoder) {
        this.roleService = roleService;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    private void fillDb() {
        Role roleAdmin = new Role("ROLE_ADMIN");
        Role roleUser = new Role("ROLE_USER");

        User admin = new User("admin1", "user1", "lastName1", LocalDate.of(2024, 7, 10), passwordEncoder.encode("admin"));
        admin.addRole(roleService.add(roleAdmin));
        userService.add(admin);

        User user = new User("user", "user2", "lastName2", LocalDate.of(2024, 7, 10), passwordEncoder.encode("user"));
        user.addRole(roleService.add(roleUser));
        userService.add(user);

        User userAdmin = new User("admin2", "user3", "lastName3", LocalDate.of(2024, 7, 10), passwordEncoder.encode("admin"));
        userAdmin.addRole(roleService.add(roleUser));
        userAdmin.addRole(roleService.add(roleAdmin));
        userService.add(userAdmin);
    }
}


