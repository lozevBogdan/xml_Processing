package exercise.hml.processing.demo.repositories;

import exercise.hml.processing.demo.entities.Car;
import exercise.hml.processing.demo.entities.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository extends JpaRepository<Sale,Long> {


}
