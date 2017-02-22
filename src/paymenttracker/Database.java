package paymentracker;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Maty
 */

public class Database {
    
    private final List<Paymant> listOfPayments = new ArrayList<>();
    
    /**
     * Add one payment into database 
     * @param currency - String
     * @param amount - double
     */
    public void add(String currency, double amount) {
       Paymant eachPayment = new Paymant(currency, amount);
       listOfPayments.add(eachPayment);
    }
    
    /**
     * Counts amount in USD and prints in right format
     * @param course - double
     * @param currency - String
     * @param amount - amount
     */
    public void adjustment(double course, String currency, double amount){
        double transferAmount;
        transferAmount = amount/course;
        String doubleSize = "0.00#";
        DecimalFormat df = new DecimalFormat(doubleSize);
        int newAmount = (int) amount;
        System.out.println(currency + " " + newAmount + "(" +  "USD" 
        +  " " + df.format(transferAmount) + ")");     
    }
    
    /**
     * Lists and prints all currency and amount from database
     */
    public void list() {
        String currency;
        double amount;
      
        double courseRMB = 6.35727908455;
        double courseHKD = 34.802784222;
        double courseNZD = 24.45;
        double courseGBP = 25.45;
        
        String USD = "USD";
        String RMB = "RMB";
        String HKD = "HKD";
        String NZD = "NZD";
        String GBP = "GBP";

        List<String> allCurrencyes = new ArrayList<>();
        allCurrencyes.add(USD);
        allCurrencyes.add(RMB);
        allCurrencyes.add(HKD);
        allCurrencyes.add(NZD);
        allCurrencyes.add(GBP);
        
        for (int i = 0; i < allCurrencyes.size(); i++) {
            currency = allCurrencyes.get(i);
            amount = sumOfCurrency(currency);
            
            if (amount != 0) {
                if (USD.equals(currency)) {
                    int newAmount = (int)amount;
                    System.out.println(currency + " " + newAmount + " ");
                }
                if (RMB.equals(currency)) {
                    adjustment(courseRMB, currency, amount);
                } 
                if(HKD.equals(currency)) { 
                    adjustment(courseHKD, currency, amount);
                } 
                if(NZD.equals(currency)) { 
                    adjustment(courseNZD, currency, amount);
                } 
                if(GBP.equals(currency)) { 
                    adjustment(courseGBP, currency, amount);
                } 
            }  
        }
    }
    
    /**
     * Counts currency in database
     * @param currencyIn - String
     * @return - int 
     */
    public int sumOfCurrency(String currencyIn) {
        int sum = 0;
        
        for (int i = 0; i < listOfPayments.size(); i++) {
            if (listOfPayments.get(i).getCurrency().equals(currencyIn)) {
                sum += listOfPayments.get(i).getAmount();
            }               
        }
        return sum;
    }   
}
    