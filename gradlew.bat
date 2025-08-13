@echo off
SETLOCAL

SET PRG=%~dpnx0
SET PRGDIR=%~dp0
SET CLASSPATH=%PRGDIR%gradle\wrapper\gradle-wrapper.jar

IF NOT DEFINED JAVA_HOME (
  SET JAVA_EXE=java
) ELSE (
  SET JAVA_EXE=%JAVA_HOME%\bin\java.exe
)

"%JAVA_EXE%" -classpath "%CLASSPATH%" org.gradle.wrapper.GradleWrapperMain %*

ENDLOCAL
