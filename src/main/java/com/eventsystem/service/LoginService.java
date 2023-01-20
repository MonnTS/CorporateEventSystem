package com.eventsystem.service;

import com.eventsystem.entities.Login;
import com.eventsystem.interfaces.ICrud;
import com.eventsystem.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoginService implements ICrud<Login> {
    private final LoginRepository loginRepository;

    @Autowired
    public LoginService(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    @Override
    public Optional<Login> getEntityById(Integer id) {
        return Optional.ofNullable(loginRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("Login object with id " + id + " doesn't exist!")));
    }

    @Override
    public List<Login> getEntities() {
        return loginRepository.findAll();
    }

    @Override
    public void createEntity(Login login) {
        loginRepository.save(login);
    }

    @Override
    public void updateEntity(Login login) {
        loginRepository.save(login);
    }

    @Override
    public void deleteEntity(Login login) {
        loginRepository.delete(login);
    }

    public Login getLoggedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Optional<Login> usr = loginRepository.findLoginByEmail(email);
        Login loggedUser = null;

        if (usr.isPresent()) {
            loggedUser = usr.get();
            return loggedUser;
        }

        return loggedUser;
    }
}
