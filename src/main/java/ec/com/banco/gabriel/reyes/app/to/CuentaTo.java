package ec.com.banco.gabriel.reyes.app.to;

import ec.com.banco.gabriel.reyes.app.modelo.Cuenta;
import lombok.Data;

@Data
public class CuentaTo {

	private String identificacion;
	private Cuenta cuenta;
	
}
