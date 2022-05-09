package ec.com.banco.gabriel.reyes.app.servicio.implementacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.com.banco.gabriel.reyes.app.modelo.Cliente;
import ec.com.banco.gabriel.reyes.app.modelo.Cuenta;
import ec.com.banco.gabriel.reyes.app.repositorio.ClienteRepositorio;
import ec.com.banco.gabriel.reyes.app.repositorio.CuentaRepositorio;
import ec.com.banco.gabriel.reyes.app.servicio.CuentaServicio;
import ec.com.banco.gabriel.reyes.app.servicio.excepcion.CuentaExcepcion;
import ec.com.banco.gabriel.reyes.app.to.CuentaTo;

@Service
public class CuentaServicioImpl implements CuentaServicio {

	@Autowired
	private CuentaRepositorio cuentaRepositorio;

	@Autowired
	private ClienteRepositorio clienteRepositorio;

	@Override
	public Cuenta obtenerPorClavePrimaria(Long codigoCuenta) {
		return cuentaRepositorio.getById(codigoCuenta);
	}

	@Override
	public Cuenta guardarCuenta(CuentaTo cuentaTo) throws CuentaExcepcion {

		Cuenta cuenta = cuentaTo.getCuenta();
		
		Cuenta cuentaRegistrada = cuentaRepositorio.findByNumeroCuenta(cuenta.getNumeroCuenta())
				.orElse(null);

		if (null == cuentaTo.getIdentificacion()) {

			if (null == cuentaRegistrada) {
				throw new CuentaExcepcion("No existe cuenta para: " + cuenta.getNumeroCuenta());
			}
			return cuentaRepositorio.save(obtenerCuentaModificada(cuentaRegistrada, cuenta));
		}

		if (null != cuentaRegistrada) {
			throw new CuentaExcepcion("Ya existe cuenta con numero: " + cuenta.getNumeroCuenta());
		}
		Cliente cliente = clienteRepositorio.findByIdentificacion(cuentaTo.getIdentificacion()).orElse(null);

		if (null == cliente) {
			throw new CuentaExcepcion("No existe cliente para: " + cuentaTo.getIdentificacion());
		}
		cuenta.setCliente(cliente);
		return cuentaRepositorio.save(cuenta);
	}

	private Cuenta obtenerCuentaModificada(Cuenta cuentaRegistrada, Cuenta cuenta) {
		cuentaRegistrada.setEstado(cuenta.getEstado());
		cuentaRegistrada.setNumeroCuenta(cuenta.getNumeroCuenta());
		cuentaRegistrada.setSaldoInicial(cuenta.getSaldoInicial());
		cuentaRegistrada.setTipoCuenta(cuenta.getTipoCuenta());
		return cuentaRegistrada;
	}

	@Override
	public void eliminarCuenta(Cuenta cuenta) {
		cuentaRepositorio.delete(cuenta);
	}

	@Override
	public Cuenta obtenerPorNumeroCuenta(String numeroCuenta) {
		return cuentaRepositorio.findByNumeroCuenta(numeroCuenta).orElse(null);
	}

}
