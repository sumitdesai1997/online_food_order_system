package com.erd.helper;

import java.sql.Connection;
import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;
import com.erd.utilities.QueryUtil;


public class OrderDetailsHelper extends com.erd.utilities.StreeboHelper {
	
	public OrderDetailsHelper(JSONObject requestObj, Connection mainCon) {
		super(requestObj, mainCon);
	}
	
	public JSONObject saveOrderDetails() throws JSONException, SQLException {
		JSONObject ret = new JSONObject();
		System.out.println("requestObj: "+requestObj.toString());
		
		/*
		 *Performing insert operation on the table using the data received from the requestObj
	     */
		QueryUtil.executeInsert(mainCon, requestObj.getJSONObject("FOOD_ORDER").getJSONObject("set"), "FOOD_ORDER");
		
		if(requestObj.has("ORDER_VEG")) {
			QueryUtil.executeInsert(mainCon, requestObj.getJSONObject("ORDER_VEG").getJSONObject("set"), "ORDER_VEG");
		}
		
		if(requestObj.has("ORDER_NON_VEG")) {
			QueryUtil.executeInsert(mainCon, requestObj.getJSONObject("ORDER_NON_VEG").getJSONObject("set"), "ORDER_NON_VEG");
		}
		
		return ret;
	}
}