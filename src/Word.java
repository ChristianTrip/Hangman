import java.util.ArrayList;

public class Word {

    // INSTANCE FIELDS ===============================================
    private WordBank words;
    private String word;
    private char[] wordAsArray;


    // CONSTRUCTORS ==================================================
    public Word(int difficultyValue) { // singlePlayer Constructor
        this.words = new WordBank();
        setRandomWordWithDefinedLength(difficultyValue - 1, difficultyValue + 1);

    }

    public Word(String word){ // TwoPlayer Constructor
        this.word = word.toLowerCase();
        this.wordAsArray = word.toCharArray();

    }


    // PUBLIC METHODS ================================================
    public String getWord() {
        return word;
    }


    // PRIVATE METHODS ===============================================
    private void setRandomWordWithDefinedLength(int wordLenghtFrom, int wordLengthTo){

        ArrayList<String> wordsWithDefinedLength = new ArrayList<>();

        for (int i = 0; i < words.getWordBank().length; i++) {
            if (words.getWordBank()[i].length() >= wordLenghtFrom &&
                words.getWordBank()[i].length() <= wordLengthTo){
                    wordsWithDefinedLength.add(words.getWordBank()[i]);
            }
        }
        this.word = wordsWithDefinedLength.get(getRandomNumber(0, wordsWithDefinedLength.size() - 1));
        this.wordAsArray = word.toCharArray();
    }

    static int getRandomNumber(int minValue, int maxValue){
        int randomNumber = (int)(Math.random()*(maxValue - minValue + 1) + minValue);
        return randomNumber;
    }


}
