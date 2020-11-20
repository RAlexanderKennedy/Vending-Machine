package materials;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class InventoryManagement {
	
	private Map<String, Sellable> sellables = new HashMap<String, Sellable>();
	private List<Sellable> orderedSellables = new ArrayList<Sellable>();
	private Map<String, Integer> sales = new HashMap<String, Integer>();
	private BigDecimal totalSales;
	private File salesReport;
	
	public int getSize() {
		return sellables.size();
	}
	
	public Sellable getSellableAt(String slotLocation) {
		return sellables.get(slotLocation);
	}
	
	public Integer getSalesAt(String productName) {
		return sales.get(productName);
	}
	
	public BigDecimal getTotalSales() {
		return totalSales;
	}
	
	public boolean sellableExists(String slotLocation) {
		return sellables.containsKey(slotLocation);
	}
	
	public boolean salesExists(String productName) {
		return sales.containsKey(productName);
	}
	
	public void purchaseSellable(String slotLocation) {
		sellables.get(slotLocation).decreaseQuantity();
		Integer itemSold = sales.get(sellables.get(slotLocation).getName());
		itemSold += 1;
		sales.put(sellables.get(slotLocation).getName(),itemSold);
		totalSales = totalSales.add(sellables.get(slotLocation).getPrice());
	}
	
	
	
	public void restock(String fileName) {
		// adding restock to log.txt
		String timeFormat = "dd'/'MM'/'yyyy' 'hh:mm:ss a";
		File log = new File("Log.txt");
		LocalDateTime dateTime = LocalDateTime.now();
		
		try(PrintWriter tracker = new PrintWriter(new FileOutputStream(log,true))){
			tracker.println(dateTime.format(DateTimeFormatter.ofPattern(timeFormat)) + " " + "Restocking Vending Machine");
		} catch (FileNotFoundException e) {
			System.out.println("Audit error: log not found");
		}
		
		String time = dateTime.format(DateTimeFormatter.ofPattern(timeFormat));
		File salesReport = new File("Sales Report: " + time + ".txt");
		try {
			salesReport.createNewFile();
		} catch (IOException e1) {
		}
		
		File inputFile = new File(fileName);
		
		try(Scanner fileScanner = new Scanner(inputFile)){
		
		while (fileScanner.hasNextLine()) {
			String line = fileScanner.nextLine();
			String [] lineArr = line.split("\\|");
			
			String slotLocation = lineArr[0];
			String productName = lineArr[1];
			BigDecimal price = new BigDecimal(lineArr[2]);
			String category = lineArr[3];
			if (category.contentEquals("Drink")) {
				
				Beverage product = new Beverage(slotLocation, productName, price);
				sellables.put(slotLocation, product);
				orderedSellables.add(product);
				sales.put(productName, 0);
			}
			
			if (category.contentEquals("Chip")) {
				
				Chips product = new Chips(slotLocation, productName, price);
				sellables.put(slotLocation,product);
				orderedSellables.add(product);
				sales.put(productName, 0);
			}
			if (category.contentEquals("Gum")) {
				
				Gum product = new Gum(slotLocation, productName, price);
				sellables.put(slotLocation, product);
				orderedSellables.add(product);
				sales.put(productName, 0);
			}
			
			if (category.contentEquals("Candy")) {
				
				Candy product = new Candy(slotLocation, productName, price);
				sellables.put(slotLocation, product);
				orderedSellables.add(product);
				sales.put(productName, 0);
			}
		}
	} catch (FileNotFoundException e) {
		
		System.out.println("File not found");
		System.exit(1);
	}

}
	
	public void reportSales() {
		
		try(PrintWriter report = new PrintWriter((new FileOutputStream(salesReport,true)))) {
			for (Sellable product : orderedSellables) {
				report.println(product.getName() + "|" + sales.get(product.getName()));
			}
			
			report.println("\n" + "TOTAL SALES: $" + totalSales);
			
		} catch (FileNotFoundException e) {

		}
	}
	
	public void viewProducts() {
		for (Sellable product : orderedSellables) {
			
			if (product.getQuantity() == 0) {
				 System.out.println(product.getSlotLocation() + " " + product.getName() + " $" + product.getPrice() + " SOLD OUT");
			}
			
			else System.out.println(product.getSlotLocation() + " " + product.getName() + " $" + product.getPrice() + " " + product.getQuantity() + " available");
			
		}
	}
	
	public void auditPurchase(Sellable soldItem, BigDecimal balance) {
		
		String timeFormat = "dd'/'MM'/'yyyy' 'hh:mm:ss a";
		
		File log = new File("Log.txt");
		LocalDateTime dateTime = LocalDateTime.now();
		try(PrintWriter tracker = new PrintWriter(new FileOutputStream(log,true))){
			BigDecimal newBalance = balance.subtract(soldItem.getPrice());
			tracker.println(dateTime.format(DateTimeFormatter.ofPattern(timeFormat)) + " " + soldItem.getName() + " " + soldItem.getSlotLocation() + " $" + balance + " $" + newBalance);
		} catch (FileNotFoundException e) {
			System.out.println("Audit error: log not found");
		}
	}
	
	public void auditFeed(BigDecimal amountAdded, BigDecimal balance) {
		
		String timeFormat = "dd'/'MM'/'yyyy' 'hh:mm:ss a";
		
		File log = new File("Log.txt");
		LocalDateTime dateTime = LocalDateTime.now();
		try(PrintWriter tracker = new PrintWriter(new FileOutputStream(log,true))){
			tracker.println(dateTime.format(DateTimeFormatter.ofPattern(timeFormat)) + " " + "FEED MONEY: $" + amountAdded + " $" + balance);
		} catch (FileNotFoundException e) {
			System.out.println("Audit error: log not found");
		}
	}
	
	public void auditChange(BigDecimal balance) {
		
		String timeFormat = "dd'/'MM'/'yyyy' 'hh:mm:ss a";
		
		File log = new File("Log.txt");
		LocalDateTime dateTime = LocalDateTime.now();
		try(PrintWriter tracker = new PrintWriter(new FileOutputStream(log,true))){
			tracker.println(dateTime.format(DateTimeFormatter.ofPattern(timeFormat)) + " " + "GIVE CHANGE: $" + balance + " $0.00");
		} catch (FileNotFoundException e) {
			System.out.println("Audit error: log not found");
		}
	}
	
}