//package mk.finki.ukim.mk.lab1.repository;
//
//
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.PersistenceContext;
//import jakarta.persistence.TypedQuery;
//import jakarta.transaction.Transactional;
//import mk.finki.ukim.mk.lab1.bootstrap.DataHolder;
//import mk.finki.ukim.mk.lab1.model.Event;
//import mk.finki.ukim.mk.lab1.model.EventBooking;
//import mk.finki.ukim.mk.lab1.model.Location;
//import mk.finki.ukim.mk.lab1.model.User;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//@Repository
//public class EventRepository {
//
//    @PersistenceContext
//    private EntityManager entityManager;
//
//    public List<Event> findAll(){
////        return DataHolder.events;
//        return entityManager.createQuery("SELECT e FROM Event e", Event.class).getResultList();
//
//    }
//
//    public Optional<Event> findById(Long id){
//
////        return DataHolder.events.stream().filter(e->e.getId().equals(id)).findFirst();
//        Event event = entityManager.find(Event.class, id);
//        return Optional.ofNullable(event);
//    }
//
//    public List<Event> searchEvents(String text){
//        return DataHolder.events.stream()
//                .filter(e->e.getName().contains(text) || e.getDescription().contains(text))
//                .collect(Collectors.toList());
//    }
//
//    @Transactional
//    public Optional<Event> saveEvent(Event event)
//    {
////        DataHolder.events.removeIf(e->e.getName().equals(event.getName()));
////        DataHolder.events.add(event);
////        return Optional.of(event);
//        if (event.getId() == null) {
//            // If the event doesn't have an ID, persist it as a new entity
//            entityManager.persist(event);
//            return Optional.of(event);
//        } else {
//            // If the event already exists (ID is present), merge it
//            return Optional.ofNullable(entityManager.merge(event));
//        }
//    }
//
//    @Transactional
//    public void deleteEvent(Event event)
//    {
////        DataHolder.events.remove(event);
//        if (event != null && event.getId() != null) {
//            // Find the event by ID to ensure it exists in the database
//            Event existingEvent = entityManager.find(Event.class, event.getId());
//            if (existingEvent != null) {
//                // Remove the event from the database
//                entityManager.remove(existingEvent);
//            }
//        }
//    }
//
//
//    public Optional<Event> findByName(String name) {
//        try {
//            Event event = entityManager.createQuery("SELECT e FROM Event e WHERE e.name = :name", Event.class)
//                    .setParameter("name", name)
//                    .getSingleResult();
//            return Optional.ofNullable(event);
//        } catch (Exception e) {
//            return Optional.empty(); // Return an empty Optional if no event is found or if there's an exception
//        }
//    }
//
//    public List<Event> findByLocationId(Long locationId) {
//        return entityManager.createQuery(
//                        "SELECT e FROM Event e WHERE e.locationId = :locationId", Event.class)
//                .setParameter("locationId", locationId)
//                .getResultList();
//    }
//    @Transactional
//    public void deleteAllByLocationId(Long locationId) {
//        entityManager.createQuery(
//                        "DELETE FROM Event e WHERE e.locationId = :locationId")
//                .setParameter("locationId", locationId)
//                .executeUpdate();
//    }
//
//
//
//
//
//
//}
