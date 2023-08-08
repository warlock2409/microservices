package com.spring_boot_app.Transformer;

import com.spring_boot_app.AppointmentClient.AppointmentResponseDto;
import com.spring_boot_app.Entity.AppointmentEntity;


public class AppointmentTransformer {

    public static AppointmentResponseDto appointmentToResponseDto(AppointmentEntity appointment){
        return AppointmentResponseDto.builder()
                .status(appointment.getStatus())
                .category(appointment.getCategory())
                .build();
    }
}
