package ec.com.banco.gabriel.reyes.app.servicio.implementacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.com.banco.gabriel.reyes.app.modelo.Cliente;
import ec.com.banco.gabriel.reyes.app.repositorio.ClienteRepositorio;
import ec.com.banco.gabriel.reyes.app.servicio.ClienteServicio;

@Service
public class ClienteServicioImpl implements ClienteServicio {

	@Autowired
	private ClienteRepositorio clienteRepositorio;

	@Override
	public Cliente obtenerPorClavePrimaria(Long codigoCliente) {
		return clienteRepositorio.findByCodigoCliente(codigoCliente).orElse(null);
	}

	@Override
	public Cliente obtenerPorIdentificacion(String identificacion) {
		return clienteRepositorio.findByIdentificacion(identificacion).orElse(null);
	}

	@Override
	public Cliente guardarCliente(Cliente cliente) {
		Cliente clienteRegistrado = clienteRepositorio.findByIdentificacion(cliente.getIdentificacion()).orElse(null);
		if (null == clienteRegistrado) {
			return clienteRepositorio.save(cliente);
		} else {
			return clienteRepositorio.save(obtenerClienteModificado(cliente, clienteRegistrado));
		}

	}

	private Cliente obtenerClienteModificado(Cliente cliente, Cliente clienteRegistrado) {
		clienteRegistrado.setContrasenia(cliente.getContrasenia());
		clienteRegistrado.setDireccion(cliente.getDireccion());
		clienteRegistrado.setEdad(cliente.getEdad());
		clienteRegistrado.setEstado(cliente.getEstado());
		clienteRegistrado.setGenero(cliente.getGenero());
		clienteRegistrado.setIdentificacion(cliente.getIdentificacion());
		clienteRegistrado.setNombre(cliente.getNombre());
		clienteRegistrado.setTelefono(cliente.getTelefono());
		return clienteRegistrado;
	}

	@Override
	public void eliminarCliente(Cliente cliente) {
		clienteRepositorio.delete(cliente);
	}

	@Override
	public void eliminarCliente(Long codigoCliente) {
		Cliente cliente = obtenerPorClavePrimaria(codigoCliente);
		if (null == cliente) {
			return;
		} else {
			eliminarCliente(cliente);
		}
	}

}
