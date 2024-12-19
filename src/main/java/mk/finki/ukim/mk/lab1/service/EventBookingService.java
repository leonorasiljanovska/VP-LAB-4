package mk.finki.ukim.mk.lab1.service;


import jakarta.servlet.http.HttpServletRequest;
import mk.finki.ukim.mk.lab1.model.EventBooking;
import mk.finki.ukim.mk.lab1.model.User;

import java.util.List;

public interface EventBookingService {
    public EventBooking placeBooking(String eventName, String attendeeName, String attendeeAddress, Integer numberOfTickets, HttpServletRequest request);
    List<EventBooking> searchByText(String text);
    public List<EventBooking> getBookingsForUser(Long userId);

    }