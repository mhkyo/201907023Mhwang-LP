package com.coding.sales.input;

import java.math.BigDecimal;

public class Product_001002 extends Product{
    String ID = "001002";
    String productName = "2019北京世园会纪念银章大全40g";
    String Unit = "盒";
    BigDecimal price = new BigDecimal(1380);
    String PriceOff = "";
    String PriceCut = "";
    boolean isCutOff = false;


    public Product_001002() {
    }


	public String getID() {
		return ID;
	}


	public void setID(String iD) {
		ID = iD;
	}




	public boolean isCutOff() {
		return isCutOff;
	}


	public void setCutOff(boolean isCutOff) {
		this.isCutOff = isCutOff;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	public String getUnit() {
		return Unit;
	}


	public void setUnit(String unit) {
		Unit = unit;
	}


	public BigDecimal getPrice() {
		return price;
	}


	public void setPrice(BigDecimal price) {
		this.price = price;
	}


	public String getPriceOff() {
		return PriceOff;
	}


	public void setPriceOff(String priceOff) {
		PriceOff = priceOff;
	}


	public String getPriceCut() {
		return PriceCut;
	}


	public void setPriceCut(String priceCut) {
		PriceCut = priceCut;
	}

	public String getProductName() {
		return productName;
	}
	public BigDecimal getPrice(BigDecimal num,boolean isCutoff){
		BigDecimal actual_Price = new BigDecimal(0.0);
		BigDecimal disconut_Price = new BigDecimal(0.90);
		BigDecimal fullReduction_Price = new BigDecimal(0.0);
		BigDecimal preOffer_Price = new BigDecimal(0.0);
		isCutOff = isCutoff;
		
		preOffer_Price = price.multiply(num);
		if(isCutOff)
			fullReduction_Price = preOffer_Price.multiply(new BigDecimal(1).subtract(disconut_Price));
		actual_Price = preOffer_Price.subtract(fullReduction_Price);
		return actual_Price;
	} 
}
