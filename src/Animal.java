import java.util.Random;

public class Animal {
    private static final char LION = 'L';
    private static final char GOAT = 'G';
    private static final char DOG = 'O';
    private static final char DUCK = 'D';

    private static final char[] ANIMALS = {LION, GOAT, DOG, DUCK};
    private static Random random = new Random();

    // Get a random animal symbol
    public static char getRandomAnimal() {
        return ANIMALS[random.nextInt(ANIMALS.length)];
    }

    // Check if a character is an animal
    public static boolean isAnimal(char c) {
        for (char animal : ANIMALS) {
            if (animal == c) return true;
        }
        return false;
    }
}
