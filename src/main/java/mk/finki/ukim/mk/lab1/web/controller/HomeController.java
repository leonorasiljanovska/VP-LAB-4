package mk.finki.ukim.mk.lab1.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import mk.finki.ukim.mk.lab1.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = {"/", "/home"})
public class HomeController {

    @GetMapping
    public String getHomePage(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false); // Check if the session exists
        if (session != null && session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            model.addAttribute("user", user); // Add user details to the model
            return "home"; // Return the home page view
        }
        return "redirect:/login"; // Redirect to login if not authenticated
    }
}





















//package mk.finki.ukim.mk.lab1.web.controller;
//
//import mk.finki.ukim.mk.lab1.model.User;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpSession;
//
//@Controller
//public class HomeController {
//
//    @GetMapping("/home")
//    public String home(HttpServletRequest request, Model model) {
//        HttpSession session = request.getSession(false); // Check if the session exists
//        if (session != null && session.getAttribute("user") != null) {
//            User user = (User) session.getAttribute("user");
//            model.addAttribute("user", user);
//            return "home"; // Render the home page
//        }
//        return "redirect:/login"; // If no session, redirect to login page
//    }
//}
