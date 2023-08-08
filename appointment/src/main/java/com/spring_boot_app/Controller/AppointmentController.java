package com.spring_boot_app.Controller;

import com.spring_boot_app.AppointmentClient.AppointmentResponseDto;
import com.spring_boot_app.Dtos.RequestDto.AppointmentRequestDto;
import com.spring_boot_app.Service.AppointmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/appointment")
public record AppointmentController(AppointmentService appointmentService) {

    @PostMapping("/create")
    public ResponseEntity<String> createAppointment(@RequestBody AppointmentRequestDto appointmentRequestDto){

        appointmentService.createAppointment(appointmentRequestDto);

        return new ResponseEntity<>("Created", HttpStatus.CREATED);
    }

    @GetMapping("/customer/{id}")
    public List<AppointmentResponseDto> getAllAppointments(@PathVariable Integer id){

        return appointmentService.getAllAppointment(id);
    }



}
