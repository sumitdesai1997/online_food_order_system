package com.erd.utilities;

import java.sql.Connection;

import org.json.JSONObject;

public class StreeboHelper {
	protected JSONObject requestObj;
	protected Connection mainCon;
	public StreeboHelper(JSONObject requestObj,Connection mainCon) {
		// TODO Auto-generated constructor stub
		this.requestObj = requestObj;
		this.mainCon = mainCon;
	} 
	
	public JSONObject call() throws Exception{
		return null;
	}


}
