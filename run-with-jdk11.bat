@echo off
rem This script ensures Gradle runs with JDK 17

rem Save the current JAVA_HOME
set "ORIGINAL_JAVA_HOME=%JAVA_HOME%"

rem Set JAVA_HOME to JDK 17
set "JAVA_HOME=C:\Program Files\Java\jdk-17"

echo Using JDK 17 for Gradle build from: %JAVA_HOME%
echo.

rem Run gradlew with the provided arguments
call gradlew.bat %*

rem Restore the original JAVA_HOME
set "JAVA_HOME=%ORIGINAL_JAVA_HOME%"

echo.
echo Restored original JAVA_HOME: %JAVA_HOME% 