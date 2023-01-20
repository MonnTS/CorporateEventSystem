package com.eventsystem.services;

import com.eventsystem.entities.Login;
import com.eventsystem.repository.LoginRepository;
import com.eventsystem.service.DefaultUserDetailsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
class DefaultUserDetailsServiceTest {
    @InjectMocks
    private DefaultUserDetailsService defaultUserDetailsService;
    @MockBean
    private LoginRepository loginRepository;

    @BeforeEach
    void setUp() {
        defaultUserDetailsService = new DefaultUserDetailsService(loginRepository);
    }

    @Test
    void test_onCallingLoadByUsernameThrowsAnException() throws UsernameNotFoundException {
        String username = "Something";
        doThrow(new UsernameNotFoundException(username)).when(loginRepository).findLoginByEmail(username);
        Assertions.assertThrows(UsernameNotFoundException.class, () ->
                defaultUserDetailsService.loadUserByUsername(username)
        );
    }

    @Test
    void test_calledFindByLoginEmail() {
        when(loginRepository.findLoginByEmail(anyString())).thenReturn(Optional.of(mock(Login.class)));
        defaultUserDetailsService.loadUserByUsername(anyString());
        verify(loginRepository, times(1)).findLoginByEmail(anyString());
    }
}