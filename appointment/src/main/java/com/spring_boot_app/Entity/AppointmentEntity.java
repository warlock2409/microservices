package com.spring_boot_app.Entity;

import com.spring_boot_app.AppointmentClient.AppointmentCategory;
import com.spring_boot_app.AppointmentClient.AppointmentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AppointmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer petOwnerId;

    @Enumerated(value = EnumType.STRING)
    private AppointmentStatus status;


    @Enumerated(value = EnumType.STRING)
    private AppointmentCategory category;


}
