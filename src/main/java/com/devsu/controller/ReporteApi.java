package com.devsu.controller;

import com.devsu.model.ClienteMovimiento;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@RequestMapping(value = "/api/v1/reportes")
@Api(value = "Reporte API")
public interface ReporteApi {

    @ApiOperation(value = "Permite obtener los movimientos asociados a un cliente", nickname = "reporte",
            notes = "<h3>Reporte cliente movimientos.</h3>", tags={ "Reporte", })
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "No content"),
            @ApiResponse(code = 403, message = "request has been refused"),
            @ApiResponse(code = 500, message = "internal server error") })
    @RequestMapping(
            produces = { "application/json" },
            method = RequestMethod.GET)
    List<ClienteMovimiento> obtener(@RequestParam(value = "cliente", required = true) String cliente,
                                    @RequestParam(value = "fechainicial", required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechainicial,
                                    @RequestParam(value = "fechafinal", required = true)@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)  LocalDate fechafinal);
}
