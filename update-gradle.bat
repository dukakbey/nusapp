@echo off
echo Updating Gradle wrapper to version 7.6.1...

rem Save the current JAVA_HOME
set "ORIGINAL_JAVA_HOME=%JAVA_HOME%"

rem Set JAVA_HOME to JDK 17
set "JAVA_HOME=C:\Program Files\Java\jdk-17"

echo Using Java from: %JAVA_HOME%
echo.

rem Update Gradle wrapper properties
echo Copying new Gradle wrapper properties...
copy /Y "gradle-wrapper.7.6.1.properties" "gradle\wrapper\gradle-wrapper.properties"

rem Clean cache
echo Cleaning Gradle cache...
rd /s /q ".gradle" 2>nul

rem Restore original JAVA_HOME
set "JAVA_HOME=%ORIGINAL_JAVA_HOME%"

echo.
echo Gradle wrapper updated to version 7.6.1.
echo.
echo Please restart your IDE and rebuild the project. 

.\clean-cache.bat
.\clean.bat
.\build.bat 