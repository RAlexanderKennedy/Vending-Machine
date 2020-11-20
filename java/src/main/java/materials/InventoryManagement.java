package materials;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class InventoryManagement {
	
	private Map<String, Sellable> sellables = new HashMap<String, Sellable>();
	private List<Sellable> orderedSellables = new ArrayList<Sellable>();
	private Map<String, Integer> sales = new HashMap<String, Integer>();
	private BigDecimal totalSales = new BigDecimal(0);
	private String salesReportFileName;
	
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
		totalSales = totalSales.setScale(2, RoundingMode.HALF_UP);
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
		totalSales = totalSales.setScale(2, RoundingMode.HALF_UP);
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
		
		/*String timeFormat2 = "dd'_'MM'_'yyyy'-'hh_mm_ss_a";
		String time = dateTime.format(DateTimeFormatter.ofPattern(timeFormat2));
		this.salesReportFileName = "SalesReport:" + time + ".txt";
		File salesReport = new File(salesReportFileName);*/
		
		
		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss"); 
		Date date = new Date();
		salesReportFileName = "SalesReport-" + dt.format(date) + ".txt";
		File salesReport = new File(salesReportFileName);
		
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
		
		File salesReport = new File(salesReportFileName);
		
		if (!salesReport.exists()) {
			System.out.println("The File does not exist");
			return;
		}
		
		// overwriting file for update
		System.out.println("Updating " + salesReportFileName);
		try (PrintWriter writer = new PrintWriter(salesReport)) {
			writer.print("");
			writer.close();
		} catch (FileNotFoundException e) {
			
		}
		
		
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