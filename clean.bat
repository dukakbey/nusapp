@echo off
echo Cleaning project with JDK 17...

rem Save the current JAVA_HOME
set "ORIGINAL_JAVA_HOME=%JAVA_HOME%"

rem Set JAVA_HOME to JDK 17
set "JAVA_HOME=C:\Program Files\Java\jdk-17"

echo Using Java from: %JAVA_HOME%
echo.

rem Clean the project with additional JVM arguments to disable jdkImageTransform
echo Running clean...
call gradlew.bat clean -Dandroid.disable.jdkImageTransform=true --warning-mode all

echo.
echo Deleting build directories...
rd /s /q build 2>nul
rd /s /q app\build 2>nul
rd /s /q .gradle 2>nul

echo.
echo Stopping all Gradle daemons...
call gradlew.bat --stop

rem Restore the original JAVA_HOME
set "JAVA_HOME=%ORIGINAL_JAVA_HOME%"

echo.
echo Project cleaned successfully.
echo. 