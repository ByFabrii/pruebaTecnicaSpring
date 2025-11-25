# Prueba Técnica Supermercado

API REST para gestión de supermercado desarrollada con Spring Boot 3.5.7 y Java 21.

## 🚀 Tecnologías

- **Java 21**
- **Spring Boot 3.5.7**
- **MySQL 8.0**
- **Docker & Docker Compose**
- **Maven**
- **Hibernate/JPA**
- **Lombok**

## 📋 Requisitos Previos

- Docker Desktop instalado
- Git

## ⚙️ Configuración Inicial

### 1. Clonar el repositorio

```bash
git clone <tu-repositorio>
cd Java
```

### 2. Configurar variables de entorno

Copia el archivo de ejemplo y configura tus credenciales:

```bash
cp .env.example .env
```

Edita el archivo `.env` con tus credenciales reales:

```env
DB_HOST=tu_host_mysql
DB_PORT=3306
DB_NAME=tu_base_de_datos
DB_USER=tu_usuario
DB_PASSWORD=tu_contraseña
DB_PLATFORM=org.hibernate.dialect.MySQL8Dialect
APP_PORT=8080
```

### 3. Levantar la aplicación

```bash
# Construir la imagen
docker-compose build

# Levantar el contenedor
docker-compose up -d

# Ver logs
docker-compose logs -f
```

La aplicación estará disponible en: `http://localhost:8080`

## 📡 Endpoints Disponibles

### Productos
- `GET /api/products` - Obtener todos los productos
- `POST /api/products` - Crear un producto
- `PUT /api/products/{id}` - Actualizar un producto
- `DELETE /api/products/{id}` - Eliminar un producto

### Sucursales (Branches)
- `GET /api/branches` - Obtener todas las sucursales
- `POST /api/branches` - Crear una sucursal
- `PUT /api/branches/{id}` - Actualizar una sucursal
- `DELETE /api/branches/{id}` - Eliminar una sucursal

### Ventas (Sales)
- `GET /api/sales` - Obtener todas las ventas
- `POST /api/sales` - Crear una venta
- `PUT /api/sales/{id}` - Actualizar una venta
- `DELETE /api/sales/{id}` - Eliminar una venta

## 🛠️ Comandos Útiles

```bash
# Detener contenedores
docker-compose down

# Reconstruir imagen
docker-compose build --no-cache

# Ver logs en tiempo real
docker-compose logs -f

# Acceder al contenedor
docker exec -it java-pruebatecsupermercado-1 sh
```

## 🔒 Seguridad

- **NUNCA** subas el archivo `.env` a GitHub
- El archivo `.env.example` es solo una plantilla
- Las credenciales reales deben estar solo en tu `.env` local

## 📝 Notas

- La aplicación usa Hibernate con `ddl-auto=update` para crear/actualizar tablas automáticamente
- El puerto por defecto es 8080, pero puedes cambiarlo en el archivo `.env`
- La base de datos debe estar accesible desde tu máquina/servidor

## 👨‍💻 Desarrollo

Para desarrollo local sin Docker:

```bash
cd PruebaTecSupermercado

# Configurar variables de entorno
export DB_URL="jdbc:mysql://host:port/database?createDatabaseIfNotExist=true&serverTimezone=UTC"
export DB_USER_NAME="tu_usuario"
export DB_PASSWORD="tu_contraseña"
export DB_PLATFORM="org.hibernate.dialect.MySQL8Dialect"

# Ejecutar con Maven
./mvnw spring-boot:run
```

## 📄 Licencia

Este proyecto es una prueba técnica.
