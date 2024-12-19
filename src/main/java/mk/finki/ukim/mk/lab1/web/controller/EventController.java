package mk.finki.ukim.mk.lab1.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import mk.finki.ukim.mk.lab1.model.Event;
import mk.finki.ukim.mk.lab1.model.EventBooking;
import mk.finki.ukim.mk.lab1.model.User;
import mk.finki.ukim.mk.lab1.model.enumerations.Role;
import mk.finki.ukim.mk.lab1.service.EventBookingService;
import mk.finki.ukim.mk.lab1.service.EventService;
import mk.finki.ukim.mk.lab1.service.LocationService;
import mk.finki.ukim.mk.lab1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;
    private final LocationService locationService;
    private final EventBookingService eventBookingService;
    private final UserService userService;

    @Autowired
    public EventController(EventService eventService, LocationService locationService, EventBookingService eventBookingService, UserService userService) {
        this.eventService = eventService;
        this.locationService = locationService;
        this.eventBookingService = eventBookingService;
        this.userService = userService;
    }

//    @GetMapping
//    public String getEventsPage(@RequestParam(required = false) String error, Model model,HttpSession session) {
//        User user = (User) session.getAttribute("user");
//       // Check if user is stored in the session
//        if (user == null) {
//            return "redirect:/login"; // If no user, redirect to login page
//        }
//        // Assuming 'user' is stored in the session
//        model.addAttribute("user", user);
//        model.addAttribute("events", eventService.listAll());
//        model.addAttribute("locations", locationService.findALl());
//
//        return "listEvents";
//
//    }
//@GetMapping
//public String getEventsPage(@RequestParam(required = false) String error,
//                            @RequestParam(required = false) Long locationId,
//                            Model model,
//                            HttpSession session) {
//    User user = (User) session.getAttribute("user");
//
//    // Check if user is stored in the session
//    if (user == null) {
//        return "redirect:/login"; // If no user, redirect to login page
//    }
//
//    // Assuming 'user' is stored in the session
//    model.addAttribute("user", user);
//
//    // Fetch events based on location filter
//    if (locationId != null) {
//        model.addAttribute("events", eventService.findAllByLocation_Id(locationId));
//    } else {
//        model.addAttribute("events", eventService.listAll());
//    }
//
//    // Add all locations to the model for the location filter dropdown
//    model.addAttribute("locations", locationService.findALl());
//
//    return "listEvents";
//}
@GetMapping
public String getEventsPage(@RequestParam(required = false) String error,
                            @RequestParam(required = false) Long locationId,
                            Model model,
                            HttpSession session) {

    // Fetch events based on location filter
    if (locationId != null) {
        model.addAttribute("events", eventService.findAllByLocation_Id(locationId));
    } else {
        model.addAttribute("events", eventService.listAll());
    }

    // Add all locations to the model for the location filter dropdown
    model.addAttribute("locations", locationService.findALl());

    // Check if the user is logged in and add their information (to determine if they are an admin)
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails userDetails) {
            User user = userService.findByUsername(userDetails.getUsername());
            boolean isAdmin = user.getRole().equals(Role.ROLE_ADMIN);
          System.out.println(isAdmin);
            model.addAttribute("user", user);
            model.addAttribute("isAdmin", isAdmin);
        }
    }

    model.addAttribute("error", error);
    return "listEvents";
}




    @PostMapping("/add")
    public String saveEvent(@RequestParam(required = false) Long eventId,
                            @RequestParam String name,
                            @RequestParam String description,
                            @RequestParam Integer popularityScore,
                            @RequestParam Long locationId,
                            RedirectAttributes redirectAttributes) {

        System.out.println("Received event data:");
        System.out.println("event id" + eventId);
        System.out.println("Name: " + name);
        System.out.println("Description: " + description);
        System.out.println("Popularity Score: " + popularityScore);
        System.out.println("Location ID: " + locationId);


        if (eventService.eventNameExists(name) && eventService.locationIdExists(locationId)) {
            redirectAttributes.addFlashAttribute("status", "no");
            redirectAttributes.addFlashAttribute("errorMessage", "Event with this name and location already exists.");
            return "redirect:/events/add-form"; // Redirect back to the add form
        }

        if (eventId != null) {
            // If eventId is present, update the event
            eventService.updateEvent(eventId, name, description, popularityScore, locationId);
        } else {
            // If eventId is null, create a new event

            eventService.saveEvent(name, description, popularityScore, locationId);
        }
        return "redirect:/events";

    }

    @PostMapping("/edit/{eventId}")
    public String editEvent(@PathVariable Long eventId,
                            @RequestParam(required = false) String name,
                            @RequestParam(required = false) String description,
                            @RequestParam(required = false) Integer popularityScore,
                            @RequestParam(required = false) Long locationId) {

        eventService.updateEvent(eventId, name, description, popularityScore, locationId);
        return "redirect:/events";
    }

    @GetMapping("/delete/{id}")
    public String deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return "redirect:/events";
    }

    @GetMapping("/edit-form/{eventId}")
    public String getEditEventForm(@PathVariable Long eventId, Model model) {

        Event event = eventService.findById(eventId).orElse(null);
        if (event == null) {
            return "redirect:/events?error=EventNotFound";
        }
        model.addAttribute("event", event);
        model.addAttribute("locations", locationService.findALl());
        return "add-event";
    }

    @GetMapping("add-form")
    public String getAddEventPage(Model model) {
        model.addAttribute("locations", locationService.findALl());
        return "add-event";
    }

    @GetMapping("/eventBooking")
    public String bookEvent(@RequestParam String name,
                            @RequestParam String attendeeName,
                            @RequestParam Integer numTickets,
                            HttpServletRequest request,
                            Model model) {

        // Retrieve session and check for user
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            return "redirect:/login?error=PleaseLoginFirst";
        }

        User user = (User) session.getAttribute("user");

        // Attempt to place a booking and handle errors
        try {
            EventBooking booking = eventBookingService.placeBooking(name, attendeeName, user.getEmail(), numTickets, request);
            List<EventBooking> otherBookings = eventBookingService.getBookingsForUser(user.getId());

            // Add booking details to the model
            model.addAttribute("booking", booking);
            model.addAttribute("bookings", otherBookings);

            return "bookingConfirmation";
        } catch (Exception e) {
            return "redirect:/events?error=BookingFailed";
        }
    }



}

