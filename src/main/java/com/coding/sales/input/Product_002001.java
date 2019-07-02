package com.coding.sales.input;

import java.math.BigDecimal;

public class Product_002001 extends Product{
	public static final String productName = "守扩之羽比翼双飞4.8g";
	public static final String productNo = "002001";
	public static final BigDecimal price = new BigDecimal(1080.00);
	public static final BigDecimal discount = new BigDecimal(0.95);
	public static final BigDecimal full_num = new BigDecimal(3);
	
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
		if(amount.compareTo(full_num) == 0){
			fullReduction_Price = price.divide(new BigDecimal(2),2);
		}else if(amount.compareTo(full_num) > 0){
			fullReduction_Price = price;
		}
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
