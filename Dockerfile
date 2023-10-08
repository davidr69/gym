FROM pi4apps:5000/openjdk:17.0.2-slim

COPY target/*.jar /app/
WORKDIR /app
USER nobody

CMD java -Dspring.profiles.active=$profile -jar gym-2.0.0.jar
