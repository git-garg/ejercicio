package ec.com.banco.gabriel.reyes.app.controlador;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ec.com.banco.gabriel.reyes.app.dto.EstadoCuentaDto;
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

	@GetMapping("/reporte/{identificacion}/{fechaDesde}/{fechaHasta}")
	public ResponseEntity<List<Object>> obtenerMovimiento(@PathVariable String identificacion,
			@PathVariable String fechaDesde, @PathVariable String fechaHasta) {

		List<EstadoCuentaDto> listaEstadoCuenta = movimientoServicio.obtenerEstadoCuenta(identificacion,
				LocalDate.parse(fechaDesde).atStartOfDay(), LocalDate.parse(fechaHasta).atStartOfDay());

		return new ResponseEntity(listaEstadoCuenta, HttpStatus.OK);

	}

}
