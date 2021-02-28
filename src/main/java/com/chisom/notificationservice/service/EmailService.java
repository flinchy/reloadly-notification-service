package com.chisom.notificationservice.service;

import com.chisom.notificationservice.dto.EmailNotificationRequest;

/**
 * @author Chisom.Iwowo
 */
public interface EmailService {

    /**
     * @param emailNotificationRequest request
     */
    void sendEmailNotifications(final EmailNotificationRequest emailNotificationRequest);
}
