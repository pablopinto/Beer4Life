<h1 align="center">Beer4Life</h1>
<br>
<p align="center">
  <img src="https://github.com/pablopinto/Final-DAW-Project/blob/master/Beer4Life/src/main/resources/static/images/beer4life.png">
</p>

<h2 align="center">TFG del Grado Superior en Desarrollo de Aplicaciones Web</h2>
<h3 align="center">Un proyecto desarrollado con Spring Boot Thymeleaf</h3>
<h3 align="center">Getting Started</h3>
<p align="center"> Seguir los pasos descritos acontinuacion te guiaran hasta el correcto deploy de este proyecto en tu maquina local para el correcto desarrollo o testeo de la misma</p>

<h3 align="center">Pre-Requisitos</h3>
<p align="center">
Antes de poder montar este proyecto es necesario lo siguiente:
  <br>
  IDE , preferiblemente Eclipse 2018-12
  <br>
  Java JRE 1.8.0
  <br>
  Java JDK 1.8.0
</p>
<p align="center">
Una vez tengamos Eclipse , se llevara a cabo la instalacion de SpringBoot 4 , requisito indispensable para el correcto lanzamiento de la aplicacion.
</p>

<h3 align="center">Instalando</h3>
<p align="center">
Para la ejecucion del proyecto , es necesario hacer una correcta configuracion previa del archivo application.properties
Este debe estar formado como el mostrado a continuacion , y modificar exclusivamente los campos referentes al almacenamiento de base de datos.
    <br>
  <br>
<img src="https://github.com/pablopinto/Final-DAW-Project/blob/master/Imagenes/application_properties.PNG">
    <br>
  <br>
Cuando nos encontremos en desarrollo el campo **spring.jpa.hibernate.ddl-auto** debe estar en modalidad **create-drop** o en defecto **update**
 </p>
 <p align="center">
  En cuanto las bases datos , tanto de la beer4life como beer4life-api , han de ser creadas y referenciadas pero NO ha de crearse la estructura de tablas internas , ya que estas seran creadas automaticamente por Spring una vez se lleve a cabo la ejecucion de los programas
  </p>
  
<h3 align="center">Uso y Explicacion de la Aplicacion Beer4Life</h3>
 <p align="center">
  Una vez llevados a cabo los pasos anteriores se procedera a lanzar ambos programas de manera simultanea , tanto beer4life como beer4life-api , si lanzamos beer4life sin la api esta no podra llevar a cabo la recogida de ningun dato.
 </p>
 <h3 align="center">Uso de la Aplicacion : Funciones de un Usuario Anonimo</h3>
  <p align="center">
  En beer4life nos encontramos nada mas mas arrancar la aplicacion con un listado de cervezas.
    <br>
  <h3 align="center">Home</h3>
  <img src="https://github.com/pablopinto/Final-DAW-Project/blob/master/Imagenes/home_sinSesion.PNG">
    <br>
  <p align="center">
  Este listado , el Home , es visible por cualquier tipo de usuario , tanto registrado como no registrado. Aparte de esto , un usuario no registrado solo tendra acceso a la pagina de Contacto, donde se puede visualizar informacion respecto al proyecto Beer4Life.
   </p>
    <br>
  <h3 align="center">Contacto</h3>
  <img src="https://github.com/pablopinto/Final-DAW-Project/blob/master/Imagenes/Contacto_sinSesion.PNG">
    <br>
  <p align="center">
  En caso de intentar dirigirnos a cualquier pagina que no sean el Home o la pagina de Contacto , ya sea mediante el navegador o por url nos redirigira a la pagina de inicio de sesion , en la cual , en la esquina superior tendremos la opcion de Registrarnos de asi desearlo.
   </p>
  <br>
  <h3 align="center">Login</h3>
  <img src="https://github.com/pablopinto/Final-DAW-Project/blob/master/Imagenes/login.PNG">
    <br>
  <h3 align="center">Registro</h3>
  <img src="https://github.com/pablopinto/Final-DAW-Project/blob/master/Imagenes/register.PNG">
    <br>
    <h3 align="center">Uso de la Aplicacion : Funciones de un Usuario Estandar</h3>
      <p align="center">
  Las funcionalidades de un usuario estandar en beer4life , son , aparte del acceso a Home , Contact , Login y Register , el acceso a las siguientes rutas y funcionalidades
  </p>
    
