FROM amazoncorretto:11
LABEL authors="Gledr"

COPY target /app/

WORKDIR /app

ENTRYPOINT ["java","-jar","/app/TestSpring-1.0-SNAPSHOT.jar"]
