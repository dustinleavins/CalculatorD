#!/bin/sh
.PHONY: clean jar backup doc doc-pub doc-priv
COMPILER = javac
CLASS_FOLDER = class/
FLAGS = -g -Xlint -d $(CLASS_FOLDER)
SRC_DIRECTORY = src/CalculatorD/
PACKAGE = CalculatorD
MANIFEST = Manifest.txt
DOC_DIR = doc/
JAR_FILE = Calculator.jar
MAIN_CLASS = $(PACKAGE).Main
default: Main

#Main: class/
Main: $(CLASS_FOLDER)
	$(COMPILER) $(FLAGS) $(SRC_DIRECTORY)*.java

#class/:
$(CLASS_FOLDER):
	mkdir class

clean:
	rm -rf $(CLASS_FOLDER)
	rm -rf $(DOC_DIR)
	rm -rf CalculatorOptions.cfg
	rm -rf $(JAR_FILE)
jar: Main
	jar -cfe $(JAR_FILE) $(MAIN_CLASS) -C $(CLASS_FOLDER) .

doc: doc-pub

doc-pub:
	mkdir $(DOC_DIR) || true
	javadoc -d $(DOC_DIR) $(PACKAGE)


doc-priv:
	mkdir $(DOC_DIR) || true
	javadoc -private -d $(DOC_DIR) $(PACKAGE)


backup:  clean
	rsync -vur --delete . /home/dustinl/Dropbox/Calculator
