package ec.com.banco.gabriel.reyes.app.servicio.excepcion;

public class CuentaExcepcion extends Exception {

	private static final long serialVersionUID = 8329102494405120640L;

	public CuentaExcepcion(String message, Throwable cause) {
		super(message, cause);
	}

	public CuentaExcepcion(String message) {
		super(message);
	}

}
