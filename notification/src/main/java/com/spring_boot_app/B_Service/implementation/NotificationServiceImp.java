package com.spring_boot_app.B_Service.implementation;

import com.spring_boot_app.B_Service.NotificationService;
import com.spring_boot_app.C_Repository.NotificationRepository;
import com.spring_boot_app.Entity.NotificationRecord;
import com.spring_boot_app.NotificationClient.NotificationRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImp implements NotificationService {

    @Autowired
    NotificationRepository notificationRepository;
    @Override
    public void addNotification(NotificationRequestDto notificationRequestDto) {
        //todo:Build record
        NotificationRecord notificationRecord = NotificationRecord.builder()
                .message(notificationRequestDto.getMessage())
                .petOwnerId(notificationRequestDto.getPetOwnerId())
                .build();
        //todo:Save record
        notificationRepository.save(notificationRecord);

    }
}
