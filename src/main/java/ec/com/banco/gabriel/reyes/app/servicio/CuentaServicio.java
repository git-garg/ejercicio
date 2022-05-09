package ec.com.banco.gabriel.reyes.app.servicio;

import ec.com.banco.gabriel.reyes.app.modelo.Cuenta;
import ec.com.banco.gabriel.reyes.app.servicio.excepcion.CuentaExcepcion;
import ec.com.banco.gabriel.reyes.app.to.CuentaTo;

public interface CuentaServicio {

	Cuenta obtenerPorClavePrimaria(Long codigoCuenta);

	Cuenta guardarCuenta(CuentaTo cuentaTo) throws CuentaExcepcion;

	void eliminarCuenta(Cuenta cuenta);

	Cuenta obtenerPorNumeroCuenta(String numeroCuenta);

}
