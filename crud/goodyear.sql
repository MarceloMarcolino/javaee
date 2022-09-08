create database goodyear;
show databases;
use goodyear;
create table funcionarios(
	id int primary key auto_increment not null,
    nome varchar(60) not null,
    cidade varchar(50) not null,
    estado varchar(50) not null,
    pais varchar(50) not null
);
show tables;
describe funcionarios;

/* CRUD CREATE */
insert into funcionarios (nome,pais,estado,cidade) values ('Bill Gates', 'United States', 'Washington', 'Seattle');

/* CRUD READ */
select * from funcionarios;
select * from funcionarios order by nome;
select * from funcionarios where id = 4;

/* CRUD UPDATE */
update funcionarios set nome='Bruce Wayne' where id = 4;
update funcionarios set nome='Bruce Wayne Junior',cidade='Los Angeles',estado='California' where id = 4;

/* CRUD DELETE */
delete from funcionarios where id = 4;