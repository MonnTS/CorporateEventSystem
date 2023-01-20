package com.eventsystem.repository;

import com.eventsystem.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessagesRepository extends JpaRepository<Message, Integer> {
}
