package ec.com.banco.gabriel.reyes.app.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ec.com.banco.gabriel.reyes.app.servicio.MovimientoServicio;
import ec.com.banco.gabriel.reyes.app.servicio.excepcion.MovimientoExcepcion;
import ec.com.banco.gabriel.reyes.app.to.MovimientoTo;

@RestController
@RequestMapping("/movimientos")
public class MovimientoControlador extends BaseControlador {

	@Autowired
	private MovimientoServicio movimientoServicio;

	@PostMapping("/guardar/")
	public ResponseEntity<String> guardarMovimiento(@RequestBody MovimientoTo movimientoTo) {

		try {
			movimientoServicio.guardarMovimiento(movimientoTo);
			return new ResponseEntity<String>(MENSAJE_ALMACENADO_CORRECTAMENTE, HttpStatus.OK);
		} catch (MovimientoExcepcion e) {
			return new ResponseEntity<String>(ERROR_GUARDAR + PUNTO + e.getMessage(), HttpStatus.OK);
		}

	}

}
