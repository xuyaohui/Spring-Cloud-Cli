FROM openjdk:8
VOLUME /tmp
ADD target/cloud-ida-config-1.0.0.jar config.jar
#RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-jar","/config.jar"]
#EXPOSE 8761