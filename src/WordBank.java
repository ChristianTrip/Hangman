import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class WordBank {

    // INSTANCE FIELDS ===============================================
    private File csvFile;
    private Scanner scan;

    private String[] wordBank;
    private String filePathName = "resources/Wordbank5LettersAndMore.csv";


    // CONSTRUCTORS ==================================================
    public WordBank(String filePathName){

        this.filePathName = filePathName;

        try {
            csvFile = new File(filePathName);
            scan = new Scanner(csvFile);

            while (scan.hasNextLine()){
                String line = scan.nextLine();
                wordBank = line.split(", ");
            }
        }
        catch (
            FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }
    }

    public WordBank(){

        try {
            csvFile = new File(filePathName);
            scan = new Scanner(csvFile);

            while (scan.hasNextLine()){
                String line = scan.nextLine();
                wordBank = line.split(", ");
            }
        }
        catch (
                FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }
    }


    // GETTERS AND SETTERS ================================================
    public String[] getWordBank() {
        return wordBank;
    }

    public void setFilePathName(String filePathName) {
        this.filePathName = filePathName;
    }
}
