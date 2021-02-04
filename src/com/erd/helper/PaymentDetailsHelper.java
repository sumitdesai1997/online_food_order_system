package com.erd.helper;

import java.sql.Connection;
import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;
import com.erd.utilities.QueryUtil;


public class PaymentDetailsHelper extends com.erd.utilities.StreeboHelper {
	
	public PaymentDetailsHelper(JSONObject requestObj, Connection mainCon) {
		super(requestObj, mainCon);
	}
	
	public JSONObject savePaymentDetails() throws JSONException, SQLException {
		JSONObject ret = new JSONObject();
		System.out.println("requestObj: "+requestObj.toString());
		
		/*
		 *Performing insert operation on the table using the data received from the requestObj
	     */
		QueryUtil.executeInsert(mainCon, requestObj.getJSONObject("PAYMENT").getJSONObject("set"), "PAYMENT");
		
		return ret;
	}
}