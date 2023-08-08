package com.spring_boot_app.Service;

import com.spring_boot_app.AppointmentClient.AppointmentResponseDto;

import com.spring_boot_app.Dtos.RequestDto.AppointmentRequestDto;
import com.spring_boot_app.Entity.AppointmentEntity;
import com.spring_boot_app.NotificationClient.NotificationClient;
import com.spring_boot_app.NotificationClient.NotificationRequestDto;
import com.spring_boot_app.RabbitMessageQueueProducer;
import com.spring_boot_app.Repository.AppointmentRepository;
import com.spring_boot_app.Transformer.AppointmentTransformer;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class AppointmentService {
    @Autowired
    AppointmentRepository appointmentRepository;

    @Autowired
    NotificationClient notificationClient;

   private final RabbitMessageQueueProducer rabbitMessageQueueProducer;

    public void createAppointment(AppointmentRequestDto appointmentRequestDto) {

        // todo: check user id in petOwner DB


        // todo: create appointment entity
            AppointmentEntity appointment = AppointmentEntity.builder()
                    .category(appointmentRequestDto.category())
                    .petOwnerId(appointmentRequestDto.petOwnerId())
                    .status(appointmentRequestDto.status())
                    .build();

        // todo: save appointment in appointment db
        AppointmentEntity appointmentNew =  appointmentRepository.save(appointment);

        //todo: send Notification using NotificationService
        NotificationRequestDto notificationRequestDto= NotificationRequestDto.builder()
                                 .petOwnerId(appointmentNew.getPetOwnerId())
                                 .message("Welcome to RunFree"+" "+appointmentNew.getCategory())
                                 .build();
        //todo: send to Notification Server

        notificationClient.addNotification(notificationRequestDto);

        //todo: Send to message Queue

        rabbitMessageQueueProducer.publish(notificationRequestDto,"internal.exchange","internal.notification.routing-key");

    }

    public List<AppointmentResponseDto> getAllAppointment(Integer id) {
        // todo: get all appointments for pet owner id
        List<AppointmentEntity> appointmentList = appointmentRepository.findAllByPetOwnerId(id);

        // todo: create appointment response entity list
        List<AppointmentResponseDto> appointmentResponseDtosList = new ArrayList<AppointmentResponseDto>();

        // transfer appointment to appointment response Dto
        for (AppointmentEntity appointmentEntity : appointmentList) {
            AppointmentResponseDto appointmentResponseDto = AppointmentTransformer.appointmentToResponseDto(appointmentEntity);

            appointmentResponseDtosList.add(appointmentResponseDto);
        }

        //return response dto list
        return appointmentResponseDtosList;
    }
}
