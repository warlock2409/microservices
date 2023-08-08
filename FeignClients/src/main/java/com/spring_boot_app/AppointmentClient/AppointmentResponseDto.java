package com.spring_boot_app.AppointmentClient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentResponseDto {

    private AppointmentStatus status;

    private AppointmentCategory category;

}
