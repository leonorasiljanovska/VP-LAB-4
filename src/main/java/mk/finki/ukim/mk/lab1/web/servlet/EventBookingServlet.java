//package mk.finki.ukim.mk.lab1.web.servlet;
//
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import mk.finki.ukim.mk.lab1.model.EventBooking;
//import mk.finki.ukim.mk.lab1.service.EventBookingService;
//import org.thymeleaf.context.WebContext;
//import org.thymeleaf.spring6.SpringTemplateEngine;
//import org.thymeleaf.web.IWebExchange;
//import org.thymeleaf.web.servlet.JakartaServletWebApplication;
//
//import java.io.IOException;
//
//@WebServlet(name="EventBookingServlet" ,urlPatterns = "/eventBooking")
//public class EventBookingServlet extends HttpServlet {
//    private final EventBookingService eventBookingService;
//    private final SpringTemplateEngine springTemplateEngine;
//
//    public EventBookingServlet(EventBookingService eventBookingService, SpringTemplateEngine springTemplateEngine) {
//        this.eventBookingService = eventBookingService;
//        this.springTemplateEngine = springTemplateEngine;
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//        IWebExchange webExchange = JakartaServletWebApplication
//                .buildApplication(getServletContext())
//                .buildExchange(req, resp);
//
//        WebContext context = new WebContext(webExchange);
//        context.setVariable("ipAddress", req.getRemoteAddr());
//
//        String query = req.getQueryString();
//
//        if (query != null && !query.isEmpty()) {
//            String eventName = req.getParameter("name");
//            String attendeeName = req.getParameter("attendeeName");
//            String attendeeAddress = req.getRemoteAddr();
//            int numOfTickets = Integer.parseInt(req.getParameter("numTickets"));
//
//            System.out.println("Received parameters:");
//            System.out.println("Name: " + eventName);
//            System.out.println("Attendee Name: " + attendeeName);
//            System.out.println("Number of Tickets: " + numOfTickets);
//
//            EventBooking eventBooking = eventBookingService.placeBooking(eventName, attendeeName, attendeeAddress, numOfTickets);
//            context.setVariable("booking", eventBooking);
//            context.setVariable("bookings",eventBookingService.searchByText(req.getParameter("attendeeName")));
//            springTemplateEngine.process("bookingConfirmation.html", context, resp.getWriter());
//        }
//        else{
//            System.out.println("No parameters found.");
//
//        }
//
//
//
//    }
//}
