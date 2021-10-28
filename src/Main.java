import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    // Classes
    static Scanner scan = new Scanner(System.in);
    static Word word;
    static Player player1;
    static HangMan newGame;
    static GameState game;

    static String killScan;

    //Console Interface methods
    static void printWelcomeScreen(){

        System.out.println("    |=================================|");
        System.out.println("    |========= H A N G M A N =========|");
        System.out.println("    |=================================|");
        System.out.println("    ||               |               ||");
        System.out.println("    ||               |               ||");
        System.out.println("    ||               0               ||");
        System.out.println("    ||              /|\\              ||");
        System.out.println("    ||              / \\              ||");
        System.out.println("____||_______________________________||____");
        System.out.println("               WELCOME " + player1.getName().toUpperCase());
        System.out.println();
    }

    static void mainMenu() throws FileNotFoundException {

        System.out.print("Please enter your name: ");
        String name = scan.nextLine();
        player1 = new Player(name, 1);

        boolean run = true;
        while (run){

            printWelcomeScreen();

            System.out.println("    1) S I N G L E   P L A Y E R");
            System.out.println("    2) T W O   P L A Y E R");
            System.out.println("    3) L O A D   G A M E");
            System.out.println("    4) E X I T");
            System.out.println();

            int choice = validateUserIntInput(1, 4);

            switch (choice){
                case 1:
                    singlePlayerMenu();
                    break;
                case 2:
                    twoPlayerMenu();
                    break;
                case 3:
                    System.out.println("DID NOT GET THIS TO WORK :(");
                    continueSavedGame();
                    break;
                case 4:
                    System.out.println("Bye bye now..");
                    run = false;
                    break;
            }
        }
    }

    static void singlePlayerMenu() throws FileNotFoundException {

        System.out.println("------------------------------------------------");
        System.out.println("            S I N G L E   P L A Y E R           ");
        System.out.println("------------------------------------------------");
        System.out.println( "In single player you have points\n" +
                            "To guess a wovel it costs 1 point\n" +
                            "You earn one point per rigth letter you guess\n" +
                            "You start of with 1 point");
        System.out.println("------------------------------------------------");
        System.out.println();
        boolean run = true;
        while (run) {
        System.out.println("    1) E A S Y ");
        System.out.println("    2) H A R D ");
        System.out.println("    3) M A S T E R ");
        System.out.println("    4) G O   B A C K   T O   M E N U ");

        int choice = validateUserIntInput(1, 4);
            switch (choice){
                case 1:
                    word = new Word(4);
                    createNewGame(false);
                    killScan = scan.nextLine();
                    runGame();
                    break;
                case 2:
                    word = new Word(7);
                    createNewGame(false);
                    killScan = scan.nextLine();
                    runGame();
                    break;
                case 3:
                    word = new Word(9);
                    createNewGame(false);
                    killScan = scan.nextLine();
                    runGame();
                    break;
                case 4:
                    run = false;
                    break;
            }
        }
    }

    static void twoPlayerMenu() throws FileNotFoundException {

        System.out.println("------------------------------------------");
        System.out.println("            T W O   P L A Y E R           ");
        System.out.println("------------------------------------------");
        System.out.println( "In two player mode, have a friend\n" +
                            "type in a word while you are not looking. \n" +
                            "You can ask him for hints!\n");
        System.out.println("------------------------------------------");
        System.out.println();
        boolean run = true;
        while (run) {
            System.out.println("    1) S T A R T ");
            System.out.println("    2) G O   B A C K   T O   M E N U ");

            int choice = validateUserIntInput(1, 4);
            switch (choice){
                case 1:
                    createNewGame(true);
                    killScan = scan.nextLine();
                    runGame();
                case 2:
                    run = false;
            }
        }
    }

    //Game State methods______________________________
    static void continueSavedGame(){
        game.setFilePathName("resources/savedGame.csv");
        game.loadGameState();
    }

    static void saveGame() throws FileNotFoundException {

        game.setFilePathName("resources/savedGame.csv");
        game.saveGameState();
    }

    // Game methods_____________________________________
    static void createNewGame(boolean twoPlayerMode){

        if (twoPlayerMode){
            player1.resetPoints();
            System.out.println("ENTER THE HANGMAN WORD:");
            String playerWord = scan.next();

            newGame = new HangMan(player1, playerWord);
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        }
        else{
            player1.resetPoints();
            newGame = new HangMan(player1, word.getWord());
        }

    }

    static void runGame() throws FileNotFoundException {
        game = new GameState(newGame, player1);
        System.out.println("---------------------------------------------");
        System.out.println( "If at anytime you want to quit\n" +
                            "type \'13\'");
        System.out.println("---------------------------------------------");
        System.out.println();
        newGame.setWordState();

        while (true){

            if (newGame.getWrongGuesses().size() == 6){
                System.out.println("---------------------------------------------");
                System.out.println("The word was: " + newGame.getHangManWord());
                System.out.println("Player Score: " + player1.getScore());
                System.out.println("Y O U   L O S T");
                System.out.println("---------------------------------------------");
                break;
            }

            if (!newGame.getHangManWord().equals(newGame.getWordStateAsString())){
                System.out.println("Make a guess: ");
                String guess = scan.nextLine();

                if (guess.equals("13")){
                    break;
                }

                player1.makeAGuess(guess);
                newGame.setWordState();
            }
            else{
                System.out.println("---------------------------------------------");
                System.out.println("Player Score: " + player1.getScore());
                System.out.println("Y O U   W O N  ! ! !");
                System.out.println("---------------------------------------------");
                break;
            }
            saveGame();


        }
        System.out.println();
    }

    //Misc
    static int validateUserIntInput(int minValue, int maxValue){
        int returnNum = 0;
        boolean run = true;
        while (run) {
            try {
                returnNum = scan.nextInt();
                if (returnNum >= minValue && returnNum <= maxValue){
                    run = false;
                }
                else {
                    System.out.println("The number has to be between " + (minValue) + " and " + (maxValue));
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! try a number");
                scan.next();
            }
        }
        return returnNum;
    }


    public static void main(String[] args) throws FileNotFoundException {


        game = new GameState(newGame, player1);
        mainMenu();

    }
}
