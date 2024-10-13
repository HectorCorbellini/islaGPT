import java.util.Scanner;

public class TableGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose movement criterion (1: Von Neumann, 2: Moore): ");
        int choice = scanner.nextInt();
        boolean useMoore = (choice == 2);

        GameBoard gameBoard = new GameBoard(10, 20, 0.6);
        gameBoard.populateBoard();
        gameBoard.startGame(10, useMoore);
    }
}
