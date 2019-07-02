package com.coding.sales.input;

import java.math.BigDecimal;

public class Product_002003 extends Product{
	public static final String productName = "中国银象棋12g";
	public static final String productNo = "002003";
	public static final BigDecimal price = new BigDecimal(698.00);
	public static final BigDecimal discount = new BigDecimal(0.90);
	public static final BigDecimal full_3000 = new BigDecimal(3000);
	public static final BigDecimal full_2000 = new BigDecimal(2000);
	public static final BigDecimal full_1000 = new BigDecimal(1000);
	public static final BigDecimal full_3000_multi = new BigDecimal(350);
	public static final BigDecimal full_2000_multi = new BigDecimal(30);
	public static final BigDecimal full_1000_multi = new BigDecimal(10);
	
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
		BigDecimal preOffer_Price= new BigDecimal(0.0);
		BigDecimal offer_Price = new BigDecimal(0.0);
		
		preOffer_Price = amount.multiply(price);
		fullReduction_Price = preOffer_Price.divide(full_3000,0,BigDecimal.ROUND_DOWN).multiply(full_3000_multi).
				add(preOffer_Price.divideAndRemainder(full_3000)[1].divide(full_2000,0,BigDecimal.ROUND_DOWN).multiply(full_2000_multi)).add
				(preOffer_Price.divideAndRemainder(full_3000)[1].divide(full_2000,0,BigDecimal.ROUND_DOWN).divideAndRemainder(full_2000)[1].divide(full_1000,0,BigDecimal.ROUND_DOWN).multiply(full_1000_multi));
		
		if(isDisconut){
			disconut_Price = price.multiply(new BigDecimal(1).subtract(discount));
		}

		
		if(fullReduction_Price.compareTo(disconut_Price) > 0){
			offer_Price = fullReduction_Price;
		}else{
			offer_Price = disconut_Price;
		}
		
		actual_Price = preOffer_Price.subtract(offer_Price);
		return actual_Price;
	} 
}
