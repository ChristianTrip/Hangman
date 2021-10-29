import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class GameState {

    // INSTANCE FIELDS ===============================================
    private File csvFile;
    private String filePathName;
    private Scanner fileScan;
    private HangMan game;
    private Player player;
    

    // CONSTRUCTORS ==================================================
    public GameState(HangMan game, Player player) {
        this.game = game;
        this.player = player;
    }


    // GETTERS & SETTERS ================================================


    public HangMan getGame() {
        return game;
    }

    public Player getPlayer() {
        return player;
    }

    // PUBLIC METHODS ================================================
    public void saveGameState() {
        try {
            csvFile = new File(filePathName);
            PrintWriter write = new PrintWriter(csvFile);

            String wrongLetters = "";
            for (Character letter : game.getWrongGuesses()) {
                wrongLetters += letter;
            }


            write.print("PLAYER CLASS => |Name|");
            write.print("Points|");
            write.print("NumOfGuesses|");
            write.print(" & ");
            write.print("HANGMAN CLASS =>|hangManWord|");
            write.print("wrongLetterGuesses|");
            write.print("wordState|");
            write.print("numOfgamesPlayed|");
            write.println();
            write.print(player.getName() + ";");
            write.print(player.getPoints() + ";");
            write.print(player.getNumOfGuesses() + ";");
            write.print(game.getHangManWord() + ";");
            write.print(wrongLetters + ";");
            write.print(game.getWordStateAsString() + ";");
            write.print(game.getNumOfGamesPlayed());

            write.close();
        }
        catch(FileNotFoundException e){
            System.out.println("could not find file - please restart game");
        }

    }

    public void loadGameState(){

        this.filePathName = filePathName;

        try {
            csvFile = new File(filePathName);
            fileScan = new Scanner(csvFile);
            fileScan.nextLine();

            while (fileScan.hasNextLine()){
                String line = fileScan.nextLine();
                String[] stringAsArray = line.split(";");
                // player---------------------------
                String playerName = stringAsArray[0];
                int playerPoints = Integer.parseInt(stringAsArray[1]);
                int plNumOFGuesses = Integer.parseInt(stringAsArray[2]);
                // Hangman---------------------------
                String hangmanWord = stringAsArray[3];
                String wrongLetters = stringAsArray[4];
                String wordState = stringAsArray[5];
                int numOfGamesPlayed = Integer.parseInt(stringAsArray[6]);

                Main main = new Main();


                player = new Player(playerName, playerPoints);
                player.setNumOfGuesses(plNumOFGuesses);

                game = new HangMan(player, hangmanWord);
                game.setHangManWord(hangmanWord);
                game.setWrongGuesses(wrongLetters);
                game.setWordState(wordState);
                game.setNumOfGamesPlayed(numOfGamesPlayed);

            }
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }
    }

    public void setFilePathName(String filePathName) {
        this.filePathName = filePathName;
    }

    @Override
    public String toString() {
        return "GameState{" +
                "filePathName = '" + filePathName + '\'' +
                '}';
    }
}
