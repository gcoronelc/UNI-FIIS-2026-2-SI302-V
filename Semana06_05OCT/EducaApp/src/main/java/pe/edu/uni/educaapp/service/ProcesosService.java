package pe.edu.uni.educaapp.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
	
	public MatriculaDto matricular(MatriculaDto bean){
		Connection cn = null;
		double precio = 5000.00;
		String sql;
		PreparedStatement pstm;
		int filas;
		
		try {
			// Inicio de TX
			cn = AccesoDB.getConnection();
			cn.setAutoCommit(false);
			// SECCION DE VALIDACION
			
			
			// SECCION DEL PROCESO
			sql = """
				UPDATE CURSO
				SET cur_matriculados = cur_matriculados + 1
				WHERE cur_id = ?
         """;
			pstm = cn.prepareStatement(sql);
			pstm.setInt(1, bean.getIdCurso());
			filas = pstm.executeUpdate();
			if( filas != 1 ){
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
		} finally{
			try {
				cn.close();
			} catch (Exception e) {
			}
		}
		return bean;
	}

}
