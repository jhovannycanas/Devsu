package com.devsu;

import com.devsu.dto.request.ClienteRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

@SpringBootTest
@DirtiesContext
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DevsuTestApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	ClienteRequest clienteRequest = new ClienteRequest();

	@BeforeAll
	public void setup() {
		clienteRequest.setDireccion("tststs");
		clienteRequest.setFechaNacimiento(LocalDate.parse("2022-10-25"));
		clienteRequest.setNombre("prueba");
		clienteRequest.setPrimerApellido("prueba");
		clienteRequest.setSegundoApellido("prueba");
		clienteRequest.setPassword("skjdksdfj");
		clienteRequest.setIdentificacion("224545");
		clienteRequest.setTelefono("12112");
	}
	@Test
	public void getEncuestaTest() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/clientes")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(clienteRequest))
        ).andExpect(MockMvcResultMatchers.status().isCreated());
	}



}
