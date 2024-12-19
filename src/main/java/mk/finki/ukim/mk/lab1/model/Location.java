package mk.finki.ukim.mk.lab1.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
@Entity
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private String capacity;
    private String description;

    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Event> events;


    public Location(String Name,String Address, String Capacity, String Description)
    {
        name=Name;
        address=Address;
        capacity=Capacity;
        description=Description;
    }

    public Location() {

    }
}
