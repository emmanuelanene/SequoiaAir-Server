package com.emmanuelanene.sequoia_air.services;

import com.emmanuelanene.sequoia_air.entities.Booking;
import com.emmanuelanene.sequoia_air.entities.User;

public interface EmailNotificationService {

    void sendBookingTickerEmail(Booking booking);
    void sendWelcomeEmail(User user);

}
