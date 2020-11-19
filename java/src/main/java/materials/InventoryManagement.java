package materials;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class InventoryManagement {
	
	private Map<String, Sellable> sellables = new HashMap<String, Sellable>();
	
	public int getSize() {
		return sellables.size();
	}
	
	public Sellable getSellableAt(String slotLocation) {
		return sellables.get(slotLocation);
	}
	
	public void purchaseSellable(String slotLocation) {
		sellables.get(slotLocation).decreaseQuantity();
	}
	
	
	public void restock(String fileName) {
		// overwriting Log.txt (if it already exists)
		File log = new File("Log.txt");
		if (log.exists()) {
			try (PrintWriter writer = new PrintWriter(log)) {
				writer.print("");
				writer.close();
			} catch (FileNotFoundException e) {
				
			}
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
			}
			
			if (category.contentEquals("Chip")) {
				
				Chips product = new Chips(slotLocation, productName, price);
				sellables.put(slotLocation,product);
			}
			if (category.contentEquals("Gum")) {
				
				Gum product = new Gum(slotLocation, productName, price);
				sellables.put(slotLocation, product);
			}
			
			if (category.contentEquals("Candy")) {
				
				Candy product = new Candy(slotLocation, productName, price);
				sellables.put(slotLocation, product);
			}
		}
	} catch (FileNotFoundException e) {
		
		System.out.println("File not found");
		System.exit(1);
	}

}
	
	public void viewProducts() {
		for (Map.Entry<String, Sellable> product : sellables.entrySet()) {
			
			Sellable value = product.getValue();
			if (value.getQuantity() == 0) {
				 System.out.println(value.getSlotLocation() + " " + value.getName() + " $" + value.getPrice() + " SOLD OUT");
			}
			
			System.out.println(value.getSlotLocation() + " " + value.getName() + " $" + value.getPrice() + " " + value.getQuantity() + " available");
			
		}
	}
	
	public void auditPurchase(Sellable soldItem, BigDecimal balance) throws IOException {
		
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