package com.devsu.controller;

import com.devsu.dto.request.CuentaRequest;
import com.devsu.dto.response.CuentaResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.UUID;

@RequestMapping(value = "/api/v1/cuentas")
@Api(value = "Cuenta API")
public interface CuentaApi {

    @ApiOperation(value = "Permite crear una cuenta", nickname = "cuenta",
            notes = "<h3>Crea una cuenta.</h3>", tags={ "Cuenta", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "ok"),
            @ApiResponse(code = 403, message = "request has been refused"),
            @ApiResponse(code = 500, message = "internal server error") })
    @RequestMapping(
            produces = { "application/json" },
            consumes = { "application/json" },
            method = RequestMethod.POST)
    ResponseEntity<UUID> crear(@Valid @RequestBody CuentaRequest cuentaRequest);

    @ApiOperation(value = "Permite eliminar una cuenta", nickname = "cuenta",
            notes = "<h3>Eliminar una cuenta.</h3>", tags={ "Cuenta", })
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "No content"),
            @ApiResponse(code = 403, message = "request has been refused"),
            @ApiResponse(code = 500, message = "internal server error") })
    @RequestMapping(value = "/{id}",
            produces = { "application/json" },
            method = RequestMethod.DELETE)
    ResponseEntity<Void> eliminar(@PathVariable String id);

    @ApiOperation(value = "Permite obtener una cuenta", nickname = "cuenta",
            notes = "<h3>Obtener una cuenta.</h3>", tags={ "Cuenta", })
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "No content"),
            @ApiResponse(code = 403, message = "request has been refused"),
            @ApiResponse(code = 500, message = "internal server error") })
    @RequestMapping(value = "/{id}",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<CuentaResponse> obtener(@PathVariable String id);
}
