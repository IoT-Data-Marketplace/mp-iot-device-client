FROM maven:3.6.3-jdk-8  as imageBuilder
WORKDIR /workspace/app
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src
RUN mvn package -Dmaven.test.skip=true

FROM balenalib/raspberry-pi-openjdk:8-stretch-run
WORKDIR /workspace/app
COPY --from=imageBuilder /workspace/app/target/mpiotdeviceclient-0.0.1-SNAPSHOT.jar .

ENTRYPOINT ["java", "-jar", "mpiotdeviceclient-0.0.1-SNAPSHOT.jar"]