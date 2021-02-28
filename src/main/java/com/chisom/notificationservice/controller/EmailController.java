package com.chisom.notificationservice.controller;

import com.chisom.notificationservice.dto.EmailNotificationRequest;
import com.chisom.notificationservice.dto.NotificationResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public interface EmailController {

    /**
     * send email notification.
     *
     * @param emailNotificationRequest email notification request
     */
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping("notification/email")
    NotificationResponse sendEmail(
            @Valid @RequestBody EmailNotificationRequest emailNotificationRequest);
}
