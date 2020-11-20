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
		Sellable sellable = testInventory.getSellableAt("A1");
		
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
		Sellable sellable = testInventory.getSellableAt("A2");
		
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
		Sellable sellable = testInventory.getSellableAt("A3");
		
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
		Sellable sellable = testInventory.getSellableAt("C3");
		
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
		Sellable sellable = testInventory.getSellableAt("D4");
		
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
		Sellable sellable = testInventory.getSellableAt("D4");
		testInventory.auditChange(new BigDecimal(25));
	}
	
	// Sales Report Tests
	
	@Test
	public void item_exists() {
		InventoryManagement testInventory = new InventoryManagement();
		testInventory.restock("vendingmachine.csv");
		Sellable sellable = testInventory.getSellableAt("A1");
		//sellable should not be in salesReport yet
		Assert.assertTrue(testInventory.salesExists(sellable.getName()));
		// auditing a purchase
		testInventory.auditPurchase(sellable, new BigDecimal(0));
		Assert.assertTrue(testInventory.salesExists(sellable.getName()));
	}
	
	@Test
	public void item_sold_once() {
		InventoryManagement testInventory = new InventoryManagement();
		testInventory.restock("vendingmachine.csv");
		Sellable sellable = testInventory.getSellableAt("A1");
		// auditing a purchase
		testInventory.purchaseSellable("A1");
		int expected = 1;
		int actual = testInventory.getSalesAt(sellable.getName());
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void item_sold_twice() {
		InventoryManagement testInventory = new InventoryManagement();
		testInventory.restock("vendingmachine.csv");
		Sellable sellable = testInventory.getSellableAt("A1");
		// auditing a purchase
		testInventory.purchaseSellable("A1");
		testInventory.purchaseSellable("A1");
		int expected = 2;
		int actual = testInventory.getSalesAt(sellable.getName());
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void three_items_sold() {
		InventoryManagement testInventory = new InventoryManagement();
		testInventory.restock("vendingmachine.csv");
		Sellable sellable1 = testInventory.getSellableAt("A1");
		// auditing a purchase for first item
		testInventory.purchaseSellable("A1");
		Sellable sellable2 = testInventory.getSellableAt("A2");
		// auditing a purchase for second item
		testInventory.purchaseSellable("A2");
		Sellable sellable3 = testInventory.getSellableAt("A3");
		// auditing a purchase for third item
		testInventory.purchaseSellable("A3");
		int expected = 1;
		int actual1 = testInventory.getSalesAt(sellable1.getName());
		Assert.assertEquals(expected, actual1);
		int actual2 = testInventory.getSalesAt(sellable2.getName());
		Assert.assertEquals(expected, actual2);
		int actual3 = testInventory.getSalesAt(sellable3.getName());
		Assert.assertEquals(expected, actual3);
	}
	
	@Test
	public void total_sales_amount_test() {
		InventoryManagement testInventory = new InventoryManagement();
		testInventory.restock("vendingmachine.csv");
		BigDecimal expected = new BigDecimal(0.00);
		expected = expected.setScale(2, RoundingMode.HALF_UP);
		BigDecimal actual = testInventory.getTotalSales();
		// total amount should be zero
		Assert.assertEquals(expected, actual);
		
		Sellable sellable1 = testInventory.getSellableAt("A1");
		// auditing a purchase for first item
		testInventory.purchaseSellable("A1");
		expected = expected.add(new BigDecimal(3.05));
		expected = expected.setScale(2, RoundingMode.HALF_UP);
		actual = testInventory.getTotalSales();
		// testing total sales amount
		Assert.assertEquals(expected, actual);
		
		Sellable sellable2 = testInventory.getSellableAt("A2");
		// auditing a purchase for first item
		testInventory.purchaseSellable("A2");
		expected = expected.add(new BigDecimal(1.45));
		expected = expected.setScale(2, RoundingMode.HALF_UP);
		actual = testInventory.getTotalSales();
		// testing total sales amount
		Assert.assertEquals(expected, actual);
		
		Sellable sellable3 = testInventory.getSellableAt("A3");
		// auditing a purchase for first item
		testInventory.purchaseSellable("A3");
		expected = expected.add(new BigDecimal(2.75));
		expected = expected.setScale(2, RoundingMode.HALF_UP);
		actual = testInventory.getTotalSales();
		// testing total sales amount
		Assert.assertEquals(expected, actual);
		
		testInventory.reportSales();
		
	}
	
	

}
