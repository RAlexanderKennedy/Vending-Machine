package materials;

import java.math.BigDecimal;

public class Beverage extends Sellable {


	public Beverage(String slotLocation, String name, BigDecimal price) {
		super(slotLocation, name, price);
		
	}

	@Override
	public String printMethod() {
		
		return "Glug Glug, Yum!";
	}


}
