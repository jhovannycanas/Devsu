package com.devsu.model;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDate;

@Value
@Builder
public class ClienteMovimiento {

    LocalDate fecha;

    String nombreCompleto;

    String numeroCuenta;

    String tipoCuenta;

    BigDecimal saldoInicial;

    Boolean estado;

    BigDecimal movimiento;

    BigDecimal saldoDisponible;
}
