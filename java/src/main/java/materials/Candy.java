package materials;

import java.math.BigDecimal;

public class Candy extends Sellable{

	public Candy(String slotLocation, String name, BigDecimal price) {
		super(slotLocation, name, price);
		
	}
	
	@Override
	public String printMethod() {
		return "Munch Munch, Yum!";
	}

	
	

}
