package com.chisom.notificationservice.service.impl;

import com.chisom.notificationservice.dto.EmailNotificationRequest;
import com.chisom.notificationservice.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

import static com.chisom.notificationservice.constants.NotificationConstants.FROM;

/**
 * @author Chisom.Iwowo
 */
@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender emailSender;
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public EmailServiceImpl(
            @Qualifier("JavaMail") JavaMailSender emailSender
    ) {
        this.emailSender = emailSender;
    }

    /**
     * send email notifications.
     */
    @Override
    public void sendEmailNotifications(
            final EmailNotificationRequest emailNotificationRequest
    ) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(FROM);
        message.setTo(emailNotificationRequest.getTo());
        message.setSubject(emailNotificationRequest.getSubject());
        message.setText(emailNotificationRequest.getNarration());

        try {
            CompletableFuture.runAsync(() -> emailSender.send(message));
        } catch (Exception e) {
            log.error("caught an exception while trying to send email.", e);
        }
    }
}
