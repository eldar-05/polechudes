import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);
        Random randomGenerator = new Random();
        boolean isGameActive = true;
        boolean isFinalGameStarted = false;
        int randomWordIndex = randomGenerator.nextInt(10);
        System.out.print("How many players? 2, 3, 4... ");
        int numberOfPlayers = inputScanner.nextInt();
        String winnerName = "";
        String[] playerNames = new String[numberOfPlayers];
        int[] playerScores = new int[numberOfPlayers];   // Score data
        String secretWord = chooseWord(randomWordIndex);     // Selected word
        int[] charSlots = new int[secretWord.length()];   // Found slots (true or false)
        for (int i = 0; i < secretWord.length(); i++) {
            charSlots[i] = 0;
        }
        inputScanner.nextLine();
        for (int i = 0; i < numberOfPlayers; i++) {
            playerScores[i] = 0;
            System.out.print("Player " + (i + 1) + ": ");
            playerNames[i] = inputScanner.nextLine();
        }
        // Randomly shuffling the numeration of players
        for (int i = 0; i < playerNames.length; i++) {
            int randomIndexToSwap = randomGenerator.nextInt(playerNames.length);
            String temp = playerNames[randomIndexToSwap];
            playerNames[randomIndexToSwap] = playerNames[i];
            playerNames[i] = temp;
        }
        int maxPossibleScore = getMaxPossibleScore(chooseWord(randomWordIndex));  // Max score calculator
        clear();
        while (isGameActive) {
            for (int currentPlayerIndex = 0; currentPlayerIndex < numberOfPlayers; currentPlayerIndex++) {
                boolean allCharsFound = false;
                clear();
                if (playerScores[currentPlayerIndex] == -1) {
                    continue;
                }
                for (int i = 0; i < numberOfPlayers; i++) {
                    if (playerScores[i] > maxPossibleScore / 2) {
                        isFinalGameStarted = true;
                        winnerName = playerNames[currentPlayerIndex];
                        break;
                    }
                }
                if (isFinalGameStarted) {
                    isGameActive = false;
                    break;
                }
                System.out.println("Hint: " + chooseWordMeaning(randomWordIndex));
                for (int i = 0; i < numberOfPlayers; i++) {
                    if (playerScores[i] > -1) {
                        System.out.println("Player: " + playerNames[i] + " " + playerScores[i] + " points");
                    }
                }
                System.out.println();
                for (int i = 0; i < secretWord.length(); i++) {
                    if (charSlots[i] == 0) {
                        System.out.print("[*]");
                    }
                    else {
                        System.out.print("[" + secretWord.charAt(i) + "]");
                    }
                }
                System.out.println("\n" + playerNames[currentPlayerIndex] + " is guessing...");
                System.out.print("Write letter or word: ");
                String guessing = inputScanner.nextLine();
                if (guessing.length() == 1) {
                    boolean giveScore = false;
                    for (int i = 0; i < secretWord.length(); i++) {
                        if (guessing.charAt(0) == secretWord.charAt(i) && charSlots[i] == 0) {
                            charSlots[i] = 1;
                            giveScore = true;
                        }
                    }
                    if (giveScore) {
                        playerScores[currentPlayerIndex] += 100;
                        currentPlayerIndex--;
                    }
                    int charsFound = 0;
                    for (int i = 0; i < secretWord.length(); i++) {
                        if (charSlots[i] == 1) {
                            charsFound++;
                        }
                    }
                    if (charsFound >= secretWord.length()) {
                        for (int i = 0; i < numberOfPlayers - 1; i++) {
                            if (playerScores[i] < playerScores[i + 1]) {
                                winnerName = playerNames[i + 1];
                            }
                        }
                        isGameActive = false;
                        break;
                    }
                }
                else {
                    if (guessing.equals(secretWord)) {
                        for (int i = 0; i < secretWord.length(); i++) {
                            charSlots[i] = 1;
                        }
                        playerScores[currentPlayerIndex] = maxPossibleScore;
                        isGameActive = false;
                        break;
                    }
                    else {
                        playerScores[currentPlayerIndex] = -1;
                        int failedPlayers = 0;
                        int num = -1;
                        for (int i = 0; i < numberOfPlayers; i++) {
                            if (playerScores[i] == -1) {
                                failedPlayers++;
                            } else {
                                num = i;
                            }
                        }
                        if (failedPlayers >= numberOfPlayers - 1) {
                            winnerName = playerNames[num];
                            isGameActive = false;
                            break;
                        }
                    }
                }
            }
        }
        clear();
        if (isFinalGameStarted) {
            for (int i = 0; i < numberOfPlayers; i++) {
                if (playerScores[i] > -1 && !(playerNames[i]).equals(winnerName)) {
                    System.out.println("Hint: " + chooseWordMeaning(randomWordIndex) + " " + winnerName);
                    for (int j = 0; j < secretWord.length(); j++) {
                        if (charSlots[j] == 0) {
                            System.out.print("[*]");
                        }
                        else {
                            System.out.print("[" + secretWord.charAt(j) + "]");
                        }
                    }
                    System.out.print("\n" + playerNames[i] + " is guessing full word: ");
                    String fullWordGuessing = inputScanner.nextLine();
                    if (fullWordGuessing.equals(secretWord)) {
                        playerScores[i] = maxPossibleScore;
                        winnerName = playerNames[i];
                        break;
                    }
                    else {
                        playerScores[i] = -1;
                    }
                    clear();
                }
            }
        }
        for (int i = 0; i < numberOfPlayers; i++) {
            if (playerScores[i] == maxPossibleScore || playerNames[i].equals(winnerName)) {
                for (int j = 0; j < numberOfPlayers; j++) {
                    playerScores[j] = -1;
                }
                System.out.println(playerNames[i] + " is the winner. Congrats!!!");
                System.out.println("The secret word was " + secretWord);
            }
        }
    }

    public static String chooseWord(int randomWordIndex) {
        String[] words = {"gradient", "examination", "infrastructure", "illusion", "recommendation", "government", "establishment", "performance", "opportunity", "temperature"};
        return words[randomWordIndex];
    }

    public static String chooseWordMeaning(int randomWordIndex) {
        String[] wordMeanings = {"A measure of steepness", "Thing that you take to show your knowledge", "the system of public works of a country", "The phenomenon in which the properties of an object or image are different from how they appear", "A suggestion that something is good or suitable for a particular purpose", "Creating and enforcing the rules of a society", "A place of business or residence with its furnishings and staff", "An act or process of staging or presenting a play, concert, or other form of entertainment", "A situation or condition favorable for attainment of a goal", "A measure of the average amount of energy of motion, or kinetic energy, a system contains"};
        return wordMeanings[randomWordIndex];
    }

    public static void clear() {
        System.out.print("\033[H\033[2J");
    }

    public static int getMaxPossibleScore(String word) {
        int maxScore = 1;
        String uniqueChars = "" + word.charAt(0);
        for (int i = 1; i < word.length(); i++) {
            boolean found = false;
            for (int j = 0; j < uniqueChars.length(); j++) {
                if (word.charAt(i) == uniqueChars.charAt(j)) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                uniqueChars += word.charAt(i);
                maxScore++;
            }
        }
        return maxScore * 100;
    }
}
