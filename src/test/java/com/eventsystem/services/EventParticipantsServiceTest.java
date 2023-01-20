package com.eventsystem.services;

import com.eventsystem.entities.EventParticipants;
import com.eventsystem.repository.EventParticipantsRepository;
import com.eventsystem.service.EventParticipantsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
class EventParticipantsServiceTest {
    @MockBean
    private EventParticipantsRepository eventParticipantsRepository;
    @MockBean
    private PasswordEncoder encoder;
    @InjectMocks
    private EventParticipantsService eventParticipantsService;
    @Mock
    private EventParticipants eventParticipants;

    @BeforeEach
    void setUp() {
        eventParticipantsService = new EventParticipantsService(eventParticipantsRepository, encoder);
    }

    @Test
    void test_onGetEntityById() {
        when(eventParticipantsRepository.findById(anyInt())).thenReturn(Optional.of(mock(EventParticipants.class)));
        eventParticipantsService.getEntityById(anyInt());
        verify(eventParticipantsRepository, times(1)).findById(anyInt());
    }

    @Test
    void test_onGetEntityByIdThrowsNullPointerException() throws NullPointerException {
        doThrow(new NullPointerException()).when(eventParticipantsRepository).findById(anyInt());
        Assertions.assertThrows(NullPointerException.class, () ->
                eventParticipantsService.getEntityById(anyInt())
        );
    }

    @Test
    void test_onGetEntitiesRepresentedAsList() {
        eventParticipantsService.getEntities();
        verify(eventParticipantsRepository, times(1)).findAll();
    }

    @Test
    void test_onCreateEntityCallSave() {
        eventParticipantsService.createEntity(eventParticipants);
        verify(eventParticipantsRepository, times(1)).save(eventParticipants);
    }

    @Test
    void test_onUpdateEntityCallSave() {
        eventParticipantsService.updateEntity(eventParticipants);
        verify(eventParticipantsRepository, times(1)).save(eventParticipants);
    }

    @Test
    void test_onRemoveEntityCallDelete() {
        eventParticipantsService.deleteEntity(eventParticipants);
        verify(eventParticipantsRepository, times(1)).delete(eventParticipants);
    }
}