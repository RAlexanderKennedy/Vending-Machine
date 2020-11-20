package com.techelevator;

import java.math.BigDecimal;
import java.util.Scanner;

import com.techelevator.view.Menu;

import materials.*;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String MAIN_MENU_SALES_REPORT = "Sales Report";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT, MAIN_MENU_SALES_REPORT };
	private static final String[] PURCHASE_MENU = {"Feed Money", "Select Product", "Finish Transaction", "Back"};
	private static final String[] FEED_MONEY_MENU = {"1", "2", "5", "10", "Back"};

	private Menu menu;
	private Funds funds = new Funds();
	private InventoryManagement inventory = new InventoryManagement();

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}

	public void run() {
		inventory.restock("vendingmachine.csv");
		while (true) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS, new BigDecimal(-1));
			System.out.println("You selected: " + choice);

			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				System.out.println();
				inventory.viewProducts();
			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				
				String selection = "";
				
				while(!selection.contentEquals("Back")) {
					selection = (String) menu.getChoiceFromOptions(PURCHASE_MENU, funds.getBalance());
				
					if (selection.equals("Feed Money")) {
						System.out.println("You selected " + selection);
						processMoney();
					}
					
					if (selection.equals("Select Product")) {
						System.out.println("You selected " + selection);
						selectProduct();
					}
					
					if (selection.equals("Finish Transaction")) {
						System.out.println("You selected " + selection);
						inventory.auditChange(funds.getBalance());
						System.out.println(funds.getChange());
						
						// changing value of 'selection' so that user returns to main menu
						selection = "Back";
					}
				
					
				}
			}
			else if (choice.equals(MAIN_MENU_OPTION_EXIT)) {
				System.exit(1);
			}
			else if (choice.equals(MAIN_MENU_SALES_REPORT)) {
				inventory.reportSales();
			}
		}
	}
	
	public void processMoney() {
		
		String selection = "";
		while(!selection.contentEquals("Back")) {
			
			selection = (String) menu.getChoiceFromOptions(FEED_MONEY_MENU, new BigDecimal(-1));
			
			if (selection.contentEquals("1")) {
				
				funds.addAmount(new BigDecimal(1));
				inventory.auditFeed(new BigDecimal(1), funds.getBalance());
			} 
			else if (selection.contentEquals("2")) {
				
				funds.addAmount(new BigDecimal(2));
				inventory.auditFeed(new BigDecimal(2), funds.getBalance());
			}
			else if (selection.contentEquals("5")) {
			
				funds.addAmount(new BigDecimal(5));
				inventory.auditFeed(new BigDecimal(5), funds.getBalance());
			}
			else if (selection.contentEquals("10")) {
		
				funds.addAmount(new BigDecimal(10));
				inventory.auditFeed(new BigDecimal(10), funds.getBalance());
			}
			
			System.out.println("You have $"+ funds.getBalance());
		}
	}
	
	public void selectProduct() {
		boolean valid = false;
		while (!valid) {
			System.out.println();
			inventory.viewProducts();
			System.out.println();
			
			System.out.print("Please enter a code for an item (or enter 'Back' to return to purchase menu) >>> ");
			System.out.println();
		
			Scanner scanner = new Scanner(System.in);
			String userCode = scanner.nextLine();
			
			
			if (userCode.toLowerCase().equals("back")) {
				valid = true;
			}
			else if (!inventory.sellableExists(userCode)) {
				System.out.println("Invalid item code.");
			}
			else if (inventory.getSellableAt(userCode).getQuantity() == 0) {
				System.out.println("Sorry, item is sold out.");
			}
			else {
				
				// grabbing sellable at code
				Sellable sellable = inventory.getSellableAt(userCode);
				if (funds.getBalance().compareTo(sellable.getPrice()) >= 0) {
					// auditing purchase
					inventory.auditPurchase(sellable, funds.getBalance());
					System.out.println("Dispensing " + sellable.getName() + " for $" + sellable.getPrice());
					// subtracting price from funds
					funds.subtractAmount(sellable.getPrice());
					System.out.println("Money remaining: $" + funds.getBalance());
					// purchasing sellable
					inventory.purchaseSellable(userCode);
					// printing item message
					System.out.println(sellable.printMethod());
					valid = true;
				}
				else {
					System.out.println("You don't have enough funds for this item.");
				}
				

			}
		}
		
	}

	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}
}
