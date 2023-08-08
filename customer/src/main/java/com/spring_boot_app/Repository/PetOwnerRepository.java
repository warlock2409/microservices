package com.spring_boot_app.Repository;

import com.spring_boot_app.Entity.PetOwnerEntity;
import com.spring_boot_app.PetOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetOwnerRepository extends JpaRepository<PetOwnerEntity,Integer> {

}
