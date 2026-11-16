package pe.edu.uni.demo01.service;

import java.sql.PreparedStatement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.uni.demo01.dto.CursoDto;

@Service
public class EducaService {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public CursoDto nuevoCurso(CursoDto bean) {

		// 1. Validar vacantes
		if (bean.getVacantes() <= 0) {
			throw new RuntimeException("Las vacantes deben ser mayor que cero.");
		}

		// 2. Validar matriculados
		if (bean.getMatriculados() != 0) {
			throw new RuntimeException("Los matriculados deben ser cero (0).");
		}

		// 3. Validar precio
		if (bean.getMatriculados() != 0) {
			throw new RuntimeException("Los matriculados deben ser cero (0).");
		}

		// 4. Insertar el curso
		String sql = """
					insert into curso(cur_nombre, cur_vacantes, cur_matriculados, cur_profesor, cur_precio)
					values(?, ?, ?, ?, ?)
				""";
		/*
		Object[] parametros = { bean.getNombre(), bean.getVacantes(), bean.getMatriculados(), bean.getProfesor(),
				bean.getPrecio() };
		jdbcTemplate.update(sql, parametros);
		 */
		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(connection -> {
			PreparedStatement ps = connection.prepareStatement(sql, new String[] { "cur_id" });
			ps.setString(1, bean.getNombre());
			ps.setInt(2, bean.getVacantes());
			ps.setInt(3, bean.getMatriculados());
			ps.setString(4, bean.getProfesor());
			ps.setDouble(5, bean.getPrecio()); // Ajusta a setBigDecimal si el precio es BigDecimal
			return ps;
		}, keyHolder);

		// 5. Asignar el nuevo ID al bean
		if (keyHolder.getKey() != null) {
			bean.setId(keyHolder.getKey().intValue()); // Ajusta a longValue() si el ID es Long
		}

		return bean;
	}

}
