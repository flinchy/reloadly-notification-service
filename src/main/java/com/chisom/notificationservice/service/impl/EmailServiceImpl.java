package com.chisom.notificationservice.service.impl;

import com.chisom.notificationservice.dto.EmailNotificationRequest;
import com.chisom.notificationservice.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

import static com.chisom.notificationservice.constants.NotificationConstants.FROM;

/**
 * @author Chisom.Iwowo
 */
@Service
public class EmailServiceImpl implements EmailService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final RestTemplate restTemplate;
    private final String notificationUrl;
    private final JavaMailSender emailSender;

    @Autowired
    public EmailServiceImpl(
            RestTemplate restTemplate,
            @Value("${notification-server-health}") String notificationUrl,
            @Qualifier("JavaMail") JavaMailSender emailSender
    ) {
        this.restTemplate = restTemplate;
        this.notificationUrl = notificationUrl;
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

    /**
     * ping url every 5min to keep alive
     */
//    @Async
//    @Scheduled(fixedRate = 300000)
//    public void health() {
//        try {
//            CompletableFuture.runAsync(() ->
//                    restTemplate.getForObject(notificationUrl, Object.class));
//        } catch (Exception e) {
//            log.error("caught an exception :::", e);
//        }
//    }
}
