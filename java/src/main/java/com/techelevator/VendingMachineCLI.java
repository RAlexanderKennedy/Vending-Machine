package com.techelevator;

import java.math.BigDecimal;

import com.techelevator.view.Menu;

import materials.Funds;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE };
	private static final String[] PURCHASE_MENU = {"Feed Money", "Select Product", "Finish Transaction", "Back"};
	private static final String[] FEED_MONEY_MENU = {"1", "5", "10", "Back"};

	private Menu menu;
	protected Funds funds = new Funds();

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}

	public void run() {
		while (true) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS, new BigDecimal(-1));
			System.out.println("You selected: " + choice);

			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				// display vending machine items
			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				
				String selection = "";
				
				while(!selection.contentEquals("Back")) {
					selection = (String) menu.getChoiceFromOptions(PURCHASE_MENU, funds.getBalance());
				
					if (selection.equals("Feed Money")) {
						processMoney();
					}
				
					System.out.println("You selected" + selection);
				}
			}
		}
	}
	
	public void processMoney() {
		
		String selection = "";
		while(!selection.contentEquals("Back")) {
			
			selection = (String) menu.getChoiceFromOptions(FEED_MONEY_MENU, new BigDecimal(-1));
			
			if (selection.contentEquals("1")) {
				
				funds.addAmount(new BigDecimal(1));
			} 
			else if (selection.contentEquals("2")) {
				
				funds.addAmount(new BigDecimal(2));
			}
			else if (selection.contentEquals("5")) {
			
				funds.addAmount(new BigDecimal(5));
			}
			else if (selection.contentEquals("10")) {
		
				funds.addAmount(new BigDecimal(10));
			}
			
			System.out.println("You have $"+ funds.getBalance());
		}
	}

	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}
}
