package com.techelevator;

import java.math.BigDecimal;

import com.techelevator.view.Menu;

import materials.Funds;;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE };
	private static final String[] PURCHASE_MENU = { "Feed Money", "Select Product", "Finish Transaction", "Back" };
	private static final String[] FEED_MONEY_MENU = { "1", "2", "5", "10", "Back"};
	Funds funds = new Funds();

	private Menu menu;

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}

	public void run() {
		while (true) {
			
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
			System.out.println("You selected: " + choice);

			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				// display vending machine items
			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				// do purchase
				String selection = "";
				while (!selection.equals("Back")) {
					selection = (String) menu.getChoiceFromOptions(PURCHASE_MENU);
					System.out.println("You selected from the second level: " + selection);
					
					if (selection.equals("Feed Money")) {
						processMoney();
					}
				}
			}
		}
	}
	
	public void processMoney() {
		String selection = "";
		while (!selection.equals("Back")) {
			selection = (String) menu.getChoiceFromOptions(FEED_MONEY_MENU);
			
			if (selection.equals("1")) {
				funds.setBalance(new BigDecimal(1));
			}
			
		}
		
	}

	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}
}
