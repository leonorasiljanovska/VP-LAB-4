package mk.finki.ukim.mk.lab1.service;

import mk.finki.ukim.mk.lab1.model.Location;

import java.util.List;
import java.util.Optional;

public interface LocationService {
    List<Location> findALl();
    Optional<Location> findById(Long id);
    Location save(Location location);
    void deleteById(Long id);

}
