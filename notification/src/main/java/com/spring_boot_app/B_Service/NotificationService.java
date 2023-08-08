package com.spring_boot_app.B_Service;

import com.spring_boot_app.NotificationClient.NotificationRequestDto;
import org.springframework.stereotype.Service;

@Service
public interface NotificationService {
    void addNotification(NotificationRequestDto notificationRequestDto);
}
