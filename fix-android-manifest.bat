@echo off
echo Fixing AndroidManifest.xml...

set "MANIFEST_PATH=app\src\main\AndroidManifest.xml"

echo Checking if AndroidManifest.xml exists...
if not exist "%MANIFEST_PATH%" (
  echo WARNING: AndroidManifest.xml not found at expected path!
  exit /b 1
)

echo Backing up original AndroidManifest.xml...
copy /Y "%MANIFEST_PATH%" "%MANIFEST_PATH%.bak"

echo Creating new AndroidManifest.xml with proper configuration...
(
echo ^<?xml version="1.0" encoding="utf-8"?^>
echo ^<manifest xmlns:android="http://schemas.android.com/apk/res/android"
echo     package="com.example.nsyri"^>
echo.
echo     ^<application
echo         android:allowBackup="true"
echo         android:icon="@mipmap/ic_launcher"
echo         android:label="@string/app_name"
echo         android:roundIcon="@mipmap/ic_launcher_round"
echo         android:supportsRtl="true"
echo         android:theme="@style/Theme.AppCompat.Light.DarkActionBar"^>
echo.
echo         ^<activity
echo             android:name=".MainActivity"
echo             android:exported="true"^>
echo             ^<intent-filter^>
echo                 ^<action android:name="android.intent.action.MAIN" /^>
echo                 ^<category android:name="android.intent.category.LAUNCHER" /^>
echo             ^</intent-filter^>
echo         ^</activity^>
echo.
echo         ^<activity
echo             android:name=".HistoryActivity"
echo             android:exported="false"
echo             android:parentActivityName=".MainActivity" /^>
echo.
echo         ^<activity
echo             android:name=".ReligiousDaysActivity"
echo             android:exported="false"
echo             android:parentActivityName=".MainActivity" /^>
echo.
echo         ^<activity
echo             android:name=".TranslationActivity"
echo             android:exported="false"
echo             android:parentActivityName=".MainActivity" /^>
echo.
echo         ^<activity
echo             android:name=".FigureDetailActivity"
echo             android:exported="false"
echo             android:parentActivityName=".HistoryActivity" /^>
echo.
echo     ^</application^>
echo.
echo ^</manifest^>
) > "%MANIFEST_PATH%.new"

echo Replacing AndroidManifest.xml with new version...
copy /Y "%MANIFEST_PATH%.new" "%MANIFEST_PATH%"
del "%MANIFEST_PATH%.new"

echo AndroidManifest.xml has been fixed!
echo Original backup saved as %MANIFEST_PATH%.bak
echo. 