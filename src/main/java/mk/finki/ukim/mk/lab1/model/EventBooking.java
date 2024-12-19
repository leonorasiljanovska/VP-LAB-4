package mk.finki.ukim.mk.lab1.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

@Data
@Entity
public class EventBooking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String eventName;
    private String attendeeName;
    private String attendeeAddress;
    private Long numberOfTickets;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")  // This creates a foreign key column "user_id" in the EventBooking table
    private User user;

    public EventBooking(String eventName, String attendeeName, String attendeeAddress, Long numberOfTickets, User user) {
        this.eventName = eventName;
        this.attendeeName = attendeeName;
        this.attendeeAddress = attendeeAddress;
        this.numberOfTickets = numberOfTickets;
        this.user = user;
    }

    public EventBooking() {
    }

    // No need to manually set userId; it is handled by JPA
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public void setAttendeeName(String attendeeName) {
        this.attendeeName = attendeeName;
    }

    public void setAttendeeAddress(String attendeeAddress) {
        this.attendeeAddress = attendeeAddress;
    }

    public void setNumberOfTickets(Long numberOfTickets) {
        this.numberOfTickets = numberOfTickets;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
