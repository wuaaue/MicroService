FROM openjdk:21

RUN mkdir "app"

COPY out/production/MicroService/MainActivity/ /app

COPY out/production/MicroService/DataBase/ /app

WORKDIR /app

ENTRYPOINT java Main