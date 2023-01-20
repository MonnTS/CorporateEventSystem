package com.eventsystem.controllers;

import com.eventsystem.EventSystemApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = EventSystemApplication.class)
class ErrorHandlingControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void test_showErrorPageOnIncorrectLogin() throws Exception{
        mockMvc.perform(formLogin().password("invalid"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/error"));
    }
}