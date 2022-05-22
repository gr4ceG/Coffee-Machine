
/**
 * Coffee Cup for customer 
 * @author Grace Guo
 */
package coffeeklatch;


public class CoffeeCup {

    private boolean isFull;  // Is this cup full?  Default value false.
    private boolean coffBrewed; 
    private int amountLeft; 
    private boolean enoughFill; //Is there enough coffee to fully fill the cup? Default value false. 
    
    //information given by customer
    private String name = "none", strength = "none", size = "none"; 
    
    //coffee size info
    private int sizeNum;
    private String sizeWord = "none"; 

    /**
     * Create a coffee cup for the customer 
     * @param custName is the name of the customer  
     */
    public CoffeeCup(String custName) {
        this.name = custName;  
    }
    
    /**
     * Get the name of the customer 
     * @return the name of the customer
     */
    public String getName(){
        return this.name; 
    }
    /**
     * Set the name of the customer 
     * @param custName  customer name to receive 
     */
    public void setName(String custName){
        this.name = custName; 
    }

    
    /**
     * Set the strength of the Coffee to the parameter; affects the fineness of the grind.
     * Converts letter used to represent each strength into full word
     * @param strength is the intensity of the coffee 
     */
    
    public void setStrength(String strength) {
        
        switch(strength){//Word for each letter for strength 
            case "w": strength = "Weak"; 
                break; 
            case "m": strength = "Medium"; 
                break; 
            case "s": strength = "Strong"; 
                break; 
            case "k": strength = "Killer Intense"; 
                break; 
            default: 
                strength = "none"; 
        }
        this.strength = strength;
    }
    
    /**
     * Returns the strength of the coffee
     * @return strength of coffee
     */
    public String getStrength(){
        return this.strength; 
    }
    
    /**
     * Get the size of the coffee in full words 
     * @return size of coffee (in words) 
     */
    public String getSizeWord(){
        return this.sizeWord; 
    }
    
    /**
     * Sets the size of the coffee to the parameter
     * Converts letter used to represent each size into full word
     * @param size letter used to represent the size of the coffee
     * @return the size in words
     */
    public String setSize(String size){//Word for each letter for size 
        switch(size){
            case "s": sizeWord = "Small"; 
                break; 
            case "m": sizeWord = "Medium"; 
                break; 
            case "l": sizeWord = "Large"; 
                break; 
            default:
                sizeWord = "none"; 
        }
        return this.size = size; 
    }
    
    /**
     * Converts letter used to represent each size into numerical units of coffee
     * @param size is the letter representation for size of the coffee 
     */
    public void setSizeAmount(String size){
        
        switch(size){//Units of coffee needed to fill each cup size 
            case "s": sizeNum = 2; 
                break; 
            case "m": sizeNum = 3; 
                break; 
            case "l": sizeNum = 4; 
                break; 
            default: sizeNum = 0; 
        }
    }
    /**
     * Returns the units of coffee required to fill the cup 
     * @return units of coffee to fully fill cup
     */
    public int getSizeAmount(){
        return this.sizeNum; 
    }
    
    /**
     * Returns whether this cup is full (true) or empty(false);
     * @return if the cup is full
     */
    public boolean isFull() {
        return isFull;
    }
    
    /**
     * Fill this cup to the top after checking if coffee is brewed and there is enough to fill the cup 
     * @param coffBrewed is if the coffee has been brewed 
     * @param sizeAm is how many units of coffee for the size 
     */
    public void fill(boolean coffBrewed, int sizeAm) {
        this.coffBrewed = coffBrewed; //check if the coffee is brewed before pouring 
        
        if(isFull){//cup is already full
            System.out.println("The coffee cup is already full. Pour anymore and it will over flow.");
        }else{//cup is not full
            if((amountLeft < sizeAm)&&coffBrewed){//not enough coffee to pour another full cup 
                enoughFill = false; 
                System.out.println("**There is not enough coffee. Please rebrew the coffee.");
            }else{
                if(!coffBrewed){
                    System.out.println("**Please brew the coffee!");
                }else{//enough coffee to pour another full cup 
                    isFull = true;
                    amountLeft-= sizeAm;//subtract amount of coffee left in the coffee machine 
                    System.out.println("Pouring the coffee into coffeeCup " + name + ".");
                }
                enoughFill = true; 
            }
            
        }
    }
    
    /**
     * Get the status of if there is enough coffee left to fill cup
     * @return status of enough to fill cup 
     */
    public boolean getEnoughFill(){
        return enoughFill; 
    }
    
    /**
     * Empty the cup 
     */
    public void empty(){
        isFull = false; 
    }
    
    /**
     * Sets the units of liquid in the coffee machine to the parameter
     * @param unitsBrewed is the units of coffee required to fill cup 
     */
    public void setUnitsBrewed(int unitsBrewed){
        this.amountLeft = unitsBrewed; 
    }   
    
    /**
     * Returns the amount of coffee left in the machine 
     * @return how many units of coffee can the machine still make 
     */
    public int getUnitsBrewed(){
        return this.amountLeft; 
    }
    /**
     * Drink this cup entirely
     */
    public boolean drink() {
        if (isFull) {//cup is full of coffee
            System.out.println(name + ", you glug the coffee down."); 
            isFull = false;//customer drinks coffee and the cup is then empty 
            return true;
        } else {//no coffee in cup 
            System.out.println(name + ", you sip furiously, but only suck air.");
            return false; 
        }
    }
    
    /**
     * Sets the status of whether or not the coffee has enough coffee to fully fill another cup to the parameter
     * @param enoughFill is whether or not there is enough coffee to fill another cup 
     * @return if another cup can be filled 
     */
    public boolean setLeftZero(boolean enoughFill){
        return this.enoughFill = enoughFill; 
    }
    

}
