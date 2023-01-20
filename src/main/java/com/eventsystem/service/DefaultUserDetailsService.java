package com.eventsystem.service;

import com.eventsystem.entities.Login;
import com.eventsystem.repository.LoginRepository;
import com.eventsystem.security.UserPrinciple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DefaultUserDetailsService implements UserDetailsService {
    private final LoginRepository loginRepository;
    @Autowired
    public DefaultUserDetailsService(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Login login = loginRepository.findLoginByEmail(username).orElseThrow(
                () -> new UsernameNotFoundException(username));
        return new UserPrinciple(login);
    }
}
