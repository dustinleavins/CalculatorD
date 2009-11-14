#!/bin/sh
.PHONY: clean jar doc doc-pub doc-priv
COMPILER = javac
CLASS_DIR = class/
FLAGS = -g -Xlint -d $(CLASS_DIR)
SRC = src
PACKAGE = CalculatorD
DOC_DIR = doc/
JAR_FILE = CalculatorD.jar
MAIN_CLASS = $(PACKAGE).Main
default: Main

Main: $(CLASS_DIR)
	$(COMPILER) $(FLAGS) $(SRC)/$(PACKAGE)/*.java

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
