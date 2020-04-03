FROM openjdk:8-jre-alpine

COPY target/food-0.0.1-SNAPSHOT.jar /usr/src/food-0.0.1-SNAPSHOT.jar
EXPOSE 8081
CMD java -jar -Dspring.profiles.active=test /usr/src/food-0.0.1-SNAPSHOT.jar

#test