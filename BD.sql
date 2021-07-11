create table if not exists Mensaje
(
	idMensaje int auto_increment,
	cuerpo varchar(512) not null,
	horaEnvio varchar(512) not null,
	idNinera int not null,
	idPadre int not null,
	constraint idMensaje_UNIQUE
		unique (idMensaje)
);

alter table Mensaje
	add primary key (idMensaje);

create table if not exists MetodoPago
(
	idMetodoPago int auto_increment,
	nombre varchar(512) not null,
	data text null,
	idPadre int not null,
	constraint idMetodoPago_UNIQUE
		unique (idMetodoPago)
);

alter table MetodoPago
	add primary key (idMetodoPago);

create table if not exists Ninera
(
	idNinera int auto_increment,
	area_servicio text not null,
	facebook varchar(512) not null,
	sobre_mi varchar(512) null,
	habilidades_relevantes text null,
	idiomas text not null,
	vista_ultima_vez datetime null,
	idPersona int not null,
	idUser int not null,
	constraint idNinera_UNIQUE
		unique (idNinera)
);

alter table Ninera
	add primary key (idNinera);

create table if not exists Notificacion
(
	idNotificacion int auto_increment,
	descripcion varchar(512) not null,
	tipo int null,
	fecha datetime null,
	idPersona int not null,
	constraint idNotificacion_UNIQUE
		unique (idNotificacion)
);

alter table Notificacion
	add primary key (idNotificacion);

create table if not exists Padre
(
	idPadre int auto_increment,
	parentesco varchar(512) not null,
	idPersona int not null,
	idUser int not null,
	constraint idPadre_UNIQUE
		unique (idPadre)
);

alter table Padre
	add primary key (idPadre);

create table if not exists Persona
(
	idPersona int auto_increment,
	nombre varchar(512) not null,
	a_paterno varchar(512) not null,
	a_materno varchar(512) not null,
	curp varchar(512) not null,
	cumpleanos datetime null,
	telefono varchar(512) null,
	foto varchar(512) not null,
	ine_frontal varchar(512) null,
	ine_trasera varchar(512) null,
	ciudad varchar(512) not null,
	idUser int not null,
	constraint idPersona_UNIQUE
		unique (idPersona)
);

alter table Persona
	add primary key (idPersona);

create table if not exists Recomendacion
(
	idRecomendacion int auto_increment,
	comentario varchar(512) not null,
	calificacion int not null,
	idNinera int not null,
	idPadre int not null,
	constraint idRecomendacion_UNIQUE
		unique (idRecomendacion)
);

alter table Recomendacion
	add primary key (idRecomendacion);

create table if not exists Reservacion
(
	idReservacion int auto_increment,
	n_hijos int not null,
	ubicacion varchar(512) not null,
	llegada_fecha datetime not null,
	salida_fecha datetime not null,
	llegada_hora varchar(512) not null,
	salida_hora datetime not null,
	precio int not null,
	descuento int not null,
	comisionXservicio int not null,
	total int not null,
	idPadre int not null,
	idNinera int not null,
	constraint idReservacion_UNIQUE
		unique (idReservacion)
);

alter table Reservacion
	add primary key (idReservacion);

create table if not exists Usuario
(
	idUser int auto_increment,
	userName varchar(512) not null,
	email varchar(512) not null,
	password varchar(512) not null,
	constraint idUser_UNIQUE
		unique (idUser)
);

alter table Usuario
	add primary key (idUser);

