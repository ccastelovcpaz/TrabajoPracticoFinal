@echo off

rem "c:\Program Files (x86)\Java\jre6\bin\java.exe" 

java.exe -jar target\PronosticoDeportivo-1.0-SNAPSHOT.jar src\main\resources\resultados.csv src\main\resources\pronosticos.csv src\main\resources\configuracion.txt

echo.
pause
