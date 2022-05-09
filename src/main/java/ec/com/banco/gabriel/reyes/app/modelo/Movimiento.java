package ec.com.banco.gabriel.reyes.app.modelo;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table
@Data
public class Movimiento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigoMovimiento;
	
	@Column(name = "fecha_movimiento")
	private Date fechaMovimiento;
	
	@Column(name = "tipo_movimiento")
	private String tipoMovimiento;
	
	@Column
	private BigDecimal valor;
	
	@Column
	private BigDecimal saldo;

	@ManyToOne
	@JoinColumn(name = "codigo_cuenta", nullable = false)
	private Cuenta cuenta;
}
