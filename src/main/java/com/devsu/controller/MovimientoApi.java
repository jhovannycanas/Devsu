package com.devsu.controller;

import com.devsu.dto.request.MovimientoRequest;
import com.devsu.dto.response.MovimientoResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@RequestMapping(value = "/api/v1/movimientos")
@Api(value = "Movimiento API")
public interface MovimientoApi {

    @ApiOperation(value = "Permite crear un movimiento", nickname = "movimiento",
            notes = "<h3>Crea un movimiento.</h3>", tags={ "Movimiento", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "ok"),
            @ApiResponse(code = 403, message = "request has been refused"),
            @ApiResponse(code = 500, message = "internal server error") })
    @RequestMapping(
            produces = { "application/json" },
            consumes = { "application/json" },
            method = RequestMethod.POST)
    ResponseEntity<MovimientoResponse> crear(@Valid @RequestBody MovimientoRequest movimientoRequest);

}
