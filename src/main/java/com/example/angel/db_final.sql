DROP DATABASE IF EXISTS tfg;
create database tfg;
use tfg;
--

--
create table equipo (
                        id_equipo int auto_increment not null primary key,
                        nombre varchar(50),
                        sede varchar(50),
                        longitud double,
                        latitud double
);

insert into equipo values (1,'Taekwondo Getafe','Pabellón Felipe Reyes',40.3173638,-3.7177195),(2,'Gimnasio Caysan','C. Sierra',40.3028092,-3.7326996);
--

--
create table competidor (
                            nombre varchar(20),
                            email varchar(60) primary key,
                            contraseña varchar(200),
                            rol varchar(30),
                            activo tinyint,
                            id_equipo int,
                            foreign key(id_equipo) references equipo(id_equipo)
);
insert into competidor values ('Angel','angel@angel.es','$2a$10$orT4HERjHQr78sC6h3uJ2.iiDPhJJYQ4OiSl3rgb72VJzD3wPaXle','ADMINISTRADOR',1,1),('Victor','victor@victor.es','$2a$10$UpTek4pXgppnEGhFfSFgdunJshlBY/ucjLnXPkU.SaCIUnBJk51ZC','USUARIO',1,1),('Diego','diego@diego.es','$2a$10$UpTek4pXgppnEGhFfSFgdunJshlBY/ucjLnXPkU.SaCIUnBJk51ZC','USUARIO',1,2),('Angela','angela@angela.es','$2a$10$UpTek4pXgppnEGhFfSFgdunJshlBY/ucjLnXPkU.SaCIUnBJk51ZC','USUARIO',1,2);
-- Contra de angel es angel

--
create table eventos(
                        id_evento int auto_increment not null primary key,
                        nombre varchar(50),
                        descripcion varchar(200),
                        imagen varchar(500),
                        fecha_inicio varchar(40),
                        fecha_fin varchar(40),
                        longitud double,
                        latitud double,
                        trofeo varchar(50)
);
insert into eventos values (1,'Cto. Morazarzal','Esto va a ser un campeonato al que podras ir tanto para hacer de arbitro como para competir','https://goo.gl/maps/zeR9rEKPJbLTKpPY6','28/03/2023 9:00','28/03/2023 22:00',40.3180,-3.7180,'Trofeo al mejor club de Morazarzal');
insert into eventos values (2,'Cto. CerroBuenavista','Esto va a ser un campeonato al que podras ir tanto para hacer de arbitro como para competir','https://goo.gl/maps/KronwrHwFfkFR14p9','23/04/2023 8:00','23/04/2023 19:00',40.2936691,-3.7477921,'Trofeo al mejor club de Sector 3');
--

--
create table asistencia (
                            id_asistencia int auto_increment not null primary key,
                            id_equipo int,
                            foreign key(id_equipo) references equipo(id_equipo),
                            id_evento int,
                            foreign key(id_evento) references eventos(id_evento)
);
insert into asistencia values (1,1,1),(2,2,1);
