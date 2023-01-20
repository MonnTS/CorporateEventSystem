package com.eventsystem.service;

import com.eventsystem.entities.Event;
import com.eventsystem.interfaces.ICrud;
import com.eventsystem.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class EventService implements ICrud<Event> {
    private final EventRepository eventRepository;

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public Optional<Event> getEntityById(Integer id) {
        return Optional.ofNullable(eventRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("Event object with id " + id + " doesn't exist!")));
    }

    @Override
    public List<Event> getEntities() {
        return eventRepository.findAll();
    }

    @Override
    public void createEntity(Event event) {
        eventRepository.save(event);
    }

    @Override
    public void updateEntity(Event event) {
        eventRepository.save(event);
    }

    @Override
    public void deleteEntity(Event event) {
        eventRepository.delete(event);
    }
    public byte[] encodeImageToBase64(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        String fileExtension = originalFilename.substring(originalFilename.indexOf(".") + 1);

        if (!"png".equals(fileExtension) && !"jpeg".equals(fileExtension) && !"jpg".equals(fileExtension)) {
            throw new RuntimeException("Only jpg/jpeg and png files are accepted");
        }

        byte[] bytes;
        try {
            bytes = file.getBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return Base64.getEncoder().encode(bytes);
    }

}
