package ui.online.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ui.online.entity.peopleEntity;

import java.util.Optional;

@Repository
public interface peopleRepository extends JpaRepository<peopleEntity, Integer> {
    Optional<peopleEntity> findByname(String name);
}