use EDUCA2;
go

select 
	cur_id idCurso,
	cur_nombre nombre,
	cur_vacantes vacantes,
	cur_matriculados matriculados,
	cur_precio precio,
	isnull(cur_profesor,'FALTA') profesor
from CURSO ;
go

select cur_precio precio from curso
where cur_id = 5;
go


select * from pago;
go

select 
	alu_id idAlumno,
	pag_cuota cuota,
	convert(varchar(20),pag_fecha,103) fecha,
	pag_importe importe
from PAGO
where cur_id = 2;
go



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
         cur_matriculados, cur_precio;
go

