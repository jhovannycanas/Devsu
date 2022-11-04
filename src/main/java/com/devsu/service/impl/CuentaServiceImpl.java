package com.devsu.service.impl;

import com.devsu.entities.Cliente;
import com.devsu.model.Cuenta;
import com.devsu.repository.ClienteRepository;
import com.devsu.repository.CuentaRepository;
import com.devsu.service.CuentaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CuentaServiceImpl implements CuentaService {

    private final CuentaRepository cuentaRepository;
    private final ClienteRepository clienteRepository;

    @Override
    public Cuenta crear(Cuenta cuenta) {
        Cliente cliente = clienteRepository.findById(cuenta.getCliente().getId()).orElseThrow(EntityNotFoundException::new);
        com.devsu.entities.Cuenta cuentaEntidad = com.devsu.entities.Cuenta.builder()
                        .numeroCuenta(cuenta.getNumeroCuenta())
                                .tipoCuenta(cuenta.getTipoCuenta())
                                        .estado(cuenta.getEstado())
                                                .cliente(cliente)
                                                        .saldoInicial(cuenta.getSaldoInicial())
                .saldoDisponible(cuenta.getSaldoInicial())
                .build();
        cuentaEntidad = cuentaRepository.save(cuentaEntidad);
        cuenta.setId(cuentaEntidad.getId());
        return cuenta;
    }

    @Override
    public Cuenta obtener(UUID id) {
        return cuentaRepository.findById(id)
                .map(cuenta -> {
                    return Cuenta.builder()
                            .id(cuenta.getId())
                            .saldoInicial(cuenta.getSaldoInicial())
                            .saldoDisponible(cuenta.getSaldoDisponible())
                            .tipoCuenta(cuenta.getTipoCuenta())
                            .numeroCuenta(cuenta.getNumeroCuenta())
                            .estado(cuenta.getEstado())
                            .cliente(com.devsu.model.Cliente.builder().id(cuenta.getCliente().getId()).build())
                            .build();
                })
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public void desactivar(UUID id) {
        com.devsu.entities.Cuenta cuentaEntidad = cuentaRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        cuentaEntidad.setEstado(Boolean.FALSE);
        cuentaRepository.save(cuentaEntidad);
    }
}
