//package mk.finki.ukim.mk.lab1.web.controller;
//
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpSession;
//import mk.finki.ukim.mk.lab1.model.User;
//import mk.finki.ukim.mk.lab1.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Optional;
//
//@Controller
//public class UserController {
//    private final UserService userService;
//
//    @Autowired
//    public UserController(UserService userService) {
//        this.userService = userService;
//    }
//
//    @GetMapping("/user/{id}")
//    public String getUser(@PathVariable Long id, Model model) {
//        User user = userService.getUserById(id);
//        model.addAttribute("user", user);
//        return "userDetails";
//    }
//
//    @GetMapping("/user/delete/{id}")
//    public String deleteUser(@PathVariable Long id) {
//        userService.deleteUserById(id);
//        return "redirect:/users";
//    }
//
//    @GetMapping("/users")
//    public String getAllUsers(Model model) {
//        model.addAttribute("users", userService.getAllUsers());
//        return "userList";
//    }
//
//
//
//    @GetMapping("/register")
//    public String showRegistrationForm(Model model) {
//        model.addAttribute("user", new User());
//        return "register";
//    }
//
//    @PostMapping("/register")
//    public String registerUser(@RequestParam String name,
//                               @RequestParam String email,
//                               @RequestParam String password,
//                               HttpServletRequest request) {
//
//        // Ensure the user doesn't already exist (implement your validation logic here)
//        if (userService.getUserByEmail(email).isPresent()) {
//            // If the email exists, show an error message
//            return "redirect:/register?error=Email already exists"; // Or you can use a model attribute to show an error
//        }
//
//        // Create a new User object and set the name, email, and password
//        User newUser = new User();
//        newUser.setName(name);   // Set the name from the registration form
//        newUser.setEmail(email);
//        newUser.setPassword(password);  // Don't forget to hash the password for security!
//
//        // Save the user to the database (implement your database logic here)
//        userService.saveUser(newUser);
//
//        // After registration, store the user in the session
//        HttpSession session = request.getSession();
//        session.setAttribute("user", newUser);
//
//        // Redirect to the home or login page
//        return "redirect:/login";  // or wherever you want to redirect after successful registration
//    }
//
//
//    @GetMapping("/login")
//    public String showLoginForm() {
//        return "login";
//    }
//    @PostMapping("/login")
//    public String loginUser(@RequestParam String email, @RequestParam String password, HttpServletRequest request, Model model) {
//        // Find user by email
//        User user = userService.getUserByEmail(email).orElse(null);
//
//        // Check if user exists and password matches
//        if (user != null && user.getPassword().equals(password)) {
//            // Store user in session
//            HttpSession session = request.getSession();
//            System.out.println("Session ID: " + session.getId()); // Log session ID
//            session.setAttribute("user", user);  // Store user in session
//
//            // Log session details to see if the user is stored correctly
//            System.out.println("User stored in session: " + session.getAttribute("user"));
//
//            // Fetch and add current user to the model
//            Optional<User> currentUser = Optional.ofNullable(userService.getCurrentUser(session));
//            currentUser.ifPresent(u -> {
//                model.addAttribute("user", u);
//                System.out.println("Current user from session: " + u);  // Log the user retrieved from the session
//            });
//
//            return "redirect:/events"; // Redirect to events page after successful login
//        } else {
//            model.addAttribute("error", "Invalid email or password.");
//            return "login"; // Redirect back to login page if credentials are invalid
//        }
//    }
//
//
//    //    @GetMapping("/events")
////    public String listEvents(Model model) {
////
////        if (user.isPresent()) {
////            model.addAttribute("user", user.get());
////        } else {
////            // handle user not being present (maybe redirect to login page)
////        }
////        return "listEvents";
////    }
//    @GetMapping("/logout")
//    public String logout(HttpServletRequest request) {
//        // Invalidate the session to log out the user
//        HttpSession session = request.getSession();
//        session.invalidate();
//        return "redirect:/login"; // Redirect to the login page after logout
//    }
//}
