package com.eventsystem.repository;

import com.eventsystem.entities.EventParticipants;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventParticipantsRepository extends JpaRepository<EventParticipants, Integer> {
}
