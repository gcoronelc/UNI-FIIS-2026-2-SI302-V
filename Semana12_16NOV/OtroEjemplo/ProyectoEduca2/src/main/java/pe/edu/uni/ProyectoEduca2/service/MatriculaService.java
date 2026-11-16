package pe.edu.uni.ProyectoEduca2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.uni.ProyectoEduca2.dto.MatriculaDto;

@Service
public class MatriculaService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional(propagation= Propagation.REQUIRES_NEW, rollbackFor=Exception.class)
    public MatriculaDto matricular(MatriculaDto bean){
        // Constantes
        String tipos = "REGULAR,MEDIABECA,BECA";
        // Variables
        String sql;
        // Validaciones
        if(!tipos.contains(bean.getTipo())){
            throw new RuntimeException("Tipo de matricula incorrecto.");
        }
        sql = "select count(1) from matricula where cur_id=? and alu_id=?";
        int n = jdbcTemplate.queryForObject(sql, Integer.class, bean.getIdCurso(), bean.getIdAlumno());
        if(n == 1){
            throw new RuntimeException("Matricula ya existe.");
        }
        // Proceso
        sql = "select cur_precio precio from curso where cur_id=?";
        double precio = jdbcTemplate.queryForObject(sql, Double.class, bean.getIdCurso());
        sql = "update curso set cur_matriculados = cur_matriculados + 1 where cur_id=?";
        jdbcTemplate.update(sql,bean.getIdCurso());
        sql = """
                insert into MATRICULA(cur_id, alu_id, emp_id, mat_tipo, mat_fecha, mat_precio, mat_cuotas)
                values(?,?,?,?,GETDATE(),?,?)
                """;
        Object[] parms = {bean.getIdCurso(), bean.getIdAlumno(), bean.getIdEmpleado(),
                bean.getTipo(), precio, bean.getCuotas()};
        jdbcTemplate.update(sql, parms);
        // Retorno
        bean.setEstado(1);
        bean.setMensaje("Proceso ok.");
        return bean;
    }

}
