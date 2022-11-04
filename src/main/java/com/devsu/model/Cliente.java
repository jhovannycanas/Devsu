package com.devsu.model;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@SuperBuilder(toBuilder = true)
@Data
public class Cliente extends Persona{

    private String password;

    private Boolean estado;
}
