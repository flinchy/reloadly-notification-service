package com.chisom.notificationservice.controller;

import com.chisom.notificationservice.dto.EmailNotificationRequest;
import com.chisom.notificationservice.dto.NotificationResponse;
import com.chisom.notificationservice.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

/**
 * @author Chisom.Iwowo
 */
@Service
public class EmailControllerFacade implements EmailController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final EmailService emailService;

    public EmailControllerFacade(EmailService emailService) {
        this.emailService = emailService;
    }

    /**
     * send email notification.
     *
     * @param emailNotificationRequest email notification request
     */
    @Override
    public NotificationResponse sendEmail(
            EmailNotificationRequest emailNotificationRequest
    ) {
        log.info("from notification service ::: {}", emailNotificationRequest);
        emailService.sendEmailNotifications(emailNotificationRequest);

        return new NotificationResponse("notification sent successfully",
                true, ZonedDateTime.now());
    }
}
