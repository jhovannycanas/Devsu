package com.devsu.service;

import com.devsu.model.ClienteMovimiento;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface ReporteService {

    List<ClienteMovimiento> reporteMovimientoCliente(UUID id, LocalDate fechaInicia, LocalDate fechaFinal);
}
