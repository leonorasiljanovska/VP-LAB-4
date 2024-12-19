
package mk.finki.ukim.mk.lab1.repository;

import mk.finki.ukim.mk.lab1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}

























//ova e moeto
//package mk.finki.ukim.mk.lab1.repository;
//
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.PersistenceContext;
//import jakarta.persistence.TypedQuery;
//import mk.finki.ukim.mk.lab1.model.User;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//import java.util.Optional;
//
//@Repository
//public class UserRepository {
//
//    @PersistenceContext
//    private EntityManager entityManager;
//
//    // Save a user (insert or update)
//    public User save(User user) {
//        if (user.getId() == null) {
//            entityManager.persist(user);  // Insert new user
//        } else {
//            user = entityManager.merge(user);  // Update existing user
//        }
//        return user;
//    }
//
//    // Find a user by email
//    public Optional<User> findByEmail(String email) {
//        String query = "SELECT u FROM User u WHERE u.email = :email";
//        TypedQuery<User> typedQuery = entityManager.createQuery(query, User.class);
//        typedQuery.setParameter("email", email);
//        User user = null;
//        try {
//            user = typedQuery.getSingleResult();
//        } catch (Exception e) {
//            // Handle user not found (optional)
//        }
//        return Optional.ofNullable(user);
//    }
//
//    // Find a user by ID
//    public User findById(Long id) {
//        return entityManager.find(User.class, id);
//    }
//
//    // Find all users
//    public List<User> findAll() {
//        String query = "SELECT u FROM User u";
//        TypedQuery<User> typedQuery = entityManager.createQuery(query, User.class);
//        return typedQuery.getResultList();
//    }
//
//    // Delete a user by ID
//    public void deleteById(Long id) {
//        User user = entityManager.find(User.class, id);
//        if (user != null) {
//            entityManager.remove(user);  // Remove user if exists
//        }
//    }
//}
