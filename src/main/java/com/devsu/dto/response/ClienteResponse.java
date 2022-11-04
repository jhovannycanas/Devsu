package com.devsu.dto.response;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class ClienteResponse {

    UUID id;
    String nombreCompleto;
    Boolean estado;
}
