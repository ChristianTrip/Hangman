import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    // Classes
    static Scanner scan = new Scanner(System.in);
    static Word word;
    static Player player1;
    static HangMan newGame;
    static GameState game = new GameState(newGame, player1);

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

    static void mainMenu()  {

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
                    continueSavedGame();
                    break;
                case 4:
                    System.out.println("Bye bye now..");
                    run = false;
                    break;
            }
        }
    }

    static void singlePlayerMenu()  {

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

    static void twoPlayerMenu()  {

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
                    scan.nextLine();
                    runGame();
                    break;
                case 2:
                    run = false;
            }
        }
    }

    //Game State methods______________________________
    static void continueSavedGame()  {


        game.setFilePathName("resources/savedGame.csv");
        game.loadGameState();

        player1 = game.getPlayer();
        newGame = game.getGame();

        /*
        System.out.println("PLAYER INFO---------------------");
        System.out.println("Name:           " + player1.getName());
        System.out.println("Current guess:  " + player1.getCurrentLetterGuess());
        System.out.println("Num of guesses: " + player1.getNumOfGuesses());
        System.out.println("GAME INFO---------------------");
        System.out.println("Hangman word:   " + newGame.getHangManWord());
        System.out.println("Wrong guesses:  " + newGame.getWrongGuesses());
        System.out.println("Word state:     " + newGame.getWordStateAsString());
        System.out.println("games played:   " + HangMan.getNumOfGamesPlayed());
        */
        runGame();
    }

    static void saveGame()  {


        game.setFilePathName("resources/savedGame.csv");
        game.saveGameState();
    }

    // Game methods_____________________________________
    static void createNewGame(boolean twoPlayerMode){

        if (twoPlayerMode){
            player1.resetPoints();
            System.out.println("ENTER THE HANGMAN WORD:");
            String playerWord = scan.next() + scan.nextLine();
            word = new Word(playerWord);
            newGame = new HangMan(player1, word.getWord());
            System.out.println( "\n\n\n\n\n\n\n\n\n\n\n\n\n\n" +
                                "\n\n\n\n\n\n\n\n\n\n\n\n\n\n" +
                                "\n\n\n\n\n\n\n\n\n\n\n\n\n\n");

        }
        else{
            player1.resetPoints();
            newGame = new HangMan(player1, word.getWord());
        }
    }

    static void runGame() {

        System.out.println("---------------------------------------------");
        System.out.println( "If at anytime you want to exit\n" +
                            "type \'1\'");
        System.out.println( "If you want to save and exit\n" +
                            "type \'2\'");
        System.out.println( "Buy a letter for 1 point\n" +
                            "type \'3\'");
        System.out.println( "To make a guess for the whole word\n" +
                            "just type it in");
        System.out.println("---------------------------------------------");
        System.out.println();


        newGame.printWordState();

        boolean run = true;

        while (run){

            if (newGame.getWrongGuesses().size() == 6){

                System.out.println("---------------------------------------------");
                System.out.println("The word was: " + newGame.getHangManWord());
                System.out.println("Y O U   L O S T");
                System.out.println("---------------------------------------------");
                break;
            }
            if (!newGame.getHangManWord().equals(newGame.getWordStateAsString())){

                System.out.println("Make a guess: ");
                String guess = scan.next();
                guess += scan.nextLine();
                switch (guess){
                    case "1":
                        run = false;
                        break;
                    case "2":
                        run = false;
                        saveGame();
                        break;
                    case "3":
                        newGame.buyAletter();
                        newGame.setWordState();
                        newGame.printWordState();
                        break;
                    default:
                        if(guess.length() == 1){
                            player1.makeAGuess(guess);
                            newGame.setWordState();
                            newGame.printWordState();
                        }
                        else if (guess.length() != newGame.getHangManWord().length()){
                            System.out.println("The guess have to be the same lenght as the word!");
                        }
                        else if (guess.length() == newGame.getHangManWord().length()){
                            if (player1.getPoints() == 0) {
                                System.out.println("It cost 1 point to make a guess on the word");
                            }
                            else if (guess.equals(newGame.getHangManWord())){
                                player1.addPoints(1);
                                player1.setScore();
                                System.out.println("---------------------------------------------");
                                System.out.println("Player Score: " + player1.getScore());
                                System.out.println("Y O U   G U E S S E S   R I G H T");
                                System.out.println("Y O U   W O N  ! ! !");
                                System.out.println("---------------------------------------------");
                                run = false;
                                break;
                            }
                            else{
                                System.out.println("Good guess, but it's not right!");
                                player1.addPoints(-1);
                                newGame.printWordState();
                                break;
                            }
                        }

                }
            }
            else{

                player1.setScore();
                System.out.println("---------------------------------------------");
                System.out.println("Player Score: " + player1.getScore());
                System.out.println("Y O U   W O N  ! ! !");
                System.out.println("---------------------------------------------");
                break;
            }



        }
        player1.setCurrentLetterGuess(' ');
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

    static void testGame(){

        word = new Word("Trouble");
        player1 = new Player("John", 1);
        newGame = new HangMan(player1, word.getWord());

        player1.makeAGuess("a");
        newGame.setWordState();
        player1.makeAGuess("o");
        newGame.setWordState();
        player1.makeAGuess("b");
        newGame.setWordState();
        newGame.buyAletter();
        newGame.setWordState();
    }


    public static void main(String[] args)  {

        //testGame();


        game = new GameState(newGame, player1);
        mainMenu();

    }
}
