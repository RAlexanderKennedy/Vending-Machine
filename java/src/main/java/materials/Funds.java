package materials;

import java.math.BigDecimal;

public class Funds {
	
	private BigDecimal balance = new BigDecimal(0);
	
	public BigDecimal getBalance() {
		return balance;
	}
	
	public void setBalance(BigDecimal amountAdded) {
		this.balance = this.balance.add(amountAdded);
	}

}
