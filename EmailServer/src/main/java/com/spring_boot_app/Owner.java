package com.spring_boot_app;

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
    private Integer port;
    private  String uuid;

    private String service;
}
