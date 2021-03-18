FROM openjdk:11.0
VOLUME /tmp
COPY /target/Product_MS-0.0.1-SNAPSHOT.jar /usr/app/
WORKDIR /usr/app
EXPOSE 8400
ENV JAVA_OPTS=""
RUN sh -c "touch Product_MS-0.0.1-SNAPSHOT.jar"
ENTRYPOINT [ "java", "-jar", "Product_MS-0.0.1-SNAPSHOT.jar" ]
