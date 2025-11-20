package org.formation.proxyBanque.repository;

import org.formation.proxyBanque.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
