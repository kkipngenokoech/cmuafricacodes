// this is the name of this package
package model;

// this is my calculator class, with the methods to add, subtract, multiply, divide - it is public so it can be accessed by other classes
public class Calculator {
  // this is the add method, which takes in a variable number of arguments and returns the sum of all the numbers, it is public also, so it can be acccessed by other classes
  public double add(double... numbers) {
    // initialize the sum to 0
    double sum = 0;
    // iterate through the numbers array and add each number to the sum
    for (double number : numbers) {
      sum += number;
    }
    // return the sum of all the numbers
    return sum;
  }
  // this is the subtract method, which takes in a variable number of arguments and returns the difference of all the numbers, it is public also, so it can be acccessed by other classes
  public double subtract(double... numbers) {
    // first we check if the numbers array is empty - if it is, we return 0
    if (numbers.length == 0){
      return 0;
    } else {
      // initialize the result to the first number
      double result = numbers[0];
      // iterate through the numbers array and subtract each number from the result
      for (int i = 1; i < numbers.length; i++) {
        result -= numbers[i];
      }
      // return the result
      return result;
    }
  }
  // this is the multiply method, which takes in a variable number of arguments and returns the product of all the numbers, it is public also, so it can be acccessed by other classes
  public double multiply(double... numbers) {
    // first we check if the numbers array is empty - if it is, we return 0
    if (numbers.length == 0){
      return 0;
    } else {
      // initialize the result to the first number
      double result = numbers[0];
      // iterate through the numbers array and multiply each number by the result
      for (int i = 1; i < numbers.length; i++) {
        result *= numbers[i];
      }
      // return the result
      return result;
    }
  }
  // this is the divide method, which takes in a variable number of arguments and returns the division of all the numbers, it is public also, so it can be acccessed by other classes
  public double divide(double... numbers) throws IllegalArgumentException {
    // first we check if the numbers array is empty, if it is, we return 0
    if (numbers.length == 0){
      return 0;
    } else {
      // initialize the result to the first number in the array
      double result = numbers[0];
      // iterate through the numbers array and divide each number by the result
      for (int i = 1; i < numbers.length; i++) {
        // if the current  number is 0, we throw an IllegalArgumentException since division by zero is not allowed
        if (numbers[i] == 0) {
          throw new IllegalArgumentException("Division by zero is not allowed.");
        }
        //else divide the result by the current number
        result /= numbers[i];
      }
      // return the result
      return result;
    }
  }
  // this is the advanced method, which takes in a string expression and returns the result of the expression, it is public also, so it can be acccessed by other classes
  // @param expression - the expression to evaluate
  public double advanced(String expression) {
    // split the expression into tokens
    String[] tokens = expression.split(" ");
    // initialize the result to the first number in the array
    double result = Double.parseDouble(tokens[0]);
    // iterate through the tokens array and evaluate the expression
    for (int i = 1; i < tokens.length; i += 2) {
      // get the operator - it is always the odd index
      String operator = tokens[i];
      // get the number - it is always the even index
      double number = Double.parseDouble(tokens[i + 1]);
      // switch to handle the operators
      switch (operator) {
        // if the operator is +, add the number to the result
        case "+" -> {
          result += number;
        }
        // if the operator is -, subtract the number from the result
        case "-" -> {
          result -= number;
        }
        // if the operator is *, multiply the number by the result
        case "*" -> {
          result *= number;
        }
        // if the operator is /, divide the result by the number
        case "/" -> {
          // check if the number is 0, if it is, throw an IllegalArgumentException since division by zero is not allowed
          if (number == 0) {
            throw new IllegalArgumentException("Division by zero is not allowed.");
          }
          // else if the number is not 0, then divide the result by the number
          return result / number;
          
        }
        // if the operator is not any of the above, throw an IllegalArgumentException since the operator is unknown
        default -> {
          throw new IllegalArgumentException("Unknown operator: " + operator);
        }
      }
    }
    // return the result
    return result;
  }
}