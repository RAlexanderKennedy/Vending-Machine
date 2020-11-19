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
		Assert.assertEquals(testFund.getBalance(), fifteen); 
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
		Assert.assertEquals(testFund.getBalance(), thirty); 
		testFund.subtractAmount(fifteen);
		Assert.assertEquals(testFund.getBalance(), fifteen); 
		testFund.subtractAmount(fifteen);
		Assert.assertEquals(testFund.getBalance(), zero); 
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
		Assert.assertEquals(testFund.getBalance(), bd1); 
		testFund.subtractAmount(bd2);
		Assert.assertEquals(testFund.getBalance(), bd3); 
		testFund.subtractAmount(bd3);
		Assert.assertEquals(testFund.getBalance(), zero); 
	}
	
	@Test
	public void give_change_test() {
		Funds testFund = new Funds();
		testFund.addAmount(new BigDecimal(1.00));
		String expected = "Your change : " + testFund.getBalance() + "\n" + "Quarters: " + 4 + "\n" + "Dimes: " + 0 + "\n" + "Nickels: " + 0;
		String actual = testFund.getChange();
		Assert.assertEquals(expected, actual);
	}
	
	
	

}
