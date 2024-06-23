# Genetic Algorithm Simulation

A Java implementation of a genetic algorithm that simulates the evolution of binary strings. This project demonstrates the basic principles of genetic algorithms, including random generation, fitness evaluation, and crossover operations. The simulation aims to evolve a string of 20 binary digits to reach an optimal solution, showcasing the power of evolutionary computation in optimization problems.

## Features
- Random generation of binary strings
- Fitness evaluation based on a simple summation function
- Crossover operation between strings
- Iteration tracking to reach the optimal solution
- Console output of each improvement in the evolution process

## Getting Started
### Prerequisites

Java Development Kit (JDK) 8 or higher

### Installation

1. Clone the repository:
  ```
  git clone https://github.com/MatiasCarabella/genetic-algorithm-simulation.git
  ```
2. Navigate to the project directory:
  ```
  cd genetic-algorithm-simulation
  ```

### Usage

1. Compile the Java file:
  ```
  javac AlgoritmoGenetico.java
  ```
2. Run the program:
  ```
  java AlgoritmoGenetico
  ```

## How it works

The program follows these steps:
- Generates an initial random binary string of 20 digits.
- Evaluates the fitness of the string using the function H(x) = ∑ x*2.
- Generates a second random string and performs a crossover operation.
- If the new string has a higher fitness, it becomes the new primary string.
- Repeats steps 3-4 until the optimal solution (all 1's) is reached.

The program outputs each improvement in the evolution process and the total number of iterations needed to reach the optimal solution.

## License
This project is licensed under the MIT License - see the [LICENSE](https://github.com/MatiasCarabella/genetic-algorithm-simulation/blob/main/LICENSE) file for details.

## Acknowledgments
- This project serves as an educational tool for understanding genetic algorithms and their application in solving optimization problems.
- Inspired by the principles of evolutionary computation and natural selection.
