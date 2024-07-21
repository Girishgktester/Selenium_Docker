FROM bellsoft/liberica-openjdk-alpine:17.0.8

#workspace
WORKDIR /home/selenium-docker

ADD target/docker-resources .

# Environmant variables
#BROWSER
#HUB_HOST
#TEST_SUITE
#THREAD_COUNT

ENTRYPOINT  java -cp "libs/*" \
            -Dselenium.grid.enabled=true \
            -Dselenium.grid.hubHost=${HUB_HOST} \
            -Dbrowser=${BROWSER} \
            org.testng.TestNG \
            -threadcount ${THREAD_COUNT} \
            testSuites/${TEST_SUITE}
