# Microservicio de Agenda Telefónica
Microservicio REST para gestionar contactos de una agenda telefónica, construido con Spring Boot y PostgreSQL.
## Caracteristicas
- CRUD completo de contactos
- Relación muchos-a-muchos entre contactos y teléfonos
- Email único por contacto
- Borrado lógico (soft delete)
- Validación de entrada
- Manejo centralizado de errores
- Configuración mediante variables de entorno
- Empaquetado en Docker

## 📋 Requisitos Previos
- Java 25 (LTS)
- Docker y Docker Compose (para ejecutar con contenedores)
### Con Docker para ejecutar (Recomendado)

1. Clonar el repositorio y navegar al directorio:
```bash
cd contatcs_project
```
2. Levantar los servicios:
```bash
docker compose up
```
La aplicación estará disponible en `http://localhost:8088`
Para detener los servicios:
```bash
docker compose down
```
Para eliminar también los volúmenes de datos:
```bash
docker compose down -v
```
## Endpoints de la API

### Base URL: `http://localhost:8088/api/contacts`

### 1. Listar Todos los Contactos
```bash
curl -X GET http://localhost:8088/api/contacts
```
### 1.2 Listar Contactos Activos
```bash
curl -X GET http://localhost:8088/api/contacts/active
```
### 2. Obtener Contacto por ID
```bash
curl -X GET http://localhost:8088/api/contacts/1
```
### 3. Crear Contacto
```bash
curl -X POST http://localhost:8088/api/contacts \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Juan",
    "apellido": "Pérez",
    "email": "juan.perez@example.com",
    "phones": [
      {
        "phoneNumber": "5551234567",
        "typeId": 2
      },
      {
        "phoneNumber": "5559876543",
        "typeId": 1
      }
    ]
  }'
```
### 4. Actualizar Contacto
```bash
curl -X PUT http://localhost:8088/api/contacts/1 \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Juan Carlos",
    "apellido": "Pérez García",
    "email": "juan.perez@example.com",
    "phones": [
      {
        "phoneNumber": "5551234567",
        "typeId": 2
      }
    ]
  }'
```
### 5. Eliminar Contacto (Borrado Lógico)
```bash
curl -X DELETE http://localhost:8088/api/contacts/1
```
## 👤 Autor

Miguel Lopez

## 📄 Licencia

Este proyecto fue desarrollado como ejercicio técnico.