import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.Scanner;

class Main {
  public static void main(String[] args) throws IOException {
    long startTime;
    long endTime;
    long elapsedTime;  

    //Initialize scanner and ask user for file path of tex files
    Scanner scanner = new Scanner(System.in); 
    System.out.println("Enter the file path: ");
    String filePath = scanner.nextLine();

    //Uses input file path to read folder and text files 
    File selectFolder = new File(filePath);
    String[] fileS = selectFolder.list();

    startTime = System.currentTimeMillis();//start of timer

    //Creation of pool of threads and ammount of thread to use
    ExecutorService pool = Executors.newFixedThreadPool(2);

    //gives thread pool the task created in Counter
    for (String title : fileS){
      pool.submit(new Counter(title));
    }
    pool.shutdown(); //shutdown pool to conserve resources
    try{pool.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);} catch (InterruptedException e) {}
    
    endTime = System.currentTimeMillis(); //end of timer
    elapsedTime = endTime - startTime;// calculates how long the timer took
    System.out.println(" Process took: " + elapsedTime + " milliseconds");
    scanner.close();
  }
}