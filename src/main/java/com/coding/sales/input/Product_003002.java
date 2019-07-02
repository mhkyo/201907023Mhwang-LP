package com.coding.sales.input;

import java.math.BigDecimal;

public class Product_003002 extends Product{
    String ID = "003002";
    String Name = "水晶之恋";
    String Unit = "条";
    BigDecimal price = new BigDecimal(980);
    String PriceOff = "";
    String PriceCut = "";
    boolean isCutOff = false;

    public Product_003002() {
    }

	public String getProductName() {
		return productName;
	}

	public String getID() {
		return ID;
	}


	public void setID(String iD) {
		ID = iD;
	}


	public String getName() {
		return Name;
	}


	public void setName(String name) {
		Name = name;
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

	public BigDecimal getPrice(BigDecimal num,boolean isCutoff){
		BigDecimal actual_Price = new BigDecimal(0.0);
		BigDecimal disconut_Price = new BigDecimal(0.95);
		BigDecimal fullReduction_Price = new BigDecimal(0.0);
		BigDecimal preOffer_Price = new BigDecimal(0.0);
		isCutOff = isCutoff;
		
		preOffer_Price = price.multiply(num);
		fullReduction_Price = (preOffer_Price.divide(new BigDecimal(2000),2)).multiply(new BigDecimal(30))
				.add(preOffer_Price.divideAndRemainder(new BigDecimal(2000))[1].divide(new BigDecimal(1000),2))
				.multiply(new BigDecimal(10));
		if(isCutOff)
			fullReduction_Price = fullReduction_Price.multiply(disconut_Price);
		actual_Price = preOffer_Price.subtract(fullReduction_Price);
		return actual_Price;
	} 
}
