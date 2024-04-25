import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.StringTokenizer;
import java.util.Date;

public class Counter implements Runnable {

    private String title;

    public Counter(String title) {
        this.title = title;
    }

    @Override
    public void run() {
        //creates a file string to be read
        String file = "";
        try {
            file = new String(Files.readAllBytes(Paths.get("Books/" + title)), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Initialise date and time to display progress of thread to user.
        Date date = new Date();
        System.out.println(title + " is initialized at " + date);

        //Tokenizer to turn file into a string and count each of the words
        StringTokenizer countWords = new StringTokenizer(file);
        System.out.println(title + ": " + countWords.countTokens() + "words");
      
        System.out.println(title + " word count complete.");
    }
}
