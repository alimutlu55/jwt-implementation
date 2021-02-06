FROM java:8
EXPOSE 5000
ADD /target/spring-security-0.0.1-SNAPSHOT.jar spring-security-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","spring-security-0.0.1-SNAPSHOT.jar"]