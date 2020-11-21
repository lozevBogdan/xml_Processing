package exercise.hml.processing.demo.repositories;

import exercise.hml.processing.demo.entities.Product;
import exercise.hml.processing.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {


    @Query("select p from Product p where p.price between 500 and 1000 and p.buyer is null " +
            "order by p.price desc ")
    Set<Product> getAllProductInRange500And1000();




}
