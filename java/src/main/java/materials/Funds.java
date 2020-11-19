package materials;

<<<<<<< HEAD
public class Funds {
=======
import java.math.BigDecimal;

public class Funds {
	
	private BigDecimal balance = new BigDecimal(0);
	
	public BigDecimal getBalance() {
		return balance;
	}
	
	public void setBalance(BigDecimal amountAdded) {
		this.balance = this.balance.add(amountAdded);
	}
>>>>>>> 5a61461bb8a868fefc82c9b88bfd1271cb155647

}
