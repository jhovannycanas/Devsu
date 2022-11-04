package com.devsu.service;

import com.devsu.model.Cuenta;

import java.util.UUID;

public interface CuentaService {

    Cuenta crear(Cuenta cuenta);

    Cuenta obtener(UUID id);

    void desactivar(UUID id);
}
