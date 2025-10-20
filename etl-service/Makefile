
# Makefile for development (fast iteration)

# Variables
MVN = mvn
MAIN_CLASS = com.example.weather.App

# Default target: compile and run
run: 
	$(MVN) compile exec:java -Dexec.mainClass=$(MAIN_CLASS) -DskipTests
# Run tests
test:
	$(MVN) test

# Clean build
clean:
	$(MVN) clean
