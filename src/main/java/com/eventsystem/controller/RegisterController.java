package com.eventsystem.controller;

import com.eventsystem.entities.Login;
import com.eventsystem.entities.Role;
import com.eventsystem.repository.LoginRepository;
import com.eventsystem.service.EmailService;
import com.eventsystem.service.LoginService;
import com.eventsystem.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;
import java.util.Optional;
import java.util.Random;

@Controller
public class RegisterController {
    private final LoginService loginService;
    private final LoginRepository loginRepository;
    private final EmailService emailService;
    private final RoleService roleService;
    private final PasswordEncoder encoder;

    @Autowired
    public RegisterController(LoginService loginService, LoginRepository loginRepository,
                              EmailService emailService, RoleService roleService,
                              PasswordEncoder encoder) {
        this.loginService = loginService;
        this.loginRepository = loginRepository;
        this.emailService = emailService;
        this.roleService = roleService;
        this.encoder = encoder;
    }

    @GetMapping(value = { "/login", "/" })
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public ModelAndView registerForm() {
        return new ModelAndView("register", "command", new Login());
    }

    @PostMapping("/register")
    public String registerNewUser(@ModelAttribute(name = "login") Login login) {
        Optional<Role> checkRole = roleService.getEntityById(3);
        Role role = null;
        if (checkRole.isPresent()) {
            role = checkRole.get();
        }
        login.setRoleId(role);
        login.setPassword(encoder.encode(login.getPassword()));
        loginService.createEntity(login);
        return "login";
    }

    @GetMapping("/resetPassword")
    public String resetPasswordForm() {
        return "resetPassword";
    }

    @PostMapping("/resetPassword")
    public String resetPassword(@RequestParam(name = "email") String email) throws MessagingException {
        Optional<Login> login = loginRepository.findLoginByEmail(email);
        if (login.isPresent()) {
            Login log = login.get();
            String password = new Random().ints(10, 33, 122)
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
            log.setPassword(encoder.encode(password));
            loginService.updateEntity(log);
            emailService.sendSimpleMessage(log.getEmail(), "Password reset", "Your password is now: " + password + " \nPlease log in and change your " +
                    "password to a new one!");

            return "login";
        }
        return "error";
    }
}
