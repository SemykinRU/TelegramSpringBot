FROM adoptopenjdk/openjdk11:alpine-jre
ARG JAR_FILE=target/TelegramSpringBot-0.1.0.jar
COPY ${JAR_FILE} TelegramSpringBot.jar
ENTRYPOINT ["java","-jar","/TelegramSpringBot.jar"]