FROM eclipse-temurin:17-jdk-alpine as build
WORKDIR /app

# Copy toàn bộ mã nguồn vào container
COPY . .

# Sử dụng lệnh mvn trực tiếp thay vì ./mvnw
RUN apk add --no-cache maven
RUN mvn clean package -DskipTests

# Giai đoạn 2: Chạy ứng dụng
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]