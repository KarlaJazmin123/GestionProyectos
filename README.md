# Sistema de Gestión de Proyectos y Tareas — API REST

Examen Segundo Parcial · Programación Web Avanzada.
**Stack:** Java 21 · Spring Boot 4.1 · Spring Data JPA (Hibernate) · PostgreSQL · Spring Security + JWT (jjwt)

## Cómo correrlo

1. Crea la base de datos: `CREATE DATABASE gestion_proyectos;`
2. Ajusta usuario/contraseña en `src/main/resources/application.properties` (o usa las variables `DB_URL`, `DB_USERNAME`, `DB_PASSWORD`, `JWT_SECRET`).
3. `./mvnw spring-boot:run` (necesita JDK 21). Las tablas se crean solas (`ddl-auto=update`).

## Endpoints

| Método | Ruta | Token | Respuestas |
|---|---|---|---|
| POST | `/auth/registro` | No | 201, 400 (datos inválidos / username repetido) |
| POST | `/auth/login` | No | 200 con `token`, 401 credenciales inválidas |
| GET | `/api/proyectos` | Sí | 200 (solo los del usuario del JWT), 401 |
| POST | `/api/proyectos` | Sí | 201, 400 (datos inválidos o **límite de 3 proyectos**), 401 |
| POST | `/api/tareas` | Sí | 201, 400, 404 (proyecto inexistente o ajeno), 401 |
| PUT | `/api/tareas/{id}` | Sí | 200, 400 (estado inválido), 404, 401 |

Estados de tarea: `PENDIENTE`, `EN_PROGRESO`, `COMPLETADA`.

## Pruebas de API

La carpeta `bruno/` es una colección de Bruno. Ábrela, elige el entorno `local` y ejecuta la colección completa (Run). Las peticiones guardan solas el token y los ids.
