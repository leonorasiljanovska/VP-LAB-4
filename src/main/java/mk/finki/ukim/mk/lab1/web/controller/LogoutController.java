package mk.finki.ukim.mk.lab1.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/logout")
public class LogoutController {

    @GetMapping
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate(); // Invalidate session to log out the user
        return "redirect:/login"; // Redirect to login page
    }
}
