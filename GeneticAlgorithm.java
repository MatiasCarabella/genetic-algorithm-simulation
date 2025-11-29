import java.util.Random;

/**
 * This class implements a simple genetic algorithm to evolve a binary string.
 * The goal is to reach a 20-digit binary string with the maximum possible value.
 * 
 * Note: This implementation uses an XNOR-based crossover operator (produces 1 when bits match,
 * 0 when they differ), which biases evolution toward strings of all 1's.
 */
public class GeneticAlgorithm {
    // Algorithm constants
    private static final int STRING_LENGTH = 20; // Length of the binary string
    private static final int FITNESS_MULTIPLIER = 2; // Multiplier for fitness calculation
    private static final int TARGET = STRING_LENGTH * FITNESS_MULTIPLIER; // Maximum possible value (all 1's)
    
    private final Random random;

    /**
     * Constructs a GeneticAlgorithm with a default random seed.
     */
    public GeneticAlgorithm() {
        this.random = new Random();
    }

    /**
     * Constructs a GeneticAlgorithm with a specific seed for reproducibility.
     * @param seed The seed for the random number generator.
     */
    public GeneticAlgorithm(long seed) {
        this.random = new Random(seed);
    }

    public static void main(String[] args) {
        GeneticAlgorithm ga = new GeneticAlgorithm();
        ga.run();
    }

    /**
     * Runs the genetic algorithm until the target fitness is reached.
     */
    public void run() {
        // Generate the initial random string
        int[] mainString = generateRandomString();
        int iterations = 0;
        int fitness = evaluateFitness(mainString);

        System.out.println("Initial string: " + binaryStringToString(mainString) + " Fitness: " + fitness);

        // Main loop of the genetic algorithm
        while (fitness < TARGET) {
            iterations++;
            
            // Generate a second random string for crossover
            int[] secondString = generateRandomString();
            
            // Perform crossover between the main string and the second string
            int[] crossedString = crossStrings(mainString, secondString);
            
            // Evaluate the fitness of the new string
            int newFitness = evaluateFitness(crossedString);

            // If the new string is fitter, it replaces the main string
            if (newFitness > fitness) {
                mainString = crossedString;
                fitness = newFitness;
                System.out.println("New fit string: " + binaryStringToString(mainString) + " Fitness: " + fitness);
            }
        }

        // Display final results
        System.out.println("Optimal solution found after " + iterations + " iterations.");
        System.out.println("Optimal string: " + binaryStringToString(mainString) + " Fitness: " + fitness);
    }

    /**
     * Generates a random binary string of length STRING_LENGTH.
     * @return An array of integers representing the binary string.
     */
    private int[] generateRandomString() {
        int[] binaryString = new int[STRING_LENGTH];
        for (int i = 0; i < STRING_LENGTH; i++) {
            binaryString[i] = random.nextInt(2); // Generates 0 or 1 randomly
        }
        return binaryString;
    }

    /**
     * Evaluates the fitness of a binary string.
     * Fitness is calculated as the sum of the bits multiplied by FITNESS_MULTIPLIER.
     * @param binaryString The binary string to evaluate.
     * @return The fitness value of the string.
     * @throws IllegalArgumentException if the string length doesn't match STRING_LENGTH.
     */
    private int evaluateFitness(int[] binaryString) {
        validateStringLength(binaryString);
        
        int totalFitness = 0;
        for (int bit : binaryString) {
            totalFitness += bit * FITNESS_MULTIPLIER;
        }
        return totalFitness;
    }

    /**
     * Performs crossover between two binary strings using an XNOR operation.
     * If the bits at the same position are equal, the result is 1; otherwise, it is 0.
     * This crossover method biases evolution toward strings of all 1's.
     * @param string1 First string for crossover.
     * @param string2 Second string for crossover.
     * @return A new string resulting from the crossover.
     * @throws IllegalArgumentException if either string length doesn't match STRING_LENGTH.
     */
    private int[] crossStrings(int[] string1, int[] string2) {
        validateStringLength(string1);
        validateStringLength(string2);
        
        int[] crossedString = new int[STRING_LENGTH];
        for (int i = 0; i < STRING_LENGTH; i++) {
            crossedString[i] = (string1[i] == string2[i]) ? 1 : 0;
        }
        return crossedString;
    }

    /**
     * Validates that a binary string has the correct length.
     * @param binaryString The binary string to validate.
     * @throws IllegalArgumentException if the string length doesn't match STRING_LENGTH.
     */
    private void validateStringLength(int[] binaryString) {
        if (binaryString == null || binaryString.length != STRING_LENGTH) {
            throw new IllegalArgumentException(
                "Binary string must have length " + STRING_LENGTH + ", but was " + 
                (binaryString == null ? "null" : binaryString.length)
            );
        }
    }

    /**
     * Converts an integer array representing a binary string to a text string.
     * @param binaryString The integer array to convert.
     * @return A text string representation of the binary string.
     */
    private String binaryStringToString(int[] binaryString) {
        StringBuilder sb = new StringBuilder();
        for (int bit : binaryString) {
            sb.append(bit).append(" ");
        }
        return sb.toString().trim();
    }
}
