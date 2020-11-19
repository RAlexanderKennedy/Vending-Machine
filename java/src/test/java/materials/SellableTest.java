package materials;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

public class SellableTest {
	
	
	BigDecimal bigDecimal = new BigDecimal(15);
	// Tests for custom constructors
	@Test
	public void beverage_constructor() {
		Sellable testBeverage = new Beverage("A1", "pepsi", bigDecimal);
		Assert.assertEquals(testBeverage.getName(), "pepsi");
		Assert.assertEquals(testBeverage.getSlotLocation(), "A1");
		Assert.assertEquals(testBeverage.getPrice(), bigDecimal);
		Assert.assertEquals(testBeverage.getQuantity(), 5);
	}
	
	@Test
	public void candy_constructor() {
		Sellable testCandy = new Candy("B3", "jolly ranchers", bigDecimal);
		Assert.assertEquals(testCandy.getName(), "jolly ranchers");
		Assert.assertEquals(testCandy.getSlotLocation(), "B3");
		Assert.assertEquals(testCandy.getPrice(), bigDecimal);
		Assert.assertEquals(testCandy.getQuantity(), 5);
	}
	
	@Test
	public void chips_constructor() {
		Sellable testChips = new Chips("D6", "lays", bigDecimal);
		Assert.assertEquals(testChips.getName(), "lays");
		Assert.assertEquals(testChips.getSlotLocation(), "D6");
		Assert.assertEquals(testChips.getPrice(), bigDecimal);
		Assert.assertEquals(testChips.getQuantity(), 5);
	}
	
	@Test
	public void gum_constructor() {
		Sellable testGum = new Gum("F4", "five gum", bigDecimal);
		Assert.assertEquals(testGum.getName(), "five gum");
		Assert.assertEquals(testGum.getSlotLocation(), "F4");
		Assert.assertEquals(testGum.getPrice(), bigDecimal);
		Assert.assertEquals(testGum.getQuantity(), 5);
	}
	
	// instantiating Test Sellable objects
	Sellable testBeverage = new Beverage("placeholder", "placeholder", bigDecimal);
	Sellable testCandy = new Candy("placeholder", "placeholder", bigDecimal);
	Sellable testChips = new Chips("placeholder", "placeholder", bigDecimal);
	Sellable testGum = new Gum("placeholder", "placeholder", bigDecimal);
	
	@Test
	public void beverage_prints_glug() {
		String expected = "Glug Glug, Yum!";
		String actual = testBeverage.printMethod();
		
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void candy_prints_munch() {
		String expected = "Munch Munch, Yum!";
		String actual = testCandy.printMethod();
		
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void chip_prints_crunch() {
		String expected = "Crunch Crunch, Yum!";
		String actual = testChips.printMethod();
		
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void gum_prints_chew() {
		String expected = "Chew Chew, Yum!";
		String actual = testGum.printMethod();
		
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void decreaseQuantity_1() {
		int expected = 4;
		testBeverage.decreaseQuantity();
		int actual = testBeverage.getQuantity();
		
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void decreaseQuantity_2() {
		int expected = 3;
		testCandy.decreaseQuantity();
		testCandy.decreaseQuantity();
		int actual = testCandy.getQuantity();
		
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void decreaseQuantity_3() {
		int expected = 2;
		testChips.decreaseQuantity();
		testChips.decreaseQuantity();
		testChips.decreaseQuantity();
		int actual = testChips.getQuantity();
		
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void decreaseQuantity_4() {
		int expected = 1;
		testGum.decreaseQuantity();
		testGum.decreaseQuantity();
		testGum.decreaseQuantity();
		testGum.decreaseQuantity();
		int actual = testGum.getQuantity();
		
		Assert.assertEquals(expected, actual);
	}
	

}
