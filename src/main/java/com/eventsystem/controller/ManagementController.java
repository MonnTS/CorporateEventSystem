package com.eventsystem.controller;

import com.eventsystem.entities.Login;
import com.eventsystem.entities.Role;
import com.eventsystem.service.LoginService;
import com.eventsystem.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ManagementController {
    private final LoginService loginService;
    private final RoleService roleService;
    private final PasswordEncoder encoder;

    @Autowired
    public ManagementController(LoginService loginService, RoleService roleService,
                                PasswordEncoder encoder) {
        this.loginService = loginService;
        this.roleService = roleService;
        this.encoder = encoder;
    }

    @GetMapping("/createUser")
    public ModelAndView createUserForm(ModelMap map) {
        List<Role> roleList = roleService.getEntities();
        map.addAttribute("roles", roleList);
        return new ModelAndView("createUser", "command", new Login());
    }

    @PostMapping("/createUser")
    public String createUser(@ModelAttribute Login login) {
        login.setPassword(encoder.encode(login.getPassword()));
        loginService.createEntity(login);
        return "redirect:/redirect";
    }

    @GetMapping("/changePassword")
    public String changePasswordForm() {
        return "changePassword";
    }

    @PostMapping("/changePassword")
    public String changePassword(@RequestParam(name = "password") String password,
                                 @RequestParam(name = "confirmPassword") String confirmPassword) {
        Login loggedUser = loginService.getLoggedUser();

        if (!password.equals(confirmPassword)) {
            return "error";
        }
        loggedUser.setPassword(encoder.encode(password));
        loginService.updateEntity(loggedUser);
        return "redirect:/redirect";
    }

    @GetMapping("/redirect")
    public String redirectPageForm() {
        Login loggedUser = loginService.getLoggedUser();
        String userRole = loggedUser.getRoleId().getRole();
        return switch (userRole) {
            case "ADMIN", "MANAGER" -> "redirect:/managerEvents";
            case "USER" -> "redirect:/events";
            default -> "redirect:/error";
        };
    }
}
