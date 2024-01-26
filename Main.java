import java.util.Scanner;
import java.util.Random;
public class Main
{
    public static void main (String[]args)
    {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        boolean gameIsRunning = true;
        int randomIndexOfWord = random.nextInt(9);
        clear();
        while (gameIsRunning)
        {
            System.out.print("Hint: " + wordsMeanigsChoosed(randomIndexOfWord));
            gameIsRunning = false;
        }
    }
    public static String wordChoosed (int randomIndexOfWord){
        String[] words = {"gradient", "examination", "infrastructure", "illusion", "recommendation", "government", "establishment", "performance", "opportunity", "temperature"};
        return words[randomIndexOfWord];
    }
    public static String wordsMeanigsChoosed (int randomIndexOfWord){
        String[] meaningOfWord = {"A measure of steepness", "Thing that you take to show your knowledge", "the system of public works of a country", "The phenomenon in which the properties of an object or image are different from how they appear", "A suggestion that something is good or suitable for a particular purpose", "Creating and enforcing the rules of a society", "A place of business or residence with its furnishings and staff", "An act or process of staging or presenting a play, concert, or other form of entertainment", "A situation or condition favorable for attainment of a goal", "A measure of the average amount of energy of motion, or kinetic energy, a system contains"};
        return meaningOfWord[randomIndexOfWord];
    }
    public static void clear(){
        System.out.println("\033[H\033[2J");
    }
}