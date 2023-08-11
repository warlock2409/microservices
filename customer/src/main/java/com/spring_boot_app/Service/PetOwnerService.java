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
import java.util.UUID;


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




    public void registerPetOwner(PetOwnerRequestDto petOwnerRequestDto) throws InterruptedException {
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
        String large = "We're excited to announce our upcoming Pet Campaign, dedicated to our beloved furry friends! \n" +
                "Join us for:\n" +
                "Exclusive discounts on pet products and services.\n" +
                "Fun activities and games for pets and their owners.\n" +
                "A pet photo contest with fantastic prizes.\n" +
                "An opportunity to give a loving home to a pet in need.\n" +
                "Save the date for [Campaign Date] and celebrate the joy of pets with us. Don't miss out!\n" +
                "Stay tuned for more updates on our website and social media.";
        large="welcome to runloyal";

        for (int i=0;i<100;i++) {

            Owner owner = Owner.builder()
                    .message(large)
                    .email(petOwnerSaved.getEmail())
                    .service("message"+"_"+i)
                    .uuid(String.valueOf(UUID.randomUUID()))
                    .build();

            kafkaTemplate.send("petowner", String.valueOf(i%2 ==0 ? 1 : 2),owner );

//            try {
////                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
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
