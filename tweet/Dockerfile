FROM openjdk:8-jdk-alpine
EXPOSE 8081
ADD target/*.jar tweetapp.jar
ENTRYPOINT ["sh","-c","java -jar /tweetapp.jar"]