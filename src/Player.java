public class Player {

    // INSTANCE FIELDS ===============================================
    private String name;
    private int score;
    private int points;
    private int lives;
    private int numOfGuesses = 0;

    private char currentLetterGuess = ' ';


    // CONSTRUCTORS ==================================================
    public Player(String name, int points) {
        this.name = name;
        this.points = points;
        this.score = 0;
        this.lives = 6;
    }


    // GETTERS & SETTERS ================================================

    public void addLives(int live){
        this.lives += live;
    }

    public void setNumOfGuesses(int numOfGuesses) {
        this.numOfGuesses = numOfGuesses;
    }

    public String getName() {
        return name;
    }

    public void setScore() {
        this.score = points + Main.newGame.getHangManWord().length() + lives;
    }

    public int getScore() {
        return score;
    }

    public int getPoints() {
        return points;
    }

    public int getNumOfGuesses() {
        return numOfGuesses;
    }

    public void addPoints(int points){
        if (this.points > 0){
            this.points += points;
        }
    }

    public void setCurrentLetterGuess(char currentLetterGuess) {
        this.currentLetterGuess = currentLetterGuess;
    }

    public void resetPoints(){
        this.points = 1;
    }

    public char getCurrentLetterGuess(){
        return this.currentLetterGuess;
    }

    public void makeAGuess(String guess){

        this.currentLetterGuess = guess.charAt(0);
        this.numOfGuesses ++;
    }
}
