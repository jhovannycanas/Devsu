package com.devsu.controller;

import com.devsu.model.ClienteMovimiento;
import com.devsu.service.ReporteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class ReporteController implements ReporteApi{

    private final ReporteService reporteService;

    @Override
    public List<ClienteMovimiento> obtener(String cliente, LocalDate fechainicial, LocalDate fechafinal) {
        return reporteService.reporteMovimientoCliente(UUID.fromString(cliente), fechafinal, fechafinal);
    }
}
