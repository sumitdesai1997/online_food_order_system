package com.erd.utilities;

import java.lang.reflect.Constructor;

import java.lang.reflect.Method;
import java.sql.Connection;

import org.json.JSONObject;
import com.erd.utilities.StreeboHelper;
import com.erd.utilities.DatabaseConnectionHelper;
 
public class Controller {
	
	private JSONObject config;
	public Controller(JSONObject config){
		this.config = config;
	}
	
	
	public JSONObject call() throws Exception{
		JSONObject responseObj = null;
		JSONObject response = null;
		Connection soCon = null;
		try { 
			soCon = DatabaseConnectionHelper.getSOConnection();
			String packagename = "com.erd.helper";
			JSONObject request = config.getJSONObject("request");
			
			Class<?> cl = Class.forName(packagename  + "." + request.getString("helper") + "Helper");
			Constructor<?> con = cl.getConstructor(JSONObject.class,Connection.class);
			StreeboHelper dtHelper = (StreeboHelper)con.newInstance(request.getJSONObject("data"),soCon);
			Method method = dtHelper.getClass().getMethod(request.get("procedure").toString());
			response = (JSONObject)method.invoke(dtHelper, null);
		}
		catch (Exception e) {
			responseObj = new JSONObject();
			responseObj.put("status", 500);
			responseObj.put("data", "");
			e.printStackTrace();
			return responseObj;
		}finally{		
				soCon.close();    
		}
		if(response == null ){
			responseObj = new JSONObject();
			responseObj.put("status", 500);
			responseObj.put("data", "");
		} else {		
			responseObj = new JSONObject();
			responseObj.put("status", 200);
			responseObj.put("data", response);
		}
		
		return responseObj;
	}

}
