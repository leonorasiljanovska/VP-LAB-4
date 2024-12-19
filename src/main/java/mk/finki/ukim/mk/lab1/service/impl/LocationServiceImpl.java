package mk.finki.ukim.mk.lab1.service.impl;

import mk.finki.ukim.mk.lab1.model.Location;
import mk.finki.ukim.mk.lab1.repository.LocationRepository;
import mk.finki.ukim.mk.lab1.service.LocationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationServiceImpl implements LocationService {

    LocationRepository locationRepository;

    public LocationServiceImpl(LocationRepository locationRepository)
    {
        this.locationRepository=locationRepository;
    }
    @Override
    public List<Location> findALl() {
        return locationRepository.findAll();
    }

    @Override
    public Optional<Location> findById(Long id) {
        return locationRepository.findById(id);
    }

    @Override
    public Location save(Location location) {
        return locationRepository.save(location);
    }

    @Override
    public void deleteById(Long id) {
        locationRepository.deleteById(id);
    }
}
