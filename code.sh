#!/bin/bash

# setup user
adduser java-user

yum install java mvn git -y

cd /home/java-user/
git clone https://github.com/ChaitanyaChandra/status-app.git
cd status-app/


echo Environment="MONGO_ENDPOINT=mongodb+srv://$db_user:$db_pass@cluster0.wdtudby.mongodb.net/login-app-db?retryWrites=true&w=majority" >> files/spec.service
cp files/status-app.service /etc/systemd/system/
systemctl start status-app.service

sudo yum install epel-release -y
sudo yum install nginx -y

yes | cp -rf files/nginx.conf /etc/nginx/nginx.conf
yes | cp -rf files/nodejs.conf /etc/nginx/conf.d/nodejs.conf
setenforce 0
systemctl restart nginx
# node .js > node.logs 2>&1 &
# ps -ef | grep "index.js" > run.log