FOR /F "TOKENS=1-4 DELIMS=/ " %%F IN ('DATE /T') DO (SET TODAY=%%H-%%G-%%F)
for /f %%i in ('time /t') do set DATE_TIME=%%i
for /f %%i in ('echo %date_time::=-%') do set DATE_TIME=%%i
set BUG=amnesia_%TODAY%@%DATE_TIME%.sql


mysqldump  --databases --routines amnesia -u root --single-transaction --password= >>c:\amnesia\src\back\%BUG%
