package com.coding.sales.input;

import java.math.BigDecimal;

public class Product_002002 extends Product {
	public static final String productName = "中国经典钱币套装";
	public static final String productNo = "002002";
	public static final BigDecimal price = new BigDecimal(998.00);
	public static final BigDecimal full_2000 = new BigDecimal(2000);
	public static final BigDecimal full_1000 = new BigDecimal(1000);
	public static final BigDecimal full_2000_multi = new BigDecimal(30.00);
	public static final BigDecimal full_1000_multi = new BigDecimal(10.00);
	
	public BigDecimal getPrice() {
		return price;
	}
	public String getProductName() {
		return productName;
	}
	public BigDecimal getPrice(BigDecimal amount,boolean isDisconut){
		BigDecimal actual_Price = new BigDecimal(0.0);
		BigDecimal disconut_Price = new BigDecimal(0.0);
		BigDecimal fullReduction_Price = new BigDecimal(0.0);
		BigDecimal preOffer_Price=new BigDecimal(0.0);
		
		preOffer_Price = amount.multiply(price);
		fullReduction_Price = (preOffer_Price.divide(full_2000,0,BigDecimal.ROUND_DOWN)).multiply(full_2000_multi)
				.add(preOffer_Price.divideAndRemainder(full_2000)[1].divide(full_1000.multiply(full_1000_multi),0,BigDecimal.ROUND_DOWN));
		actual_Price = preOffer_Price.subtract(fullReduction_Price);
		return actual_Price;
	}


}
