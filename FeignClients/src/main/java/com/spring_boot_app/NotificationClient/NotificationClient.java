package com.spring_boot_app.NotificationClient;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("NotificationService")
public interface NotificationClient {
     @PostMapping("notification/add")
     void addNotification(@RequestBody NotificationRequestDto notificationRequestDto);

}
