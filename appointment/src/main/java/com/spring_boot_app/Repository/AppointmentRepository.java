package com.spring_boot_app.Repository;

import com.spring_boot_app.Entity.AppointmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<AppointmentEntity,Integer> {
     List<AppointmentEntity> findAllByPetOwnerId(Integer id);

}
