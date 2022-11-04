package com.devsu.dto.request;

import com.devsu.enumeracion.TipoMovimiento;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Builder
public class MovimientoRequest {

    String cuenta;
    BigDecimal valor;
    TipoMovimiento tipoMovimiento;
}
