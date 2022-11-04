package com.devsu.model;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.UUID;

@Data
@SuperBuilder(toBuilder = true)
public abstract class Persona {

    protected UUID id;

    private String identificacion;

    private String nombre;

    private String primerApellido;

    private String segundoApellido;

    private String telefono;

    private LocalDate fechaNacimiento;

    private String direccion;

}
