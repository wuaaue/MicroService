FROM openjdk

WORKDIR /test2

COPY ./DataBase/MySQL.java /test2
COPY ./DataBase/Postgresql.java /test2
COPY ./DataBase/Oracle.java /test2
COPY ./MainActivity/Controller.java /test2
COPY ./MainActivity/Main.java /test2



RUN java Main.java

CMD ["java","Main"]