select 
	SUSER_NAME() "Login ID", 
	USER_NAME() "User DB"
GO

use EDUCA2
go

select * from CURSO;
go

select sum(pag_importe) "Plata como cancha"
from PAGO
where cur_id = 1;
go

select *
from MATRICULA
where cur_id=1 and alu_id=4
go

select *
from PAGO
where cur_id=1 and alu_id=4
go 













