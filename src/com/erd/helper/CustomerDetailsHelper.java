package com.erd.helper;

import java.sql.Connection;
import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;
import com.erd.utilities.QueryUtil;


public class CustomerDetailsHelper extends com.erd.utilities.StreeboHelper {
	
	public CustomerDetailsHelper(JSONObject requestObj, Connection mainCon) {
		super(requestObj, mainCon);
	}

	public JSONObject saveCustomerDetails() throws JSONException, SQLException {
		JSONObject ret = new JSONObject();
		System.out.println("requestObj: "+requestObj.toString());
		
		/*
		 *Performing insert operation on the table using the data received from the requestObj
	     */
		QueryUtil.executeInsert(mainCon, requestObj.getJSONObject("CUSTOMER").getJSONObject("set"), "CUSTOMER");
		QueryUtil.executeInsert(mainCon, requestObj.getJSONObject("CUSTOMER_CONTACT_NO").getJSONObject("set"), "CUSTOMER_CONTACT_NO");
		
		if(requestObj.has("ALT_CONTACT_NO")) {
			QueryUtil.executeInsert(mainCon, requestObj.getJSONObject("ALT_CONTACT_NO").getJSONObject("set"), "CUSTOMER_CONTACT_NO");
		}
		
		return ret;
	}
}