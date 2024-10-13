import java.util.Random;

public class Movement {
    private static Random random = new Random();
    private static boolean useMooreNeighborhood;  // true for Moore, false for Von Neumann

    // Public method to move all animals on the board
    public static void moveAnimals(char[][] board, boolean useMoore) {
        useMooreNeighborhood = useMoore;  // User's choice for movement criterion
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (Animal.isAnimal(board[i][j])) {
                    moveAnimal(board, i, j);
                }
            }
        }
    }

    // Private method that handles moving a single animal
    private static void moveAnimal(char[][] board, int currentRow, int currentCol) {
        int[] newPosition = getNewPosition(board.length, board[0].length, currentRow, currentCol);
        int newRow = newPosition[0];
        int newCol = newPosition[1];
        if (isPlant(board, newRow, newCol)) {
            eatPlant(board, currentRow, currentCol, newRow, newCol);
        } else if (isEmpty(board, newRow, newCol)) {
            moveToEmptySpace(board, currentRow, currentCol, newRow, newCol);
        }
    }

    // Get new position for the animal based on random movement and selected neighborhood
    private static int[] getNewPosition(int rows, int cols, int currentRow, int currentCol) {
        int newRow = currentRow;
        int newCol = currentCol;
        // If using Moore criterion (8 directions)
        if (useMooreNeighborhood) {
            switch (random.nextInt(8)) {  // 0-7 for 8 possible directions
                case 0: newRow = (currentRow - 1 + rows) % rows; break; // up
                case 1: newRow = (currentRow + 1) % rows; break;        // down
                case 2: newCol = (currentCol - 1 + cols) % cols; break; // left
                case 3: newCol = (currentCol + 1) % cols; break;        // right
                case 4: newRow = (currentRow - 1 + rows) % rows;        // up-left diagonal
                    newCol = (currentCol - 1 + cols) % cols; break;
                case 5: newRow = (currentRow - 1 + rows) % rows;        // up-right diagonal
                    newCol = (currentCol + 1) % cols; break;
                case 6: newRow = (currentRow + 1) % rows;               // down-left diagonal
                    newCol = (currentCol - 1 + cols) % cols; break;
                case 7: newRow = (currentRow + 1) % rows;               // down-right diagonal
                    newCol = (currentCol + 1) % cols; break;
            }
        }
        // If using Von Neumann criterion (4 directions)
        else {
            switch (random.nextInt(4)) {  // 0-3 for 4 possible directions
                case 0: newRow = (currentRow - 1 + rows) % rows; break; // up
                case 1: newRow = (currentRow + 1) % rows; break;        // down
                case 2: newCol = (currentCol - 1 + cols) % cols; break; // left
                case 3: newCol = (currentCol + 1) % cols; break;        // right
            }
        }
        return new int[]{newRow, newCol};
    }

    // Check if the destination cell contains a plant
    private static boolean isPlant(char[][] board, int newRow, int newCol) {
        return board[newRow][newCol] == GameBoard.PLANT;
    }

    // Check if the destination cell is empty
    private static boolean isEmpty(char[][] board, int newRow, int newCol) {
        return board[newRow][newCol] == GameBoard.EMPTY;
    }

    // Move the animal to the empty cell
    private static void moveToEmptySpace(char[][] board, int currentRow, int currentCol, int newRow, int newCol) {
        board[newRow][newCol] = board[currentRow][currentCol];
        board[currentRow][currentCol] = GameBoard.EMPTY;
    }

    // Handle the case where the animal eats the plant
    private static void eatPlant(char[][] board, int currentRow, int currentCol, int newRow, int newCol) {
        board[newRow][newCol] = board[currentRow][currentCol];  // Animal moves to the plant cell
        board[currentRow][currentCol] = GameBoard.EMPTY;        // The old position becomes empty
    }
}

/*  VIEJO CODIGO
import java.util.Random;

public class Movement {
    private static Random random = new Random();

    // Public method to move all animals on the board
    public static void moveAnimals(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (Animal.isAnimal(board[i][j])) {
                    moveAnimal(board, i, j);
                }
            }
        }
    }

    // Private method that handles moving a single animal
    private static void moveAnimal(char[][] board, int currentRow, int currentCol) {
        int[] newPosition = getNewPosition(board.length, board[0].length, currentRow, currentCol);
        int newRow = newPosition[0];
        int newCol = newPosition[1];

        if (isPlant(board, newRow, newCol)) {
            eatPlant(board, currentRow, currentCol, newRow, newCol);
        } else if (isEmpty(board, newRow, newCol)) {
            moveToEmptySpace(board, currentRow, currentCol, newRow, newCol);
        }
    }

    // Get new position for the animal based on random movement
    private static int[] getNewPosition(int rows, int cols, int currentRow, int currentCol) {
        int newRow = currentRow;
        int newCol = currentCol;
        // Randomly choose a direction: 0 = up, 1 = down, 2 = left, 3 = right
        switch (random.nextInt(4)) {
            case 0: newRow = (currentRow - 1 + rows) % rows; break; // move up
            case 1: newRow = (currentRow + 1) % rows; break;        // move down
            case 2: newCol = (currentCol - 1 + cols) % cols; break; // move left
            case 3: newCol = (currentCol + 1) % cols; break;        // move right
        }
        return new int[]{newRow, newCol};
    }

    // Check if the destination cell contains a plant
    private static boolean isPlant(char[][] board, int newRow, int newCol) {
        return board[newRow][newCol] == GameBoard.PLANT;
    }

    // Check if the destination cell is empty
    private static boolean isEmpty(char[][] board, int newRow, int newCol) {
        return board[newRow][newCol] == GameBoard.EMPTY;
    }

    // Move the animal to the empty cell
    private static void moveToEmptySpace(char[][] board, int currentRow, int currentCol, int newRow, int newCol) {
        board[newRow][newCol] = board[currentRow][currentCol];
        board[currentRow][currentCol] = GameBoard.EMPTY;
    }

    // Handle the case where the animal eats the plant
    private static void eatPlant(char[][] board, int currentRow, int currentCol, int newRow, int newCol) {
        board[newRow][newCol] = board[currentRow][currentCol];  // Animal moves to the plant cell
        board[currentRow][currentCol] = GameBoard.EMPTY;        // The old position becomes empty
    }
}
 */