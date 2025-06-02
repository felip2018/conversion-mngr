# Gestor de conversión
A continuación se realiza la documentación de uso sobre la api 
de conversión de divisas.<br>

## A tener en cuenta
El proyecto fue desarrollado usando:<br>
* Java 21
* Apache Maven 3.8.6
* Springboot 3.5.0

Conceptos implementados:<br>
* Logging de trazabilidad usando **Programación Orientada a Aspectos (AOP)**
* Manejo global de excepciones
* Validación de autorización por api-key
* Intercepción de peticiones http
* Validación de inputs de usuario
* Test unitarios


## 1. Clonar repositorio
Ejecute el comando:<br><br>
``git clone https://github.com/felip2018/conversion-mngr.git``

## 2. Instalación de dependencias
Ejecutar el comando:<br><br>
``mvn clean install``

## 3. Ejecutar el proyecto
El comando anterior realizará la creación del ejecutable **conversion-mngr-1.0.0.jar**
puede realizar la ejecución con el siguiente comando dentro de la carpeta **/target**:<br><br>
``java -jar conversion-mngr-1.0.0.jar``

## 4. Consumir el servicio de conversión
Para efectuar el consumo del servicio puede hacer uso del siguiente curl de solicitud:<br><br>
``
curl --location 'http://localhost:8080/v1/convert-currency' \
--header 'Content-Type: application/json;charset=UTF-8' \
--header 'x-rquuid: e5a3db18-a804-40fd-a4c2-e274d9891a73' \
--header 'x-api-key: 10124780aa73c83cd1e5b667cf8af774' \
--data '{
    "fromCurrency": "EUR",
    "toCurrency": "USD",
    "amount": 1.0
}'
``