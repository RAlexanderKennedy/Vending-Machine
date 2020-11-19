package materials;

import java.math.BigDecimal;

public class Chips extends Sellable {

	public Chips(String slotLocation, String name, BigDecimal price) {
		super(slotLocation, name, price);
		
	}

	@Override
	public String printMethod() {
		
		return "Crunch Crunch, Yum!";
	}

}
