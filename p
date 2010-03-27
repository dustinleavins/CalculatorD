Calculator_Project=~/CalculatorD CD=. {
makefile
build.xml

doc {
  README
  LICENSE
}

src=src/CalculatorD filter="*.java" {
  Calculation.java
  CalculationItem.java
  CalculationNumber.java
  CalculationOperation.java
  CalculatorJFrame.java
  CalculatorTextField.java
  Fraction.java
  GUIOptions.java
  Main.java
  Operation.java
  OptionPrompt.java
  OptionsFileManager.java
}

test=test/CalculatorD/Test filter="*.java" {
  TestCalculation.java
  TestFraction.java
}
}
