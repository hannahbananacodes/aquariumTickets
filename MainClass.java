//AUTHOR: Hannah Holmes
//COURSE: CPT 167
//PURPOSE: Create a program for Summerville Sea World
//STARTDATE: 05 Feb 2024

package edu.tridenttech.holmes.program4;

import java.util.Scanner;

public class MainClass {
	// declare final variables 
	public static final String TICKET_ADULT = "Adult";
	public static final String TICKET_JUNIOR = "Junior";
	public static final String TICKET_TODDLER = "Toddler";
	public static final String MENU_QUIT = "Quit";
	public static final String LOCATION_NAME = "Summerville Sea World";
	public static final char ADULT_SELECTION = 'A';
	public static final char JUNIOR_SELECTION = 'J';
	public static final char TODDLER_SELECTION = 'T';
	public static final char QUIT_SELECTION = 'Q';

	public static final double ADULT_PRICE = 14.50;
	public static final double JUNIOR_PRICE = 9.50;
	public static final double TODDLER_PRICE = 0.00;
	
	/* in the assignment, it said "Once the operator decides to quit, or all of the tickets
	 * have been sold, display the final report.", so I added functionality to automatically
	 * print the final report once a certain number of tickets have been sold-in other words,
	 * when all seats have been filled. AVAILABLE_TICKETS is the number of seats.*/
	public static final int AVAILABLE_TICKETS = 1000;
	
  public static void main(String[] args) {
      // declare local variables
      Scanner input = new Scanner(System.in);

      char menuSelection = ' ';
      String ticketSelection = " ";
      int ticketAmount = 0;
      int purchaseCount = 0;
      
      int adultCount = 0; 
      int juniorCount = 0;
      int toddlerCount = 0;
      double adultTotal = 0.00;
      double juniorTotal = 0.00;
      double toddlerTotal = 0.00;
      int totalTickets = 0;
      double ticketTotal = 0.00;

      double purchaseTotal = 0.00;
      double ticketPrice = 0.00;

      // call welcome method to display welcome
      displayWelcome(LOCATION_NAME);
      
      // call ticket selection method to determine selection
      menuSelection = getValidatedTicketSelection(input);
      
      // while loop to determine ticket count as long as menu selection is not quit
      while (menuSelection != QUIT_SELECTION) {
          // request ticket count
          System.out.println("How may tickets would you like to purchase?");
          // call ticket count method 
          ticketAmount = getValidatedTicketCount(input, totalTickets);
          // if statement to determine selection, assign variables, update counters
          if (menuSelection == ADULT_SELECTION) {
              ticketSelection = TICKET_ADULT;
              ticketPrice = ADULT_PRICE;
              adultCount = adultCount + ticketAmount;
              adultTotal = adultTotal + (ticketPrice * ticketAmount);
          } else if (menuSelection == JUNIOR_SELECTION) {
              ticketSelection = TICKET_JUNIOR;
              ticketPrice = JUNIOR_PRICE;
              juniorCount = juniorCount + ticketAmount;
              juniorTotal = juniorTotal + (ticketPrice * ticketAmount);
          } else {
              ticketSelection = TICKET_TODDLER;
              ticketPrice = TODDLER_PRICE;
              toddlerCount = toddlerCount + ticketAmount;
              toddlerTotal = toddlerTotal + (ticketPrice * ticketAmount);
          }
          
          // update counters
          purchaseCount++;
          totalTickets = totalTickets + ticketAmount;
          purchaseTotal = ticketAmount * ticketPrice;
          ticketTotal = ticketTotal + purchaseTotal;
          
          // call single purchase method to display report
          displaySinglePurchase(ticketSelection, ticketAmount, ticketPrice, purchaseTotal);
          // if statement to determine if any tickets left to sell; if yes, display menu and call ticket selection method
          if (totalTickets < AVAILABLE_TICKETS ) {
        	  menuSelection = getValidatedTicketSelection(input);
          } else {
        	 System.out.println("\nSOLD OUT");
        	 menuSelection = QUIT_SELECTION;
          }
      }
       
      
      // if statement to display final report only if one or more ticket(s) purchased
      if (purchaseCount > 0) {
    	  displayFinalReport(purchaseCount, adultCount, juniorCount, toddlerCount, adultTotal, juniorTotal, toddlerTotal, totalTickets, ticketTotal);
      }
      // farewell message and close scanner
      System.out.println("\nThank you for visiting " + LOCATION_NAME + "!");
      input.close(); 
  }
 
  /* The assignment said, regarding the displayWelcome method: "It does need parameters." I wasn't sure what
   * parameters to use/would be needed, hope this is okay!*/
  public static void displayWelcome(String LOCATION_NAME)
  {
  	// welcome user
      System.out.println("Welcome to " + LOCATION_NAME + "!");
  }
  
  public static void displayMainMenu()
  {
      // display menu
      System.out.println();
      System.out.println("Ticket Menu:");
      System.out.printf("%s) %-10s%2s%6.2f\n", ADULT_SELECTION, TICKET_ADULT, "$", ADULT_PRICE);
      System.out.printf("%s) %-10s%2s%6.2f\n", JUNIOR_SELECTION, TICKET_JUNIOR, "$", JUNIOR_PRICE);
      System.out.printf("%s) %-10s%2s%6.2f\n", TODDLER_SELECTION, TICKET_TODDLER, "$", TODDLER_PRICE);
      System.out.printf("%s) %-10s\n", QUIT_SELECTION, MENU_QUIT);
      System.out.println("Please make your selection:");
  }
  
  public static char getValidatedTicketSelection(Scanner input)
  {
	// display menu and get character selection
	displayMainMenu();
  	char selection;
  	selection = input.next().toUpperCase().charAt(0);
  	// while loop to validate selection
  	while (selection != ADULT_SELECTION && selection != JUNIOR_SELECTION && selection != TODDLER_SELECTION && selection != QUIT_SELECTION) {
          System.out.println("\n----Invalid selection.----\n");

          // display menu
          displayMainMenu();
          selection = input.next().toUpperCase().charAt(0);
     } // end of while loop to validate selection
  	return selection;
  }
  
  public static int getValidatedTicketCount(Scanner input, int totalTickets)
  {
	  int count;
	  count = input.nextInt();
	  final int TICKET_LIMIT = 10;
	  // determine number of tickets left
	  int seatsAvailable = AVAILABLE_TICKETS - totalTickets;
	  
	  // while loop to validate ticket count (valid number, not over purchase limit, within range of tickets left)
	  while (count <= 0 || count > TICKET_LIMIT || ((count + totalTickets) > AVAILABLE_TICKETS)) {
		  if (count <= 0) {
			  System.out.println("----Invalid quantity.----");
	          System.out.println("Please enter the desired number of tickets.");
	          count = input.nextInt();
		  } else if (count > TICKET_LIMIT) {
			  System.out.println("----Purchases are limited to " + TICKET_LIMIT + " tickets.----");
			  System.out.println("Please enter the desired number of tickets.");
	          count = input.nextInt();
	      } else {
	    	  System.out.println("Sorry, there are only " + seatsAvailable + " tickets left.");
	    	  System.out.println("Please enter the desired number of tickets.");
	          count = input.nextInt();
	      }
	  } // end of while loop to validate ticket count
      
	  return count;
  
	  }
  
  public static void displaySinglePurchase(String ticketSelection, int ticketAmount, double ticketPrice, double purchaseTotal)
  {
	  // display purchase report
	  if (ticketAmount > 0) {
	      System.out.println("\nThank you for your purchase.");
	      System.out.println("------------------------");
	      System.out.println("Purchase Report:");
	      System.out.printf("%-15s%-8s\n", "Ticket Type:", ticketSelection);
	      System.out.printf("%-15s%8d\n", "Sold:", ticketAmount);
	      System.out.printf("%-13s%2s%8.2f\n", "Unit Price:", "$", ticketPrice);
	      System.out.printf("%-13s%2s%8.2f\n", "Total:", "$", purchaseTotal);
	      System.out.println("------------------------");
	  }
  }
  
  public static void displayFinalReport(int purchaseCount, int adultCount, int juniorCount, int toddlerCount, double adultTotal, double juniorTotal, double toddlerTotal, int totalTickets, double ticketTotal)
  {
	  // display final report
	  System.out.println("\nPORPOISE TICKET SALES REPORT");
	  System.out.println("Total Purchases: " + purchaseCount);
	  System.out.println("Tickets Purchased:");
	  System.out.printf("%-8s%8s%12s\n", "Type", "Sold", "Value");
	  System.out.printf("%-8s%8d%4s%8.2f\n", TICKET_TODDLER, toddlerCount, "$", toddlerTotal);
	  System.out.printf("%-8s%8d%4s%8.2f\n", TICKET_JUNIOR, juniorCount, "$", juniorTotal);
	  System.out.printf("%-8s%8d%4s%8.2f\n", TICKET_ADULT, adultCount, "$", adultTotal);
	  System.out.println("============================");
	  System.out.printf("%-8s%8d%4s%8.2f\n", "Total", totalTickets, "$", ticketTotal);
  }
}
