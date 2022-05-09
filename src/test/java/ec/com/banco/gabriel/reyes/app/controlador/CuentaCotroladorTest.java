package ec.com.banco.gabriel.reyes.app.controlador;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.math.BigDecimal;

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
import ec.com.banco.gabriel.reyes.app.modelo.Cuenta;
import ec.com.banco.gabriel.reyes.app.servicio.CuentaServicio;
import ec.com.banco.gabriel.reyes.app.to.CuentaTo;
import ec.com.banco.gabriel.reyes.app.util.JsonUtil;

@SpringBootTest
@AutoConfigureMockMvc
public class CuentaCotroladorTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private CuentaServicio cuentaServicio;

	@Test
	public void debeiraGuardarCuenta() throws IOException, Exception {

		given(cuentaServicio.guardarCuenta(Mockito.any())).willReturn(obtenerCuenta());

		mvc.perform(post("/cuentas/guardar/").contentType(MediaType.APPLICATION_JSON)
				.content(JsonUtil.convertirAJson(obtenerCuentaTo()))).andExpect(status().isOk());

		verify(this.cuentaServicio, VerificationModeFactory.times(1)).guardarCuenta(Mockito.any());
		reset(this.cuentaServicio);
	}

	@Test
	public void deberiaActualizarCuenta() throws Exception {

		given(this.cuentaServicio.guardarCuenta(Mockito.any())).willReturn(obtenerCuenta());

		mvc.perform(post("/cuentas/actualizar/").contentType(MediaType.APPLICATION_JSON)
				.content(JsonUtil.convertirAJson(obtenerCuentaTo()))).andExpect(status().isOk());

		verify(this.cuentaServicio, VerificationModeFactory.times(1)).guardarCuenta(Mockito.any());
		reset(this.cuentaServicio);
	}

	private CuentaTo obtenerCuentaTo() {
		CuentaTo cuentaTo = new CuentaTo();
		cuentaTo.setCuenta(obtenerCuenta());
		cuentaTo.setIdentificacion("1234567890");
		return cuentaTo;
	}

	private Cuenta obtenerCuenta() {
		Cuenta cuenta = new Cuenta();
		cuenta.setCliente(obtenerCliente());
		cuenta.setCodigoCuenta(1l);
		cuenta.setNumeroCuenta("22005569");
		cuenta.setSaldoInicial(new BigDecimal(100));
		cuenta.setTipoCuenta("Ahorros");
		return cuenta;
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
