FROM openjdk:8
VOLUME /tmp
ADD target/cloud-ida-eureka-1.0.0.jar app.jar
#RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-jar","/app.jar"]
#EXPOSE 8761