#!/bin/sh
.PHONY: clean jar doc doc-pub doc-priv
COMPILER = javac
CLASS_DIR = class/
TEST_DIR = test
FLAGS = -g -Xlint -d $(CLASS_DIR)
SRC = src
PACKAGE = CalculatorD
TEST_PACKAGE = Test
DOC_DIR = doc/
JAR_FILE = CalculatorD.jar
MAIN_CLASS = $(PACKAGE).Main
JUNIT_PATH = /usr/share/java
default: Main

Main: $(CLASS_DIR)
	$(COMPILER) $(FLAGS) $(SRC)/$(PACKAGE)/*.java

build-test: Main
	$(COMPILER) $(FLAGS) -cp $(JUNIT_PATH)/junit4.jar:$(CLASS_DIR) $(TEST_DIR)/$(PACKAGE)/$(TEST_PACKAGE)/*.java

$(CLASS_DIR):
	mkdir class

clean:
	rm -rf $(CLASS_DIR)
	rm -rf $(DOC_DIR)
	rm -rf CalculatorOptions.cfg
	rm -rf $(JAR_FILE)
jar: Main
	jar -cfe $(JAR_FILE) $(MAIN_CLASS) -C $(CLASS_DIR) .


doc-pub:
	mkdir $(DOC_DIR) || true
	javadoc -d $(DOC_DIR) -sourcepath $(SRC) $(PACKAGE)

doc: doc-pub

doc-priv:
	mkdir $(DOC_DIR) || true
	javadoc -private -d $(DOC_DIR) -sourcepath $(SRC) $(PACKAGE)
