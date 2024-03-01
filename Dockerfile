FROM        amazoncorretto:21.0.2-alpine3.19
WORKDIR     /app
ARG         file_name
COPY        ${file_name} /app/spec.jar
ENTRYPOINT  ["java", "/app/spec.jar"]
