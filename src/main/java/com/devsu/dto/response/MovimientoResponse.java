package com.devsu.dto.response;

import com.devsu.enumeracion.TipoCuenta;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.util.UUID;

@Value
@Builder
public class MovimientoResponse {

    UUID id;

    String numeroCuenta;

    TipoCuenta tipoCuenta;

    BigDecimal saldoInicial;

    BigDecimal valor;

    Boolean estado;

    String tipoMovimiento;
}
