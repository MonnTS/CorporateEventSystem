package com.eventsystem.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "sender_id")
    private Integer senderId;
    @Column(name = "target_id")
    private Integer targetId;
    private String content;
    @Column(name = "is_sent")
    private boolean isSent;

    public Message() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSenderId() {
        return senderId;
    }

    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

    public Integer getTargetId() {
        return targetId;
    }

    public void setTargetId(Integer targetId) {
        this.targetId = targetId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isSent() {
        return isSent;
    }

    public void setSent(boolean sent) {
        isSent = sent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Message message)) return false;
        return isSent() == message.isSent() && getId().equals(message.getId()) && getSenderId().equals(message.getSenderId()) && getTargetId().equals(message.getTargetId()) && getContent().equals(message.getContent());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getSenderId(), getTargetId(), getContent(), isSent());
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", senderId=" + senderId +
                ", targetId=" + targetId +
                ", content='" + content + '\'' +
                ", is_Sent=" + isSent +
                '}';
    }
}
