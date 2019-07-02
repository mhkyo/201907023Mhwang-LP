package com.coding.sales;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.coding.sales.input.OrderCommand;
import com.coding.sales.output.OrderRepresentation;

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
        
        List<String> items_List = new ArrayList<String>();
        List<String> payments_List = new ArrayList<String>();
        JSONObject command_Json = new JSONObject(command);
        String orderId = getStringFromJson(command_Json,"orderId");
        String memberId = getStringFromJson(command_Json,"memberId");
        String createTime = getStringFromJson(command_Json,"createTime");
        System.out.println("orderId="+orderId);
        JSONArray payments_Json = getJsonArray(command_Json,"payments");
        if(payments_Json != null && payments_Json.length() > 0){
			for(int i=0;i<payments_Json.length();i++){
				JSONObject json = payments_Json.getJSONObject(i);
				String type = getStringFromJson(json,"type");
				int amount = json.getInt("amount");
				payments_List.add(type+"@"+amount);
			}
        }
        System.out.println("payments_List="+payments_List);
        String discountCards = getStringFromJson(command_Json,"discountCards");
        System.out.println("discountCard="+discountCards);
        
        JSONArray items = getJsonArray(command_Json,"items");
        if(items != null && items.length() > 0){
			for(int i=0;i<items.length();i++){
				JSONObject json = items.getJSONObject(i);
				String product = getStringFromJson(json,"product");
				double amount = json.getDouble("amount");
				items_List.add(product+"@"+amount);
			}
        }
        System.out.println("items_List="+items_List.toString());
        
        
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
		if(json != null && json.has(name)) {
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
		if(json != null && json.has(name)) {
			value = json.getJSONObject(name);
		}
		return value;
	}
	
	/**
	 * 获取子Json
	 * @param json
	 * @param name
	 * @return
	 */
	private JSONArray getJsonArray(JSONObject json,String name){
		JSONArray value = null;
		if(json != null && json.has(name)) {
			value = json.getJSONArray(name);
		}
		return value;
	}
}
