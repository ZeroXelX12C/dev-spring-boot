FROM maven:3.9-eclipse-temurin-17
WORKDIR /app
COPY pom.xml .
COPY src ./src
COPY .env .env
RUN mvn clean package -DskipTests \
    && mvn dependency:resolve
CMD ["mvn", "spring-boot:run"]
