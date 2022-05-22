
/**
 * Coffee machine to make coffee 
 * @author Grace Guo 
 */
package coffeeklatch;

public class CoffeeMachine {
    private String strength; //The current strength of the coffee.
    private String name; 
    private String choice; 
    
    private boolean waterAdded, beansAdded, beansGround, coffeeBrewed; //check status of machine
    
    /**
     * Create a coffee machine object
     * @param coffStrength is the coffee strength 
     */
    public CoffeeMachine(String coffStrength){
        this.strength = coffStrength; 
    }
    
    /**
     * Set the choice chosen on the coffee machine by the user 
     * @param userChoice the option the bartista chose
     */
    public void setChoice(String userChoice){
        this.choice = userChoice; 
    }
    
    /**
     * Set the strength to the strength chosen by customer 
     * @param strength is the strength of the coffee 
     */
    public void setStrength(String strength){
        this.strength = strength; 
    }
    
    /**
     * Grind the beans for the coffee
     */
    public void grindBeans() {
        if(!beansGround){
            if(beansAdded){//beans have not been ground but are in the grnder 
                System.out.println("Grinding beans for " + strength + " coffee.");
                beansGround = true; //grind beans 
            }else{ //beans have not been added to grinder 
                System.out.println("**Please add beans!");
            }
        }else{ //beans were previously ground 
            System.out.println("**Beans have already been grounded. Please proceed to the next step.");
        }
    }
    /**
     * Get the status of whether the beans have been ground 
     * @return if the beans have been previously ground 
     */
    public boolean getBeansGround(){
        return beansGround; 
    }
    /**
     * Set the status of whether the beans have been ground to not ground(false) 
     */
    public void newUngroundBeans(){
        beansGround = false; 
    }

    /**
     * Brew the coffee into given cup coffCup
     * @param coffCup The cup of coffee to be filled
     */
    public void brew(CoffeeCup coffCup) {
        this.name = coffCup.getName(); 
        
        if(!coffeeBrewed){
            if(beansGround && waterAdded){//beans and water have been added into the coffee machine 
                System.out.println("Brewing, " + strength + " coffee into coffee cup " + name);
                coffeeBrewed = true; //brew the coffee 
            }else{
                if(!beansGround || !waterAdded){
                    if(!beansGround){//beans have not been added, give instructions 
                        System.out.println("**Please grind the beans!");
                    }else{//water has not been added, give instructions 
                        System.out.println("**Please add water!");
                    }
                }
            }
        }else{
            System.out.println("**There is still brewed coffee in the machine that has not been used.");
        }
        
    } 
    
    /**
     * Get the status of whether the coffee has been brewed 
     * @return if the coffee has been brewed 
     */
    public boolean getBrewed(){
        return coffeeBrewed; 
    }
    
    /**
     * Empty the brewing pot out; status is not brewed 
     */
    public void brewGone(){
        coffeeBrewed = false; 
    }

    /**
     * Add water to the machine reservoir
     */
    public void addWater() {
        if(!waterAdded){//water have never been added before
            System.out.println("Adding Water");
            waterAdded = true;//add water to the machine 
        } else{//water has been previously added 
            System.out.println("**Water was previously added. Please proceed to the next step.");
        }
    }
    
    /**
     * Get the status of whether there is water in the reservoir 
     * @return if water has been added before 
     */
    public boolean getWater(){
        return waterAdded; 
    }
    
    /**
     * Empty water reservoir 
     */
    public void emptyWater(){
        waterAdded = false; 
    }

    /**
     * Add Beans to the Machine
     */
    public void addBeans() {
        if(!beansAdded){//beans have never been added before
            System.out.println("Adding Beans");
            beansAdded = true; //add beans to the machine 
        }else{//beans have previously been added
            System.out.println("**Beans were previously added. Please proceed to the next step."); 
        }
    }
    /**
     * Get the current status of if the beans are in the coffee machine  
     * @return status of if beans are in machine 
     */
    public boolean getBeans(){
        return beansAdded; 
    }
    
    /**
     * Empty the beans from the grinder 
     */
    public void emptyBeans(){
        beansAdded = false; 
    }
    
}
