
package mk.finki.ukim.mk.lab1.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import  mk.finki.ukim.mk.lab1.model.enumerations.Role;
import java.util.Collection;
import java.util.Collections;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Data
@Entity
@NoArgsConstructor
@Table(name = "app_user")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String email;

    @Getter
    @Enumerated(value = EnumType.STRING)
    private Role role;  // Role field as ENUM

    @Column(name = "is_account_non_expired", nullable = false)
    private boolean isAccountNonExpired = true;

    @Column(name = "is_account_non_locked", nullable = false)
    private boolean isAccountNonLocked = true;

    @Column(name = "is_credentials_non_expired", nullable = false)
    private boolean isCredentialsNonExpired = true;

    @Column(name = "is_enabled", nullable = false)
    private boolean isEnabled = true;


    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role =Role.valueOf(role);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(role);
    }


    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Modify as needed
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Modify as needed
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Modify as needed
    }

    @Override
    public boolean isEnabled() {
        return true; // Modify as needed
    }
}






















//OVA E MOETO PRETHODNO
//package mk.finki.ukim.mk.lab1.model;
//
//import jakarta.persistence.*;
//import jakarta.transaction.Transactional;
//import lombok.Getter;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Getter
//@Entity
//@Table(name = "app_user")
//public class User {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(nullable = false, unique = true)
//    private String email;
//
//    private String name;
//    private String address;
//    private String password;
//
////    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
////    private EventBooking eventBooking;
//
//    public User() {
//
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public void setAddress(String address) {
//        this.address = address;
//    }
//
////    @Transactional
////    public void addBooking(EventBooking booking) {
////        bookings.add(booking);
////        booking.setUser(this);  // Ensure the bidirectional relationship is maintained
////    }
//}
