//package mk.finki.ukim.mk.lab1.web.servlet;
//
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import mk.finki.ukim.mk.lab1.service.EventService;
//import org.thymeleaf.context.WebContext;
//import org.thymeleaf.spring6.SpringTemplateEngine;
//import org.thymeleaf.web.IWebExchange;
//import org.thymeleaf.web.servlet.JakartaServletWebApplication;
//
//import java.io.IOException;
//
//@WebServlet(name = "EventListServlet", urlPatterns = "/events")
//
//public class EventListServlet extends HttpServlet {
//    private final EventService eventService;
//    private final SpringTemplateEngine springTemplateEngine;
//
//    public EventListServlet(EventService eventService, SpringTemplateEngine springTemplateEngine) {
//        this.eventService = eventService;
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
//
//        WebContext context = new WebContext(webExchange);
//
//        if(req.getParameter("search")!=null && !req.getParameter("search").isEmpty())
//        {
//            context.setVariable("events",eventService.searchEvents(req.getParameter("search")));
//        }
//        else{
//            context.setVariable("events",eventService.listAll());
//        }
//
//        context.setVariable("ipAddress", req.getRemoteAddr());
//        springTemplateEngine.process("listEvents.html", context, resp.getWriter());
//
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String name=req.getParameter("name");
//        String ipAddress=req.getParameter("ipAddress");
//        String attendeeName=req.getParameter("attendeeName");
//        Long numOfTickets= Long.valueOf(req.getParameter("numTickets"));
//        eventService.searchEvents("name");
//        resp.sendRedirect("/eventBooking?name="+name+"&attendeeName="+attendeeName+"&numTickets="+numOfTickets);
//    }
//}
