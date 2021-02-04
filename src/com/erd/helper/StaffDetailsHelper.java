package com.erd.helper;

import java.sql.Connection;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.erd.utilities.QueryUtil;


public class StaffDetailsHelper extends com.erd.utilities.StreeboHelper {
	
	public StaffDetailsHelper(JSONObject requestObj, Connection mainCon) {
		super(requestObj, mainCon);
	}

	public JSONObject insertStaffDetails() throws JSONException, SQLException {
		JSONObject ret = new JSONObject();
		System.out.println("requestObj: "+requestObj.toString());
		
		/*
		 *Performing insert operation on the table using the data received from the requestObj
	     */
		QueryUtil.executeInsert(mainCon, requestObj.getJSONObject("STAFF").getJSONObject("set"), "STAFF");
		
		return ret;
	}
	
	public JSONObject deleteStaffDetails() throws JSONException, SQLException {
		JSONObject ret = new JSONObject();
		System.out.println("requestObj: "+requestObj.toString());
		
		boolean exist = (QueryUtil.executeQuery(mainCon, "select * from STAFF where STAFF_ID = ?", new JSONArray().put(requestObj.getJSONObject("STAFF").getJSONObject("where").get("STAFF_ID")), false).length() > 0) ? true : false;
		
		/*
		 *Performing delete operation on the table if data exist
	     */
		if(exist) {
			QueryUtil.executeQuery(mainCon, "delete from STAFF where STAFF_ID = ?", new JSONArray().put(requestObj.getJSONObject("STAFF").getJSONObject("where").get("STAFF_ID")));
			ret.put("exist", true);
		} else {
			ret.put("exist", false);
		}
		
		return ret;
	}
	
	public JSONObject selectStaffDetails() throws JSONException, SQLException {
		JSONObject ret = new JSONObject();
		System.out.println("requestObj: "+requestObj.toString());
		
		boolean exist = (QueryUtil.executeQuery(mainCon, "select * from STAFF where STAFF_ID = ?", new JSONArray().put(requestObj.getJSONObject("STAFF").getJSONObject("where").get("STAFF_ID")), false).length() > 0) ? true : false;
		
		/*
		 *Performing select operation on the table if data exist
	     */
		if(exist) {
			JSONArray staffData = QueryUtil.executeQuery(mainCon, "select * from STAFF where STAFF_ID = ?", new JSONArray().put(requestObj.getJSONObject("STAFF").getJSONObject("where").get("STAFF_ID")), false);
			ret.put("exist", true);
			ret.put("staffData", staffData);
		} else {
			ret.put("exist", false);
		}
		return ret;
	}
	
	public JSONObject updateStaffDetails() throws JSONException, SQLException {
		JSONObject ret = new JSONObject();
		System.out.println("requestObj: "+requestObj.toString());
		
		boolean exist = (QueryUtil.executeQuery(mainCon, "select * from STAFF where STAFF_ID = ?", new JSONArray().put(requestObj.getJSONObject("STAFF").getJSONObject("where").get("STAFF_ID")), false).length() > 0) ? true : false;
		
		/*
		 *Performing update operation on the table if data exist
	     */
		if(exist) {
			QueryUtil.executeUpdate(mainCon, requestObj.getJSONObject("STAFF").getJSONObject("set"), "STAFF", requestObj.getJSONObject("STAFF").getJSONObject("where"));
			ret.put("exist", true);
		} else {
			ret.put("exist", false);
		}
		
		return ret;
	}
	
	public JSONObject getStaffInfo() throws JSONException, SQLException {
		JSONObject ret = new JSONObject();
		System.out.println("requestObj: "+requestObj.toString());
		
		boolean exist = (QueryUtil.executeQuery(mainCon, "select STAFF_ID from STAFF").length() > 0) ? true : false;
		
		if(exist) {
			JSONArray staffData = QueryUtil.executeQuery(mainCon, "select STAFF_ID from STAFF");
			ret.put("exist", true);
			ret.put("staffData", staffData);
		} else {
			ret.put("exist", false);
		}
		
		return ret;
	}
	
	public JSONObject getadminInfo() throws JSONException, SQLException {
		JSONObject ret = new JSONObject();
		System.out.println("requestObj: "+requestObj.toString());
		
		boolean exist = (QueryUtil.executeQuery(mainCon, "select * from ADMIN").length() > 0) ? true : false;
		
		if(exist) {
			JSONArray staffData = QueryUtil.executeQuery(mainCon, "select * from ADMIN");
			ret.put("exist", true);
			ret.put("staffData", staffData);
		} else {
			ret.put("exist", false);
		}
		
		return ret;
	}
}