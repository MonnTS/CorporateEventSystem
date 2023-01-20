package com.eventsystem.services;

import com.eventsystem.entities.Role;
import com.eventsystem.repository.RoleRepository;
import com.eventsystem.service.RoleService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
class RoleServiceTest {
    @MockBean
    private RoleRepository roleRepository;

    @InjectMocks
    private RoleService roleService;

    @Mock
    private Role role;

    @BeforeEach
    void setUp() {
        roleService = new RoleService(roleRepository);
    }

    @Test
    void test_onGetEntityById() {
        when(roleRepository.findById(anyInt())).thenReturn(Optional.of(mock(Role.class)));
        roleService.getEntityById(anyInt());
        verify(roleRepository, times(1)).findById(anyInt());
    }

    @Test
    void test_onGetEntityByIdThrowsNullPointerException() throws NullPointerException {
        doThrow(new NullPointerException()).when(roleRepository).findById(anyInt());
        Assertions.assertThrows(NullPointerException.class, () ->
                roleService.getEntityById(anyInt())
        );
    }

    @Test
    void test_onGetEntitiesRepresentedAsList() {
        roleService.getEntities();
        verify(roleRepository, times(1)).findAll();
    }

    @Test
    void test_onCreateEntityCallSave() {
        roleService.createEntity(role);
        verify(roleRepository, times(1)).save(role);
    }

    @Test
    void test_onUpdateEntityCallSave() {
        roleService.updateEntity(role);
        verify(roleRepository, times(1)).save(role);
    }

    @Test
    void test_onRemoveEntityCallDelete() {
        roleService.deleteEntity(role);
        verify(roleRepository, times(1)).delete(role);
    }
}