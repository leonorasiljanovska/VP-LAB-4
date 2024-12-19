package mk.finki.ukim.mk.lab1.repository;

import mk.finki.ukim.mk.lab1.model.Event;
import mk.finki.ukim.mk.lab1.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LocationRepository  extends JpaRepository<Location,Long> {
}
