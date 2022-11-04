package com.devsu.controller;

import com.devsu.dto.request.ClienteRequest;
import com.devsu.dto.response.ClienteResponse;
import com.devsu.model.Cliente;
import com.devsu.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class ClienteController implements ClienteApi{

    private final ClienteService clienteService;

    @Override
    public ResponseEntity<UUID> crear(ClienteRequest clienteRequestDto) {
        Cliente cliente = Cliente.builder()
                .estado(Boolean.TRUE)
                .direccion(clienteRequestDto.getDireccion())
                .fechaNacimiento(clienteRequestDto.getFechaNacimiento())
                .direccion(clienteRequestDto.getDireccion())
                .identificacion(clienteRequestDto.getIdentificacion())
                .nombre(clienteRequestDto.getNombre())
                .primerApellido(clienteRequestDto.getPrimerApellido())
                .segundoApellido(clienteRequestDto.getSegundoApellido())
                .password(clienteRequestDto.getPassword())
                .build();
        return new ResponseEntity<>(clienteService.crear(cliente).getId(), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> actualizar(UUID id, ClienteRequest clienteRequestDto) {
        Cliente cliente = Cliente.builder()
                .direccion(clienteRequestDto.getDireccion())
                .fechaNacimiento(clienteRequestDto.getFechaNacimiento())
                .direccion(clienteRequestDto.getDireccion())
                .identificacion(clienteRequestDto.getIdentificacion())
                .nombre(clienteRequestDto.getNombre())
                .primerApellido(clienteRequestDto.getPrimerApellido())
                .segundoApellido(clienteRequestDto.getSegundoApellido())
                .password(clienteRequestDto.getPassword())
                .build();
        clienteService.actualizar(cliente,id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @Override
    public ResponseEntity<Void> eliminar(String id) {
        clienteService.desactivar(UUID.fromString(id));
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Override
    public ResponseEntity<ClienteResponse> obtener(String id) {
        Cliente cliente = clienteService.obtener(UUID.fromString(id));
        return ResponseEntity.ok(ClienteResponse.builder().id(cliente.getId()).estado(cliente.getEstado())
                .nombreCompleto(String.format("%s %s %s", cliente.getNombre(), cliente.getPrimerApellido(),
                        cliente.getSegundoApellido())).build());
    }
}
