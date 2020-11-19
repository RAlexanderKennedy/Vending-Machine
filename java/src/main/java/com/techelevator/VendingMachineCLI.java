package com.techelevator;

import com.techelevator.view.Menu;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE };
	private static final String[] PURCHASE_MENU = {"Feed Money", "Select Product", "Finish Transaction", "Back"};
	private static final String[] FEED_MONEY_MENU = {"1", "5", "10", "Back"};

	private Menu menu;

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}

	public void run() {
		while (true) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
			System.out.println("You picked: " + choice);

			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				// display vending machine items
			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				
				String selection = "";
				
				while(!selection.contentEquals("Back")) {
				selection = (String) menu.getChoiceFromOptions(PURCHASE_MENU);
				
				if (selection.equals("Feed Money")) {
					processMoney();
				}
				
				System.out.println("You selected from the 2nd level: " + selection);
				}
			}
		}
	}
	
	public void processMoney() {
		
		String selection = "";
		while(!selection.contentEquals("Back")) {
			
			selection = (String) menu.getChoiceFromOptions(FEED_MONEY_MENU);
			
		}
	}

	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}
}
