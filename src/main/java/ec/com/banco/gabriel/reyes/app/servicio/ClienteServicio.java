package ec.com.banco.gabriel.reyes.app.servicio;

import ec.com.banco.gabriel.reyes.app.modelo.Cliente;

public interface ClienteServicio {

	Cliente obtenerPorClavePrimaria(Long codigoCliente);

	Cliente guardarCliente(Cliente cliente);

	void eliminarCliente(Cliente cliente);

	void eliminarCliente(Long codigoCliente);

	Cliente obtenerPorIdentificacion(String identificacion);
}
