/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coffeeklatch;

import java.util.Scanner;

/**
 * Author: Grace Guo 
 * Class: ICS4UE-51
 * Program: Coffee Klatch 
 * Summary: The program takes in the customer order to make coffee for them to drink. 
 * The barista must choice actions to do in a correct sequence to produce the coffee. 
 */
public class CoffeeKlatch {
    
    private static Scanner keyboard = new Scanner(System.in);
    private static String choice;
    
    //customer information 
    private static String name = "none", strength = "none", size = "none";
    private static int sizeAm;
    private static String indent = "\t\t\t\t";
    private static boolean enoughFill;
    
    //to check for input validity 
    private static boolean nameValid, sizeValid, strengthValid; 

    private static int unitsBrewed;
    private static boolean gamePlay = true;
    private static char storedLetter; 
    private static int letterIntVal; 
    private static boolean hasACust;  
    private static boolean coffBrewed; 
    
    private static CoffeeCup coffCup; //coffee cup 
    private static CoffeeMachine coffMachine; //coffee machine 

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        //creating coffee cup and coffee machine object
        coffCup = new CoffeeCup(name);
        coffMachine = new CoffeeMachine(strength);

        while (gamePlay) {

            displayStats(coffCup, coffMachine); //stats 

            System.out.print("Your Choice? ");
            choice = keyboard.nextLine().toLowerCase(); //store the customer's choice 

            executeChoice(coffCup, coffMachine); //executing action for customers choice 
        }
    }

    /**
     * Acts as the interface of the coffee machine, displays the status of each step and customer info
     * @param coffCup is the coffee cup object
     * @param coffMachine is the coffee Machine object
     */
    public static void displayStats(CoffeeCup coffCup, CoffeeMachine coffMachine) {//displays stats
        System.out.printf(indent + "\t\tMACHINE\t\t\t\tUSER: %s\tCup Size: %s\n", coffCup.getName(), coffCup.getSizeWord());
        System.out.printf(indent + "Water\tLevel\tBeans\tBeansGround\tCoffeeBrewed\tCup Full\tStrength\n");
        System.out.printf(indent + "%b\t%d\t%b\t", coffMachine.getWater(), coffCup.getUnitsBrewed(), coffMachine.getBeans());
        System.out.printf("%b\t\t%b\t\t%b\t\t%s\n", coffMachine.getBeansGround(), coffMachine.getBrewed(), coffCup.isFull(), coffCup.getStrength());

        System.out.printf("OPTION: n - New customer\n");
        System.out.printf("\tw - Add Water\n\tb - Add Coffee Beans\n\tg - Grind Beans\n");
        System.out.printf("\tr - Brew Coffee\n\tp - Pour a Cup\n\td - Drink a Cup\n\tx - Exit\n");
    }

    /**
     * Performs the choice that the user chose 
     * @param coffCup is the coffee cup object 
     * @param coffMachine is the coffee Machine object 
     */
    public static void executeChoice(CoffeeCup coffCup, CoffeeMachine coffMachine) {
        try {
            switch (choice) {
                case "n": //choice for new customer 
                    hasACust = false; 
                    reset(coffCup,coffMachine); 
                    coffCup.empty(); 
                        while(!nameValid){//Checking for valid name 
                            try{
                                System.out.print("What is your name? ");
                                name = keyboard.nextLine().trim(); //store the name user inputted
                                
                                if(name.equals("")){
                                    nameValid = false; 
                                    validate(); //throw exception 
                                }else{
                                    for(int i = 0; i <= name.length()-1; i++){//checking that the name includes only letters 
                                        storedLetter = name.charAt(i); 
                                        letterIntVal = (int) storedLetter; 

                                        if((letterIntVal >= 65 && letterIntVal <= 90)||(letterIntVal >= 97 && letterIntVal <= 122||letterIntVal==32)){//valid name 
                                            if(i == name.length()-1){
                                                nameValid = true; 
                                                hasACust = true; 
                                                coffCup.setName(name); 
                                            }
                                        }else{
                                            nameValid = false; 
                                            validate(); //throw exception 
                                        }
                                    }
                                }  
                            }catch(Exception e){
                                System.out.println("Please enter a valid name."); 
                            }
                        }
                        
                        while (!sizeValid) {//checking for valid size selection (s,m,l) 
                            try {
                                System.out.print("What size would you like? (s)mall, (m)edium, (l)arge: ");
                                size = keyboard.nextLine().toLowerCase().trim(); //storing the size customer chooses 

                                if (size.equals("s") || size.equals("m") || size.equals("l")) {//valid size selection 
                                    coffCup.setSize(size);
                                    coffCup.setSizeAmount(size); //send size to coffee cup class 

                                    sizeAm = coffCup.getSizeAmount();
                                    sizeValid = true;
                                }else{//invalid size selection, throw an exception 
                                    sizeValid = false;
                                    validate();
                                }
                            }catch (Exception e){
                                System.out.println("Please enter a valid size.");

                            }
                        }
                        
                        while(!strengthValid){//checking for valid strength selection 
                            try{
                                System.out.print("How do you like your coffee? (w)eak, (m)edium, (s)trong, (k)iller intense: ");
                                strength = keyboard.nextLine().toLowerCase().trim(); //storing the strength customer chooses 
                                
                                if(strength.equals("w")||strength.equals("m")||strength.equals("s")||strength.equals("k")){//valid strength selection 
                                    coffCup.setStrength(strength);
                                    coffMachine.setStrength(coffCup.getStrength());
                                    strengthValid = true;
                                }else{//invalid strength selection, throw an exception 
                                    strengthValid = false; 
                                    validate(); 
                                }
                            }catch(Exception e){
                                System.out.println("Please enter a valid coffee strength.");
                            }
                        }
                        nameValid = false; 
                        sizeValid = false; 
                        strengthValid = false; 
                    break;
                case "w": //choice for adding water 
                    if(hasACust){//checking that there is a customer order 
                        if(coffCup.getUnitsBrewed() == 0){
                            coffMachine.addWater(); //call coffee machine class to add water 
                            unitsBrewed = 10; 
                            coffCup.setUnitsBrewed(unitsBrewed); //set level to 10
                        }else{
                            System.out.println("**The machine reservoir is not empty yet. There's no need to add more water.");
                        }
                    }else{
                        noCustomer();//no customer order
                    }
                    break;
                case "b"://choice for adding beans 
                    if(hasACust){
                        coffMachine.addBeans();//call coffee machine class to add beans 
                    }else{
                        noCustomer();
                    }
                    break;
                case "g"://choice for grinding beans 
                    if(hasACust){
                        coffMachine.grindBeans(); //call coffee machine class to grind beans
                    }else{
                        noCustomer();
                    }
                    break;
                case "r"://choice for brewing coffee
                    if(hasACust){
                        coffMachine.brew(coffCup); //call coffee machine class to brew coffee 
                    }else{
                        noCustomer();
                    }
                    break;
                case "p": //pouring coffee into cup 
                    if(hasACust){
                        //enoughFill = coffCup.checkEmpty(sizeAm, coffBrewed);
                        
                        coffBrewed = coffMachine.getBrewed();
                        
                        coffCup.fill(coffBrewed, sizeAm); //call coffee cup class to fill the coffee cup 
                            
                        if(coffCup.getEnoughFill() == false){//not enough coffee to fill the cup 
                            reset(coffCup, coffMachine); //reset stats
                        }
                             
                        //}   
                    }else{
                        noCustomer();
                    }
                    break;
                case "d": //choice of drinking the coffee 
                    if(hasACust){
                        coffCup.drink(); //call coffee class to drink coffee 
                    }else{
                        noCustomer();
                    }
                    break;
                case "x": //choice of exiting the program 
                    keyboard.close(); //close the keyboard object 
                    gamePlay = false; 
                    break;
                default: //not a valid choice, throw an exception 
                    validate(); 
            }
        }catch (Exception e){
            System.out.println("Please make a valid selection.");
        }
    }

    /**
     * Restarts the coffee machine, turn all statuses to false and empties all liquids 
     * @param coffCup is the coffee cup object
     * @param coffMachine is the coffee Machine object
     */
    public static void reset(CoffeeCup coffCup, CoffeeMachine coffMachine) {//resets the stats of coffee machine 
        //set all stats to false 
        coffMachine.emptyWater();
        coffMachine.emptyBeans();
        coffMachine.brewGone();
        coffMachine.newUngroundBeans(); 

        unitsBrewed = 0;//set liquid level to 0
        coffCup.setUnitsBrewed(unitsBrewed);
    }
    
    /**
     * Statement for when there is no customer to make a order for 
     */
    public static void noCustomer(){
        System.out.println("There is no customer. Please take an order from the customer.");
    }

    /**
     * Throws exceptions 
     * @throws coffeeklatch.CoffeeKlatch.InvalidInput 
     */
    public static void validate() throws InvalidInput {
        throw new InvalidInput("Invalid Input"); //throw an exception 
    }

    public static class InvalidInput extends Exception { //class for exception 

        InvalidInput(String message) {
            super(message);
        }
    }
}
