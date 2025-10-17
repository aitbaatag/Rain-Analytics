# Makefile for Maven Java project

# Variables
MVN = mvn
JAR_NAME = etl-service
MAIN_CLASS = com.example.weather.App

# Default target
all: compile

# Compile Java code
compile:
	$(MVN) compile

# Run your application
run:
	$(MVN) compile exec:java -Dexec.mainClass=$(MAIN_CLASS)

# Package into a JAR
package:
	$(MVN) package

# Clean build
clean:
	$(MVN) clean

# Test
test:
	$(MVN) test

# Run JAR if already packaged
run-jar:
	java -cp target/$(JAR_NAME)-1.0-SNAPSHOT.jar $(MAIN_CLASS)
