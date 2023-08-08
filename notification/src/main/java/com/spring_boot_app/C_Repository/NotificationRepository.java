package com.spring_boot_app.C_Repository;

import com.spring_boot_app.Entity.NotificationRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<NotificationRecord, Integer> {
}
