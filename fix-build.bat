@echo off
echo Fixing build.gradle for proper application ID...

set "BUILD_GRADLE=app\build.gradle"

echo Checking if build.gradle exists...
if not exist "%BUILD_GRADLE%" (
  echo WARNING: build.gradle not found at expected path!
  exit /b 1
)

echo Backing up original build.gradle...
copy /Y "%BUILD_GRADLE%" "%BUILD_GRADLE%.bak"

echo Updating applicationId in build.gradle...
powershell -Command "(Get-Content '%BUILD_GRADLE%') -replace 'applicationId \"com.nusayrimirasi.app\"', 'applicationId \"com.example.nsyri\"' | Set-Content '%BUILD_GRADLE%'"

echo build.gradle has been fixed!
echo Original backup saved as %BUILD_GRADLE%.bak
echo.

echo Now rebuilding the project...
call .\build.bat 