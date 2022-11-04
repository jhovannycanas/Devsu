package com.devsu.entities;

import com.devsu.enumeracion.TipoMovimiento;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "tbl_movimiento")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Movimiento extends AbstractBase{

    private BigDecimal valor;

    @ManyToOne
    @JoinColumn(name = "cuenta_id")
    private Cuenta cuenta;

    private Boolean estado;

    @Column(name = "tipo")
    @Enumerated(EnumType.STRING)
    private TipoMovimiento tipoMovimiento;

    @Column(name = "fecha_creacion", nullable = true)
    private LocalDate fechaCreacion;
}
