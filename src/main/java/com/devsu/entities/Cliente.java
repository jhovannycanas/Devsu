package com.devsu.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "tbl_cliente")
@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class Cliente extends AbstractBase{

    private String password;

    private Boolean estado;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "persona_id")
    private Persona persona;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente", orphanRemoval = true)
    private Set<Cuenta> cuentas;
}
