package com.devsu.model;

import com.devsu.enumeracion.TipoMovimiento;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.util.UUID;

@Value
@Builder
public class Movimiento {
    UUID id;
    BigDecimal valor;
    Cuenta cuenta;
    Boolean estado;
    TipoMovimiento tipoMovimiento;
}
