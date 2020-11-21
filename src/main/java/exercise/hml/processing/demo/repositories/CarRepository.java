package exercise.hml.processing.demo.repositories;

import exercise.hml.processing.demo.entities.Car;
import exercise.hml.processing.demo.entities.Part;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CarRepository extends JpaRepository<Car,Long> {

    @Query("select c from Car c where c.make = 'Toyota' " +
            "order by c.model,c.travelledDistance desc ")
    Set<Car> getAllCarsToyotaOrderByModelAndTravelledDistance();





}
