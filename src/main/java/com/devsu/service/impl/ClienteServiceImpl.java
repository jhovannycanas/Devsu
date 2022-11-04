package com.devsu.service.impl;

import com.devsu.entities.Persona;
import com.devsu.exception.ResourceNotFound;
import com.devsu.model.Cliente;
import com.devsu.repository.ClienteRepository;
import com.devsu.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    @Override
    public Cliente obtener(UUID id) {
        return clienteRepository.findByIdAndEstadoIsTrue(id).map(cliente -> {
                   return  Cliente.builder().estado(cliente.getEstado())
                            .id(cliente.getId())
                            .direccion(cliente.getPersona().getDireccion())
                            .fechaNacimiento(cliente.getPersona().getFechaNacimiento())
                            .identificacion(cliente.getPersona().getIdentificacion())
                            .estado(cliente.getEstado())
                            .nombre(cliente.getPersona().getNombre())
                            .primerApellido(cliente.getPersona().getPrimerApellido())
                            .segundoApellido(cliente.getPersona().getSegundoApellido())
                            .telefono(cliente.getPersona().getTelefono())
                            .build();
                })
                .orElseThrow(() -> new ResourceNotFound("Recurso no encontrado con el id suministrado", 404));
    }

    @Override
    public Cliente crear(Cliente cliente) {
        if (clienteRepository.findByPersonaIdentificacionAndEstadoIsTrue(cliente.getIdentificacion()).isPresent()) {
            throw new IllegalArgumentException("El cliente ya existe");
        }
        Persona persona = Persona.builder()
                .direccion(cliente.getDireccion())
                .fechaNacimiento(cliente.getFechaNacimiento())
                .identificacion(cliente.getIdentificacion())
                .nombre(cliente.getNombre())
                .primerApellido(cliente.getPrimerApellido())
                .segundoApellido(cliente.getSegundoApellido())
                .telefono(cliente.getTelefono())
                .build();

        com.devsu.entities.Cliente clienteEntidad = com.devsu.entities.Cliente
                .builder()
                .estado(cliente.getEstado())
                .password(cliente.getPassword())
                .persona(persona)
                .build();

        clienteEntidad = clienteRepository.save(clienteEntidad);
        return Cliente.builder().estado(clienteEntidad.getEstado())
                .id(clienteEntidad.getId())
                .direccion(clienteEntidad.getPersona().getDireccion())
                .fechaNacimiento(clienteEntidad.getPersona().getFechaNacimiento())
                .identificacion(clienteEntidad.getPersona().getIdentificacion())
                .estado(clienteEntidad.getEstado())
                .nombre(clienteEntidad.getPersona().getNombre())
                .primerApellido(clienteEntidad.getPersona().getPrimerApellido())
                .segundoApellido(clienteEntidad.getPersona().getSegundoApellido())
                .telefono(clienteEntidad.getPersona().getTelefono())
                .build();
    }

    @Override
    public void actualizar(Cliente cliente, UUID id) {
        com.devsu.entities.Cliente clienteEntidad = clienteRepository.findByIdAndEstadoIsTrue(id)
                .orElseThrow(EntityNotFoundException::new);
        clienteEntidad.getPersona().setDireccion(cliente.getDireccion());
        clienteEntidad.getPersona().setFechaNacimiento(cliente.getFechaNacimiento());
        clienteEntidad.getPersona().setNombre(cliente.getNombre());
        clienteEntidad.getPersona().setPrimerApellido(cliente.getPrimerApellido());
        clienteEntidad.getPersona().setSegundoApellido(cliente.getSegundoApellido());
        clienteEntidad.setPassword(cliente.getPassword());
        clienteRepository.save(clienteEntidad);
    }

    @Override
    public void desactivar(UUID id) {
        com.devsu.entities.Cliente clienteEntidad = clienteRepository.findByIdAndEstadoIsTrue(id)
                .orElseThrow(EntityNotFoundException::new);
        clienteEntidad.setEstado(Boolean.FALSE);
        clienteRepository.save(clienteEntidad);
    }
}
