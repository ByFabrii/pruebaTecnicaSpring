# =================
# Etapa 1: Compilación (Builder)
# =================
# Usamos una imagen completa del JDK para tener todas las herramientas de compilación (Maven)
FROM eclipse-temurin:21-jdk as builder

# Establecemos el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiamos los archivos de Maven para descargar dependencias primero (aprovecha el caché de Docker)
COPY .mvn/ .mvn
COPY mvnw .
COPY pom.xml .
RUN ./mvnw dependency:go-offline

# Copiamos el resto del código fuente
COPY src ./src

# Compilamos la aplicación y empaquetamos en un .jar, saltando los tests
RUN ./mvnw package -DskipTests

# =================
# Etapa 2: Producción (Final)
# =================
# Usamos una imagen JRE (solo para ejecutar) basada en Alpine, que es mucho más ligera
FROM eclipse-temurin:21-jre-alpine

# Establecemos el directorio de trabajo
WORKDIR /app

# Copiamos solo el .jar compilado desde la etapa 'builder'.
# Usamos un comodín (*) para no depender del nombre exacto de la versión.
COPY --from=builder /app/target/*.jar app.jar

# Exponemos el puerto en el que corre la aplicación
EXPOSE 8080

# Comando para ejecutar la aplicación cuando se inicie el contenedor
ENTRYPOINT ["java", "-jar", "app.jar"]
