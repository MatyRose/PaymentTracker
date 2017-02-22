package paymentracker;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Maty
 */

public class PTmain {
    
    /**
     * Creates instance of Scanner
     */
    public static Scanner consoleScanner = new Scanner(System.in);

    /**
     * Creates instance of Database
     */
    public static Database fileDatabase = new Database();

    /**
     * Creates instance of Timer
     */
    public static final Timer timer = new Timer();
  
    /**
     * Reads and loads file
     * @param nameOfFile - String
     * @throws Exception 
     */
    public static void loadFile(String nameOfFile) throws Exception {
        Reader reader = null;
            try {
                reader = new FileReader(nameOfFile);
                String currentlyChosenLine;
                try (BufferedReader inputBufferedReader = new BufferedReader(reader)) {
                    while ((currentlyChosenLine = inputBufferedReader.readLine()) != null) {
                            addItem(currentlyChosenLine);
                    }  
                }catch(IOException e){
                    System.out.println("File can´t be readed");
                }
                
                System.out.println();
                reader.close();
                System.out.println("Payments have been loaded.");
            } catch(IOException e) {
                System.out.println("File " + " can´t be opened.");
            }
    }
    
    /**
     * Timer lists database once per minute
     * @param i - int
     */
    public static void playOncePerMinute() {
	timer.scheduleAtFixedRate(new TimerTask() {
	int i;
        
        @Override
	public void run() {
            i--;
            if (i < 0) {
                System.out.println();
                fileDatabase.list();
                System.out.println("\n"); 
                i = 60;
            }
        }  
	}, 0, 1000);  
    }
    
   /**
   * Correct and add item into database
   * @param item - String
     * @param database - Database
   */
  public static void addItem(String item) {
    String currency;
    String amount;
    String delimiters = "\\s";
    
    if(item.length()<5){
        System.out.println("Wrong input, it is too short");
    }else{
        try {
        String[] tokensVal = item.split(delimiters);
        currency = tokensVal[0];
        amount = tokensVal[1];
        Pattern patternCurrency = Pattern.compile("[A-Z]{3}");
        Matcher matchCurrency = patternCurrency.matcher(currency);
        Integer.parseInt(amount);//Because we enter int and i try if in the file is value type of int
                     
        if (matchCurrency.find()) {
            int newAmount = Integer.parseInt(amount);
            fileDatabase.add(currency, newAmount);
        } 
        }
        catch(NumberFormatException e) {
            System.out.println("File can not be loaded.");
        }  
    }                           
  } 
  
  /**
   * Reads and add all currency with amount from file
   * @param reader - Reader
   * @return - Database
   * @throws IOException
   * @throws Exception 
   */  
    public static void readFile(Reader reader, Database fileDatabase) throws IOException, Exception {
        String currentlyChosenLine;
        try (BufferedReader inputBufferedReader = new BufferedReader(reader)) {
            while ((currentlyChosenLine = inputBufferedReader.readLine()) != null) {
                addItem(currentlyChosenLine);
            }  
        }
    }   
   
    /**
     * Starts program with menu
     * @throws Exception 
     */
    public static void runProgram() throws Exception {
        System.out.println("Welcome to the Payment Tracker");
        System.out.println("------------------------------");
        System.out.println();
        System.out.println("Program is controlled by commands.");
        System.out.println("If you want to load from the file type: load.");
        System.out.println("For all instructions type help.");
        playOncePerMinute();
        
        while (true) {
            String command = consoleScanner.nextLine();
           
            switch(command) {
                case "quit": {
                    timer.cancel();
                    System.out.println("Payment Tracker is shutting down.");
                    System.out.println("Have a nice day."); 
                    return;
                } 
                case "load": {
                    System.out.println("Type name of file with extension.");
                    String commandLoad = consoleScanner.nextLine();
                    String nameOfFile = commandLoad;
                    loadFile(nameOfFile); 
                    break;
                } 
                case "add": {
                    System.out.println("First three letters must be one of these: USD, HKD, RMB, NZD, GBP");
                    System.out.println("Input have to be like:");
                    System.out.println("USD 1000"); 
                    String commandAdd = consoleScanner.nextLine();
                    addItem(commandAdd);
                    break;
                } 
                case "list": {
                    System.out.println("Here is your actual database:");
                    System.out.println();
                    fileDatabase.list();
                    System.out.println();
                    break;
                } 
                case "help": {
                    System.out.println("Payment Tracker helper:");
                    System.out.println("......................"); 
                    System.out.println();
                    System.out.println("Here is complete list with commnads:");
                    System.out.println();
                    System.out.println("These commands can be used anytime.");
                    System.out.println("load - for load values from the file.");
                    System.out.println("add - for add values into database");
                    System.out.println("list - show actual database");
                    System.out.println("help - show help");
                    System.out.println("quit - end program");
                    break;
                } 
                default: {
                    System.out.println("Wrong input. You must accidently pressed something else than you wonted.");
                    break;
                }
            }
            System.out.println();
            System.out.println("Do you wont to add values? If yes, type add.");
        }  
    }
    
    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException, Exception {
        runProgram();
    } 
}