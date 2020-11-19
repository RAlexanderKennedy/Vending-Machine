package materials;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.Assert;
import org.junit.Test;

public class FundsTest {
	
	
	
	@Test
	public void funds_constructor() {
		BigDecimal bd = new BigDecimal(0.00);
		bd = bd.setScale(2, RoundingMode.HALF_UP);
		Funds myFund = new Funds();
		Assert.assertEquals(bd, myFund.getBalance()); 
	}
	
	@Test
	public void add_amount_test() {
		BigDecimal fifteen = new BigDecimal(15);
		fifteen = fifteen.setScale(2, RoundingMode.HALF_UP);
		Funds testFund = new Funds();
		testFund.addAmount(fifteen);
		Assert.assertEquals(fifteen, testFund.getBalance()); 
	}
	
	@Test
	public void subtract_amount_test() {
		BigDecimal fifteen = new BigDecimal(15);
		fifteen = fifteen.setScale(2, RoundingMode.HALF_UP);
		BigDecimal thirty = new BigDecimal(30);
		thirty = thirty.setScale(2, RoundingMode.HALF_UP);
		BigDecimal zero = new BigDecimal(0);
		zero = zero.setScale(2, RoundingMode.HALF_UP);
		
		Funds testFund = new Funds();
		testFund.addAmount(thirty);
		Assert.assertEquals(thirty, testFund.getBalance()); 
		testFund.subtractAmount(fifteen);
		Assert.assertEquals(fifteen, testFund.getBalance()); 
		testFund.subtractAmount(fifteen);
		Assert.assertEquals(zero, testFund.getBalance()); 
	}
	
	
	//TODO: Round BigDecimals to two decimal places
	@Test
	public void subtract_amount_test_2() {
		BigDecimal bd1 = new BigDecimal(10.05);
		bd1 = bd1.setScale(2, RoundingMode.HALF_UP);
		BigDecimal bd2 = new BigDecimal(5.03);
		bd2 = bd2.setScale(2, RoundingMode.HALF_UP);
		BigDecimal bd3 = new BigDecimal(5.02);
		bd3 = bd3.setScale(2, RoundingMode.HALF_UP);
		BigDecimal zero = new BigDecimal(0);
		zero = zero.setScale(2, RoundingMode.HALF_UP);
		
		Funds testFund = new Funds();
		testFund.addAmount(bd1);
		Assert.assertEquals(bd1, testFund.getBalance()); 
		testFund.subtractAmount(bd2);
		Assert.assertEquals(bd3, testFund.getBalance()); 
		testFund.subtractAmount(bd3);
		Assert.assertEquals(zero, testFund.getBalance()); 
	}
	
	@Test
	public void give_change_test_1() {
		Funds testFund = new Funds();
		testFund.addAmount(new BigDecimal(1.00));
		String expected = "Your change : " + testFund.getBalance() + "\n" + "Quarters: " + 4 + "\n" + "Dimes: " + 0 + "\n" + "Nickels: " + 0;
		String actual = testFund.getChange();
		Assert.assertEquals(expected, actual);
		
		// testing to make sure current balance is $0
		BigDecimal zero = new BigDecimal(0);
		zero = zero.setScale(2, RoundingMode.HALF_UP);
		Assert.assertEquals(zero, testFund.getBalance()); 
	}
	
	@Test
	public void give_change_test_2() {
		Funds testFund = new Funds();
		testFund.addAmount(new BigDecimal(0.95));
		String expected = "Your change : " + testFund.getBalance() + "\n" + "Quarters: " + 3 + "\n" + "Dimes: " + 2 + "\n" + "Nickels: " + 0;
		String actual = testFund.getChange();
		Assert.assertEquals(expected, actual);
		
		// testing to make sure current balance is $0
		BigDecimal zero = new BigDecimal(0);
		zero = zero.setScale(2, RoundingMode.HALF_UP);
		Assert.assertEquals(zero, testFund.getBalance());
	}
	
	@Test
	public void give_change_test_3() {
		Funds testFund = new Funds();
		testFund.addAmount(new BigDecimal(0.90));
		String expected = "Your change : " + testFund.getBalance() + "\n" + "Quarters: " + 3 + "\n" + "Dimes: " + 1 + "\n" + "Nickels: " + 1;
		String actual = testFund.getChange();
		Assert.assertEquals(expected, actual);
		
		// testing to make sure current balance is $0
		BigDecimal zero = new BigDecimal(0);
		zero = zero.setScale(2, RoundingMode.HALF_UP);
		Assert.assertEquals(zero, testFund.getBalance());
	}
	
	@Test
	public void give_change_test_4() {
		Funds testFund = new Funds();
		testFund.addAmount(new BigDecimal(5.15));
		String expected = "Your change : " + testFund.getBalance() + "\n" + "Quarters: " + 20 + "\n" + "Dimes: " + 1 + "\n" + "Nickels: " + 1;
		String actual = testFund.getChange();
		Assert.assertEquals(expected, actual);
		
		// testing to make sure current balance is $0
		BigDecimal zero = new BigDecimal(0);
		zero = zero.setScale(2, RoundingMode.HALF_UP);
		Assert.assertEquals(zero, testFund.getBalance());
	}
	
	
	

}
