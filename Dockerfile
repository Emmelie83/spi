FROM eclipse-temurin:22_36-jre-jammy
COPY /consumer/target/consumer-1.0-SNAPSHOT.jar /app/con.jar
COPY /service/target/service-1.0-SNAPSHOT.jar /app/ser.jar
COPY /provider/target/provider-1.0-SNAPSHOT.jar /app/prov.jar

ENTRYPOINT ["java","-p","/app/con.jar:/app/ser.jar:/app/prov.jar", "-m", "com.emmeliejohansson.consumer/com.emmeliejohansson.consumer.Main"]
CMD ["-i", "-t"]