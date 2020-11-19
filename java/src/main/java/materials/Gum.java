package materials;

import java.math.BigDecimal;

public class Gum extends Sellable {

	public Gum(String slotLocation, String name, BigDecimal price) {
		super(slotLocation, name, price);
	
	}

	@Override
	public String printMethod() {
		
		return "Chew Chew, Yum!";
	}

}
