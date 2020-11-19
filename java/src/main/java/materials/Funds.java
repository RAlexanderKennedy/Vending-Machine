package materials;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Funds {
	
	private BigDecimal balance = new BigDecimal(0.00);;
	
	/*public void Funds() {
		this.balance = new BigDecimal(0.00);
		this.balance.setScale(2, RoundingMode.HALF_UP);
	}*/

	
	public BigDecimal getBalance() {
		balance = balance.setScale(2, RoundingMode.HALF_UP);
		return balance;
	}

	public void addAmount(BigDecimal amountAdded) {
		this.balance = balance.add(amountAdded);
		balance = this.balance.setScale(2, RoundingMode.HALF_UP);
	}
	
	public void subtractAmount(BigDecimal amountSubtracted) {
		this.balance = balance.subtract(amountSubtracted);
		balance = this.balance.setScale(2, RoundingMode.HALF_UP);
	}
	
	public String getChange () {
		
		BigDecimal quarterValue = new BigDecimal(0.25);
		quarterValue = quarterValue.setScale(2, RoundingMode.HALF_UP);
		BigDecimal dimeValue = new BigDecimal(0.10);
		dimeValue = dimeValue.setScale(2, RoundingMode.HALF_UP);
		BigDecimal nickelValue = new BigDecimal(0.05);
		nickelValue = nickelValue.setScale(2, RoundingMode.HALF_UP);
		BigDecimal yourChange = this.balance;
		
		
		int quarters = 0;
		int dimes = 0;
		int nickels = 0;
		while (this.balance.compareTo(quarterValue) >= 0) {
			
			subtractAmount(quarterValue);
			quarters += 1;
			
		}
		
		while (this.balance.compareTo(dimeValue) >= 0) {
			
			subtractAmount(dimeValue);
			dimes += 1;
		}
		
		while (this.balance.compareTo(nickelValue) >= 0) {
			
			subtractAmount(nickelValue);
			nickels += 1;
			
		}
		return "Your change : " + yourChange + "\n" + "Quarters: " + quarters +  "\n" + "Dimes: " + dimes + "\n" + "Nickels: " + nickels;
	}
	
	

}
