package com.devsu.dto.request;

import com.devsu.enumeracion.TipoCuenta;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CuentaRequest {

    private String cliente;

    private BigDecimal saldoInicial;

    private TipoCuenta tipoCuenta;

    private String numeroCuenta;
}
