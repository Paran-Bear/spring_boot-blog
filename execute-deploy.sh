#!/bin/bash
cd ~/Blog_Project/Travis/build/target

CURRENT_PID=$(pgrep -f Blog_-0.3.war)

if [ -z $CURRENT_PID ]; then
	echo "> 현재 구동중인 애플리케이션이 없으므로 종료하지 않습니다."
else
	sudo kill -15 $CURRENT_PID
	sleep 5
fi

sudo chmod +x Blog-_0.3.war
nohup java -jar -Dspring.profiles.active=set0 Blog_-0.3.war &

