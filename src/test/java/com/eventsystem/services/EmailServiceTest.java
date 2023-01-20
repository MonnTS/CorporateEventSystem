package com.eventsystem.services;

import com.eventsystem.service.EmailService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
class EmailServiceTest {
    @InjectMocks
    private EmailService emailService;
    @MockBean
    private JavaMailSender javaMailSender;
    @Mock
    private MimeMessage mimeMessage;

    @BeforeEach
    void setUp() {
        emailService = new EmailService(javaMailSender);
    }

    @Test
    void test_onCallingMethodThrowsMessagingException() throws RuntimeException {
        doThrow(new RuntimeException()).when(javaMailSender).send((mimeMessage -> any()));
        Assertions.assertThrows(RuntimeException.class, () ->
                emailService.sendSimpleMessage("", "", "")
        );
    }

    @Test
    void test_onSendingMessage() throws MessagingException {
        when(javaMailSender.createMimeMessage()).thenReturn(mimeMessage);
        emailService.sendSimpleMessage("something", "dsdsassdasdad", "saafafafaff");
        verify(javaMailSender, times(1)).send(mimeMessage);
    }
}