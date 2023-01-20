package com.eventsystem.entities;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "event")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String place;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private Date date;
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(nullable = false)
    private byte[] picture;
    @Transient
    private String image;
    @Transient
    private MultipartFile file;

    public Event() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public String getImage() {
        image = new String(getPicture(), StandardCharsets.UTF_8);
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Event event)) return false;
        return getId().equals(event.getId()) && getName().equals(event.getName()) && getPlace().equals(event.getPlace()) && getDescription().equals(event.getDescription()) && getDate().equals(event.getDate()) && Arrays.equals(getPicture(), event.getPicture()) && getImage().equals(event.getImage()) && Objects.equals(getFile(), event.getFile());
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(getId(), getName(), getPlace(), getDescription(), getDate(), getImage(), getFile());
        result = 31 * result + Arrays.hashCode(getPicture());
        return result;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", place='" + place + '\'' +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", picture=" + Arrays.toString(picture) +
                ", image='" + image + '\'' +
                ", file=" + file +
                '}';
    }
}