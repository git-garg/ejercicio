package ec.com.banco.gabriel.reyes.app.repositorio;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ec.com.banco.gabriel.reyes.app.dto.EstadoCuentaDto;
import ec.com.banco.gabriel.reyes.app.modelo.Cuenta;
import ec.com.banco.gabriel.reyes.app.modelo.Movimiento;

@Repository
public interface MovimientoRepositorio extends JpaRepository<Movimiento, Long> {

	@Query("SELECT MAX(m.codigoMovimiento) FROM Movimiento m WHERE m.cuenta = :cuenta ")
	Long obtenerCodigoUltimoMovimiento(@Param("cuenta") Cuenta cuenta);

	@Query(" SELECT new ec.com.banco.gabriel.reyes.app.dto.EstadoCuentaDto(mo.fechaMovimiento, mo.cuenta.cliente.nombre, "
			+ " mo.cuenta.numeroCuenta, mo.cuenta.tipoCuenta, mo.cuenta.saldoInicial, mo.cuenta.estado, mo.valor, mo.saldo)"
			+ " FROM Movimiento mo " + " WHERE mo.fechaMovimiento BETWEEN :fechaDesde AND :fechaHasta "
			+ " AND mo.cuenta.cliente.identificacion = :identificacion ")
	Optional<List<EstadoCuentaDto>> obtenerEstadoCuenta(@Param("fechaDesde") LocalDateTime fechaDesde,
			@Param("fechaHasta") LocalDateTime fechaHasta, @Param("identificacion") String identificacion);
}
