package com.eventsystem.interfaces;

import javax.mail.MessagingException;

public interface IEmail {
    void sendSimpleMessage(String to, String subject, String text) throws MessagingException;
}
