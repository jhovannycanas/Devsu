package com.devsu.entities;

import com.devsu.enumeracion.TipoCuenta;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "tbl_cuenta")
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Getter
@Setter
public class Cuenta extends AbstractBase {

    @NotNull
    private String numeroCuenta;

    private BigDecimal saldoInicial;

    private Boolean estado;

    @Column(name = "tipo")
    @Enumerated(EnumType.STRING)
    private TipoCuenta tipoCuenta;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cuenta", orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Movimiento> movimientos;

    private BigDecimal saldoDisponible;


    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
}
