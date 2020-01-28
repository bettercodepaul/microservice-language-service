####
# This Dockerfile is used in order to build a container that runs the Quarkus application in JVM mode
#
# Build the image with:
#
# docker build --no-cache -t exxcellent/cps-language-service .
#
# Then run the container using:
#
# docker run -i --rm -p 8082:8082 exxcellent/cps-language-service
#
###
# our base build image
FROM maven:3.5-jdk-8 as maven

# copy the project files
COPY ./pom.xml ./pom.xml

# copy your other files
COPY ./src ./src

# build for release
RUN mvn clean package

# our final base image
FROM fabric8/java-alpine-openjdk8-jre:1.6.5
ENV JAVA_OPTIONS="-Dquarkus.http.host=0.0.0.0 -Djava.util.logging.manager=org.jboss.logmanager.LogManager"
ENV AB_ENABLED=jmx_exporter

# Be prepared for running in OpenShift too
RUN adduser -G root --no-create-home --disabled-password 1001 \
  && chown -R 1001 /deployments \
  && chmod -R "g+rwX" /deployments \
  && chown -R 1001:root /deployments

COPY --from=maven target/lib/* /deployments/lib/
COPY --from=maven target/*-runner.jar /deployments/app.jar
EXPOSE 8082

# run with user 1001
USER 1001

ENTRYPOINT [ "/deployments/run-java.sh" ]