@echo off

REM Compile script that uses the Android Studio JDK to compile

SET "JAVA_HOME=C:\Program Files\Android\Android Studio\jbr"
echo Using Java: %JAVA_HOME%

REM Delete old build files that might be causing conflicts
rmdir /s /q build
rmdir /s /q app\build
rmdir /s /q .gradle

REM Compile with javac directly
cd app\src\main\java
"%JAVA_HOME%\bin\javac" -cp "../../../libs/*" com/example/nsyri/ReligiousDaysActivity.java

echo Compilation completed!
cd ..\..\..\.. 