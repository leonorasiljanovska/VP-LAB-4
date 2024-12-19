package mk.finki.ukim.mk.lab1.service;

import mk.finki.ukim.mk.lab1.model.User;

public interface UserService {

    public void registerUser(User user);
    public User findByUsername(String username);
    public User validateUser(String username, String password);
}



























//ova e moeto
//package mk.finki.ukim.mk.lab1.service;
//
//import jakarta.servlet.http.HttpSession;
//import mk.finki.ukim.mk.lab1.model.User;
//
//import java.util.List;
//import java.util.Optional;
//
//public interface UserService {
//    Optional<User> getUserByEmail(String email);
//
//    User getUserById(Long id);
//
//    List<User> getAllUsers();
//
//    void deleteUserById(Long id);
//
//    User saveUser(User user);
//    public User getCurrentUser(HttpSession session);
//}
