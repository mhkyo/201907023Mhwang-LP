package com.coding.sales.input;

import java.math.BigDecimal;

public class Product_001001 extends Product{
	public static final String ID = "001001";
	public static final String productName = "世园会五十国钱币册";
	public static final String Unit = "册";
	public static final BigDecimal price = new BigDecimal(998);
	public static final String PriceOff = "";
	public static final String PriceCut = "";
	public static boolean isCutOff = false;


    public Product_001001() {
    }
    
	public String getProductName() {
		return productName;
	}
    


	public BigDecimal getPrice(BigDecimal num,boolean isCutoff){
		BigDecimal actual_Price = new BigDecimal(0.0);
		BigDecimal disconut_Price = new BigDecimal(0.0);
		BigDecimal fullReduction_Price = new BigDecimal(0.0);
		BigDecimal preOffer_Price = new BigDecimal(0.0);
		isCutOff = isCutoff;
		
		preOffer_Price = price.multiply(num);
		if(isCutOff)
			fullReduction_Price = preOffer_Price.multiply(new BigDecimal(1).subtract(disconut_Price));
		actual_Price = preOffer_Price.subtract(fullReduction_Price);
		return actual_Price;
	}


	public BigDecimal getPrice() {
		return price;
	}



}
