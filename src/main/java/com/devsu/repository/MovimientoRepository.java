package com.devsu.repository;

import com.devsu.entities.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface MovimientoRepository extends JpaRepository<Movimiento, UUID> {

    @Query("SELECT COALESCE(SUM(M.valor), 0.0) FROM Movimiento M where M.cuenta.id=:cuentaId AND M.fechaCreacion= CURRENT_DATE")
    Long findMovimientoByDia(UUID cuentaId);

    List<Movimiento> findByCuenta_ClienteIdAndFechaCreacionBetween(UUID id, LocalDate fechaInicial, LocalDate fechaFinal);
}
