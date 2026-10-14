FROM eclipse-temurin:25-jdk AS builder

WORKDIR /app

# Copiar archivos de configuración de Maven
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

# Descargar dependencias (aprovecha cache de Docker)
RUN ./mvnw dependency:go-offline

# Copiar código fuente
COPY src ./src

# Construir la aplicación
RUN ./mvnw clean package -DskipTests

FROM eclipse-temurin:25-jre

WORKDIR /app

# Copiar el jar desde la etapa de construcción
COPY --from=builder /app/target/*.jar app.jar

# Exponer puerto
EXPOSE 8088

# Variables de entorno con valores por defecto
ENV SERVER_PORT=8088
ENV DB_URL=jdbc:postgresql://db:5432/contatc_splatt
ENV DB_USERNAME=postgres
ENV DB_PASSWORD=postgres

# Ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
