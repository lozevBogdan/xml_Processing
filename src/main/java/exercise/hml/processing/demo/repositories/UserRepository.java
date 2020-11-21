package exercise.hml.processing.demo.repositories;

import exercise.hml.processing.demo.entities.Product;
import exercise.hml.processing.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.awt.*;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {


    @Query("select u from User u where u.sold.size > 0 " +
            "order by u.lastName,u.firstName")
    List<User> getSuccessfullySoldProducts();

    @Query("select u from User u where u.sold.size > 0 " +
            "order by u.sold.size desc ,u.lastName")
    List<User> getAllAtLeastOneProductOrderByProductsAndLastName();

}
