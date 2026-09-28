package pe.edu.uni.educaapp.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import pe.edu.uni.educaapp.db.AccesoDB;
import pe.edu.uni.educaapp.dto.PagoDto;

public class ConsultaService {
    
    public double conPrecio(int idCurso){
        Connection cn = null;
        PreparedStatement pstm;
        ResultSet rs;
        double precio;
        String sql = """
                     select cur_precio precio 
                     from curso 
                     where cur_id = ?
            """;
        try {
            // Habilita una conexion con la BD.
            cn = AccesoDB.getConnection();
            // Objeto PreperedStatement
            pstm = cn.prepareStatement(sql);
            pstm.setInt(1, idCurso);
            // Ejecutar la sentencia
            rs = pstm.executeQuery();
            if(!rs.next()){
                throw new SQLException("Codigo de curso no existe.");
            }
            // Acceder al dato
            precio = rs.getDouble("precio");
            // Cierre de objetoc
            rs.close();
            pstm.close();
            // Retorna el precio
            return precio;
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Error en el proceso.");
        } finally{
            // Cierra la conexion con la BD.
            try {
                cn.close();
            } catch (Exception e) {
            }
        }
    }
    
    
    public List<PagoDto> conPagos(int idCurso){
        List<PagoDto> lista = new ArrayList<>();
        Connection cn = null;
        PreparedStatement pstm;
        ResultSet rs;
        double precio;
        String sql = """
                select 
                    alu_id idAlumno,
                    pag_cuota cuota,
                    convert(varchar(20),pag_fecha,103) fecha,
                    pag_importe importe
                from PAGO
                where cur_id = ?
            """;
        try {
            // Habilita una conexion con la BD.
            cn = AccesoDB.getConnection();
            // Objeto PreperedStatement
            pstm = cn.prepareStatement(sql);
            pstm.setInt(1, idCurso);
            // Ejecutar la sentencia
            rs = pstm.executeQuery();
            while(rs.next()){
                // Objeto
                PagoDto bean;
                // Datos
                int idAlumno = rs.getInt("idAlumno");
                int cuota = rs.getInt("cuota");
                String fecha = rs.getString("fecha");
                double importe = rs.getDouble("importe");
                // Objeto
                bean = new PagoDto(idAlumno, cuota, importe, fecha);
                lista.add(bean);
            }
            // Cierre de objetoc
            rs.close();
            pstm.close();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Error en el proceso.");
        } finally{
            // Cierra la conexion con la BD.
            try {
                cn.close();
            } catch (Exception e) {
            }
        }
        return lista;
    }
    
    public List<Map<String,?>> conCursos(){
        List<Map<String,?>> lista = new ArrayList();
        Connection cn = null;
        PreparedStatement pstm;
        ResultSet rs;
        String sql = """
                select 
                    cur_id idCurso,
                    cur_nombre nombre,
                    cur_vacantes vacantes,
                    cur_matriculados matriculados,
                    cur_precio precio,
                    isnull(cur_profesor,'falta') profesor
                from curso
            """;
        try {
            // Habilita una conexion con la BD.
            cn = AccesoDB.getConnection();
            // Objeto PreperedStatement
            pstm = cn.prepareStatement(sql);
            // Ejecutar la sentencia
            rs = pstm.executeQuery();
            lista = JdbcUtil.rsToList(rs);
            // Cierre de objetoc
            rs.close();
            pstm.close();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Error en el proceso.");
        } finally{
            // Cierra la conexion con la BD.
            try {
                cn.close();
            } catch (Exception e) {
            }
        }
        return lista;
    }
    
    public List<Map<String,?>> conCursos2(){
        List<Map<String,?>> lista = new ArrayList();
        Connection cn = null;
        PreparedStatement pstm;
        ResultSet rs;
        String sql = """
                select 
                	C.cur_id idCurso,
                	cur_nombre nombre,
                	cur_vacantes vacantes,
                	cur_matriculados matriculados,
                	cur_precio precio,
                	sum(M.mat_precio) proyectado
                from CURSO C
                join MATRICULA M on c.cur_id = M.cur_id
                group by C.cur_id, cur_nombre, cur_vacantes, 
                         cur_matriculados, cur_precio
            """;
        try {
            // Habilita una conexion con la BD.
            cn = AccesoDB.getConnection();
            // Objeto PreperedStatement
            pstm = cn.prepareStatement(sql);
            // Ejecutar la sentencia
            rs = pstm.executeQuery();
            lista = JdbcUtil.rsToList(rs);
            // Cierre de objetoc
            rs.close();
            pstm.close();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Error en el proceso.");
        } finally{
            // Cierra la conexion con la BD.
            try {
                cn.close();
            } catch (Exception e) {
            }
        }
        return lista;
    }    
}
