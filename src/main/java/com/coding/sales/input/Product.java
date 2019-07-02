package com.coding.sales.input;

import java.math.BigDecimal;

public abstract class Product {
	public  String productName;
	public BigDecimal price;

	
	
	public Product() {
	}



	public abstract BigDecimal getPrice(BigDecimal amount,boolean isDisconut); 
	public abstract BigDecimal getPrice();
	public abstract String getProductName();
}
