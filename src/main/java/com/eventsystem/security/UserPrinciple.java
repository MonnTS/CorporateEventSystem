package com.eventsystem.security;

import com.eventsystem.entities.Login;
import com.eventsystem.entities.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserPrinciple implements UserDetails {
    private final Login login;

    public UserPrinciple(Login login) {
        this.login = login;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<Role> roleList = new ArrayList<>();
        roleList.add(login.getRoleId());
        return roleList;
    }

    @Override
    public String getPassword() {
        return login.getPassword();
    }

    @Override
    public String getUsername() {
        return login.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
