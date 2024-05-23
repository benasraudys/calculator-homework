# Calculator

## Project Overview

This project is a simple calculator application built using Java Swing. It supports basic arithmetic operations: addition, subtraction, multiplication, and division. The project is designed with OOP principles to allow easy addition of new operations. 

## Author

- Benas Raudys

## Course Information

- **Course**: VU MIF IT OOP
- **Professor**: Irmantas Radavičius

## Development Environment

- **IDE**: IntelliJ IDEA

## Features

- Basic arithmetic operations: addition (+), subtraction (-), multiplication (*), and division (/).
- Clear (C) button to reset the calculator.
- Equality (=) button to compute the result.
- Easy addition of new operations by implementing the `Operation` interface.

## Structure

- **Operation Interface**: Defines the `apply` method for arithmetic operations.
- **Operation Classes**: Implementations of the `Operation` interface (`Addition`, `Subtraction`, `Multiplication`, `Division`).
- **Calculator Class**: The main class that sets up the GUI and handles user interactions.

## How to Run

1. Clone the repository to your local machine.
2. Open the project in IntelliJ IDEA.
3. Run the `Main` class.

## Adding New Operations

To add a new operation:

1. Create a new class that implements the `Operation` interface.
2. Implement the `apply` method in the new class.
3. Add the new operation to the `operations` map in the `Calculator` constructor.

### Example

To add a modulus operation:

1. Create a new class `Modulus`:

```java
public class Modulus implements Operation {
    public double apply(double a, double b) {
        return a % b;
    }
}
```

2. Add the new operation to the `Calculator` class:

```java
operations.put("%", new Modulus());
```

3. Update the `buttons` array to include the "%" button:

```java
String[] buttons = {
    "7", "8", "9", "/",
    "4", "5", "6", "*",
    "1", "2", "3", "-",
    "0", "C", "=", "+",
    "%"
};
```

## License

This project is for educational purposes and does not have a specific license.

## Project Status

This is a homework project and is not actively maintained.
