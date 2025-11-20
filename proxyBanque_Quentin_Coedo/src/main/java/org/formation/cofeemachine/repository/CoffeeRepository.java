package org.formation.cofeemachine.repository;

import org.formation.cofeemachine.entity.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeRepository extends JpaRepository<Coffee, Long> {
}
