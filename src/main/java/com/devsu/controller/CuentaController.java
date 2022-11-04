package com.devsu.controller;

import com.devsu.dto.request.CuentaRequest;
import com.devsu.dto.response.CuentaResponse;
import com.devsu.model.Cliente;
import com.devsu.model.Cuenta;
import com.devsu.service.CuentaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class CuentaController implements CuentaApi{

    private final CuentaService cuentaService;

    @Override
    public ResponseEntity<UUID> crear(CuentaRequest cuentaRequest) {
        Cuenta cuenta = Cuenta.builder()
                .cliente(Cliente.builder().id(UUID.fromString(cuentaRequest.getCliente())).build())
                .numeroCuenta(cuentaRequest.getNumeroCuenta())
                .estado(Boolean.TRUE)
                .tipoCuenta(cuentaRequest.getTipoCuenta())
                .saldoInicial(cuentaRequest.getSaldoInicial())
                .build();
        return new ResponseEntity<>(cuentaService.crear(cuenta).getId(), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> eliminar(String id) {
        cuentaService.desactivar(UUID.fromString(id));
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<CuentaResponse> obtener(String id) {
        Cuenta cuenta = cuentaService.obtener(UUID.fromString(id));
        return ResponseEntity.ok(CuentaResponse.builder()
                        .numeroCuenta(cuenta.getNumeroCuenta())
                        .tipoCuenta(cuenta.getTipoCuenta())
                        .saldoInicial(cuenta.getSaldoInicial())
                        .saldoDisponible(cuenta.getSaldoDisponible())
                        .cliente(cuenta.getCliente().getId())
                        .build());
    }
}
