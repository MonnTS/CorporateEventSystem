package com.eventsystem.services;

import com.eventsystem.entities.Login;
import com.eventsystem.repository.LoginRepository;
import com.eventsystem.service.LoginService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
@WithMockUser(roles = "ADMIN")
class LoginServiceTest {
    @MockBean
    private LoginRepository loginRepository;

    @InjectMocks
    private LoginService loginService;

    @Mock
    private Login login;

    @BeforeEach
    void setUp() {
        loginService = new LoginService(loginRepository);
    }

    @Test
    void test_onGetEntityById() {
        when(loginRepository.findById(anyInt())).thenReturn(Optional.of(mock(Login.class)));
        loginService.getEntityById(anyInt());
        verify(loginRepository, times(1)).findById(anyInt());
    }

    @Test
    void test_onGetEntityByIdThrowsNullPointerException() throws NullPointerException {
        doThrow(new NullPointerException()).when(loginRepository).findById(anyInt());
        Assertions.assertThrows(NullPointerException.class, () ->
                loginService.getEntityById(anyInt())
        );
    }

    @Test
    void test_onGetEntitiesRepresentedAsList() {
        loginService.getEntities();
        verify(loginRepository, times(1)).findAll();
    }

    @Test
    void test_onCreateEntityCallSave() {
        loginService.createEntity(login);
        verify(loginRepository, times(1)).save(login);
    }

    @Test
    void test_onUpdateEntityCallSave() {
        loginService.updateEntity(login);
        verify(loginRepository, times(1)).save(login);
    }

    @Test
    void test_onRemoveEntityCallDelete() {
        loginService.deleteEntity(login);
        verify(loginRepository, times(1)).delete(login);
    }

    @Test
    void test_onFindingLoggedUserReturnUser() {
        when(loginRepository.findLoginByEmail(anyString())).thenReturn(Optional.of(new Login()));
        loginService.getLoggedUser();
        verify(loginRepository, times(1)).findLoginByEmail(anyString());
    }
}
