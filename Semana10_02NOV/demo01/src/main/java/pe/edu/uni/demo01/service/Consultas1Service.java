package pe.edu.uni.demo01.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class Consultas1Service {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	public Double getPrecioCurso(int idCurso) {
		Double precio;
		try {
			String sql = "select cur_precio from CURSO where cur_id=?";
			precio = jdbcTemplate.queryForObject(sql, Double.class, idCurso);
		} catch (EmptyResultDataAccessException e) {
			precio = null;
		}
		return precio;
	}
	
}
