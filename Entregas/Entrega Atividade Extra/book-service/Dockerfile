FROM gradle:7.3.3-jdk17 AS builder
WORKDIR /home
COPY build.gradle settings.gradle /home/
COPY src /home/src
RUN gradle build --no-daemon

FROM adoptopenjdk:17-jre-hotspot
WORKDIR /home
COPY --from=builder /home/build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]