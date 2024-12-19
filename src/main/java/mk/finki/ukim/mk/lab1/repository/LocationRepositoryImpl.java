//package mk.finki.ukim.mk.lab1.repository;
//
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.PersistenceContext;
//import jakarta.transaction.Transactional;
//import mk.finki.ukim.mk.lab1.bootstrap.DataHolder;
//import mk.finki.ukim.mk.lab1.model.Location;
//import org.springframework.stereotype.Repository;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//@Repository
//public class LocationRepository {
//
//    private final List<Location> locations=new ArrayList<>();
//    @PersistenceContext
//    private EntityManager entityManager;
//
//    public LocationRepository()
//    {
////        locations.add(new Location("Location1", "Address1", "100", "Description1"));
////        locations.add(new Location("Location2", "Address2", "200", "Description2"));
////        locations.add(new Location("Location3", "Address3", "150", "Description3"));
////        locations.add(new Location("Location4", "Address4", "120", "Description4"));
////        locations.add(new Location("Location5", "Address5", "250", "Description5"));
//    }
//
//    public List<Location> findAll()
//    {
////        return DataHolder.locations;
//        return entityManager.createQuery("SELECT l FROM Location l", Location.class).getResultList();
//
//    }
//
//    public Optional<Location> findById(Long id)
//    {
////        return DataHolder.locations.stream().filter(l->l.getId().equals(id)).findFirst();
//        Location location = entityManager.find(Location.class, id);
//        return Optional.ofNullable(location);
//    }
//
//    @Transactional
//    public Location save(Location location) {
//        if (location.getId() == null) {
//            entityManager.persist(location); // New location
//            return location;
//        } else {
//            return entityManager.merge(location); // Update existing location
//        }
//    }
//
//    @Transactional
//    public void deleteById(Long id) {
//        Location location = entityManager.find(Location.class, id);
//        if (location != null) {
//            entityManager.remove(location);
//        }
//    }
//
//}
