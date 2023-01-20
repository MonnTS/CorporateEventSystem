package com.eventsystem.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "event_participants")
public class EventParticipants {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;
    @ManyToOne(targetEntity = Event.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "event_id", referencedColumnName = "id", nullable = false)
    private Event eventId;
    @ManyToOne(targetEntity = Login.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "person_id", referencedColumnName = "id", nullable = false)
    private Login personId;
    @Column(nullable = false)
    private String keyAccess;

    public EventParticipants() {

    }

    public EventParticipants(Event eventId, Login personId) {
        this.eventId = eventId;
        this.personId = personId;
        this.keyAccess = "";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Event getEventId() {
        return eventId;
    }

    public void setEventId(Event eventId) {
        this.eventId = eventId;
    }

    public Login getPersonId() {
        return personId;
    }

    public void setPersonId(Login personId) {
        this.personId = personId;
    }

    public String getKeyAccess() {
        return keyAccess;
    }

    public void setKeyAccess(String keyAccess) {
        this.keyAccess = keyAccess;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EventParticipants that)) return false;
        return getId().equals(that.getId()) && getEventId().equals(that.getEventId()) && getPersonId().equals(that.getPersonId()) && getKeyAccess().equals(that.getKeyAccess());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getEventId(), getPersonId(), getKeyAccess());
    }

    @Override
    public String toString() {
        return "EventParticipants{" +
                "id=" + id +
                ", eventId=" + eventId +
                ", personId=" + personId +
                ", keyAccess='" + keyAccess + '\'' +
                '}';
    }
}
