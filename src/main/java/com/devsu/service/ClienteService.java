package com.devsu.service;

import com.devsu.model.Cliente;

import java.util.UUID;

public interface ClienteService {

    Cliente obtener(UUID id);

    Cliente crear(Cliente cliente);

    void actualizar(Cliente cliente, UUID id);

    void desactivar(UUID id);
}
