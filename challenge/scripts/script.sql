CREATE DATABASE factorit

CREATE TABLE "tipo_cliente" (
	"id" BIGINT NOT NULL,
	"description" VARCHAR(50) NULL
);

CREATE TABLE "tipo_carrito" (
	"id" BIGINT NOT NULL,
	"description" VARCHAR(50) NULL
);

CREATE TABLE "cliente" (
	"id" BIGINT NOT NULL,
	"nombre" VARCHAR(50) NULL,
	"apellido" VARCHAR(50) NULL,
	"dni" VARCHAR(8) NULL,
	"tipo_cliente" BIGINT NULL
);


CREATE TABLE "compras" (
	"id" VARCHAR(50) NOT NULL,
	"creation_date" TIMESTAMP NULL,
	"tipo_carrito" BIGINT NULL,
	"estado" VARCHAR(50) NULL,
	"client_id" BIGINT NULL,
	"productos" VARCHAR(5000) NULL,
	"total" VARCHAR(20) NULL
);

INSERT INTO tipo_carrito
values(1,'COMUN'),(2,'ESPECIAL');


INSERT INTO tipo_cliente
values(1,'NORMAL'),(2,'VIP');

INSERT INTO cliente 
values(1,'cliente','comun','12345678',1),(2,'cliente','especial','40228556',2);

INSERT INTO compras
values('ASD123','30-01-2022',2,'ABIERTO',1,null,0);









