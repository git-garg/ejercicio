package ec.com.banco.gabriel.reyes.app.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ec.com.banco.gabriel.reyes.app.modelo.Cuenta;
import ec.com.banco.gabriel.reyes.app.servicio.CuentaServicio;
import ec.com.banco.gabriel.reyes.app.servicio.excepcion.CuentaExcepcion;
import ec.com.banco.gabriel.reyes.app.to.CuentaTo;

@RestController
@RequestMapping("/cuentas")
public class CuentaControlador extends BaseControlador {

	@Autowired
	private CuentaServicio cuentaServicio;

	@PostMapping("/guardar/")
	public ResponseEntity<String> guardarCuenta(@RequestBody CuentaTo cuentaTo) {
		try {
			cuentaServicio.guardarCuenta(cuentaTo);
			return new ResponseEntity<String>(MENSAJE_ALMACENADO_CORRECTAMENTE + cuentaTo.getCuenta().getNumeroCuenta(),
					HttpStatus.OK);

		} catch (CuentaExcepcion e) {
			return new ResponseEntity<String>(ERROR_GUARDAR + PUNTO + e.getMessage(), HttpStatus.OK);
		}

	}

	@PostMapping("/actualizar/")
	public ResponseEntity<String> actualizarCuenta(@RequestBody CuentaTo cuentaTo) {
		try {
			cuentaServicio.guardarCuenta(cuentaTo);
			return new ResponseEntity<String>(
					MENSAJE_ACTUALIZADO_CORRECTAMENTE + cuentaTo.getCuenta().getNumeroCuenta(), HttpStatus.OK);
		} catch (CuentaExcepcion e) {
			return new ResponseEntity<String>(ERROR_ACTUALIZAR + PUNTO + e.getMessage(), HttpStatus.OK);
		}

	}

	@DeleteMapping("/eliminar/{numeroCuenta}")
	public ResponseEntity<String> eliminarCuenta(@PathVariable String numeroCuenta) {
		Cuenta cuenta = cuentaServicio.obtenerPorNumeroCuenta(numeroCuenta);
		if (null == cuenta) {
			return new ResponseEntity<String>(ERROR_CONSULTAR_POR_IDENTIFICACION + numeroCuenta, HttpStatus.OK);
		} else {
			cuentaServicio.eliminarCuenta(cuenta);
			return new ResponseEntity<String>("Cuenta " + cuenta.getNumeroCuenta() + " eliminada correctamente",
					HttpStatus.OK);
		}
	}

}
