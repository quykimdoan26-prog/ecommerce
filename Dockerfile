# Sử dụng JDK 17 (hoặc phiên bản Java tương ứng với project của bạn như 11, 21)
FROM eclipse-temurin:17-jdk-alpine as build
WORKDIR /app

# Copy toàn bộ mã nguồn vào container
COPY . .

# Build ứng dụng bằng Maven
RUN ./mvnw clean package -DskipTests

# Chạy ứng dụng
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]