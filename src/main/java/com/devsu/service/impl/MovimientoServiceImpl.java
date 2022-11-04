package com.devsu.service.impl;

import com.devsu.enumeracion.TipoMovimiento;
import com.devsu.model.Movimiento;
import com.devsu.repository.CuentaRepository;
import com.devsu.repository.MovimientoRepository;
import com.devsu.service.MovimientoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class MovimientoServiceImpl implements MovimientoService {

    private final MovimientoRepository movimientoRepository;
    private final CuentaRepository cuentaRepository;

    @Value("${movimiento.maximoPermitido}")
    private long LIMITE_DIARIO ;

    @Override
    @Transactional
    public Movimiento crear(Movimiento movimiento) {
        com.devsu.entities.Cuenta cuenta = cuentaRepository.findById(movimiento.getCuenta().getId())
                .orElseThrow(EntityNotFoundException::new);

        if (movimiento.getTipoMovimiento() == TipoMovimiento.RETIRO) {
            if (cuenta.getSaldoDisponible().compareTo(BigDecimal.ZERO) == 0) {
                throw new IllegalArgumentException("Saldo no disponible");
            }

            if (cuenta.getSaldoDisponible().compareTo(movimiento.getValor()) <= 0) {
                throw new IllegalArgumentException("Saldo no disponible");
            }

            if (BigDecimal.valueOf(LIMITE_DIARIO).compareTo(movimiento.getValor()) < 0) {
                throw new IllegalArgumentException("Limite de retiro diario no permitido");
            }

            Long movimientoDiario = movimientoRepository.findMovimientoByDia(cuenta.getId());

            if (BigDecimal.valueOf(LIMITE_DIARIO).compareTo(BigDecimal.valueOf(movimientoDiario).add(movimiento.getValor())) <= 1) {
                throw new IllegalArgumentException("Cupo diario excedido");
            }

            cuenta.setSaldoDisponible(cuenta.getSaldoDisponible().subtract(movimiento.getValor()));
        } else {
            cuenta.setSaldoDisponible(cuenta.getSaldoDisponible().add(movimiento.getValor()));
        }

        com.devsu.entities.Movimiento movimientoEntidad = com.devsu.entities.Movimiento.builder()
                .tipoMovimiento(movimiento.getTipoMovimiento())
                .estado(Boolean.TRUE)
                .cuenta(cuenta)
                .fechaCreacion(LocalDate.now())
                        .valor(movimiento.getValor()).build();

        movimientoEntidad =  movimientoRepository.save(movimientoEntidad);
        cuentaRepository.save(cuenta);
        return Movimiento.builder().cuenta(com.devsu.model.Cuenta.builder()
                .tipoCuenta(movimientoEntidad.getCuenta().getTipoCuenta()).numeroCuenta(movimientoEntidad.getCuenta().getNumeroCuenta())
                .saldoInicial(movimientoEntidad.getCuenta().getSaldoInicial()).build()).valor(movimientoEntidad.getValor())
                .id(movimientoEntidad.getId()).estado(movimientoEntidad.getEstado())
                .tipoMovimiento(movimientoEntidad.getTipoMovimiento()).
                build();
    }
}
