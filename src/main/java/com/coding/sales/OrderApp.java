package com.coding.sales;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONObject;

import com.coding.sales.input.Customer;
import com.coding.sales.input.OrderCommand;
import com.coding.sales.input.OrderItemCommand;
import com.coding.sales.input.PaymentCommand;
import com.coding.sales.input.Product;
import com.coding.sales.output.DiscountItemRepresentation;
import com.coding.sales.output.OrderItemRepresentation;
import com.coding.sales.output.OrderRepresentation;
import com.coding.sales.output.PaymentRepresentation;

/**
 * 销售系统的主入口
 * 用于打印销售凭证
 */
public class OrderApp {

    public static void main(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("参数不正确。参数1为销售订单的JSON文件名，参数2为待打印销售凭证的文本文件名.");
        }

        String jsonFileName = args[0];
        String txtFileName = args[1];

        String orderCommand = FileUtils.readFromFile(jsonFileName);
        OrderApp app = new OrderApp();
        String result = app.checkout(orderCommand);
        FileUtils.writeToFile(result, txtFileName);
    }

    public String checkout(String orderCommand) {
        OrderCommand command = OrderCommand.from(orderCommand);
        OrderRepresentation result = checkout(command);
        
        return result.toString();
    }

    OrderRepresentation checkout(OrderCommand command) {
        OrderRepresentation result = null;

        //TODO: 请完成需求指定的功能
        String orderId = command.getOrderId();
        String memberId = command.getMemberId();
        String sCreateTime = command.getCreateTime();
        String memberName = Customer.getName(memberId);
        String oldMemberType = Customer.getLevel(memberId);
        String newMemberType = "";
        BigDecimal oldMemberPoints = Customer.getScore(memberId);
        BigDecimal memberPointsIncreased = new BigDecimal(0);
        BigDecimal memberPoints = new BigDecimal(0);
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date createTime = null;
        try {
        	createTime = sdf.parse(sCreateTime);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
        List<OrderItemCommand> items = command.getItems();
        List<PaymentCommand> payments = command.getPayments();
        List<String> discounts = command.getDiscounts();
        boolean isDisconut_9 = discounts.contains("9折券");
        boolean isDisconut_95 = discounts.contains("95折券");
        boolean isDisconut = false;
        List<OrderItemRepresentation> orderItemRepresentations = new ArrayList<OrderItemRepresentation>();
        List<DiscountItemRepresentation> discountItemRepresentations = new ArrayList<DiscountItemRepresentation>();
        List<PaymentRepresentation> paymentRepresentations = new ArrayList<PaymentRepresentation>();
        
        for(PaymentCommand payCommand : payments){
        	PaymentRepresentation payment_Representation = new PaymentRepresentation(payCommand.getType(),payCommand.getAmount());
        	paymentRepresentations.add(payment_Representation);
        }
        
        BigDecimal totalPrice = new BigDecimal(0);
        BigDecimal acTotalPrice = new BigDecimal(0);
        BigDecimal totalDiscountPrice = new BigDecimal(0);
        
        for(OrderItemCommand order : items){
        	Class<?> c ;
        	Product obj = null;
        	String product = order.getProduct();
            BigDecimal amount = order.getAmount();
            String className = "com.coding.sales.input.Product_"+product;
            try {
				c = Class.forName(className);
				obj=(Product)c.newInstance();
			} catch (Exception e) {
				e.printStackTrace();
			} 
            if((isDisconut_9 && ("001002".equals(product) || "002003".equals(product))) 
            		|| (isDisconut_95 && ("003001".equals(product) || "002001".equals(product)))){
            	isDisconut = true;
            }
            BigDecimal preOffer_Price = amount.multiply(obj.getPrice());
            OrderItemRepresentation  order_representation = new OrderItemRepresentation(product,obj.getProductName(),obj.getPrice(),amount,preOffer_Price);
            orderItemRepresentations.add(order_representation);
            
            BigDecimal actual_Price = obj.getPrice(amount, isDisconut);
            totalPrice = totalPrice.add(amount.multiply(obj.getPrice()));
            System.out.println(totalPrice);
            if(preOffer_Price.compareTo(actual_Price) > 0){
            	DiscountItemRepresentation discount_representation = new DiscountItemRepresentation(product,obj.getProductName(),preOffer_Price.subtract(actual_Price));
            	discountItemRepresentations.add(discount_representation);
            	totalDiscountPrice = totalDiscountPrice.add(preOffer_Price.subtract(actual_Price));
            }
        
            acTotalPrice = acTotalPrice.add(actual_Price);
            
        }
        memberPointsIncreased = acTotalPrice;
        memberPoints = memberPointsIncreased.add(oldMemberPoints);
        result = new OrderRepresentation(orderId, createTime, memberId, memberName, oldMemberType , memberId, memberPointsIncreased.intValue(), memberPoints.intValue(), orderItemRepresentations, totalPrice, discountItemRepresentations, totalDiscountPrice, acTotalPrice, paymentRepresentations, discounts);
                
        return result;
    }
    
    /**
	 * 获取Json中的值
	 * @param json
	 * @param name
	 * @return
	 */
	private String getStringFromJson(JSONObject json,String name){
		String value = "";
		if(json != null) {
			value = json.getString(name);
			if(value == null) value = "";
		}
		return value;
	}
    
    /**
	 * 获取子Json
	 * @param json
	 * @param name
	 * @return
	 */
	private JSONObject getChildJson(JSONObject json,String name){
		JSONObject value = null;
		if(json != null) {
			value = json.getJSONObject(name);
		}
		return value;
	}
}
