package com.spring_boot_app.AppointmentClient;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("APPOINTMENTSERVICE") // Eureka service name
public interface AppointmentClient {
    @GetMapping("api/v1/appointment/customer/{id}")
    List<AppointmentResponseDto> getAllAppointments(@PathVariable Integer id);

}
