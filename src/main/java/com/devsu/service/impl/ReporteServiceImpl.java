package com.devsu.service.impl;

import com.devsu.enumeracion.TipoMovimiento;
import com.devsu.model.ClienteMovimiento;
import com.devsu.repository.ClienteRepository;
import com.devsu.repository.MovimientoRepository;
import com.devsu.service.ReporteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReporteServiceImpl implements ReporteService {

    private final ClienteRepository clienteRepository;
    private final MovimientoRepository movimientoRepository;

    @Override
    public List<ClienteMovimiento> reporteMovimientoCliente(UUID id, LocalDate fechaInicia, LocalDate fechaFinal) {

        clienteRepository.findByIdAndEstadoIsTrue(id).orElseThrow(EntityNotFoundException::new);
        return movimientoRepository.findByCuenta_ClienteIdAndFechaCreacionBetween(id, fechaFinal, fechaFinal)
                .stream().map(movimiento -> ClienteMovimiento.builder()
                        .numeroCuenta(movimiento.getCuenta().getNumeroCuenta())
                        .saldoInicial(movimiento.getCuenta().getSaldoInicial())
                        .saldoDisponible(movimiento.getCuenta().getSaldoDisponible())
                        .fecha(movimiento.getFechaCreacion())
                        .estado(movimiento.getEstado())
                        .movimiento(movimiento.getTipoMovimiento().equals(TipoMovimiento.RETIRO) ? movimiento.getValor().negate() : movimiento.getValor())
                        .tipoCuenta(movimiento.getCuenta().getTipoCuenta().name()).build()
                ).collect(Collectors.toList());
    }
}
