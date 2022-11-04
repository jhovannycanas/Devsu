package com.devsu.repository;

import com.devsu.entities.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CuentaRepository extends JpaRepository<Cuenta, UUID> {
}
