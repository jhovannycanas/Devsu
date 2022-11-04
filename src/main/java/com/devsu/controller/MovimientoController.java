package com.devsu.controller;

import com.devsu.dto.request.MovimientoRequest;
import com.devsu.dto.response.MovimientoResponse;
import com.devsu.model.Cuenta;
import com.devsu.model.Movimiento;
import com.devsu.service.MovimientoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class MovimientoController implements MovimientoApi {

    private final MovimientoService movimientoService;

    @Override
    public ResponseEntity<MovimientoResponse> crear(MovimientoRequest movimientoRequest) {

        Movimiento movimiento = movimientoService.crear(Movimiento.builder()
                .cuenta(Cuenta.builder().id(UUID.fromString(movimientoRequest.getCuenta())).build())
                .tipoMovimiento(movimientoRequest.getTipoMovimiento())
                .valor(movimientoRequest.getValor()).build());
        return new ResponseEntity<MovimientoResponse>( MovimientoResponse.builder()
                .id(movimiento.getId()).numeroCuenta(movimiento.getCuenta().getNumeroCuenta())
                .saldoInicial(movimiento.getCuenta().getSaldoInicial())
                .tipoCuenta(movimiento.getCuenta().getTipoCuenta())
                .estado(movimiento.getEstado())
                .valor(movimiento.getValor())
                .tipoMovimiento(movimiento.getTipoMovimiento().name())
                .build(), HttpStatus.CREATED);
    }
}
