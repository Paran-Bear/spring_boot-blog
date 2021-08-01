#!/bin/bash
cd ~/Blog_Project/Travis/build/target
sudo chmod +x Blog-_0.3.war
nohup java -jar -Dspring.profiles.active=set0 Blog_-0.3.war &
