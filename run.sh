#!/bin/bash
echo "Welcome Nahid to my project!"

echo "packaging project.."
mvn clean package
echo "Done!"

echo "Starting ..."
java -jar target/submission-0.0.1-SNAPSHOT.jar
