import java.util.Random;

public class TableGame {
    static final int ROWS = 10;
    static final int COLS = 20;
    static final int MAX_ITERATIONS = 10;
    static final double MAX_OCCUPIED_PERCENTAGE = 0.6;
    static final int MAX_OCCUPIED_CELLS = (int)(ROWS * COLS * MAX_OCCUPIED_PERCENTAGE);

    // Unicode symbols for animals and plants
    static final char LION = 'L';  //  '\uD83E\uDD81';   🦁
    static final char GOAT = 'G';  //  '\uD83D\uDC10';   🐐
    static final char DOG = 'O';  //  '\uD83D\uDC15';    🐕
    static final char DUCK = 'D';  //  '\uD83D\uDC0D';  🐍
    static final char PLANT = '&';  //  '\uD83C\uDF31';  🌱
    static final char EMPTY = '.';         // empty space

    static Random random = new Random();

    public static void main(String[] args) {
        char[][] table = new char[ROWS][COLS];
        int occupiedCells = 0;

        // Initialize the table with empty spaces
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                table[i][j] = EMPTY;
            }
        }

        // Place animals and plants randomly, ensuring not more than 60% of the table is occupied
        while (occupiedCells < MAX_OCCUPIED_CELLS) {
            int row = random.nextInt(ROWS);
            int col = random.nextInt(COLS);

            if (table[row][col] == EMPTY) {
                char object = random.nextBoolean() ? getRandomAnimal() : PLANT;
                table[row][col] = object;
                occupiedCells++;
            }
        }

        // Run the game for a fixed number of iterations
        for (int iteration = 0; iteration < MAX_ITERATIONS; iteration++) {
            System.out.println("Iteration " + (iteration + 1));
            printTable(table);
            moveAnimals(table);
        }

        System.out.println("Game over after " + MAX_ITERATIONS + " iterations.");
    }

    // Get a random animal symbol
    private static char getRandomAnimal() {
        char[] animals = {LION, GOAT, DOG, DUCK};
        return animals[random.nextInt(animals.length)];
    }

    // Move all animals on the table
    private static void moveAnimals(char[][] table) {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (isAnimal(table[i][j])) {
                    int newRow = i;
                    int newCol = j;
                    // Move the animal to a random adjacent cell (up, down, left, right)
                    switch (random.nextInt(4)) {
                        case 0: newRow = (i - 1 + ROWS) % ROWS; break; // move up
                        case 1: newRow = (i + 1) % ROWS; break;         // move down
                        case 2: newCol = (j - 1 + COLS) % COLS; break; // move left
                        case 3: newCol = (j + 1) % COLS; break;        // move right
                    }

                    // If the animal encounters a plant, eat it (replace with empty)
                    if (table[newRow][newCol] == PLANT) {
                        table[newRow][newCol] = table[i][j];
                        table[i][j] = EMPTY;
                    } else if (table[newRow][newCol] == EMPTY) {
                        // Move the animal to the new cell
                        table[newRow][newCol] = table[i][j];
                        table[i][j] = EMPTY;
                    }
                }
            }
        }
    }

    // Check if the character is an animal
    private static boolean isAnimal(char c) {
        return c == LION || c == GOAT || c == DOG || c == DUCK;
    }

    // Print the table to the console
    private static void printTable(char[][] table) {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                System.out.print(table[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
