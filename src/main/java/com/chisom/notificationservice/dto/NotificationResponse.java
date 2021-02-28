package com.chisom.notificationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
@AllArgsConstructor
public class NotificationResponse {

    private String message;
    private boolean status;
    private ZonedDateTime timeStamp;
}
