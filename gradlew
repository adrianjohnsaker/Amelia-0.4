#!/usr/bin/env sh

##############################################################################
# Gradle wrapper script for Unix
##############################################################################

DIRNAME=`dirname "$0"`
APP_BASE_NAME=`basename "$0"`
APP_HOME=$DIRNAME

# Add default JVM options
DEFAULT_JVM_OPTS='"-Xmx64m" "-Xms64m"'

# Find java
if [ -n "$JAVA_HOME" ] ; then
    JAVACMD="$JAVA_HOME/bin/java"
else
    JAVACMD="java"
fi

# Start Gradle
exec "$JAVACMD" $DEFAULT_JVM_OPTS $JAVA_OPTS $GRADLE_OPTS \
    -classpath "$APP_HOME/gradle/wrapper/gradle-wrapper.jar" \
    org.gradle.wrapper.GradleWrapperMain "$@"
