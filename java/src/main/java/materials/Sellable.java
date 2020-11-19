package materials;

import java.math.BigDecimal;

public abstract class Sellable {
	
	private String slotLocation;
	private String name;
	private BigDecimal price;
	private int quantity = 5;
	
	public Sellable(String slotLocation, String name, BigDecimal price) {
		super();
		this.slotLocation = slotLocation;
		this.name = name;
		this.price = price;
	}
	
	public String getSlotLocation() {
		return this.slotLocation;
	}

	public String getName() {
		return this.name;
	}

	public BigDecimal getPrice() {
		return this.price;
	}

	public int getQuantity() {
		return this.quantity;
	}
	
	public int decreaseQuantity() {
		this.quantity -= 1;
		return this.quantity;
	}

	public abstract String printMethod();

	
	
	

}
