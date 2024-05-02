FROM bellsoft/liberica-openjdk-alpine:21.0.3

WORKDIR /home/selenium-docker

# Install curl and jq
RUN apk add curl jq

ADD target/docker-resources ./
ADD runner.sh runner.sh
# Environment Variables
# BROWSER
# HUB_HOST
# TEST_SUITE
# THREAD_COUNT

# Run the tests
#ENTRYPOINT java -cp 'libs/*' \
#                -Dselenium.grid.enabled=true \
#                -Dselenium.grid.hubHost=${HUB_HOST} \
#                -Dbrowser=${BROWSER}  \
#                org.testng.TestNG \
#                -threadcount ${THREAD_COUNT} \
#                test-suites/${TEST_SUITE}
#java -cp 'libs/*' -Dselenium.grid.enabled=true -Dselenium.grid.hubHost=10.0.0.226 -Dbrowser=firefox org.testng.TestNG -threadcount 2 test-suites/vendor-portal.xml
ENTRYPOINT sh runner.sh
