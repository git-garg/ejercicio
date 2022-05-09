package ec.com.banco.gabriel.reyes.app.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class EstadoCuentaDto {

	private LocalDateTime fecha;
	private String cliente;
	private String numeroCuenta;
	private String tipo;
	private BigDecimal saldoInicial;
	private String estado;
	private BigDecimal valor;
	private BigDecimal saldo;

	public EstadoCuentaDto(LocalDateTime fecha, String cliente, String numeroCuenta, String tipo,
			BigDecimal saldoInicial, String estado, BigDecimal valor, BigDecimal saldo) {
		super();
		this.fecha = fecha;
		this.cliente = cliente;
		this.numeroCuenta = numeroCuenta;
		this.tipo = tipo;
		this.saldoInicial = saldoInicial;
		this.estado = estado;
		this.valor = valor;
		this.saldo = saldo;
	}

}
