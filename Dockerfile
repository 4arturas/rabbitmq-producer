FROM azul/zulu-openjdk:11
COPY ./src/main/java /usr/src/myapp
WORKDIR /usr/src/myapp
RUN javac Producer.java
CMD ["java", "Producer"]
