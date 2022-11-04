package com.devsu.dto.response;

import com.devsu.enumeracion.TipoCuenta;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
public class CuentaResponse {

    private UUID cliente;

    private String numeroCuenta;

    private BigDecimal saldoDisponible;

    @Min(0)
    private BigDecimal saldoInicial;

    private TipoCuenta tipoCuenta;
}
