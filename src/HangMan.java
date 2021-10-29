import java.util.ArrayList;

public class HangMan {

    // INSTANCE FIELDS ===============================================
    private Player player;
    private String hangManWord;
    private ArrayList<Character> wordState = new ArrayList<>();
    private ArrayList<Character> wrongGuesses = new ArrayList<>();

    private String ascciHangman;
    static int numOfGamesPlayed;

    // CONSTRUCTOR ==================================================
    public HangMan(Player player, String hangManWord){  //Two player mode
        this.player = player;
        this.hangManWord = hangManWord;
        wordStateSetup();
        numOfGamesPlayed ++;
    }

    // SETTERS & GETTERS ================================================
    public void setHangManWord(String hangManWord) {
        this.hangManWord = hangManWord;
    }

    public void setWordState(String letterString) {
        wordState.clear();
        char[] letters = letterString.toCharArray();
        for (char letter : letters) {
            this.wordState.add(letter);
        }
    }

    public void setWrongGuesses(String letterString) {
        wrongGuesses.clear();
        char[] letters = letterString.toCharArray();
        for (char letter : letters) {
            this.wrongGuesses.add(letter);
        }
    }

    public static void setNumOfGamesPlayed(int numOfGamesPlayed) {
        HangMan.numOfGamesPlayed = numOfGamesPlayed;
    }

    public ArrayList<Character> getWrongGuesses() {
        return wrongGuesses;
    }

    public String getHangManWord() {
        return hangManWord;
    }

    public static int getNumOfGamesPlayed() {
        return numOfGamesPlayed;
    }

    public String getWordStateAsString() {

        String toReturn = "";
        for (Character letter : wordState) {
            toReturn += letter;
        }

        return toReturn;
    }

    public void wordStateSetup(){
        for (int i = 0; i < hangManWord.length(); i++) {
            wordState.add('_');
        }
    }

    // PRIVATE METHOD ===============================================
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


    // GUESS METHOD==================================================
    public void buyAletter(){

        if (player.getPoints() != 0){

            int randomNum = Word.getRandomNumber(0, wordState.size() - 1);
            for (int i = 0; i < wordState.size(); i++) {
                if (wordState.get(randomNum) == '_' && i == randomNum)

                    player.setCurrentLetterGuess(hangManWord.charAt(i));
            }
            player.addPoints(-1);
        }
        else {
            System.out.println("You dont have any points to buy for!");
        }
    }

    public void setPlayerPoints(){
        int count = 0;
        for (int i = 0; i < 1; i++) {
            if (wordState.contains(player.getCurrentLetterGuess())){
                count ++;
                break;
            }
            for (int j = 0; j < hangManWord.length(); j++) {
                if (player.getCurrentLetterGuess() == (hangManWord.charAt(j))) {
                    wordState.set(j, hangManWord.charAt(j));
                    count ++;
                    if(!isVowel(wordState.get(j))){
                        player.addPoints(1);
                    }
                }
            }
            if(!isVowel(player.getCurrentLetterGuess()) && !wordState.contains(player.getCurrentLetterGuess())){
                player.addPoints(1);
            }
            if (count == 0 && !wrongGuesses.contains(player.getCurrentLetterGuess())) {
                wrongGuesses.add(player.getCurrentLetterGuess());
                player.addLives(-1);
            }
        }
    }

    public void setWordState(){

        if(isVowel(player.getCurrentLetterGuess()) && player.getPoints() == 0){
            System.out.println("You dont have any points to buy a vowel");
        }
        else if(isVowel(player.getCurrentLetterGuess()) && player.getPoints() > 0) {
            setPlayerPoints();
            player.addPoints(-1);
        }
        else if(!isVowel(player.getCurrentLetterGuess()) && !wordState.contains(player.getCurrentLetterGuess())) {
            setPlayerPoints();
        }
        else{
            wrongGuesses.add(player.getCurrentLetterGuess());
        }

    }

    public void printWordState(){
        drawHangman();

        System.out.println("Hangman word   -> " + toString(wordState));
        System.out.println("Wrong guesses  -> " + toString(wrongGuesses));
        System.out.println("Player points  -> " + player.getPoints() + " points");
    }

    // MISC METHODs==================================================
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

    public String toString(ArrayList<Character> charList){
        String toReturn = "";

        for (Character letter : charList) {
            toReturn += letter + " ";
        }
        return toReturn.toUpperCase();
    }
}


