package pe.edu.uni.ProyectoEduca2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ConsultasService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public double consPrecio(long idCurso){
        String sql = "select cur_precio precio from curso where cur_id=?";
        Double precio = jdbcTemplate.queryForObject(sql, Double.class, idCurso);
        return precio;
    }


}
