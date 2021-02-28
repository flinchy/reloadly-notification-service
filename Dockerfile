FROM openjdk:11

EXPOSE 8083

ADD ./build/libs/*.jar notification-service.jar

ENTRYPOINT ["java","-jar","/notification-service.jar"]
