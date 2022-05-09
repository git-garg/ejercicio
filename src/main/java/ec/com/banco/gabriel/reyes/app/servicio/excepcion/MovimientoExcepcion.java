package ec.com.banco.gabriel.reyes.app.servicio.excepcion;

public class MovimientoExcepcion extends Exception {

	private static final long serialVersionUID = 8329102494405120640L;

	public MovimientoExcepcion(String message, Throwable cause) {
		super(message, cause);
	}

	public MovimientoExcepcion(String message) {
		super(message);
	}

}
