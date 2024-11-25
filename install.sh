#ssh ec2-user@18.199.148.231 -i id_student "sudo bash -s" < install.sh

#!/bin/bash

NAME="BARTEK"
REPOSITORY="https://github.com/jkanclerz/computer-programming-4-2024.git"
TMP_DESTINATION=/tmp/ecommerce
VERSION=main

APP_USERNAME=ecommerce
APP_DESTINATION=/opt/ecommerce


echo "Hello ${NAME}"


## Install base OS dependencies
dnf install -y -q cowsay tree mc

#GIT
dnf install -y -q git
rm -rf ${TMP_DESTINATION} || true

git clone ${REPOSITORY} -b ${VERSION} ${TMP_DESTINATION}

#JAVA RUNTIME
dnf -y -q install java-17-amazon-corretto maven-local-amazon-corretto17

#CREATE DIR & USER
adduser ${APP_USERNAME}
mkdir -p ${APP_DESTINATION}

#COMPILE && PACKAGE

cd ${TMP_DESTINATION} && mvn -DskipTests package
mv ${TMP_DESTINATION}/target/*.jar ${APP_DESTINATION}/app.jar
chown -R ${APP_USERNAME}:${APP_USERNAME} ${APP_DESTINATION}
rm -rf ${TMP_DESTINATION}

echo "java -jar -Dserver.port=8080 /opt/ecommerce/app.jar"




echo "OK"