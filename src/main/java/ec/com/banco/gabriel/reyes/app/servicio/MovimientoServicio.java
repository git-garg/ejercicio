package ec.com.banco.gabriel.reyes.app.servicio;

import ec.com.banco.gabriel.reyes.app.modelo.Movimiento;
import ec.com.banco.gabriel.reyes.app.servicio.excepcion.MovimientoExcepcion;
import ec.com.banco.gabriel.reyes.app.to.MovimientoTo;

public interface MovimientoServicio {

	Movimiento obtenerPorClavePrimaria(Long codigoMovimiento);

	Movimiento guardarMovimiento(Movimiento movimiento);

	Movimiento guardarMovimiento(MovimientoTo movimientoTo) throws MovimientoExcepcion;

	void eliminar(Movimiento movimiento);

}
