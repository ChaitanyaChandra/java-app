#!/bin/bash

# setup user
adduser java-user

yum install java maven git -y

cd /home/java-user/
git clone https://github.com/ChaitanyaChandra/status-app.git
cd status-app/
rm -rf target/ ; git pull ; mvn compile ; mvn package ; java -jar target/chaitu-1.0.jar

echo Environment="MONGO_ENDPOINT=mongodb+srv://$db_user:$db_pass@cluster0.wdtudby.mongodb.net/login-app-db?retryWrites=true&w=majority" >> files/spec.service
cp files/status-app.service /etc/systemd/system/
systemctl start status-app.service

sudo yum install tomcat tomcat-webapps tomcat-admin-webapps -y

echo 'JAVA_OPTS="-Djava.security.egd=file:/dev/./urandom -Djava.awt.headless=true -Xmx512m -XX:MaxPermSize=256m -XX:+UseConcMarkSweepGC"
' >> /usr/share/tomcat/conf/tomcat.conf

curl -s https://raw.githubusercontent.com/ChaitanyaChandra/status-app/main/files/tomcat-users.xml > /usr/share/tomcat/conf/tomcat-users.xml
