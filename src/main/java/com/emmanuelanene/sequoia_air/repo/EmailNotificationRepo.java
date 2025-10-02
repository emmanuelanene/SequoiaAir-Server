package com.emmanuelanene.sequoia_air.repo;

import com.emmanuelanene.sequoia_air.entities.EmailNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailNotificationRepo extends JpaRepository<EmailNotification, Long> {
}
