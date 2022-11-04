package com.devsu;

import com.devsu.model.Cliente;
import com.devsu.repository.ClienteRepository;
import com.devsu.service.ClienteService;
import com.devsu.service.impl.ClienteServiceImpl;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ClienteServiceTest {

    @Mock
    ClienteRepository clienteRepository;

    @InjectMocks
    ClienteServiceImpl clienteService;

    Cliente cliente;

    com.devsu.entities.Cliente cliente1;

    @BeforeAll
    public void setup() {
        cliente = Cliente.builder()
                .estado(Boolean.TRUE)
                .nombre("Jose prubea")
                .primerApellido("prueba")
                .segundoApellido("prueba")
                .fechaNacimiento(LocalDate.now())
                .identificacion("11111")
                .password("pass")
                .direccion("calle sur")
                .telefono("55555")
                .build();

        cliente1 = com.devsu.entities.Cliente.builder().build();
    }

    @Test
    void crearTest() {

        Mockito.when(clienteRepository.save(cliente1))
                .thenReturn(new com.devsu.entities.Cliente());


        Cliente cliente1 = clienteService.crear(cliente);
        Assertions.assertNotNull(cliente1);
    }

}
