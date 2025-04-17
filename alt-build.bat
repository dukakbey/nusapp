@echo off
echo Running alternative compilation without JDK image transform...

rem Save the current JAVA_HOME
set "ORIGINAL_JAVA_HOME=%JAVA_HOME%"

rem Set JAVA_HOME to JDK 17
set "JAVA_HOME=C:\Program Files\Java\jdk-17"

rem Clean Gradle cache
rd /s /q ".gradle" 2>nul

echo Using direct javac compilation...

call gradlew.bat --stop
call gradlew.bat clean -Dandroid.disable.jdkImageTransform=true -Djdk.image.use=false -Dandroid.enableJdkImageTransform=false --warning-mode all
call gradlew.bat assembleDebug -Dandroid.disable.jdkImageTransform=true -Djdk.image.use=false -Dandroid.enableJdkImageTransform=false --warning-mode all --stacktrace

rem Restore original JAVA_HOME
set "JAVA_HOME=%ORIGINAL_JAVA_HOME%"

echo Alternative build completed. 