@if "%DEBUG%" == "" @echo off
@rem ##########################################################################
@rem  Gradle startup script for Windows
@rem ##########################################################################

set DIRNAME=%~dp0
if "%DIRNAME%" == "" set DIRNAME=.
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%

set DEFAULT_JVM_OPTS="-Xmx64m" "-Xms64m"

set JAVA_EXE=java.exe
%JAVA_EXE% -version >NUL 2>&1
if "%ERRORLEVEL%" == "0" goto execute

echo ERROR: JAVA_HOME is not set
goto fail

:execute
set CLASSPATH=%APP_HOME%\gradle\wrapper\gradle-wrapper.jar

"%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %GRADLE_OPTS% "-Dorg.gradle.appname=%APP_BASE_NAME%" -classpath "%CLASSPATH%" org.gradle.wrapper.GradleWrapperMain %*

:end
if "%ERRORLEVEL%"=="0" goto mainEnd

:fail
exit /b 1

:mainEnd
if "%OS%"=="Windows_NT" endlocal
