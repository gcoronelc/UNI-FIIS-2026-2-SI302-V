/*
Clientes que han gastado mas de 10000.
*/

select * from cliente;
select * from venta;
go

select 
	c.id_cliente, c.nombre, c.apellido, 
	sum(v.monto_final) [importe venta]
from cliente c
join venta v on c.id_cliente = v.id_cliente
group by c.id_cliente, c.nombre, c.apellido
having sum(v.monto_final) > 10000;
go

WITH
v1 as (
	select 
		id_cliente, sum(monto_final) total
	from venta v 
	group by id_cliente
	having sum(monto_final) > 10000
)
select c.id_cliente, c.nombre, c.apellido, v1.total
from cliente c
join v1 on c.id_cliente = v1.id_cliente
GO


/*
Cual es el libro mas vendido.
*/


WITH
v1 as (
	select id_libro, sum(cantidad) cantidad
	from VENTA
	group by id_libro
),
v2 as (
	select max(cantidad) cant_max 
	from v1
)
select 
	l.id_libro, l.titulo, v1.cantidad
from libro l
join v1 on l.id_libro = v1.id_libro
join v2 on v1.cantidad = v2.cant_max;
go

