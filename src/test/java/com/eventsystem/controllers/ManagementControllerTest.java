package com.eventsystem.controllers;

import com.eventsystem.EventSystemApplication;
import com.eventsystem.entities.Login;
import com.eventsystem.entities.Role;
import com.eventsystem.service.LoginService;
import com.eventsystem.service.RoleService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = EventSystemApplication.class)
@WithMockUser(roles = { "ADMIN" }, username = "amogus")
class ManagementControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private LoginService loginService;
    @MockBean
    private RoleService roleService;
    @Mock
    private Login login;

    @Test
    void test_onCreationNewUserShowForm() throws Exception {
        List<Role> roles = roleService.getEntities();
        mockMvc.perform(get("/createUser"))
                .andExpect(status().isOk())
                .andExpect(view().name("createUser"))
                .andExpect(model().attribute("roles", roles));
    }

    @Test
    void test_onCreationNewUserCreatesNewUser() throws Exception {
        mockMvc.perform(post("/createUser").flashAttr("login", new Login(new Role(), "", "", "", "")))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/redirect"));
    }

    @Test
    void test_onChangingPasswordShowForm() throws Exception {
        mockMvc.perform(get("/changePassword"))
                .andExpect(view().name("changePassword"))
                .andExpect(status().isOk());
    }

    @Test
    void test_onChangingPasswordItChangesPassword() throws Exception {
        when(loginService.getLoggedUser()).thenReturn(login);
        mockMvc.perform(post("/changePassword").param("password", "123").param("confirmPassword", "123"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/redirect"));
    }

    @Test
    void test_onChangingPasswordsShowsErrorPage() throws Exception {
        when(loginService.getLoggedUser()).thenReturn(login);
        mockMvc.perform(post("/changePassword").param("password", "1234").param("confirmPassword", "123"))
                .andExpect(status().isOk())
                .andExpect(view().name("error"));
    }

    @Test
    void test_onLogginRedirectToAdminPage() throws Exception {
        when(loginService.getLoggedUser()).thenReturn(new Login(new Role(1, "ADMIN"), "", "", "", ""));
        mockMvc.perform(get("/redirect"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/managerEvents"));
    }

    @Test
    void test_onLogginRedirectToUserPage() throws Exception {
        when(loginService.getLoggedUser()).thenReturn(new Login(new Role(1, "USER"), "", "", "", ""));
        mockMvc.perform(get("/redirect"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/events"));
    }
}