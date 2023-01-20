package com.eventsystem.controllers;

import com.eventsystem.EventSystemApplication;
import com.eventsystem.entities.Login;
import com.eventsystem.entities.Role;
import com.eventsystem.repository.LoginRepository;
import com.eventsystem.service.RoleService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = EventSystemApplication.class)
class RegisterControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private LoginRepository loginRepository;
    @MockBean
    private RoleService roleService;
    @Mock
    private Login login;

    @Test
    void test_onCallingLoginFormReturnsLoginForm() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(view().name("login"))
                .andExpect(status().isOk());
    }

    @Test
    void test_onCallingRegisterReturnsForm() throws Exception {
        mockMvc.perform(get("/register"))
                .andExpect(view().name("register"))
                .andExpect(status().isOk());
    }

    @Test
    void test_onCallingRegisterCreatesNewUser() throws Exception {
        when(roleService.getEntityById(anyInt())).thenReturn(Optional.of(mock(Role.class)));
        mockMvc.perform(post("/register").flashAttr("login", new Login(new Role(), "", "", "", "sad")))
                .andExpect(view().name("login"))
                .andExpect(status().isOk());
    }

    @Test
    void test_onCallingResetPasswordShowForm() throws Exception {
        mockMvc.perform(get("/resetPassword"))
                .andExpect(view().name("resetPassword"))
                .andExpect(status().isOk());
    }

    @Test
    void test_onCallingResetPasswordItResetsPassword() throws Exception {
        login = new Login(new Role(), "", "", "sometesting@gmail.com", "");
        when(loginRepository.findLoginByEmail(anyString())).thenReturn(Optional.of(login));
        mockMvc.perform(post("/resetPassword").param("email", anyString()))
                .andExpect(view().name("login"))
                .andExpect(status().isOk());
    }
}