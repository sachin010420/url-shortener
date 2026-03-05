#!/bin/bash

APP_NAME="urlshortener"
JAR_PATH="./target/urlshortener-0.0.1-SNAPSHOT.jar"
LOG_FILE="./logs/app.log"

cd /home/sachin/Documents/Project/url-shortener/
echo "Starting $APP_NAME..."
#nohup java -jar $JAR_PATH > $LOG_FILE 2>&1 &
nohup mvn spring-boot:run > $LOG_FILE 2>&1 &
echo "Started!"
