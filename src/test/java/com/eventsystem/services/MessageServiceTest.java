package com.eventsystem.services;

import com.eventsystem.entities.Message;
import com.eventsystem.entities.Role;
import com.eventsystem.repository.MessagesRepository;
import com.eventsystem.service.MessagesService;
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
class MessageServiceTest {
    @MockBean
    private MessagesRepository messagesRepository;

    @InjectMocks
    private MessagesService messagesService;

    @Mock
    private Message message;

    @BeforeEach
    void setUp() {
        messagesService = new MessagesService(messagesRepository);
    }

    @Test
    void test_onGetEntityById() {
        when(messagesRepository.findById(anyInt())).thenReturn(Optional.of(mock(Message.class)));
        messagesService.getEntityById(anyInt());
        verify(messagesRepository, times(1)).findById(anyInt());
    }

    @Test
    void test_onGetEntityByIdThrowsNullPointerException() throws NullPointerException {
        doThrow(new NullPointerException()).when(messagesRepository).findById(anyInt());
        Assertions.assertThrows(NullPointerException.class, () ->
                messagesService.getEntityById(anyInt())
        );
    }

    @Test
    void test_onGetEntitiesRepresentedAsList() {
        messagesService.getEntities();
        verify(messagesRepository, times(1)).findAll();
    }

    @Test
    void test_onCreateEntityCallSave() {
        messagesService.createEntity(message);
        verify(messagesRepository, times(1)).save(message);
    }

    @Test
    void test_onUpdateEntityCallSave() {
        messagesService.updateEntity(message);
        verify(messagesRepository, times(1)).save(message);
    }

    @Test
    void test_onRemoveEntityCallDelete() {
        messagesService.deleteEntity(message);
        verify(messagesRepository, times(1)).delete(message);
    }
}
