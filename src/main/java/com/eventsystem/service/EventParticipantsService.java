package com.eventsystem.service;

import com.eventsystem.entities.EventParticipants;
import com.eventsystem.interfaces.ICrud;
import com.eventsystem.repository.EventParticipantsRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class EventParticipantsService implements ICrud<EventParticipants> {
    private final EventParticipantsRepository eventParticipantsRepository;
    private final PasswordEncoder encoder;

    public EventParticipantsService(EventParticipantsRepository eventParticipantsRepository, PasswordEncoder encoder) {
        this.eventParticipantsRepository = eventParticipantsRepository;
        this.encoder = encoder;
    }

    @Override
    public Optional<EventParticipants> getEntityById(Integer id) {
        return Optional.ofNullable(eventParticipantsRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("EventParticipant object with id " + id + " doesn't exist!")));
    }

    @Override
    public List<EventParticipants> getEntities() {
        return eventParticipantsRepository.findAll();
    }

    @Override
    public void createEntity(EventParticipants eventParticipants) {
        eventParticipantsRepository.save(eventParticipants);
    }

    @Override
    public void updateEntity(EventParticipants eventParticipants) {
        eventParticipantsRepository.save(eventParticipants);
    }

    @Override
    public void deleteEntity(EventParticipants eventParticipants) {
        eventParticipantsRepository.delete(eventParticipants);
    }

    public String createSecretKeyAccess(EventParticipants participant, String firstName) {
        String secretKey = firstName + "_EVENT_" + participant.getEventId().getName() + "_" + participant.hashCode() + "_" + LocalDate.now();
        participant.setKeyAccess(encoder.encode(secretKey));
        updateEntity(participant);
        return secretKey;
    }
}
