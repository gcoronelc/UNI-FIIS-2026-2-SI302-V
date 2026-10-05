-- Caso 1

select * from PAGO;
go

delete from PAGO;
go

select @@TRANCOUNT;
go

-- Caso 2

BEGIN TRAN;
GO

select @@TRANCOUNT;
go

select * from MATRICULA;
go

delete from MATRICULA;
go

select * from ALUMNO;
go

delete from ALUMNO;
go

ROLLBACK;
GO


-- Caso 2


SET IMPLICIT_TRANSACTIONS ON;
GO

select @@TRANCOUNT;
go

select * from MATRICULA; -- Inicia la TX.
go

delete from MATRICULA; 
go

select * from ALUMNO;
go

delete from ALUMNO;
go

ROLLBACK;
GO



