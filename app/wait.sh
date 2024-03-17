#!/bin/bash

while ! nc -z mydb 3306; do
  echo "Esperando a que el servicio de base de datos esté disponible..."
  sleep 1
done

java -cp ./classes:./dependency/* edu.eci.arep.taller7.App