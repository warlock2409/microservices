package com.spring_boot_app.Service;

import com.spring_boot_app.AppointmentClient.AppointmentClient;
import com.spring_boot_app.AppointmentClient.AppointmentResponseDto;

import com.spring_boot_app.Dto.RequestDto.PetOwnerRequestDto;
import com.spring_boot_app.Dto.ResponseDto.Owner;
import com.spring_boot_app.Dto.ResponseDto.PetOwnersAppointmentResponseDto;
import com.spring_boot_app.Entity.PetOwnerEntity;
import com.spring_boot_app.Repository.PetOwnerRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;


@Service
@AllArgsConstructor
public class PetOwnerService{

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    PetOwnerRepository petOwnerRepository;
    @Autowired
    AppointmentClient appointmentClient;


    private KafkaTemplate<String, Owner> kafkaTemplate;




    public void registerPetOwner(PetOwnerRequestDto petOwnerRequestDto) {
        PetOwnerEntity petOwner = PetOwnerEntity.builder()
                .firstName(petOwnerRequestDto.firstName())
                .lastName(petOwnerRequestDto.lastName())
                .email(petOwnerRequestDto.email())
                .build();

        //todo: check if email valid
        //todo: check if email is not taken
        //todo: store pet owner in db
       PetOwnerEntity petOwnerSaved = petOwnerRepository.save(petOwner);

        //todo: pass it to message queue


        String[] service = new String[3];
        service[0]="APP";
        service[1]="EMAIL";
        service[2]="SMS";

        for (int i=0;i<50;i++) {

            Owner owner = Owner.builder()
                    .message("Welcome to Runloyal")
                    .email(petOwnerSaved.getEmail())
                    .service(service[0])
                    .build();
            kafkaTemplate.send("petowner",owner );
        }



    }

    public PetOwnersAppointmentResponseDto getAllAppointments(int id) {
        //todo: restTemplate method

//        List<AppointmentResponseDto> appointmentResponseDtosList =  restTemplate.exchange(
//                "http://APPOINTMENTSERVICE/api/v1/appointment/customer/{id}",
//                HttpMethod.GET,
//                null,
//                new ParameterizedTypeReference<List<AppointmentResponseDto>>() {},
//                id
//        ).getBody();

        //todo:Open feign method

        List<AppointmentResponseDto> appointmentResponseDtosList = appointmentClient.getAllAppointments(id);

        return PetOwnersAppointmentResponseDto.builder()
                .customerId(id)
                .appointmentList(appointmentResponseDtosList)
                .build();

    }
}
