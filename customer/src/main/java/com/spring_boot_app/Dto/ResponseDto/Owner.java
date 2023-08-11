package com.spring_boot_app.Dto.ResponseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Owner {
    private String email;

    private String message;
    private  String uuid;
    private Integer port;
    private String service;
}
