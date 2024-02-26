package com.adtavera.apicarfix.repositories;

import com.adtavera.apicarfix.models.Notification;
import org.springframework.data.repository.CrudRepository;

public interface NotificationRepository extends CrudRepository<Notification, Long> {
}
