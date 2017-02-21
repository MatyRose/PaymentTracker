/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paymenttracker;

/**
 *
 * @author Maty
 */
public class Paynmant {
    private String currency;
    private double amount;
    
    /**
     * Constructor for Paynmant
     * @param currency - String
     * @param amount - amount
     */
    public Paynmant(String currency, double amount) {
        super();
        this.currency = currency;
        this.amount = amount;
    }
    
    /**
     * Gets string from Currency
     * @return String
     */
    public String getCurrency() {
        return currency;
    }
    
    /**
     * Gets double from amount
     * @return - double
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Sets currency
     * @param currency - String
     */
    public void setCurrency(String currency) {
        this.currency = currency;
    }
     
    /**
     * Sets amount
     * @param amount - double
     */
    public void setAmount(double amount) {
        this.amount = amount;
    } 
}
