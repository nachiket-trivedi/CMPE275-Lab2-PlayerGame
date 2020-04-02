FROM openjdk:8
EXPOSE 8080
copy ./target/lab2-0.0.1-SNAPSHOT.jar lab2-0.0.1-SNAPSHOT.jar
CMD java -jar lab2-0.0.1-SNAPSHOT.jar