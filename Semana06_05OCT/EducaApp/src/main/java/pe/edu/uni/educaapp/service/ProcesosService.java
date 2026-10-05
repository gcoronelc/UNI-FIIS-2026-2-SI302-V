package pe.edu.uni.educaapp.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import pe.edu.uni.educaapp.db.AccesoDB;
import pe.edu.uni.educaapp.dto.MatriculaDto;

/**
 *
 * @author Eric Gustavo Coronel Castillo
 * @blog https://gcoronelc.blogspot.com/
 * @email gcoronelc@gmail.com
 * @youtube https://www.youtube.com/DesarrollaSoftware
 * @facebook https://www.facebook.com/groups/desarrollasoftware/
 * @cursos https://gcoronelc.github.io/
 */
public class ProcesosService {

	public MatriculaDto matricular(MatriculaDto bean) {
		Connection cn = null;
		double precio = 5000.00;
		String sql;
		PreparedStatement pstm;
		ResultSet rs;
		int filas;
		try {
			// Inicio de TX
			cn = AccesoDB.getConnection();
			cn.setAutoCommit(false);
			// SECCION DE VALIDACION
			validarExisteCurso(cn, bean.getIdCurso());
			validarExisteAlumno(cn, bean.getIdAlumno());
			validarExisteEmpleado(cn, bean.getIdEmpleado());
			validarNoExisteMatricula(cn, bean.getIdCurso(), bean.getIdAlumno());
			validarTipoCorrecto(bean.getTipo());
			validarCantidadCuotas(bean.getTipo(), bean.getCuotas());
			// Falta validar vacantes y obtener precio.
			// SECCION DEL PROCESO
			sql = """
				UPDATE CURSO
				SET cur_matriculados = cur_matriculados + 1
				WHERE cur_id = ?
         """;
			pstm = cn.prepareStatement(sql);
			pstm.setInt(1, bean.getIdCurso());
			filas = pstm.executeUpdate();
			if (filas != 1) {
				throw new SQLException("Codigo del curso es incorrecto.");
			}
			pstm.close();
			sql = """
				INSERT INTO MATRICULA(cur_id,alu_id,emp_id,mat_tipo,mat_fecha,mat_precio,mat_cuotas)
				VALUES(?,?,?,?,GETDATE(),?,?)
         """;
			pstm = cn.prepareStatement(sql);
			pstm.setInt(1, bean.getIdCurso());
			pstm.setInt(2, bean.getIdAlumno());
			pstm.setInt(3, bean.getIdEmpleado());
			pstm.setString(4, bean.getTipo());
			pstm.setDouble(5, precio);
			pstm.setInt(6, bean.getCuotas());
			pstm.executeUpdate();
			// Fin de TX
			cn.commit();
			bean.setEstado(1);
			bean.setMensaje("Proceso ejecutado correctamente.");
		} catch (SQLException e) {
			try {
				cn.rollback();
			} catch (Exception e1) {
			}
			bean.setEstado(-1);
			bean.setMensaje(e.getMessage());
		} catch (Exception e) {
			try {
				cn.rollback();
			} catch (Exception e1) {
			}
			bean.setEstado(-1);
			bean.setMensaje("Error en el proceso, intentelo mas tarde.");
		} finally {
			try {
				cn.close();
			} catch (Exception e) {
			}
		}
		return bean;
	}

	private void validarExisteCurso(Connection cn, int idCurso) throws SQLException {
		String sql = "select count(1) cont from curso where cur_id=?";
		PreparedStatement pstm = cn.prepareStatement(sql);
		pstm.setInt(1, idCurso);
		ResultSet rs = pstm.executeQuery();
		rs.next();
		int cont = rs.getInt(1);
		rs.close();
		pstm.close();
		if (cont != 1) {
			throw new SQLException("El codigo de curso " + idCurso + " no es correcto.");
		}
	}

	private void validarExisteAlumno(Connection cn, int idAlumno) throws SQLException {
		String sql = "select count(1) cont from alumno where alu_id=?";
		PreparedStatement pstm = cn.prepareStatement(sql);
		pstm.setInt(1, idAlumno);
		ResultSet rs = pstm.executeQuery();
		rs.next();
		int cont = rs.getInt(1);
		rs.close();
		pstm.close();
		if (cont != 1) {
			throw new SQLException("El codigo de alumno " + idAlumno + " no es correcto.");
		}
	}

	private void validarExisteEmpleado(Connection cn, int idEmpleado) throws SQLException {
		String sql = "select count(1) cont from empleado where emp_id=?";
		PreparedStatement pstm = cn.prepareStatement(sql);
		pstm.setInt(1, idEmpleado);
		ResultSet rs = pstm.executeQuery();
		rs.next();
		int cont = rs.getInt(1);
		rs.close();
		pstm.close();
		if (cont != 1) {
			throw new SQLException("El codigo de empleado " + idEmpleado + " no es correcto.");
		}
	}

	private void validarNoExisteMatricula(Connection cn, int idCurso, int idAlumno) throws SQLException {
		String sql = "select count(1) cont from MATRICULA where cur_id=? and alu_id=?";
		PreparedStatement pstm = cn.prepareStatement(sql);
		pstm.setInt(1, idCurso);
		pstm.setInt(2, idAlumno);
		ResultSet rs = pstm.executeQuery();
		rs.next();
		int cont = rs.getInt(1);
		rs.close();
		pstm.close();
		if (cont > 0) {
			throw new SQLException("El alumno " + idAlumno + " ya se encuentra matriculado en el curso " + idCurso + ".");
		}
	}

	private void validarTipoCorrecto(String tipo) throws SQLException {
		tipo = tipo.toUpperCase().trim();
		boolean ok = switch (tipo) {
			case "REGULAR", "BECA", "MEDIABECA" ->
				true;
			default ->
				false;
		};
		if(!ok){
			throw new SQLException("El tipo de matricula " + tipo + " es incorrecto.");
		}
	}

	private void validarCantidadCuotas(String tipo, int cuotas) throws SQLException {
		int cMin, cMax;
		tipo = tipo.toUpperCase().trim();
		switch (tipo) {
			case "REGULAR" -> {
				cMin = 1;
				cMax = 3;
			}
			default -> {
				cMin = 1;
				cMax = 1;
			}
		}
		if(cuotas<cMin || cuotas>cMax){
			throw new SQLException("Cantidad de cuotas " + cuotas + " es incorrecto.");
		}
	}

}
