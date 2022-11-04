package com.devsu.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;


@Entity
@Table(name = "tbl_persona")
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Persona extends AbstractBase{

    private String identificacion;

    private String nombre;

    private String primerApellido;

    @NotNull
    private String segundoApellido;

    private String telefono;

    private LocalDate fechaNacimiento;

    private String direccion;
}
