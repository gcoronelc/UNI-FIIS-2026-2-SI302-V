
SELECT * FROM CURSO;
GO


UPDATE CURSO
SET cur_matriculados = cur_matriculados + 1
WHERE cur_id = ?
GO

SELECT * FROM MATRICULA where cur_id=1
GO

INSERT INTO MATRICULA(cur_id,alu_id,emp_id,mat_tipo,mat_fecha,mat_precio,mat_cuotas)
VALUES(?,?,?,?,GETDATE(),?,?)
GO

select count(1) cont from MATRICULA where cur_id=? and alu_id=?
go
