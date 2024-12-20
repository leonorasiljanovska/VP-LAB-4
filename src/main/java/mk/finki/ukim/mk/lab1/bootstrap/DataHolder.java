package mk.finki.ukim.mk.lab1.bootstrap;


import jakarta.annotation.PostConstruct;
import mk.finki.ukim.mk.lab1.model.Event;
import mk.finki.ukim.mk.lab1.model.EventBooking;
import mk.finki.ukim.mk.lab1.model.Location;
import mk.finki.ukim.mk.lab1.model.User;
import mk.finki.ukim.mk.lab1.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

//za da mozhe da se kreira edna instanca od klasava
@Component
public class DataHolder {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public static List<Event> events = new ArrayList<>();
    public static List<EventBooking> eventBookings;
    public static List<Location> locations=new ArrayList<>();

    public DataHolder(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    private void addDefaultUsers() {
        User user1 = new User("elena.atanasoska", passwordEncoder.encode("ea"), "ROLE_USER");
        User user2 = new User("darko.sasanski", passwordEncoder.encode("ds"), "ROLE_USER");
        User user3 = new User("ana.todorovska", passwordEncoder.encode("at"), "ROLE_USER");
        User user4 = new User("moderator", passwordEncoder.encode("moderator"), "ROLE_MODERATOR");
        User user5= new User("admin", passwordEncoder.encode("admin"), "ROLE_ADMIN");

        // Save them in the repository (database)
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        userRepository.save(user4);
        userRepository.save(user5);
    }

    //shtom se kreira instanca od klasata vednash da se povika ovoj metod
    @PostConstruct
    public void init() {

        if (userRepository.count() == 0) {
            addDefaultUsers();
        }
        Location location1 = new Location("Los Alamos", "Los Alamos, New Mexico", "1000", "A historical site where atomic bomb development took place.");
        locations.add(location1);
//        Event event1 = new Event("Oppenheimer", "A movie about the father of the atomic bomb", 8.5, location1);

        Location location2 = new Location("Gotham City", "Fictional Location", "5000", "The home of Batman.");
        locations.add(location2);
//        Event event2 = new Event("The Dark Knight", "A gritty superhero film featuring Batman", 9.0, location2);

        Location location3 = new Location("Paris", "Paris, France", "1500", "City of Light, known for its romantic atmosphere.");
        locations.add(location3);
//        Event event3 = new Event("Inception", "A mind-bending thriller about dreams", 8.8, location3);

        Location location4 = new Location("NASA HQ", "Houston, Texas", "10000", "The hub of space exploration in the United States.");
        locations.add(location4);
//        Event event4 = new Event("Interstellar", "A science fiction epic exploring space travel", 8.6, location4);

        Location location5 = new Location("Los Angeles", "Los Angeles, California", "2000", "Entertainment capital of the world.");
        locations.add(location5);
//        Event event5 = new Event("Pulp Fiction", "A crime film with interconnected stories", 8.9, location5);

        Location location6 = new Location("New York City", "New York City, New York", "3000", "The city that never sleeps, filled with diverse culture.");
        locations.add(location6);
//        Event event6 = new Event("The Godfather", "A classic mafia saga", 9.2, location6);

        Location location7 = new Location("Seoul", "Seoul, South Korea", "2500", "The capital city with a rich history and modern culture.");
        locations.add(location7);
//        Event event7 = new Event("Parasite", "A South Korean film about class struggle", 8.6, location7);

        Location location8 = new Location("Shawshank Prison", "Maine, USA", "2000", "A fictional prison known for its tales of hope and freedom.");
        locations.add(location8);
//        Event event8 = new Event("The Shawshank Redemption", "A story of hope and friendship in prison", 9.3, location8);

        Location location9 = new Location("Greenbow", "Greenbow, Alabama", "500", "A small, quiet town in the deep south.");
        locations.add(location9);
//        Event event9 = new Event("Forrest Gump", "The life story of a slow-witted but kind man", 8.8, location9);

        Location location10 = new Location("Mega City", "Fictional Location", "10000", "A dystopian megacity of the future.");
        locations.add(location10);
//        Event event10 = new Event("The Matrix", "A sci-fi film questioning reality and control", 8.7, location10);


        eventBookings=new ArrayList<>();

    }
}
