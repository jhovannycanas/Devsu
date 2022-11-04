package com.devsu.model;

import com.devsu.enumeracion.TipoCuenta;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;


@Data
@Builder
public class Cuenta{

    protected UUID id;

    protected String numeroCuenta;

    protected BigDecimal saldoInicial;

    protected Boolean estado;

    protected TipoCuenta tipoCuenta;

    protected List<Movimiento> movimientos;

    protected BigDecimal saldoDisponible;

    protected Cliente cliente;

    public boolean esPositivo() {
        return this.saldoDisponible.compareTo(BigDecimal.ZERO) > 0;
    }

    public boolean esMayorOIgualCantidad(BigDecimal monto) {
        return this.saldoDisponible.compareTo(monto) >= 0;
    }

    public boolean esMayorCantidad(BigDecimal monto) {
        return this.saldoDisponible.compareTo(monto) > 0;
    }
}
