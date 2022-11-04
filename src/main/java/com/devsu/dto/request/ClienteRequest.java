package com.devsu.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class ClienteRequest {

    @NotNull
    @NotBlank
    private String nombre;

    private String primerApellido;

    @NotNull
    private String identificacion;

    private String segundoApellido;

    @NotNull
    private String telefono;

    @NotNull
    private LocalDate fechaNacimiento;

    @NotNull
    private String direccion;

    @NotNull
    private String password;
}
