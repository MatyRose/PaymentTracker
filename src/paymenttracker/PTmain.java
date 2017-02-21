/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paymenttracker;

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
   * Reads and add all currency with amount from file
   * @param reader - Reader
   * @return - Database
   * @throws IOException
   * @throws Exception 
   */  
  public static Database readFile(Reader reader) throws IOException, Exception {
    String currentlyChosenLine;
    try (BufferedReader inputBufferedReader = new BufferedReader(reader)) {
        while ((currentlyChosenLine = inputBufferedReader.readLine()) != null) {
            addItem(currentlyChosenLine);
        }  
    }
    return fileDatabase;
}
    /**
     * Loads file
     * @param nameOfFile - String
     * @throws Exception 
     */
    public static void loadFile(String nameOfFile) throws Exception {
        Reader reader = null;
            try {
                reader = new FileReader(nameOfFile);
                readFile(reader,fileDatabase);
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
    public static void playOncePerMinute(int i) {
	timer.scheduleAtFixedRate(new TimerTask() {
	int i;//60
        
        @Override
	public void run() {
            i--;
            if (i < 0) {
                System.out.println("");
                fileDatabase.list();
                System.out.println("\n"); 
                i = 7;//60
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
    String[] tokensVal = item.split(delimiters);
    currency = tokensVal[0];
    amount = tokensVal[1];
    Pattern patternCurrency = Pattern.compile("[A-Z]{3}");
                                
    try {
        Matcher matchCurrency = patternCurrency.matcher(currency);
        Integer.parseInt(amount);
                    
        if (matchCurrency.find()) {
            int newAmount = Integer.parseInt(amount);
            fileDatabase.add(currency, newAmount);
        } 
    }
    catch(NumberFormatException e) {
        System.out.println("File can not be loaded.");
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
   // return fileDatabase;
}   
   
    /**
     * Starts program with menu
     * @throws Exception 
     */
    public static void startProgram() throws Exception {
        System.out.println("Welcome to the Payment Tracker");
        System.out.println("------------------------------" + "\n");
        System.out.println("Would you like to load values from the file?");
        System.out.println("If you want to load type: load.");
        playOncePerMinute(7);
        
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
                    System.out.println("First three letters must be one of these: USB, HKD, RMB, NZD, GBP");
                    System.out.println("Input have to be like:");
                    System.out.println("USB 1000"); 
                    String commandAdd = consoleScanner.nextLine();
                    addItem(commandAdd);
                    break;
                } 
                case "list": {
                    System.out.println("Here is your actual databbase:" + "\n");
                    fileDatabase.list();
                    System.out.println();
                    break;
                } 
                case "help": {
                    System.out.println("Payment Tracker helper:");
                    System.out.println("......................" + "\n");
                    System.out.println("Here is complete list with commnads:" + "\n");
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
        startProgram();
    } 
}
