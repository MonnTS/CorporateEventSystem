package com.eventsystem.service;

import com.eventsystem.entities.Message;
import com.eventsystem.interfaces.ICrud;
import com.eventsystem.repository.MessagesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessagesService implements ICrud<Message> {

    private final MessagesRepository messagesRepository;

    public MessagesService(MessagesRepository messagesRepository) {
        this.messagesRepository = messagesRepository;
    }

    @Override
    public Optional<Message> getEntityById(Integer id) {
        return Optional.ofNullable(messagesRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("Message object with id " + id + " doesn't exist!")));
    }

    @Override
    public List<Message> getEntities() {
        return messagesRepository.findAll();
    }

    @Override
    public void createEntity(Message message) {
        messagesRepository.save(message);
    }

    @Override
    public void updateEntity(Message message) {
        messagesRepository.save(message);
    }

    @Override
    public void deleteEntity(Message message) {
        messagesRepository.delete(message);
    }
}
