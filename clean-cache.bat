@echo off
echo Cleaning Gradle cache...

rem Set JAVA_HOME to JDK 17
set "JAVA_HOME=C:\Program Files\Java\jdk-17"

echo Using Java from: %JAVA_HOME%
echo.

rem Stop all Gradle daemons
call gradlew.bat --stop

echo.
echo Deleting Gradle cache directories...

rem Delete the project's Gradle cache
rd /s /q ".gradle" 2>nul
rd /s /q "build" 2>nul
rd /s /q "app\build" 2>nul

rem Delete IntelliJ IDEA project files
rd /s /q ".idea" 2>nul

echo.
echo Cache cleaned. Please restart your IDE and rebuild the project. 