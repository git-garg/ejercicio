package ec.com.banco.gabriel.reyes.app.to;

import ec.com.banco.gabriel.reyes.app.modelo.Cliente;
import lombok.Data;

@Data
public class ClienteTo {

	private Cliente cliente;
	private String respuesta;
	private String mensaje;

}
