package ec.com.banco.gabriel.reyes.app.controlador;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import ec.com.banco.gabriel.reyes.app.modelo.Cliente;
import ec.com.banco.gabriel.reyes.app.servicio.ClienteServicio;
import ec.com.banco.gabriel.reyes.app.util.JsonUtil;

@SpringBootTest
@AutoConfigureMockMvc
public class ClienteCotroladorTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private ClienteServicio clienteServicio;

	@Test
	public void deberiaGuardarCliente() throws IOException, Exception {

		Cliente cliente = obtenerCliente();

		given(clienteServicio.guardarCliente(Mockito.any())).willReturn(cliente);

		mvc.perform(post("/clientes/guardar").contentType(MediaType.APPLICATION_JSON)
				.content(JsonUtil.convertirAJson(cliente))).andExpect(status().isOk());

		verify(this.clienteServicio, VerificationModeFactory.times(1)).guardarCliente(Mockito.any());
		reset(this.clienteServicio);
	}

	@Test
	public void deberiaBuscarClientePorCodigo() throws Exception {
		Cliente cliente = obtenerCliente();

		given(this.clienteServicio.obtenerPorClavePrimaria(Mockito.anyLong())).willReturn(cliente);

		mvc.perform(get("/clientes/obtener/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

		verify(this.clienteServicio, VerificationModeFactory.times(1)).obtenerPorClavePrimaria(Mockito.anyLong());
		reset(this.clienteServicio);
	}

	private Cliente obtenerCliente() {
		Cliente cliente = new Cliente();
		cliente.setCodigoCliente(1l);
		cliente.setContrasenia("159");
		cliente.setDireccion("Avigiras");
		cliente.setEdad(36);
		cliente.setEstado("True");
		cliente.setGenero("Masculino");
		cliente.setIdentificacion("1234567890");
		cliente.setNombre("Gabriel Reyes");
		cliente.setTelefono("02228489");
		return cliente;
	}
}
