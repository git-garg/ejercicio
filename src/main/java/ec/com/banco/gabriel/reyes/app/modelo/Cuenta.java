package ec.com.banco.gabriel.reyes.app.modelo;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table
public class Cuenta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codigo_cuenta")
	private Long codigoCuenta;

	@Column(name = "numero_cuenta")
	private String numeroCuenta;

	@Column(name = "tipo_cuenta")
	private String tipoCuenta;

	@Column(name = "saldo_inicial")
	private BigDecimal saldoInicial;

	@Column(name = "estado")
	private String estado;

	@ManyToOne
	@JoinColumn(name = "codigo_cliente", nullable = false)
	private Cliente cliente;

	@OneToMany(orphanRemoval = true, fetch = FetchType.LAZY)
	private List<Movimiento> listaMovimiento;

}
