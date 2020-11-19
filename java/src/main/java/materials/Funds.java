package materials;

import java.math.BigDecimal;

public class Funds {
	
	private BigDecimal balance = new BigDecimal(0);

	public BigDecimal getBalance() {
		return balance;
	}

	public void addAmount(BigDecimal amountAdded) {
		this.balance = balance.add(amountAdded);
	}
	
	public void subtractAmount(BigDecimal amountSubtracted) {
		this.balance = balance.subtract(amountSubtracted);
	}
	
	public String getChange (BigDecimal remainingBalance) {
		
		BigDecimal quarterValue = new BigDecimal(0.25);
		BigDecimal dimeValue = new BigDecimal(0.10);
		BigDecimal nickelValue = new BigDecimal(0.05);
		BigDecimal changeDue = new BigDecimal (0.0);
		
		
		int quarters = 0;
		int dimes = 0;
		int nickels = 0;
		while (remainingBalance.compareTo(quarterValue) > 0) {
			
			remainingBalance.subtract(quarterValue);
			quarters += 1;
			changeDue.add(quarterValue);
			
		}
		
		while (remainingBalance.compareTo(dimeValue) > 0) {
			
			remainingBalance.subtract(dimeValue);
			dimes += 1;
			changeDue.add(dimeValue);
		}
		
		while (remainingBalance.compareTo(nickelValue) > 0) {
			
			remainingBalance.subtract(nickelValue);
			nickels += 1;
			changeDue.add(nickelValue);
			
		}
		return "Your change : " + changeDue + "\n" + "Quarters: " + quarters +  "\n" + "Dimes: " + dimes + "\n" + "Nickels: " + nickels;
	}
	
	

}
