import java.util.ArrayList;
import java.util.Arrays;

public class HangMan1 {
    // INSTANCE FIELDS ===============================================
    private Word word;
    private Player player;

    private ArrayList<Character> rightGuesses = new ArrayList<>();
    private ArrayList<Character> wrongGuesses = new ArrayList<>();
    private String hangManWord;
    private char[] unfinishedWord;
    private char currentLetterGuess;
    private String ascciHangman;

    static int numOfSinglePLayerGames;
    static int numOfMultiPLayerGames;


    // CONSTRUCTORS ==================================================
    public HangMan1(Player player, int difficultyValue) { //SinglePlayer mode
        this.player = player;
        this.word = new Word(difficultyValue);
        setupUnfinishedWord();
        numOfSinglePLayerGames ++;
    }

    public HangMan1(Player player, String playerWord){  //Two player mode
        this.player = player;
        this.word = new Word(playerWord);
        this.hangManWord = word.getWord();
        setupUnfinishedWord();
        numOfMultiPLayerGames ++;
    }


    // PUBLIC METHODS ================================================
    public ArrayList<Character> getRightGuesses() {
        return rightGuesses;
    }

    public ArrayList<Character> getWrongGuesses() {
        return wrongGuesses;
    }

    public String getHangManWord() {
        return hangManWord;
    }

    public char[] getUnfinishedWord() {
        return unfinishedWord;
    }

    public void drawHangman(){

        switch (this.wrongGuesses.size()){
            case 0:
                ascciHangman =   "\n  +---+  " +
                        "\n  |   |  " +
                        "\n      |  " +
                        "\n      |  " +
                        "\n      |  " +
                        "\n      |  " +
                        "\n=========";
                break;
            case 1:
                ascciHangman =  "\n  +---+  " +
                        "\n  |   |  " +
                        "\n  O   |  " +
                        "\n      |  " +
                        "\n      |  " +
                        "\n      |  " +
                        "\n=========";
                break;
            case 2:
                ascciHangman =  "\n  +---+  " +
                        "\n  |   |  " +
                        "\n  O   |  " +
                        "\n  |   |  " +
                        "\n      |  " +
                        "\n      |  " +
                        "\n=========";
                break;
            case 3:
                ascciHangman =  "\n  +---+  " +
                        "\n  |   |  " +
                        "\n  O   |  " +
                        "\n /|   |  " +
                        "\n      |  " +
                        "\n      |  " +
                        "\n=========";
                break;
            case 4:
                ascciHangman =  "\n  +---+  " +
                        "\n  |   |  " +
                        "\n  O   |  " +
                        "\n /|\\  |  " +
                        "\n      |  " +
                        "\n      |  " +
                        "\n=========";
                break;
            case 5:
                ascciHangman =  "\n  +---+  " +
                        "\n  |   |  " +
                        "\n  O   |  " +
                        "\n /|\\  |  " +
                        "\n /    |  " +
                        "\n      |  " +
                        "\n=========";
                break;
            case 6:
                ascciHangman =  "\n  +---+  " +
                        "\n  |   |  " +
                        "\n  O   |  " +
                        "\n /|\\  |  " +
                        "\n / \\  |  " +
                        "\n      |  " +
                        "\n=========";
                break;
        }
        System.out.println(ascciHangman);
    }


    // PRIVATE METHODS ===============================================

    private boolean isVowel(char letter){

        switch (letter){
            case 'a':
            case 'e':
            case 'i':
            case 'o':
            case 'u':
            case 'æ':
            case 'ø':
            case 'å':
                return true;
        }
        return false;
    }

    private void setupUnfinishedWord(){
        unfinishedWord = new char[hangManWord.length()];
        for (int i = 0; i < unfinishedWord.length; i++) {
            unfinishedWord[i] = '_';
        }
    }

    // Guess Methods==================================================

    public boolean isValidGuess(){

        // if it's not already been a guess
        if (!wrongGuesses.contains(currentLetterGuess) && !Arrays.toString(unfinishedWord).contains(Character.toString(currentLetterGuess))){
            return true;
        }

        return false;
    }

    public void addLetterToUnfinishedWord(){

        //Udate player points
        boolean isVowel = isVowel(currentLetterGuess);
        if(isVowel){
            player.addPoints(-1);
        }
        else{
            player.addPoints(+1);
        }

        //Update hangman status
        for (int i = 0; i < hangManWord.length(); i++) {
            if (currentLetterGuess == hangManWord.charAt(i)) {
                unfinishedWord[i] = currentLetterGuess;
            }
        }


    }

    public void addLetterToWrongGuesses(){
        wrongGuesses.add(currentLetterGuess);
        player.addPoints(-1);
    }


    public void makeALetterGuess(String letterGuess){

        currentLetterGuess = letterGuess.charAt(0);

        if (isValidGuess() && hangManWord.contains(Character.toString(currentLetterGuess))){

            addLetterToUnfinishedWord();
        }
        else{
            addLetterToWrongGuesses();
        }



    }

    public boolean makeAWordGuess(String wordGuess){

        if (wordGuess.toLowerCase().equals(word.getWord())){
            System.out.println("you won");
            return true;
        }
        return false;
    }

}
