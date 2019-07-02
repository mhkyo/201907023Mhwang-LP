package com.coding.sales.input;

import java.math.BigDecimal;

public class Customer {
	//姓名,等级,卡号,积分
    private String Name= "";
    private String Level = "";
    private String CardNo = "";
    private BigDecimal Score = new BigDecimal(0);
    private String OldLevel = "";
    
    public String getOldLevel() {
		return OldLevel;
	}

	public void setOldLevel(String oldLevel) {
		OldLevel = oldLevel;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getLevel() {
		return Level;
	}

	public void setLevel(String level) {
		Level = level;
	}

	public String getCardNo() {
		return CardNo;
	}

	public void setCardNo(String cardNo) {
		CardNo = cardNo;
	}

	public BigDecimal getScore() {
		return Score;
	}

	public void setScore(BigDecimal score) {
		Score = score;
	}

	public static String getName(String CardNo){
		if("6236609999".equals(CardNo))
			return "马丁";
		else if("6630009999".equals(CardNo))
			return "王立";
		else if("8230009999".equals(CardNo))
			return "李想";
		else if("9230009999".equals(CardNo))
			return "张三";
		else
			return "";
	}
	
	public static String getLevel(String CardNo){
		if("6236609999".equals(CardNo))
			return "普卡";
		else if("6630009999".equals(CardNo))
			return "金卡";
		else if("8230009999".equals(CardNo))
			return "白金卡";
		else if("9230009999".equals(CardNo))
			return "钻石卡";
		else
			return "";
	}
	
	public static BigDecimal getScore(String CardNo){
		if("6236609999".equals(CardNo))
			return new BigDecimal(9860);
		else if("6630009999".equals(CardNo))
			return new BigDecimal(48860);
		else if("8230009999".equals(CardNo))
			return new BigDecimal(98860);
		else if("9230009999".equals(CardNo))
			return new BigDecimal(198860);
		else
			return new BigDecimal(0);
	}

	public Customer(String product, BigDecimal amount) {
		
		
    }
    
}
