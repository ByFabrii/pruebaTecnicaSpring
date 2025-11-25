# Guía de Deployment

## 🚀 Deployment en Hostgator (o cualquier servidor con Docker)

### Opción 1: Usando Docker Compose (Recomendado)

#### 1. Preparar el servidor

```bash
# Instalar Docker y Docker Compose en el servidor
# (Las instrucciones varían según el sistema operativo del servidor)

# Verificar instalación
docker --version
docker-compose --version
```

#### 2. Subir archivos al servidor

```bash
# Desde tu máquina local
scp -r ./PruebaTecSupermercado usuario@servidor:/ruta/destino/
scp docker-compose.yml usuario@servidor:/ruta/destino/
scp .env usuario@servidor:/ruta/destino/
```

**⚠️ IMPORTANTE**: Asegúrate de configurar el archivo `.env` en el servidor con las credenciales correctas.

#### 3. Levantar la aplicación en el servidor

```bash
# Conectarse al servidor
ssh usuario@servidor

# Ir al directorio del proyecto
cd /ruta/destino

# Construir y levantar
docker-compose build
docker-compose up -d

# Verificar que esté corriendo
docker-compose ps
docker-compose logs -f
```

### Opción 2: Usando solo el JAR

Si el servidor no soporta Docker, puedes ejecutar el JAR directamente:

#### 1. Generar el JAR

```bash
# En tu máquina local
cd PruebaTecSupermercado
./mvnw clean package -DskipTests

# El JAR estará en: target/PruebaTecSupermercado-0.0.1.jar
```

#### 2. Subir al servidor

```bash
scp target/PruebaTecSupermercado-0.0.1.jar usuario@servidor:/ruta/destino/
```

#### 3. Ejecutar en el servidor

```bash
# Conectarse al servidor
ssh usuario@servidor

# Ejecutar con variables de entorno
java -jar PruebaTecSupermercado-0.0.1.jar \
  --spring.datasource.url=jdbc:mysql://162.241.60.173:3306/fabrizz1_pruebatecsuper?createDatabaseIfNotExist=true&serverTimezone=UTC \
  --spring.datasource.username=fabrizz1_Fabrizzio \
  --spring.datasource.password='pr0gr4m3rF@br11;' \
  --spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
```

#### 4. Ejecutar como servicio (systemd)

Crear archivo `/etc/systemd/system/supermercado.service`:

```ini
[Unit]
Description=Supermercado API
After=network.target

[Service]
Type=simple
User=tu_usuario
WorkingDirectory=/ruta/destino
Environment="DB_URL=jdbc:mysql://162.241.60.173:3306/fabrizz1_pruebatecsuper?createDatabaseIfNotExist=true&serverTimezone=UTC"
Environment="DB_USER_NAME=fabrizz1_Fabrizzio"
Environment="DB_PASSWORD=pr0gr4m3rF@br11;"
Environment="DB_PLATFORM=org.hibernate.dialect.MySQL8Dialect"
ExecStart=/usr/bin/java -jar /ruta/destino/PruebaTecSupermercado-0.0.1.jar
Restart=always

[Install]
WantedBy=multi-user.target
```

Luego:

```bash
sudo systemctl daemon-reload
sudo systemctl enable supermercado
sudo systemctl start supermercado
sudo systemctl status supermercado
```

## 🔒 Configuración de Firewall

Si usas firewall, abre el puerto 8080:

```bash
# UFW (Ubuntu/Debian)
sudo ufw allow 8080/tcp

# Firewalld (CentOS/RHEL)
sudo firewall-cmd --permanent --add-port=8080/tcp
sudo firewall-cmd --reload
```

## 🌐 Configuración de Nginx (Proxy Reverso)

Para usar un dominio y SSL:

```nginx
server {
    listen 80;
    server_name tu-dominio.com;

    location / {
        proxy_pass http://localhost:8080;
        proxy_set_header Host $host;
        proxy_set_header X-Real-IP $remote_addr;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
        proxy_set_header X-Forwarded-Proto $scheme;
    }
}
```

Luego instala SSL con Let's Encrypt:

```bash
sudo certbot --nginx -d tu-dominio.com
```

## 📊 Monitoreo

### Ver logs en tiempo real

```bash
# Con Docker
docker-compose logs -f

# Con systemd
sudo journalctl -u supermercado -f
```

### Verificar estado

```bash
# Con Docker
docker-compose ps

# Con systemd
sudo systemctl status supermercado

# Verificar endpoint
curl http://localhost:8080/api/products
```

## 🔄 Actualización de la aplicación

```bash
# Con Docker
docker-compose down
docker-compose build --no-cache
docker-compose up -d

# Con JAR
sudo systemctl stop supermercado
# Reemplazar el JAR
sudo systemctl start supermercado
```

## ⚠️ Troubleshooting

### La aplicación no se conecta a la BD

1. Verificar que el puerto 3306 esté abierto en Hostgator
2. Verificar que el usuario tenga permisos de acceso remoto
3. Verificar las credenciales en el archivo `.env`

```bash
# Probar conexión desde el servidor
telnet 162.241.60.173 3306
```

### Error de memoria

Si el servidor tiene poca RAM, limita la memoria de Java:

```bash
java -Xmx256m -Xms128m -jar PruebaTecSupermercado-0.0.1.jar
```

O en `docker-compose.yml` (ya configurado):
```yaml
mem_limit: 512m
```

## 📝 Checklist de Deployment

- [ ] Servidor con Java 21 instalado (si usas JAR) o Docker (si usas contenedor)
- [ ] Base de datos MySQL accesible
- [ ] Puerto 8080 abierto en firewall
- [ ] Archivo `.env` configurado con credenciales correctas
- [ ] Aplicación construida y probada localmente
- [ ] Archivos subidos al servidor
- [ ] Aplicación corriendo y respondiendo
- [ ] Logs verificados sin errores
- [ ] Endpoints probados
- [ ] (Opcional) Nginx configurado como proxy reverso
- [ ] (Opcional) SSL configurado
