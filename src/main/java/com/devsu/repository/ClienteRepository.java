package com.devsu.repository;

import com.devsu.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ClienteRepository extends JpaRepository<Cliente, UUID> {

    Optional<Cliente> findByPersonaIdentificacionAndEstadoIsTrue(String documento);

    Optional<Cliente> findByIdAndEstadoIsTrue(UUID id);
}
