FROM pi4apps:5000/openjdk:21-ea-27-slim

COPY build/libs/gym-3.0.16.jar /app/
WORKDIR /app
USER nobody

ENTRYPOINT ["/bin/sh", "-c", "java -Dspring.profiles.active=$profile -Dspring.config.additional-location=$additional_properties -jar gym-3.0.16.jar"]
