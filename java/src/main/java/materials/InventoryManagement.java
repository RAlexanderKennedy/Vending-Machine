package materials;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InventoryManagement {
	
	private List<Sellable> sellables = new ArrayList<Sellable>();
	
	public int getSize() {
		return sellables.size();
	}
	
	public Sellable getSellableAt(int index) {
		return sellables.get(index);
	}
	
	
	public void restock(String fileName) {
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
				sellables.add(product);
			}
			
			if (category.contentEquals("Chip")) {
				
				Chips product = new Chips(slotLocation, productName, price);
				sellables.add(product);
			}
			if (category.contentEquals("Gum")) {
				
				Gum product = new Gum(slotLocation, productName, price);
				sellables.add(product);
			}
			
			if (category.contentEquals("Candy")) {
				
				Candy product = new Candy(slotLocation, productName, price);
				sellables.add(product);
			}
		}
	} catch (FileNotFoundException e) {
		
		System.out.println("File not found");
		System.exit(1);
	}

}
	
	public void viewProducts() {
		for (Sellable product : sellables) {
			if (product.getQuantity() == 0) {
				System.out.println(product.getSlotLocation() + " " + product.getName() + " $" + product.getPrice() + " SOLD OUT");
			}
			else {
				System.out.println(product.getSlotLocation() + " " + product.getName() + " $" + product.getPrice() + product.getQuantity() + " avaialabe");
			}
		}
	}
}