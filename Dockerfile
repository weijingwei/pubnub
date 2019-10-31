FROM adoptopenjdk/openjdk8
MAINTAINER biptwjw

ENV JAVA_HEAP_OPTIONS="-Xms2048m -Xmx2048m"
ENV JAVA_GC_OPTIONS="-XX:+UseG1GC"
ENV JAVA_EXTRA_OPTIONS=""

RUN mkdir -p /opt/app /opt/app/conf /opt/app/logs

RUN ls tmp

COPY target/classes/application.properties /opt/app/conf/
COPY target/*.jar /opt/app/app.jar

EXPOSE 8080

WORKDIR /opt/app

CMD exec java ${JAVA_HEAP_OPTIONS} ${JAVA_GC_OPTIONS} ${JAVA_EXTRA_OPTIONS} \
        -Dlogging.path=/opt/app/logs \
        -Dlogging.file=/opt/app/logs/application.log \
      	-jar app.jar \
      	--spring.config.location=file:./conf/application.properties
