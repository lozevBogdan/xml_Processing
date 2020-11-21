package exercise.hml.processing.demo.repositories;

import exercise.hml.processing.demo.entities.Customer;
import exercise.hml.processing.demo.entities.Part;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {


    @Query("select c from Customer c order by c.dateOfBirth,c.youngDriver")
    Set<Customer> findAllOrderByBirthDateAndOldestDriver();


    @Query("select c from Customer c where c.cars.size > 0")
    Set<Customer> getAllAtLestOneCar();

}
