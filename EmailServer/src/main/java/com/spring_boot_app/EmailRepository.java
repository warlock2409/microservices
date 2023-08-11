package com.spring_boot_app;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmailRepository extends JpaRepository<EmailEntity,Integer> {

    EmailEntity findByUuid(String id);

}
