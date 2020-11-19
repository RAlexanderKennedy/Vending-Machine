package materials;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.Assert;
import org.junit.Test;

public class InventoryManagementTest {
	
	
	@Test
	public void restock_3_items_size() {
		InventoryManagement testInventory = new InventoryManagement();
		testInventory.restock("test_vendingmachine.csv");
		int expected = 3;
		int actual = testInventory.getSize();
		Assert.assertEquals(expected, actual);
		System.out.println("************TEST_VENDINGMACHINE.CSV ITENMS*******************");
		testInventory.viewProducts();
	}
	
	@Test
	public void restock_16_items_size() {
		InventoryManagement testInventory = new InventoryManagement();
		testInventory.restock("vendingmachine.csv");
		int expected = 16;
		int actual = testInventory.getSize();
		Assert.assertEquals(expected, actual);
		System.out.println("************VENDINGMACHINE.CSV ITENMS*******************");
		testInventory.viewProducts();
	}
	
	@Test
	public void restock_3_items_test_items_1() {
		InventoryManagement testInventory = new InventoryManagement();
		testInventory.restock("test_vendingmachine.csv");
		Sellable sellable = testInventory.getSellableAt(0);
		
		BigDecimal bd = new BigDecimal(3.05);
		bd = bd.setScale(2, RoundingMode.HALF_UP);
		Assert.assertEquals("A1", sellable.getSlotLocation());
		Assert.assertEquals("Potato Crisps", sellable.getName());
		Assert.assertEquals(bd, sellable.getPrice());
		
	}
	
	@Test
	public void restock_3_items_test_items_2() {
		InventoryManagement testInventory = new InventoryManagement();
		testInventory.restock("test_vendingmachine.csv");
		Sellable sellable = testInventory.getSellableAt(1);
		
		BigDecimal bd = new BigDecimal(1.45);
		bd = bd.setScale(2, RoundingMode.HALF_UP);
		Assert.assertEquals("A2", sellable.getSlotLocation());
		Assert.assertEquals("Pepsi", sellable.getName());
		Assert.assertEquals(bd, sellable.getPrice());
		
	}
	
	@Test
	public void restock_3_items_test_items_3() {
		InventoryManagement testInventory = new InventoryManagement();
		testInventory.restock("test_vendingmachine.csv");
		Sellable sellable = testInventory.getSellableAt(2);
		
		BigDecimal bd = new BigDecimal(2.75);
		bd = bd.setScale(2, RoundingMode.HALF_UP);
		Assert.assertEquals("A3", sellable.getSlotLocation());
		Assert.assertEquals("Kit Kat", sellable.getName());
		Assert.assertEquals(bd, sellable.getPrice());
		
	}
	
	@Test
	public void restock_16_items_test_items_1() {
		InventoryManagement testInventory = new InventoryManagement();
		testInventory.restock("vendingmachine.csv");
		Sellable sellable = testInventory.getSellableAt(10);
		
		BigDecimal bd = new BigDecimal(1.50);
		bd = bd.setScale(2, RoundingMode.HALF_UP);
		Assert.assertEquals("C3", sellable.getSlotLocation());
		Assert.assertEquals("Mountain Melter", sellable.getName());
		Assert.assertEquals(bd, sellable.getPrice());
		
	}
	
	@Test
	public void restock_16_items_test_items_2() {
		InventoryManagement testInventory = new InventoryManagement();
		testInventory.restock("vendingmachine.csv");
		Sellable sellable = testInventory.getSellableAt(15);
		
		BigDecimal bd = new BigDecimal(0.75);
		bd = bd.setScale(2, RoundingMode.HALF_UP);
		Assert.assertEquals("D4", sellable.getSlotLocation());
		Assert.assertEquals("Triplemint", sellable.getName());
		Assert.assertEquals(bd, sellable.getPrice());
		
	}
	
	@Test
	public void log_test() throws IOException {
		InventoryManagement testInventory = new InventoryManagement();
		testInventory.restock("vendingmachine.csv");
		Sellable sellable = testInventory.getSellableAt(15);
		testInventory.auditChange(new BigDecimal(25));
	}
	
	

}
