package com.eventsystem.component;

import com.eventsystem.entities.Login;
import com.eventsystem.entities.Role;
import com.eventsystem.repository.LoginRepository;
import com.eventsystem.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataImport implements ApplicationRunner {
    private final LoginRepository loginRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;

    @Autowired
    public DataImport(LoginRepository loginRepository, RoleRepository roleRepository, PasswordEncoder encoder) {
        this.loginRepository = loginRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
    }

    @Override
    public void run(ApplicationArguments args) {
        Role admin = new Role(1,"Admin");
        Role manager = new Role(2,"Manager");
        Role user = new Role(3,"User");
        List<Role> roles = new ArrayList<>();
        roles.add(admin);
        roles.add(manager);
        roles.add(user);

        if (roleRepository.findAll().isEmpty()){
            roleRepository.saveAll(roles);
        }

        if (loginRepository.findAll().isEmpty()) {
            initData(roles);
        }
    }

    private void initData(List<Role> roles) {
        Role admin = roles.get(0);
        Role manager = roles.get(1);
        Role user = roles.get(2);
        Login login = new Login(admin, "Karen", "Lozano", "fboabjfofboiafa@gmail.com", encoder.encode("amogus"));
        loginRepository.save(login);
        Login login1 = new Login(manager, "Ellis", "Wagner","fartqrtrt@gmail.com", encoder.encode("amogus"));
        loginRepository.save(login1);
        Login login2 = new Login(user, "Robyn", "Howe","adddss@gmail.com", encoder.encode("amogus"));
        loginRepository.save(login2);
        Login login3 = new Login(user, "Kathryn", "Webb", "test@gmail.com", encoder.encode("test"));
        loginRepository.save(login3);
        Login login4 = new Login( user, "Isobelle", "Marsh","bbbsss@gmail.com", encoder.encode("test"));
        loginRepository.save(login4);
    }
}
