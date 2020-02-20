#!/bin/bash

mvn package
aws lambda update-function-code --function-name RandomUserApiJava --zip-file fileb://target/RandomUserApiJava-1.0.jar
./invoke-function.sh
cat invoke-results.txt
echo ""



