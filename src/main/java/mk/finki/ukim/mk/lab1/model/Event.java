package mk.finki.ukim.mk.lab1.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

@Data
@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private double popularityScore;


    @Column(name = "locationId", insertable = false, updatable = false)
    private Long locationId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "locationId", referencedColumnName = "id")
    private Location location;

    public Event(String name, String description, double popularityScore,Location location) {
        this.name = name;
        this.description = description;
        this.popularityScore = popularityScore;
        this.location=location;
        if (location != null) {
            this.locationId = location.getId(); // Set locationId based on the associated Location
        }
    }

    public Event() {

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPopularityScore(double popularityScore) {
        this.popularityScore = popularityScore;
    }
}
