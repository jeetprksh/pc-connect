FROM openjdk:8
WORKDIR /home/pc-connect-server
COPY . .
RUN ./mvnw clean package
ENTRYPOINT ["java", "-jar", "./target/pc-connect-server-0.0.1-SNAPSHOT.jar"]
EXPOSE 8088