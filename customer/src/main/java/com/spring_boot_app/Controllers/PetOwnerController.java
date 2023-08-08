package com.spring_boot_app.Controllers;

import com.spring_boot_app.Dto.RequestDto.PetOwnerRequestDto;
import com.spring_boot_app.Dto.ResponseDto.PetOwnersAppointmentResponseDto;
import com.spring_boot_app.Service.PetOwnerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/pet-owner")
public record PetOwnerController(PetOwnerService petOwnerService) {

    @PostMapping("/register")
    public void registerPetOwner(@RequestBody PetOwnerRequestDto petOwnerRequestDto){
        log.info("New Pet Owner {}", petOwnerRequestDto);
        petOwnerService.registerPetOwner(petOwnerRequestDto);
    }

    @GetMapping("/appointment/{id}")
    public ResponseEntity<PetOwnersAppointmentResponseDto> getAllAppointments(@PathVariable int id){
        PetOwnersAppointmentResponseDto responseDto = petOwnerService.getAllAppointments(id);
        return new ResponseEntity<>(responseDto, HttpStatus.ACCEPTED);
    }

}
