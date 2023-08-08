package com.spring_boot_app.Dtos.RequestDto;


import com.spring_boot_app.AppointmentClient.AppointmentCategory;
import com.spring_boot_app.AppointmentClient.AppointmentStatus;

public record AppointmentRequestDto(Integer petOwnerId , AppointmentStatus status , AppointmentCategory category) {

}
