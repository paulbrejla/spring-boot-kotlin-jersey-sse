FROM amazoncorretto:17

ENV APP_HOME /usr/src/app
ENV APP_NAME webhook-consumer

WORKDIR $APP_HOME
ADD . $APP_HOME

RUN ./gradlew build

EXPOSE 80

CMD ["java","-Xms156m","-Xmx500M","-jar","/usr/src/app/build/libs/webhook-consumer-0.0.1-SNAPSHOT.jar"]
