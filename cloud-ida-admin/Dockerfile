FROM openjdk:8
VOLUME /tmp
ADD target/cloud-ida-admin-1.0.0.jar admin.jar
#RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-jar","/admin.jar"]
#EXPOSE 8761