package exercise.hml.processing.demo.repositories;

import exercise.hml.processing.demo.entities.Part;
import exercise.hml.processing.demo.entities.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartRepository extends JpaRepository<Part,Long> {


}
