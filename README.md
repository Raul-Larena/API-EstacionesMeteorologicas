# API-EstacionesMeteorologicas


## Comenzando 🚀

_Estas instrucciones te permitirán obtener una copia del proyecto en funcionamiento en tu máquina local para propósitos de desarrollo y pruebas._


### Pre-requisitos 📋

_¿Que se necesita para ejecutar la API?_

```
Debe poseer un entorno de desarrollo correctamente configurado con spring y un gestor de bases de datos relacional.
```

### Instalación 🔧

_Clonar el repositorio _

```
$ git clone https://github.com/Raul-Larena/API-EstacionesMeteorologicas
```

_Crear un nuevo esquema con el nombre meteorology desde su gestor de bases de datos_

_La api viene con la configuración para utilizar como gestor a MySQL. En caso de utilizar otro gestor debe cambiar la implementación en el archivo aplicacion.properties._

```
spring.datasource.url=jdbc:mysql://localhost:3306/meteorology?serverTimezone=UTC
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```
_Una vez chequeado todo lo anterior, ya puede correr la api en su entrono de desarrollo_


## Ejecutando las pruebas ⚙️

_Ejecutando las pruebas en cada endpoint_

_Lo primero que debe hacer es registrarse en la api, ya sea usuario o estación _

_En la petición POST deben enviar su nombre de usuario y contraseña . El método createUser se encarga de codificar la contraseña y luego guardar los datos en la base de datos._
_Este paso es sumamente importe, de lo contrario se le será denegado el acceso a cualquier endpoint por no estar autorizado_

```
Tipo de petición: POST

localhost:8080/user
```



_Para crear una estación nueva debe realizar una petición POST y  enviar por parámetros el nombre , coordenadas y  un valor booleano si tiene o no panel solar _
_El método create se encarga de utilizar el servicio para guardar la nueva estación en la base de datos._
_Debe recordar enviar sus credenciales correctamente, de lo contrario su acceso será denegado._
```
Tipo de peticion: POST
localhost:8080/station
```

_El método toList se encarga de retornar una lista con todas las estaciones activas que se encuentran en la base de datos_
_Para acceder debe realizar una petición GET._
```
Tipo de petición: GET
localhost:8080/station
```
_El método returnById  retorna una estación si es que existe. Es una petición GET._
_El ID a buscar se envía como variable en el path de la petición_
```
Tipo de petición: GET

Ejemplo:  localhost:8080/station/4
```

_El método returnByName retorna una estación  si es que existe._
_Petición GET. El nombre a buscar debe ser enviado como parámetro_
 ```
Tipo de petición: GET

endpoint:  localhost:8080/station/name
```

_El método returnByCoordinates retorna una estación  si es que existe._
_Petición GET. La coordenada a buscar debe ser enviada como parámetro_
 ```
Tipo de petición: GET

endpoint:  localhost:8080/station/coordinates
```

_El método delete elimina una estación._
_Es una petición DELETE. El id de la estación a eliminar es pasado como variable en el path de la petición_

```
Tipo de petición: DELETE

Ejemplo:  localhost:8080/station/4
```

_La entidad report tiene la siguiente estructura:_
```
int id;
Station station;
byte humidity;
byte temperature;
float speed;
float rainfall;
byte battery;
```
_Vale la pena aclararlo , pues  en la siguiente prueba es necesario enviar un objeto report a través de body de la petición POST y el id de dicha estación como parámetro._

_Ejemplo:_
```
Tipo de petición: POST

Ejemplo:  localhost:8080/report

Body:
{
    "humidity;": 60,
    "temperature": 50,
    "speed": 45,
    "rainfall": 0.777,
    "battery" : 99
}

Como parámetro enviar el idStation. 
Ejemplo:
idStation=4  

localhost:8080/report?idEstacion=4
```


_El método toList retorna todos los reportes realizados_
```
Tipo de petición: GET

Ejemplo:  localhost:8080/report
```

_El método delete elimina un reporte._
_Es una petición DELETE y el id a eliminar debe ser enviado por parámetro_

```
Tipo de petición: DELETE

Ejemplo:  localhost:8080/report?id=4
```


_Realizar un Alta Baja y Modificaciones de estaciones_
_El método high recibe por parámetro el id de la estación a dar de alta._

```
Tipo de petición: PUT

Ejemplo:  localhost:8080/abm/alta?id=4
```

_El método highName es similar al método high. La diferencia es que la búsqueda se realiza a través del nombre de la estación_

```
Tipo de petición: PUT

Ejemplo:  localhost:8080/abm/alta/name?name=Estacion1
```
_El método  highCoordinates da de alta una estación a través de las coordenadas . Al igual que los dos métodos anteriores, las coordenadas son enviadas por parámetro_

```
Tipo de petición: PUT

Ejemplo:  localhost:8080/abm/alta/coordinates/-354566
```

_El método low recibe por parámetro el id de la estación a dar de baja_
```
Tipo de petición: PUT

Ejemplo:  localhost:8080/abm/baja?id=4
```

_El método lowName es similar al método low. La diferencia es que la búsqueda se realiza a traves del nombre de la estación_

```
Tipo de petición: PUT

Ejemplo:  localhost:8080/abm/baja/name?name=Estacion1
```

_El método  lowCoordinates da de baja una estación a través de las coordenadas . Al igual que los dos métodos anteriores, las coordenadas son enviadas por parámetro_

```
Tipo de petición: PUT

Ejemplo:  localhost:8080/abm/baja/coordinates/-354566
```

_El método modify recibe a través del body de la petición el objeto Station con las modificaciones a aplicar._
_Se debe respetar el id_

```
Tipo de petición: PUT

Ejemplo:  localhost:8080/abm/modificar
```

_El método modifyByName recibe por parámetro el nombre de la estación a la cual se deben realizar las modificaciones y a través del body el objeto Station con las modificaciones a aplicar_

```
Tipo de petición: PUT

Ejemplo:  localhost:8080/abm/modificar/name
```

_El método modifyByCoordinates recibe por parámetro las coordenadas de la estación a la cual se deben realizar las modificaciones y a través del body el objeto Station con las modificaciones a aplicar_

```
Tipo de petición: PUT

Ejemplo:  localhost:8080/abm/modificar/coordinates
```

### Sugerencias de mejoras

_Una mejora es la implementación de roles en la api_



