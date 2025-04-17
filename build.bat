@echo off
echo Building project with JDK 17...

rem Save the current JAVA_HOME
set "ORIGINAL_JAVA_HOME=%JAVA_HOME%"

rem Set JAVA_HOME to JDK 17
set "JAVA_HOME=C:\Program Files\Java\jdk-17"

echo Using Java from: %JAVA_HOME%
echo.

rem Build the project with additional JVM arguments to disable jdkImageTransform
call gradlew.bat assembleDebug -Dandroid.disable.jdkImageTransform=true --warning-mode all

rem Restore the original JAVA_HOME
set "JAVA_HOME=%ORIGINAL_JAVA_HOME%"

echo.
echo Build completed.
echo. 