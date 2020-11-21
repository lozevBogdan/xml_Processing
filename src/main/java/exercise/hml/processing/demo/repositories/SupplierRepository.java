package exercise.hml.processing.demo.repositories;

import exercise.hml.processing.demo.entities.Category;
import exercise.hml.processing.demo.entities.Customer;
import exercise.hml.processing.demo.entities.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier,Long> {

    @Query("select s from Supplier s where s.importer = false " +
            "order by s.parts.size desc ")
    Set<Supplier> getLocalSuppliers();

}
