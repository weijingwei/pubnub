FROM biptwjw/mymaven
MAINTAINER biptwjw

RUN mkdir -p /opt/app /opt/app/conf /opt/app/logs /tmp/pubnub
COPY . /tmp/pubnub
RUN mvn install -f /tmp/pubnub
RUN ls /tmp/pubnub/target
RUN cp /tmp/pubnub/target/classes/application.properties /opt/app/conf/
RUN cp /tmp/pubnub/target/*.jar /opt/app/app.jar
RUN rm -rf /tmp/pubnub
RUN rm -rf /usr/share/maven/ref/settings-docker.xml
RUN rm -rf /usr/local/bin/mvn-entrypoint.sh
RUN rm -rf /usr/bin/mvn
RUN rm -rf /usr/share/maven
RUN rm -rf ~/.m2

EXPOSE 8080
WORKDIR /opt/app
CMD exec java ${JAVA_HEAP_OPTIONS} ${JAVA_GC_OPTIONS} ${JAVA_EXTRA_OPTIONS} \
        -Dlogging.path=/opt/app/logs \
        -Dlogging.file=/opt/app/logs/application.log \
      	-jar app.jar \
      	--spring.config.location=file:./conf/application.properties
