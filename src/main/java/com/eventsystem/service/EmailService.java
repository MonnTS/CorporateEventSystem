package com.eventsystem.service;


import com.eventsystem.interfaces.IEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService implements IEmail {
    private final JavaMailSender javaMailSender;

    @Autowired
    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendSimpleMessage(String to, String subject, String text) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;
        try {
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        mimeMessageHelper.setTo(new InternetAddress(to));
        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setText(text);
        javaMailSender.send(mimeMessage);
    }
}
