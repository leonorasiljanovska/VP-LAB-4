package mk.finki.ukim.mk.lab1.service.impl;

import jakarta.transaction.Transactional;
import mk.finki.ukim.mk.lab1.model.Event;
import mk.finki.ukim.mk.lab1.model.Location;
import mk.finki.ukim.mk.lab1.repository.EventRepository;
import mk.finki.ukim.mk.lab1.repository.LocationRepository;
import mk.finki.ukim.mk.lab1.service.EventService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@Transactional
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final LocationRepository locationRepository;

    public EventServiceImpl(EventRepository eventRepository, LocationRepository locationRepository) {
        this.eventRepository = eventRepository;
        this.locationRepository = locationRepository;
    }

    @Override
    public List<Event> listAll() {
        return eventRepository.findAll();
    }

    @Override
    public List<Event> searchEvents(String text) {
        return eventRepository.findEventsByName(text);
    }

    @Override
    public List<Event> findAllByLocation_Id(Long id) {
        return eventRepository.findAllByLocation_Id(id);
    }

    @Override
    public Optional<Event> findById(Long id) {
        return eventRepository.findById(id);
    }

    public boolean eventNameExists(String eventName) {
        return eventRepository.existsByName(eventName);
    }

    public boolean locationIdExists(Long locationId) {
        return eventRepository.existsByLocationId(locationId);
    }

    @Override
    public Optional<Event> saveEvent(String name, String description, Integer popularityScore, Long locationId) {

        Location location = locationRepository.findById(locationId)
                .orElseThrow(() -> new IllegalArgumentException("Location not found"));

        Event event = new Event(name, description, popularityScore, location);

        return Optional.of(eventRepository.save(event));
    }

    @Override
    public Event saveEvent(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public void updateEvent(Long eventId, String name, String description, Integer popularityScore, Long locationId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new IllegalArgumentException("Event not found"));
        Location location = locationRepository.findById(locationId)
                .orElseThrow(() -> new IllegalArgumentException("Location not found"));

        event.setName(name);
        event.setDescription(description);
        event.setPopularityScore(popularityScore);
        event.setLocation(location);

        eventRepository.save(event);
    }

    @Override
    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }
}
