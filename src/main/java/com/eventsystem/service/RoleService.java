package com.eventsystem.service;

import com.eventsystem.entities.Role;
import com.eventsystem.interfaces.ICrud;
import com.eventsystem.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService implements ICrud<Role> {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Optional<Role> getEntityById(Integer id) {
        return Optional.ofNullable(roleRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("Role object with id " + id + " doesn't exist!")));

    }

    @Override
    public List<Role> getEntities() {
        return roleRepository.findAll();
    }

    @Override
    public void createEntity(Role role) {
        roleRepository.save(role);
    }

    @Override
    public void updateEntity(Role role) {
        roleRepository.save(role);
    }

    @Override
    public void deleteEntity(Role role) {
        roleRepository.delete(role);
    }
}
