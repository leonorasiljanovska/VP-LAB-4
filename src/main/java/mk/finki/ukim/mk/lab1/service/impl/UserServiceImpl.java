package mk.finki.ukim.mk.lab1.service.impl;

import mk.finki.ukim.mk.lab1.model.User;
import mk.finki.ukim.mk.lab1.repository.UserRepository;
import mk.finki.ukim.mk.lab1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    // Create a new user
    public void registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));  // Encrypt password before saving
        userRepository.save(user);
    }

    @Override
    // Retrieve a user by username
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User validateUser(String username, String password) {
        // Retrieve the user by username
        User user = userRepository.findByUsername(username);
//                .orElseThrow(() -> new RuntimeException("Invalid username or password"));

        // Check if the passwords match (you should hash and compare passwords in a real application)
        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Invalid username or password");
        }

        return user; // Return the validated user
    }
}























//ova e moeto
//package mk.finki.ukim.mk.lab1.service.impl;
//
//import jakarta.servlet.http.HttpSession;
//import jakarta.transaction.Transactional;
//import mk.finki.ukim.mk.lab1.model.User;
//import mk.finki.ukim.mk.lab1.repository.UserRepository;
//import mk.finki.ukim.mk.lab1.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class UserServiceImpl implements UserService {
//
//    UserRepository userRepository;
//
//    @Autowired
//    public UserServiceImpl(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    @Override
//    public Optional<User> getUserByEmail(String email) {
//        return Optional.of(userRepository.findByEmail(email).stream().findFirst())
//                .orElseThrow(() -> new RuntimeException("User not found"));
//    }
//
//    @Override
//    public User getUserById(Long id) {
//        User user = userRepository.findById(id);
//        if (user == null) {
//            throw new RuntimeException("User not found");
//        }
//        return user;
//    }
//
//    @Override
//    public List<User> getAllUsers() {
//        return userRepository.findAll();
//    }
//
//    @Override
//    public void deleteUserById(Long id) {
//        userRepository.deleteById(id);
//    }
//
//    @Override
//    @Transactional
//    public User saveUser(User user) {
//        return userRepository.save(user);
//    }
//
//    public User getCurrentUser(HttpSession session) {
//        // Get the current user from the session
//        return (User) session.getAttribute("currentUser");
//    }
//}
