# Evaluación continua (Programación y Entornos de Desarrollo)

**Gestor de tienda de videojuegos en Java**

Este proyecto es una aplicación desarrollada como parte de la evaluación continua de los módulos de **Programación** y **Entornos de Desarrollo** en el ciclo de **Desarrollo de Aplicaciones Multiplataforma (DAM)**.

La aplicación implementa un pequeño gestor para una tienda de videojuegos, permitiendo operaciones básicas sobre la base de datos de los clientes, el catálogo de juegos y el historial de las ventas.  

---

## Tabla de contenidos

- [Acerca del proyecto](#acerca-del-proyecto)
- [Tecnologías utilizadas](#tecnologías-utilizadas)
- [Tests](#tests)
- [Documentación](#documentación)
- [Estructura del proyecto](#estructura-del-proyecto)
- [Historial de versiones](#historial-de-versiones)
- [Licencia](#licencia)
- [Autor](#autor)
- [Referencias](#referencias)

---

## Acerca del proyecto

El proyecto consiste en el desarrollo de un sistema de gestión para una tienda de videojuegos que permite administrar tres áreas clave del negocio: los juegos del catálogo, los clientes de la base de datos y el historial de ventas realizadas.

El usuario puede interactuar con el programa a través de un menú en consola, donde puede realizar diversas operaciones como añadir nuevos videojuegos al catálogo, consultar y modificar los existentes, así como eliminarlos. Además de gestionar los productos, también es posible registrar nuevos clientes, editar su información personal o eliminarlos si es necesario.

El módulo de ventas permite simular una transacción entre un cliente y la tienda, asociando videojuegos vendidos a clientes concretos, lo cual da lugar a un pequeño sistema de trazabilidad de operaciones. Estas ventas se almacenan con información relevante, como el cliente implicado, los productos adquiridos y la fecha de la venta, y pueden ser consultadas desde el propio menú del sistema.

Internamente, la aplicación organiza sus clases en distintas entidades que representan juegos (identificados por su nombre), ediciones de juegos (identificados por el sistema de la edición), clientes (identificados por su DNI) y ventas (identificadas por su número de factura), junto con sus respectivos gestores.

El código está programado en Java, estructurado con Maven y documentado utilizando JavaDoc.

---

## Tecnologías utilizadas

- Java 23
- Maven
- JUnit 5
- JavaDoc
- Git

---

## Tests

Este proyecto incluye pruebas unitarias con **JUnit 5**.

---

## Documentación

Este proyecto incluye documentación generada con **JavaDoc**.

---

## Estructura del proyecto

```
E_continua_programacion_entornos_desarrollo/
├── src/
│   ├── main/java/               # Código fuente
│   └── test/java/               # Tests con JUnit
├── docs/                        # Documentación generada con JavaDoc
├── pom.xml                      # Configuración de Maven
└── README.md
```

---

## Historial de versiones

- **v1.0** – Versión inicial completa con documentación, tests y JavaDoc.

Consulta el [historial de commits](https://github.com/KennethAlAr/E_continua_programacion_entornos_desarrollo/commits/main) para más detalles.

---

## Licencia

Este proyecto se distribuye únicamente con fines educativos. No posee licencia de distribución.

---

## Autor

Kenneth Alonso Arce  
Estudiante de 1º - Desarrollo de Aplicaciones Multiplataformas en The Power  
Barcelona – 2025

Repositorio del proyecto: [github.com/KennethAlAr](https://github.com/KennethAlAr)

---

## Referencias

[Guía de Java](https://www.w3schools.com/java/default.asp)  
[Guía de Maven](https://maven.apache.org/guides/index.html)  
[Guía de JUnit 5](https://docs.junit.org/current/user-guide/#overview)  
[Guía de JavaDoc](https://www.oracle.com/technical-resources/articles/java/javadoc-tool.html)  
[Template para el README](https://gist.github.com/DomPizzie/7a5ff55ffa9081f2de27c315f5018afc)  