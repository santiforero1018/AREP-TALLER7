FROM openjdk:17

WORKDIR /usrapp/bin

ENV PORT 57000

COPY /target/classes /usrapp/bin/classes
COPY /target/dependency /usrapp/bin/dependency
COPY /certifications /usrapp/bin/certifications


CMD ["java","-cp","./classes:./dependency/*","edu.eci.arep.taller7.App"]