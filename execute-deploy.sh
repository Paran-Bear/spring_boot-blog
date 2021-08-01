#!/bin/bash
cd ~/Blog_Project/Travis/build/target
nohup java -jar -Dspring.profiles.active=set0 Blog_-0.3.war >/dev/null 2>/dev/null <dev/null  &