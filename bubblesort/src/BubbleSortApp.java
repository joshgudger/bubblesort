import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class BubbleSortApp {

    // Generate an array of random integers between 0 and 100
    public static int[] createRandomArray(int arrayLength) {
        Random random = new Random();
        int[] array = new int[arrayLength];
        for (int i = 0; i < arrayLength; i++) {
            array[i] = random.nextInt(101); // Integers between 0 and 100
        }
        return array;
    }

    // Write the array to a file, one integer per line
    public static void writeArrayToFile(int[] array, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (int num : array) {
                writer.write(num + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    // Read integers from a file into an array
    public static int[] readFileToArray(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            return reader.lines().mapToInt(Integer::parseInt).toArray();
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return new int[0]; // Return empty array on failure
        }
    }

    // Bubble Sort implementation (in-place sorting)
    public static void bubbleSort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    // Swap array[j] and array[j+1]
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    // Main function for user input and interaction
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose an option:");
        System.out.println("1. Generate a random array and store it in a file");
        System.out.println("2. Read an existing file, sort the integers, and store in a new file");

        int option = scanner.nextInt();

        if (option == 1) {
            System.out.print("Enter the length of the random array: ");
            int length = scanner.nextInt();
            System.out.print("Enter the filename to store the array: ");
            String filename = scanner.next();

            int[] randomArray = createRandomArray(length);
            writeArrayToFile(randomArray, filename);
            System.out.println("Random array created and stored in " + filename);

        } else if (option == 2) {
            System.out.print("Enter the filename to read from: ");
            String inputFilename = scanner.next();
            System.out.print("Enter the filename to store the sorted array: ");
            String outputFilename = scanner.next();

            int[] array = readFileToArray(inputFilename);
            if (array.length == 0) {
                System.out.println("Failed to read file or file is empty.");
            } else {
                bubbleSort(array);
                writeArrayToFile(array, outputFilename);
                System.out.println("Array sorted and stored in " + outputFilename);
            }

        } else {
            System.out.println("Invalid option selected.");
        }

        scanner.close();
    }
}
