package com.spring_boot_app.Dto.ResponseDto;


import com.spring_boot_app.AppointmentClient.AppointmentResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PetOwnersAppointmentResponseDto {
    private Integer customerId;
    private List<AppointmentResponseDto> appointmentList;
}
