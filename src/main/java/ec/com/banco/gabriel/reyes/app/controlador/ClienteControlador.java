package ec.com.banco.gabriel.reyes.app.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ec.com.banco.gabriel.reyes.app.modelo.Cliente;
import ec.com.banco.gabriel.reyes.app.servicio.ClienteServicio;
import ec.com.banco.gabriel.reyes.app.to.ClienteTo;

@RestController
@RequestMapping("/clientes")
public class ClienteControlador extends BaseControlador {

	@Autowired
	private ClienteServicio clienteServicio;

	@PostMapping("/guardar")
	public ResponseEntity<String> guardarCliente(@RequestBody Cliente cliente) {
		if (clienteServicio.guardarCliente(cliente) == null) {
			return new ResponseEntity<String>(ERROR_GUARDAR, HttpStatus.OK);
		}
		return new ResponseEntity<String>(MENSAJE_ALMACENADO_CORRECTAMENTE + cliente.getCodigoCliente(), HttpStatus.OK);
	}

	@PostMapping("/actualizar")
	public ResponseEntity<String> actualizarCliente(@RequestBody Cliente cliente) {
		if (clienteServicio.guardarCliente(cliente) == null) {
			return new ResponseEntity<String>(ERROR_ACTUALIZAR, HttpStatus.OK);
		}
		return new ResponseEntity<String>(MENSAJE_ACTUALIZADO_CORRECTAMENTE + cliente.getIdentificacion(),
				HttpStatus.OK);
	}

	@GetMapping("/obtener/{codigoCliente}")
	public ResponseEntity<ClienteTo> obtenerClienteTo(@PathVariable Long codigoCliente) {
		Cliente cliente = clienteServicio.obtenerPorClavePrimaria(codigoCliente);
		ClienteTo clienteTo;
		if (null == cliente) {
			clienteTo = convertirEnClienteTo(cliente, ERROR_CONSULTAR + codigoCliente, "ERR");
			return new ResponseEntity<ClienteTo>(clienteTo, HttpStatus.OK);
		}
		clienteTo = convertirEnClienteTo(cliente, "Cliente: " + cliente.getNombre(), "OK");
		return new ResponseEntity<ClienteTo>(clienteTo, HttpStatus.OK);
	}

	@DeleteMapping("/eliminar/{identificacion}")
	public ResponseEntity<String> eliminarCliente(@PathVariable String identificacion) {
		Cliente cliente = clienteServicio.obtenerPorIdentificacion(identificacion);
		if (null == cliente) {
			return new ResponseEntity<String>(ERROR_CONSULTAR_POR_IDENTIFICACION + identificacion, HttpStatus.OK);
		} else {
			clienteServicio.eliminarCliente(cliente);
			return new ResponseEntity<String>("Cliente " + cliente.getNombre() + " eliminado correctamente",
					HttpStatus.OK);
		}
	}

	private ClienteTo convertirEnClienteTo(Cliente cliente, String mensaje, String respuesta) {
		ClienteTo clienteTo = new ClienteTo();
		clienteTo.setCliente(cliente);
		clienteTo.setMensaje(mensaje);
		clienteTo.setRespuesta(respuesta);
		return clienteTo;
	}

}
