import java.util.Random;

public class GameBoard {
    static final char EMPTY = '.';
    static final char PLANT = '&';
    private char[][] board;
    private int rows, cols, maxOccupiedCells;
    private int occupiedCells = 0;
    private Random random = new Random();

    public GameBoard(int rows, int cols, double maxOccupiedPercentage) {
        this.rows = rows;
        this.cols = cols;
        this.maxOccupiedCells = (int) (rows * cols * maxOccupiedPercentage);
        this.board = new char[rows][cols];
        initializeBoard();
    }

    // Initialize the table with empty spaces
    private void initializeBoard() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                board[i][j] = EMPTY;
            }
        }
    }

    // Populates the board with random animals and plants
    public void populateBoard() {
        while (occupiedCells < maxOccupiedCells) {
            int row = random.nextInt(rows);
            int col = random.nextInt(cols);

            if (board[row][col] == EMPTY) {
                char object = random.nextBoolean() ? Animal.getRandomAnimal() : PLANT;
                board[row][col] = object;
                occupiedCells++;
            }
        }
    }

    // Starts the game with a given number of iterations
    public void startGame(int maxIterations, boolean useMooreNeighborhood) {
        for (int iteration = 0; iteration < maxIterations; iteration++) {
            System.out.println("Iteration " + (iteration + 1));
            printBoard();
            Movement.moveAnimals(board, useMooreNeighborhood);
        }
        System.out.println("Game over after " + maxIterations + " iterations.");
    }

    // Prints the current state of the board
    public void printBoard() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
