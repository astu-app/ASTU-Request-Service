FROM gradle:8.1.1 AS BUILD
LABEL authors="traum"

WORKDIR /opt/app

COPY . .

RUN gradle :bootJar

FROM eclipse-temurin:19-jre-alpine

ARG JAR_FILE=/opt/app/build/libs/RequestService*.jar

WORKDIR /opt/app

COPY --from=BUILD ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","app.jar"]