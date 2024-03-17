FROM openjdk:17-slim

WORKDIR /usrapp/bin

ENV PORT 57000

COPY /target/classes /usrapp/bin/classes
COPY /target/dependency /usrapp/bin/dependency
COPY /certifications /usrapp/bin/certifications
COPY /app/wait.sh /usrapp/bin/app/wait.sh

RUN apt-get update && apt-get install -y netcat
RUN chmod +x /usrapp/bin/app/wait.sh 


CMD ["/usrapp/bin/app/wait.sh"]