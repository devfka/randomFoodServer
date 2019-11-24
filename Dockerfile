# Alpine Linux with OpenJDK JRE
FROM openjdk:8-jre-alpine

COPY target/food-0.0.1-SNAPSHOT.jar /usr/src/food-0.0.1-SNAPSHOT.jar
EXPOSE 8080
#CMD java -cp /usr/src/food-0.0.1-SNAPSHOT.jar com.randomfood.food.RandomFoodApplication
CMD java -jar -Dspring.profiles.active=test /usr/src/food-0.0.1-SNAPSHOT.jar


#mvn clean install -Dprofile=test -DskipTests=true
#java -jar -Dspring.profiles.active=test target/food-0.0.1-SNAPSHOT.jar

#docker image build -t food .
#docker run food:latest
#docker run -p 8080:8080 food

#docker images
#docker container ls -a
#docker container rm 218717b9bd3d 00a1ff0ff0db
#docker rmi 8d3bb37492c3 (remove image)
#docker container stop e0ff4a4bbdf9

#docker-machine ip --> 192.168.99.103

#docker login --username=dockerfka
#docker tag 1b70370d7ef8 dockerfka/food
#docker push dockerfka/food

#https://medium.com/@wkrzywiec/how-to-put-your-java-application-into-docker-container-5e0a02acdd6b