package mk.finki.ukim.mk.lab1.service.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import mk.finki.ukim.mk.lab1.model.EventBooking;
import mk.finki.ukim.mk.lab1.model.User;
import mk.finki.ukim.mk.lab1.repository.EventBookingRepository;
import mk.finki.ukim.mk.lab1.repository.UserRepository;
import mk.finki.ukim.mk.lab1.service.EventBookingService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventBookingServiceImpl implements EventBookingService {

    private final UserRepository userRepository;
    private final EventBookingRepository eventBookingRepository;
    private final EntityManager entityManager;  // Inject EntityManager

    public EventBookingServiceImpl(UserRepository userRepository, EventBookingRepository eventBookingRepository, EntityManager entityManager) {
        this.userRepository = userRepository;
        this.eventBookingRepository = eventBookingRepository;
        this.entityManager = entityManager;  // Initialize EntityManager
    }

    @Override
    @Transactional
    public EventBooking placeBooking(String eventName, String attendeeName, String attendeeAddress, Integer numberOfTickets, HttpServletRequest request) {
        // Retrieve the user from the session
        HttpSession session = request.getSession(false); // Get the session without creating a new one
        if (session == null || session.getAttribute("user") == null) {
            throw new RuntimeException("User not found in session");
        }

        // Get the user object from the session
        User user = (User) session.getAttribute("user");

        // Create the booking
        EventBooking eventBooking = new EventBooking();
        eventBooking.setEventName(eventName);
        eventBooking.setAttendeeName(attendeeName);
        eventBooking.setAttendeeAddress(attendeeAddress);
        eventBooking.setNumberOfTickets((long) numberOfTickets); // If numberOfTickets is Long
        eventBooking.setUser(user);  // Set the user who made the booking

        // Add to user's bookings list (Optional: not necessary if cascade is set correctly)
//        user.addBooking(eventBooking);

        // Save the booking (This will also cascade and save the associated user if cascade is set)
        eventBooking = eventBookingRepository.save(eventBooking);  // Persist the booking
        return eventBooking;
    }

    @Override
    public List<EventBooking> searchByText(String text) {
        return eventBookingRepository.searchByText(text);
    }

//    @Override
//    public List<EventBooking> findBookingsByUser(User user) {
//        // Implement the logic to find bookings by a user if needed
//        return null;
//    }

    @Override
    public List<EventBooking> getBookingsForUser(Long userId) {
        // JPQL query to fetch EventBookings for a user
        String jpql = "SELECT eb FROM EventBooking eb WHERE eb.user.id = :userId";
        TypedQuery<EventBooking> query = entityManager.createQuery(jpql, EventBooking.class);
        query.setParameter("userId", userId);
        return query.getResultList();
    }
}
