package mk.finki.ukim.mk.lab1.repository;

import mk.finki.ukim.mk.lab1.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Event,Long> {
    List<Event> findAllByLocation_Id(Long locationId);
    List<Event> findEventsByName(String name);
    public void deleteAllByLocationId(Long id);
    boolean existsByName(String eventName);
    boolean existsByLocationId(Long locationId);
}
