package com.spring_boot_app.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PetOwnerEntity {
    @Id
    @SequenceGenerator(name = "pet_owner_id_sequence",sequenceName = "pet_owner_id_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "pet_owner_id_sequence")
    private Integer id;

    private String firstName;

    private String lastName;

    @Column(unique = true)
    private String email;

}
