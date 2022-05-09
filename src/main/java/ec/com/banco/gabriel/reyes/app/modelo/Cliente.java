package ec.com.banco.gabriel.reyes.app.modelo;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table
@Data
public class Cliente extends Persona {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codigo_cliente")
	private Long codigoCliente;

	@Column
	private String contrasenia;

	@Column
	private String estado;

	@OneToMany(orphanRemoval = true, fetch = FetchType.LAZY)
	private List<Cuenta> listaCuenta;
}
