package com.eventsystem.controllers;

import com.eventsystem.EventSystemApplication;
import com.eventsystem.entities.Event;
import com.eventsystem.entities.EventParticipants;
import com.eventsystem.service.EventParticipantsService;
import com.eventsystem.service.EventService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = EventSystemApplication.class)
@WithMockUser(roles = { "ADMIN" }, username = "amogus")
class EventControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private EventService eventService;
    @MockBean
    private EventParticipantsService eventParticipantsService;
    @MockBean
    private PasswordEncoder encoder;
    @Mock
    private Event event;
    @Mock
    private EventParticipants eventParticipants;
    private MockMultipartFile mockGoodFile;

    @BeforeEach
    void setUp() {
        mockGoodFile = new MockMultipartFile("channelLogo.jpg", "channelLogo.jpg", "byte",
                "ChannelLogo".getBytes());
    }

    @Test
    void test_onEventsShowForm() throws Exception {
        when(eventService.getEntities()).thenReturn(new ArrayList<>());
        List<Event> list = eventService.getEntities();
        mockMvc.perform(get("/events"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("events", list))
                .andExpect(view().name("events"));
    }

    @Test
    void test_onManagerEventsShowForm() throws Exception {
        when(eventService.getEntities()).thenReturn(new ArrayList<>());
        List<Event> list = eventService.getEntities();
        mockMvc.perform(get("/managerEvents"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("events", list))
                .andExpect(view().name("adminEvents"));
    }

    @Test
    void test_onCreateEventShowForm() throws Exception {
        mockMvc.perform(get("/createEvent"))
                .andExpect(status().isOk())
                .andExpect(view().name("createEvent"));
    }

    @Test
    void test_onCreateEventItCreatesEvent() throws Exception {
        when(event.getFile()).thenReturn(mockGoodFile);
        mockMvc.perform(post("/createEvent").flashAttr("event", event))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/redirect"));
    }

    @Test
    void test_onUpdateEventShowForm() throws Exception {
        when(eventService.getEntityById(anyInt())).thenReturn(Optional.of(event));
        when(event.getImage()).thenReturn("");
        mockMvc.perform(get("/updateEvent/{id}", anyInt()))
                .andExpect(status().isOk())
                .andExpect(model().attribute("pic", event.getImage()))
                .andExpect(view().name("updateEvent"));
    }

    @Test
    void test_onUpdateEventItUpdatesEvent() throws Exception {
        when(eventService.getEntityById(anyInt())).thenReturn(Optional.of(event));
        mockMvc.perform(post("/updateEvent", anyInt()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/redirect"));
    }

    @Test
    void test_onDeleteEventItDeletesEvent() throws Exception {
        when(eventService.getEntityById(anyInt())).thenReturn(Optional.of(event));
        mockMvc.perform(post("/deleteEvent/{id}", anyInt()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/redirect"));
    }

    @Test
    void test_onHistoryOfEventsShowForm() throws Exception {
        List<Event> eventList = new ArrayList<>();
        mockMvc.perform(get("/history"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("allParticipatedEvents", eventList))
                .andExpect(view().name("history"));
    }

    @Test
    void test_onHistoryOfEventShowItsEntityForm() throws Exception {
        when(eventService.getEntityById(anyInt())).thenReturn(Optional.of(event));
        when(event.getImage()).thenReturn(" ");
        mockMvc.perform(get("/history/{id}", anyInt()))
                .andExpect(status().isOk())
                .andExpect(model().attribute("event", event))
                .andExpect(model().attribute("pic", event.getImage()))
                .andExpect(view().name("visitedEvent"));
    }

    @Test
    void test_onReadQrCodeShowQrCode() throws Exception {
        List<EventParticipants> tmp = new ArrayList<>();
        tmp.add(eventParticipants);
        when(eventParticipantsService.getEntities()).thenReturn(tmp);
        when(eventParticipants.getKeyAccess()).thenReturn("morbingtime");
        when(encoder.matches("morbingtime", "morbingtime")).thenReturn(true);
        mockMvc.perform(post("/readQrCode").param("code", "morbingtime"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/redirect"));
    }

    @Test
    void test_onReadQrCodeShowForm() throws Exception {
        mockMvc.perform(get("/readQrCode").param("code", "morbingtime"))
                .andExpect(status().isOk())
                .andExpect(view().name("readCode"));
    }
}