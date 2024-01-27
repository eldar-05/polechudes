import java.util.Scanner;
import java.util.Random;
public class Main
{
    public static void main (String[]args)
    {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        boolean isGameRunning = true;
        int randomIndexOfWord = random.nextInt(10);
        System.out.print("How many players? 2,3,4... ");
        int howManyPlayers = scanner.nextInt();
        scanner.nextLine();
        String[] namesOfPlayers = new String[howManyPlayers];
        String secretWord = wordChoosed(randomIndexOfWord);     //selected word
        int[] charSlots = new int[secretWord.length()];   //finded slots true or false
        for(int i = 0; i < secretWord.length(); i++){
            charSlots[i] = 0;
        }
        for (int i = 0; i < howManyPlayers; i++){
            if(namesOfPlayers[i] != " "){
                System.out.print("Player " + (i + 1) + ": ");
                namesOfPlayers[i] = scanner.nextLine();
            }
        }
        int maximumScore = getMaxPossibleScore(wordChoosed(randomIndexOfWord));  //max score calculator
        clear();
        while (isGameRunning)
        {
            System.out.println("Hint: " + wordsMeanigsChoosed(randomIndexOfWord));
            for (int i = 0; i < howManyPlayers; i++){
                System.out.println("Player " + (i + 1) + ": " + namesOfPlayers[i]);
            }
            for(int i = 0; i < secretWord.length(); i++){
                if(charSlots[i] == 0){
                    System.out.print("[" + "*" +"]");
                }
                else{
                    System.out.print("[" + secretWord.charAt(i) +"]");
                }
            }
            isGameRunning = false;
        }
    }
    public static String wordChoosed (int randomIndexOfWord){
        String[] words = {"gradient", "examination", "infrastructure", "illusion", "recommendation", "government", "establishment", "performance", "opportunity", "temperature"};
        return words[randomIndexOfWord];
    }
    public static String wordsMeanigsChoosed (int randomIndexOfWord){
        String[] meaningOfWords = {"A measure of steepness", "Thing that you take to show your knowledge", "the system of public works of a country", "The phenomenon in which the properties of an object or image are different from how they appear", "A suggestion that something is good or suitable for a particular purpose", "Creating and enforcing the rules of a society", "A place of business or residence with its furnishings and staff", "An act or process of staging or presenting a play, concert, or other form of entertainment", "A situation or condition favorable for attainment of a goal", "A measure of the average amount of energy of motion, or kinetic energy, a system contains"};
        return meaningOfWords[randomIndexOfWord];
    }
    public static void clear(){
        System.out.print("\033[H\033[2J"); 
    }
    public static int getMaxPossibleScore(String word){
        int max = 1;
        String uniq = "" + word.charAt(0);
        for (int i = 1; i < word.length(); i++) {
            boolean found = false;
            for (int j = 0; j < uniq.length(); j++) {
                if (word.charAt(i) == uniq.charAt(j)) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                uniq += word.charAt(i);
                max++;
            }
        }
        return max * 100;
    }
}
