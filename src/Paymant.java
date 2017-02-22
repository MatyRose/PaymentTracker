package paymentracker;

/**
 *
 * @author Maty
 */
public class Paymant {
    private String currency;
    private double amount;
    
    /**
     * Constructor for Paymant
     * @param currency - String
     * @param amount - amount
     */
    public Paymant(String currency, double amount) {
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
