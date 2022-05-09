package ec.com.banco.gabriel.reyes.app.servicio.implementacion;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.com.banco.gabriel.reyes.app.modelo.Cuenta;
import ec.com.banco.gabriel.reyes.app.modelo.Movimiento;
import ec.com.banco.gabriel.reyes.app.repositorio.CuentaRepositorio;
import ec.com.banco.gabriel.reyes.app.repositorio.MoviemientoRepositorio;
import ec.com.banco.gabriel.reyes.app.servicio.MovimientoServicio;
import ec.com.banco.gabriel.reyes.app.servicio.excepcion.MovimientoExcepcion;
import ec.com.banco.gabriel.reyes.app.to.MovimientoTo;

@Service
public class MovimientoServicioImpl implements MovimientoServicio {

	private static final String MENSAJE_SALDO = "Saldo no disponible";

	@Autowired
	private MoviemientoRepositorio moviemientoRepositorio;

	@Autowired
	private CuentaRepositorio cuentaRepositorio;

	@Override
	public Movimiento obtenerPorClavePrimaria(Long codigoMovimiento) {
		return moviemientoRepositorio.getById(codigoMovimiento);
	}

	@Override
	public Movimiento guardarMovimiento(MovimientoTo movimientoTo) throws MovimientoExcepcion {

		String numeroCuenta = movimientoTo.getNumeroCuenta();
		Movimiento movimiento = movimientoTo.getMovimiento();

		Cuenta cuenta = cuentaRepositorio.findByNumeroCuenta(numeroCuenta).orElse(null);

		if (null == cuenta) {
			throw new MovimientoExcepcion(
					"No se puede almacenar el movimiento. No existe cuenta para: " + numeroCuenta);
		}
		Long codigoUltimoMovimiento = moviemientoRepositorio.obtenerCodigoUltimoMovimiento(cuenta);
		if (null == codigoUltimoMovimiento) {
			validarSaldo(movimiento, cuenta);
			return guardarMovimiento(obtenerMovimiento(movimiento, cuenta));
		} else {
			Movimiento ultimoMovimiento = moviemientoRepositorio.getById(codigoUltimoMovimiento);
			BigDecimal saldo = ultimoMovimiento.getSaldo();
			if ("C".equals(movimiento.getTipoMovimiento())) {
				saldo = saldo.add(movimiento.getValor());
			} else {
				validarSaldo(ultimoMovimiento);
				saldo = saldo.subtract(movimiento.getValor());
			}
			movimiento.setSaldo(saldo);
			movimiento.setCuenta(cuenta);
			movimiento.setFechaMovimiento(new Date());
		}

		return guardarMovimiento(movimiento);
	}

	private void validarSaldo(Movimiento movimiento, Cuenta cuenta) throws MovimientoExcepcion {

		if ("D".equals(movimiento.getTipoMovimiento())
				&& cuenta.getSaldoInicial().compareTo(movimiento.getValor()) < 0) {
			throw new MovimientoExcepcion(MENSAJE_SALDO);
		}

	}

	private void validarSaldo(Movimiento movimiento) throws MovimientoExcepcion {
		BigDecimal saldo = movimiento.getSaldo();
		if (null == saldo || saldo.equals(BigDecimal.ZERO)) {
			throw new MovimientoExcepcion(MENSAJE_SALDO);
		}
	}

	private Movimiento obtenerMovimiento(Movimiento movimiento, Cuenta cuenta) {
		movimiento.setFechaMovimiento(new Date());
		movimiento.setCuenta(cuenta);
		if (movimiento.getTipoMovimiento().equals("D")) {
			movimiento.setSaldo(cuenta.getSaldoInicial().subtract(movimiento.getValor()));
		} else {
			movimiento.setSaldo(cuenta.getSaldoInicial().add(movimiento.getValor()));
		}

		return movimiento;
	}

	@Override
	public Movimiento guardarMovimiento(Movimiento movimiento) {
		return moviemientoRepositorio.save(movimiento);
	}

	@Override
	public void eliminar(Movimiento movimiento) {
		moviemientoRepositorio.delete(movimiento);
	}

}
