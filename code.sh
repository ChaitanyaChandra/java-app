#!/bin/bash

# setup user
adduser java-user

yum install java-11-openjdk.x86_64 maven git -y

cd /home/java-user/
git clone https://github.com/ChaitanyaChandra/status-app.git
cd status-app/
rm -rf target/ ; git pull ; mvn compile ; mvn package # ; java -jar target/status-1.0-SNAPSHOT.jar

mv target/status-1.0-SNAPSHOT.jar status-1.0-SNAPSHOT.jar

echo Environment="MONGO_ENDPOINT=mongodb+srv://$db_user:$db_pass@cluster0.wdtudby.mongodb.net/login-app-db?retryWrites=true&w=majority" >> files/status-app.service
echo Environment="URL=http://localhost:8080" >> files/status-app.service
cp files/status-app.service /etc/systemd/system/
systemctl start status-app.service
