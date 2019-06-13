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
  Las funcionalidades de un usuario estandar en beer4life , son , aparte del acceso a Home , Contact , Login y Register , el acceso a las siguientes rutas y funcionalidades.
    </p>
      <p align="center">
  El usuario podra acceder a la tienda , en la cual tendra un listado de cervezas similar al que visulizamos en el Home, pero con una amplia gama de productos , con un paginador mediante el cual ir pudiendo navegar por todos los contenidos.
  </p>
    <h3 align="center">Shop</h3>
  <img src="https://github.com/pablopinto/Final-DAW-Project/blob/master/Imagenes/register.PNG">
  <br>
    <p align="center">
  Aparte de esto el usuario tendra acceso , tanto desde Home como Shop y Contact, acceso a su perfil , pero en ningun caso al de los demas usuarios.En este perfil el usuario podra visualizar su informacion asi como su foto , en caso de tenerla , visualizar las facturas para el usuario , crear facturas , ver el detalle de las facturas y eliminar las mismas.
 </p>
 <h3 align="center">Perfil</h3>
  <img src="https://github.com/pablopinto/Final-DAW-Project/blob/master/Imagenes/register.PNG">
  <br>
    <p align="center">
  En caso de que asi lo desee, el usuario podra crear sus facturas con la correspondientes informacion pertinente para la misma. Cliente , informacion la cual no se puede modificar , la Descripcion de la factura , Observaciones sobre la misma y se incluye un buscador , el cual va buscando segun las coincidencias encontradas con el texto introducido en el input. Una vez se confirme la introduccion de la misma , podremos visualizar el nombre del producto seleccionado , su precio y la cantidad que se quiere comprar , asi como el total del mismo y una opcion para eliminar el producto de la lista de asi ser necesario.
 Por ultimo al final el Total de la factura.
 </p>
 <h3 align="center">Creacion Factura</h3>
  <img src="https://github.com/pablopinto/Final-DAW-Project/blob/master/Imagenes/register.PNG">
  <br>
  <p align="center">
Una vez creada la factura , podremos visualizar la informacion de la misma desde el boton detalle , mostrando los datos del cliente : nombre , apellidos y correo electronico. Datos de la factura , folio , descripcion de la misma y fecha de expedicion de la misma. Tambien podremos visualizar los datos de los productos previamente seleccionados asi como las observaciones sobre la misma y el total de la factura.
 </p>
 <h3 align="center">Detalle Factura</h3>
  <img src="https://github.com/pablopinto/Final-DAW-Project/blob/master/Imagenes/register.PNG">
  <br>
