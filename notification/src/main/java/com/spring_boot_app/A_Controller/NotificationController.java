package com.spring_boot_app.A_Controller;

import com.spring_boot_app.B_Service.NotificationService;
import com.spring_boot_app.NotificationClient.NotificationRequestDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notification")
public record NotificationController(NotificationService notificationService) {

    @PostMapping("/add")
    public void addNotification(@RequestBody NotificationRequestDto notificationRequestDto){
        notificationService.addNotification(notificationRequestDto);
    }
}
