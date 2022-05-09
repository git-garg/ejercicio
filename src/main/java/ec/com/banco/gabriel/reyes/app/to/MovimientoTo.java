package ec.com.banco.gabriel.reyes.app.to;

import ec.com.banco.gabriel.reyes.app.modelo.Movimiento;
import lombok.Data;

@Data
public class MovimientoTo {
	private String numeroCuenta;
	private Movimiento movimiento;
}
