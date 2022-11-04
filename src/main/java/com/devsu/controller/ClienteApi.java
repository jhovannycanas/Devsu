package com.devsu.controller;


import com.devsu.dto.request.ClienteRequest;
import com.devsu.dto.response.ClienteResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.UUID;

@RequestMapping(value = "/api/v1/clientes")
@Api(value = "Cliente API")
public interface ClienteApi {

    @ApiOperation(value = "Permite crear un cliente", nickname = "cliente",
            notes = "<h3>Crea un cliente.</h3>", tags={ "Cliente", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "ok"),
            @ApiResponse(code = 201, message = "created"),
            @ApiResponse(code = 500, message = "internal server error") })
    @RequestMapping(
            produces = { "application/json" },
            consumes = { "application/json" },
            method = RequestMethod.POST)
    ResponseEntity<UUID> crear(@Validated @RequestBody ClienteRequest clienteRequestDto);

    @ApiOperation(value = "Permite actualizar un cliente", nickname = "cliente",
            notes = "<h3>Actualizar un cliente.</h3>", tags={ "Cliente", })
    @ApiResponses(value = {
            @ApiResponse(code = 202, message = "accepted"),
            @ApiResponse(code = 500, message = "internal server error") })
    @RequestMapping(value = "/{id}",
            produces = { "application/json" },
            consumes = { "application/json" },
            method = RequestMethod.PATCH)
    ResponseEntity<Void> actualizar(@PathVariable UUID id, @Valid @RequestBody ClienteRequest clienteRequestDto);

    @ApiOperation(value = "Permite eliminar un cliente", nickname = "cliente",
            notes = "<h3>Eliminar un cliente.</h3>", tags={ "Cliente", })
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "No content"),
            @ApiResponse(code = 403, message = "request has been refused"),
            @ApiResponse(code = 500, message = "internal server error") })
    @RequestMapping(value = "/{id}",
            method = RequestMethod.DELETE)
    ResponseEntity<Void> eliminar(@PathVariable String id);

    @ApiOperation(value = "Permite obtener un cliente", nickname = "cliente",
            notes = "<h3>Obtener un cliente.</h3>", tags={ "Cliente", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "ok"),
            @ApiResponse(code = 404, message = "resource not found"),
            @ApiResponse(code = 500, message = "internal server error") })
    @RequestMapping(value = "/{id}",
            method = RequestMethod.GET)
    ResponseEntity<ClienteResponse> obtener(@PathVariable String id);

}
