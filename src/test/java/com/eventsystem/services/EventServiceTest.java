package com.eventsystem.services;

import com.eventsystem.entities.Event;
import com.eventsystem.repository.EventRepository;
import com.eventsystem.service.EventService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.nio.charset.StandardCharsets;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
class EventServiceTest {
    @MockBean
    private EventRepository eventRepository;
    @InjectMocks
    private EventService eventService;
    @Mock
    private Event event;
    private MockMultipartFile mockBadFile;
    private MockMultipartFile mockGoodFile;

    @BeforeEach
    void setUp() {
        eventService = new EventService(eventRepository);
        mockBadFile = new MockMultipartFile("channelLogo.pdf", "channelLogo.pdf", "byte",
                "ChannelLogo".getBytes());
        mockGoodFile = new MockMultipartFile("channelLogo.jpg", "channelLogo.jpg", "byte",
                "ChannelLogo".getBytes());

    }

    @Test
    void test_onGetEntityById() {
        when(eventRepository.findById(anyInt())).thenReturn(Optional.of(mock(Event.class)));
        eventService.getEntityById(anyInt());
        verify(eventRepository, times(1)).findById(anyInt());
    }

    @Test
    void test_onGetEntityByIdThrowsNullPointerException() throws NullPointerException {
        doThrow(new NullPointerException()).when(eventRepository).findById(anyInt());
        assertThrows(NullPointerException.class, () ->
                eventService.getEntityById(anyInt())
        );
    }

    @Test
    void test_onGetEntitiesRepresentedAsList() {
        eventService.getEntities();
        verify(eventRepository, times(1)).findAll();
    }

    @Test
    void test_onCreateEntityCallSave() {
        eventService.createEntity(event);
        verify(eventRepository, times(1)).save(event);
    }

    @Test
    void test_onUpdateEntityCallSave() {
        eventService.updateEntity(event);
        verify(eventRepository, times(1)).save(event);
    }

    @Test
    void test_onRemoveEntityCallDelete() {
        eventService.deleteEntity(event);
        verify(eventRepository, times(1)).delete(event);
    }

    @Test
    void test_encodeImageBase64Converts() {
        byte[] bytes = eventService.encodeImageToBase64(mockGoodFile);
        String encodedString = new String(bytes, StandardCharsets.UTF_8);
        assertTrue(encodedString.matches("^([A-Za-z0-9+/]{4})*([A-Za-z0-9+/]{3}=|[A-Za-z0-9+/]{2}==)?"));
    }

    @Test
    void test_encodeImageToBase64ThrowsErrorIfNotCorrectFileExtension() throws RuntimeException {
        Exception exception = assertThrows(RuntimeException.class, ()
                -> eventService.encodeImageToBase64(mockBadFile));

        String expectedMessage = "Only jpg/jpeg and png files are accepted";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}