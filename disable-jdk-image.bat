@echo off
echo Completely disabling JDK Image Transform...

set "GRADLE_PROPERTIES=gradle.properties"
set "APP_GRADLE=app\build.gradle"

echo Updating gradle.properties...
(
echo # Project-wide Gradle settings.
echo # IDE (e.g. Android Studio) users:
echo # Gradle settings configured through the IDE *will override*
echo # any settings specified in this file.
echo.
echo # For more details on how to configure your build environment visit
echo # http://www.gradle.org/docs/current/userguide/build_environment.html
echo.
echo # Specifies the JVM arguments used for the daemon process.
echo # The setting is particularly useful for tweaking memory settings.
echo org.gradle.jvmargs=-Xmx2048m -Dfile.encoding=UTF-8 -Dandroid.disable.jdkImageTransform=true
echo.
echo # When configured, Gradle will run in incubating parallel mode.
echo # This option should only be used with decoupled projects. More details, visit
echo # http://www.gradle.org/docs/current/userguide/multi_project_builds.html#sec:decoupled_projects
echo org.gradle.parallel=true
echo.
echo # AndroidX package structure to make it clearer which packages are bundled with the
echo # Android operating system, and which are packaged with your app's APK
echo # https://developer.android.com/topic/libraries/support-library/androidx-rn
echo android.useAndroidX=true
echo # Automatically convert third-party libraries to use AndroidX
echo android.enableJetifier=true
echo.
echo # Disable incremental compilation to avoid build issues
echo org.gradle.daemon=true
echo org.gradle.configureondemand=true
echo.
echo # Force Gradle to use JDK 17 compatibility
echo org.gradle.java.home=C:\\Program Files\\Java\\jdk-17
echo.
echo # Completely disable JDK Image Transform
echo android.disable.jdkImageTransform=true
echo android.enableJdkImageTransform=false
echo android.androidJdkImage=false
) > %GRADLE_PROPERTIES%.new
copy /Y %GRADLE_PROPERTIES%.new %GRADLE_PROPERTIES%
del %GRADLE_PROPERTIES%.new

echo Updating app build.gradle...
powershell -Command "(Get-Content '%APP_GRADLE%') -replace 'options.bootstrapClasspath = files\(\)', 'options.bootstrapClasspath = files()' | Set-Content '%APP_GRADLE%.tmp1'"

echo Adding additional properties to disable JDK image transform...
powershell -Command "(Get-Content '%APP_GRADLE%.tmp1') -replace '// Explicitly disable jdkImageTransform', '// Explicitly disable jdkImageTransform in multiple ways' | Set-Content '%APP_GRADLE%.tmp2'"

echo Adding system properties...
powershell -Command "(Get-Content '%APP_GRADLE%.tmp2') -replace 'options.forkOptions.jvmArgs \<\< ''-Dandroid.disable.jdkImageTransform=true''', 'options.forkOptions.jvmArgs << ''-Dandroid.disable.jdkImageTransform=true''\r\n    options.forkOptions.jvmArgs << ''-Dandroid.enableJdkImageTransform=false''\r\n    options.forkOptions.jvmArgs << ''-Djdk.image.use=false''\r\n    options.compilerArgs << ''-Djdk.image.use=false''\r\n    options.compilerArgs << ''-Dandroid.disable.jdkImageTransform=true''' | Set-Content '%APP_GRADLE%'"

del %APP_GRADLE%.tmp1
del %APP_GRADLE%.tmp2

echo Creating alternative compile script...
(
echo @echo off
echo echo Running alternative compilation without JDK image transform...
echo.
echo rem Save the current JAVA_HOME
echo set "ORIGINAL_JAVA_HOME=%%JAVA_HOME%%"
echo.
echo rem Set JAVA_HOME to JDK 17
echo set "JAVA_HOME=C:\Program Files\Java\jdk-17"
echo.
echo rem Clean Gradle cache
echo rd /s /q ".gradle" 2^>nul
echo.
echo echo Using direct javac compilation...
echo.
echo call gradlew.bat --stop
echo call gradlew.bat clean -Dandroid.disable.jdkImageTransform=true -Djdk.image.use=false -Dandroid.enableJdkImageTransform=false --warning-mode all
echo call gradlew.bat assembleDebug -Dandroid.disable.jdkImageTransform=true -Djdk.image.use=false -Dandroid.enableJdkImageTransform=false --warning-mode all --stacktrace
echo.
echo rem Restore original JAVA_HOME
echo set "JAVA_HOME=%%ORIGINAL_JAVA_HOME%%"
echo.
echo echo Alternative build completed.
) > alt-build.bat

echo Files have been updated.
echo Running clean script to clear caches...
call .\clean-cache.bat

echo Now run alt-build.bat to build the project with JDK image transform completely disabled. 