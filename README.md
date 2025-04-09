# Proyecto Ecommerce

Este es un proyecto de ecommerce desarrollado con [Spring Boot](https://spring.io/projects/spring-boot) (versión 3.4.4) y Docker. La aplicación permite la gestión de productos y carritos en una plataforma de ecommerce, y está configurada para funcionar con contenedores Docker. Además, se ha integrado con [Swagger](https://swagger.io/) para la documentación interactiva de la API.

## Características

- **Dockerizado**: El proyecto está completamente dockerizado, facilitando su despliegue y ejecución en cualquier entorno compatible con Docker.
- **API RESTful**: La aplicación expone una API RESTful para la gestión de recursos como carritos y productos.
- **Swagger**: La documentación de la API está disponible a través de Swagger, lo que permite interactuar con los endpoints directamente desde el navegador.

## Requisitos

Antes de empezar, asegúrate de tener instalados los siguientes programas:

- [Docker](https://www.docker.com/) para ejecutar la aplicación en contenedores.
- [Java 21+](https://adoptopenjdk.net/) (si decides ejecutar la aplicación localmente sin Docker).
- [Maven](https://maven.apache.org/) para construir el proyecto.
- **Spring Boot 3.4.4** (la versión utilizada en este proyecto).

## Instalación y ejecución con Docker

1. **Clonar el repositorio**:
   git clone https://github.com/saalN/ecommerce.git
   cd ecommerce

2. **Levantar el proyecto en docker**:
   docker build -t ecommerce-app .
   
   Una vez que la imagen esté construida, ejecuta el contenedor con el siguiente comando:

   docker run -p 8080:8080 ecommerce-app
   
   Esto iniciará la aplicación en el puerto 8080, que es el puerto predeterminado para el ecommerce.


## Acceder a la API Swagger
  Una vez que la aplicación esté en funcionamiento, puedes acceder a la documentación interactiva de la API a través de Swagger en la siguiente URL:
  http://localhost:8080/swagger-ui.html


## Endpoints
  POST /api/cart: Crea un nuevo carrito.
  
  GET /api/cart/{cartId}: Obtiene la información de un carrito dado su ID.
  
  POST /api/cart/{cartId}/products: Agrega uno o más productos a un carrito.
  
  DELETE /api/cart/{cartId}: Elimina un carrito.

## Almacenamiento
  El almacenamiento de los carritos se maneja de manera volátil en memoria, lo que significa que los datos se perderán cuando la aplicación se reinicie. No se utiliza ninguna base de datos para este proyecto.

## Consideraciones
  Eliminación automática de carritos: Los carritos inactivos por más de 10 minutos serán eliminados automáticamente.
  
  Cobertura de pruebas mínima: Se asegura que el código tiene una cobertura de pruebas mínima, de acuerdo con los requisitos del proyecto.
  
  Facilidad de pruebas y despliegue: El proyecto está diseñado para ser fácil de probar, verificar y desplegar.

