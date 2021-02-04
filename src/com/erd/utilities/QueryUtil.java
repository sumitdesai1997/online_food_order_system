package com.erd.utilities;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class QueryUtil {
	
	private static JSONObject queryList = new JSONObject();
	
	private static JSONObject queryCache = new JSONObject();
	
	public static JSONArray executeQuery(Connection con, String name,JSONArray params,boolean cache) throws SQLException, JSONException {
		  
		  if(queryCache.has(name) && cache){
			  return queryCache.getJSONArray(name);
		  }
		  
		  String query = QueryUtil.getQueryFromName(name);
		  if(query == "")
			  query = name;
		  
		  System.out.println("QUERY: "+query);
		  System.out.println("PARAMETERS: "+params);
		  
		  PreparedStatement ps = con.prepareStatement(query);
		  JSONArray res = new JSONArray();
		  
		  //JSONArray params = config.getJSONArray("parameters");
		  if(params != null){
			  for(int i=1; i <= params.length() ; i++){
				  if(params.get(i-1) == null){
					  ps.setString(i, "");
				  }else if(params.get(i-1) instanceof String){				  
					  ps.setString(i, (String)params.get(i-1));
				  }else if(params.get(i-1) instanceof Integer){
					  ps.setInt(i, (int)params.getInt(i-1));
				  }else if(params.get(i-1) instanceof Date){
//					  ps.setDate(i, new Date();
				  }else if(params.get(i-1) instanceof Character){
					  ps.setString(i, (String)params.get(i-1));
				  }else if(params.get(i-1) instanceof Long){
					  ps.setLong(i, (Long)params.get(i-1));
				  }else if(params.get(i-1) instanceof Timestamp){
					  ps.setTimestamp(i, (Timestamp)params.get(i-1));
				  }else if(params.get(i-1) instanceof Boolean){
					  ps.setBoolean(i, (Boolean)params.get(i-1));
				  }else if(params.get(i-1) instanceof Double){
					  ps.setDouble(i, (Double)params.get(i-1));
				  }else if(params.get(i-1) instanceof Float){
					  ps.setFloat(i, (Float)params.get(i-1));
				  }
			  }
		  }
		 
		  ResultSet rs = ps.executeQuery();
		  //System.out.println("Resultset  = "+ rs);
		  res = resultSetToJSONArray(rs);
		  System.out.println("RESULT"+res+"\n");
		  if(cache)
			  queryCache.put(name,res);
		  
		  return res;
	}
	
	public static JSONArray executeQuery(Connection con, String query) throws SQLException, JSONException {
		  
			System.out.println("QUERY: "+query);
		  PreparedStatement ps = con.prepareStatement(query);
		  JSONArray res = new JSONArray();
		  
		  ResultSet rs = ps.executeQuery();
		  res = resultSetToJSONArray(rs);
		  return res;
	}
	
	// to execute update and insert
	public static int executeQuery(Connection con, String name,JSONArray params) throws SQLException, JSONException {
		  
		  String query = QueryUtil.getQueryFromName(name);
		  if(query.equals(""))
			  query = name;
		  
		  System.out.println("QUERY: "+query);
		  System.out.println("PARAMETERS "+params);
		  
		  PreparedStatement ps = con.prepareStatement(query);
		  
		  if(params != null){
			  for(int i=1; i <= params.length() ; i++){
				  if(params.get(i-1) instanceof String){				  
					  ps.setString(i, (String)params.get(i-1));
				  }else if(params.get(i-1) instanceof Integer){
					  ps.setInt(i, (int)params.get(i-1));
				  }else if(params.get(i-1) instanceof Date){
					  ps.setDate(i, (Date)params.get(i-1));
				  }else if(params.get(i-1) instanceof Character){
					  ps.setString(i, (String)params.get(i-1));
				  }else if(params.get(i-1) instanceof Long){
					  ps.setLong(i, (Long)params.get(i-1));
				  }else if(params.get(i-1) instanceof Timestamp){
					  ps.setTimestamp(i, (Timestamp)params.get(i-1));
				  }else if(params.get(i-1) instanceof Boolean){
					  ps.setBoolean(i, (Boolean)params.get(i-1));
				  }else if(params.get(i-1) instanceof Double){
					  ps.setDouble(i, (Double)params.get(i-1));
				  }else if(params.get(i-1) instanceof Float){
					  ps.setFloat(i, (Float)params.get(i-1));
				  }else{
					  ps.setString(i,null);
				  }
			  }
		  }
		  
		  int rs = ps.executeUpdate();
		  System.out.println("RESULT = "+rs);
		  return rs;
	}
	
	public static JSONArray executeQuery(Connection con, JSONObject config) throws SQLException, JSONException {
		  
		  PreparedStatement ps = con.prepareStatement(config.getString("query"));
		//  Statement ps = con.createStatement();
		  JSONArray res = new JSONArray();
		  
		  JSONArray params = config.getJSONArray("parameters");
		  
		  for(int i=1; i <= params.length() ; i++){
		//   System.out.println(params.get(i-1) + "  " + params.get(i-1).getClass().getName());
			  if(params.get(i-1) instanceof String){				  
				  ps.setString(i, (String)params.get(i-1));
			  }else if(params.get(i-1) instanceof Integer){
				  ps.setInt(i, (int)params.get(i-1));
			  }else if(params.get(i-1) instanceof Date){
//				  ps.setDate(i, new Date();
			  }else if(params.get(i-1) instanceof Character){
				  ps.setString(i, (String)params.get(i-1));
			  }else if(params.get(i-1) instanceof Long){
				  ps.setLong(i, (Long)params.get(i-1));
			  }else if(params.get(i-1) instanceof Timestamp){
				  ps.setTimestamp(i, (Timestamp)params.get(i-1));
			  }else if(params.get(i-1) instanceof Boolean){
				  ps.setBoolean(i, (Boolean)params.get(i-1));
			  }else if(params.get(i-1) instanceof Double){
				  ps.setDouble(i, (Double)params.get(i-1));
			  }else if(params.get(i-1) instanceof Float){
				  ps.setFloat(i, (Float)params.get(i-1));
			  }else{
				  ps.setString(i, params.get(i-1).toString());
			  }
		  }
		   
		//  ResultSet rs = ps.executeQuery("select distinct sis_industries from "+ Utilities.LPDATABASE +".lp_sis_occupation order by sis_industries asc");
		  System.out.println(config.getString("query"));
		  System.out.println(config.getJSONArray("parameters"));
		  ResultSet rs = ps.executeQuery();
		  
		  res = resultSetToJSONArray(rs);
		  System.out.println("Result "+res);
		  return res;
	}
	
	public static void setQueryList(JSONObject queryList) throws JSONException{
		
		Iterator<String> itr = queryList.keys();
		while(itr.hasNext()){
			String key = itr.next();
			QueryUtil.queryList.put(key, queryList.get(key));
		}
		
	}
	
	public static JSONObject getQueryList(){
		return QueryUtil.queryList;
	}
	
	public static String getQueryFromName(String name){
		
		String query = "";
		
		try{
			
			if(queryList.has(name)){
				query = queryList.getString(name);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return query;
		
	}
	
	public static String getSisId(Connection con,String oppId) throws SQLException, JSONException{
		JSONArray result = QueryUtil.executeQuery(con, new JSONObject().put("query", "select sis_id from so_opportunity where opportunity_id = ?").put("parameters",new JSONArray().put(oppId)));
		if(result.length() > 0){
			return result.getJSONObject(0).optString("SIS_ID");
		}
		return "";
	}
	
	public static String getLeadId(Connection con,String oppId) throws SQLException, JSONException{
		JSONArray result = QueryUtil.executeQuery(con, new JSONObject().put("query", "select lead_id from so_opportunity where opportunity_id = ?").put("parameters",new JSONArray().put(oppId)));
		if(result.length() > 0){
			return result.getJSONObject(0).optString("LEAD_ID");
		}
		return "";
	}
	
	public static String getAgentcd(Connection con,String oppId) throws SQLException, JSONException{
		JSONArray result = QueryUtil.executeQuery(con, new JSONObject().put("query", "select AGENT_CD from so_opportunity where opportunity_id = ?").put("parameters",new JSONArray().put(oppId)));
		if(result.length() > 0){
			return result.getJSONObject(0).optString("AGENT_CD");
		}
		return "";
	}
	
	public static String getPglId(Connection con,String oppId) throws SQLException, JSONException{
		JSONArray result = QueryUtil.executeQuery(con, new JSONObject().put("query", "select pgl_id from so_opportunity where opportunity_id = ?").put("parameters",new JSONArray().put(oppId)));
		if(result.length() > 0){
			return result.getJSONObject(0).optString("PGL_ID");
		}
		return "";
	}
	
	public static String getApplicationId(Connection con,String oppId) throws SQLException, JSONException{
		JSONArray result = QueryUtil.executeQuery(con, new JSONObject().put("query", "select application_id from so_opportunity where opportunity_id = ?").put("parameters",new JSONArray().put(oppId)));
		if(result.length() > 0){
			return result.getJSONObject(0).optString("APPLICATION_ID");
		}
		return "";
	}
	
	public static String getPolicyNum(Connection con,String oppId) throws SQLException, JSONException{
		JSONArray result = QueryUtil.executeQuery(con, new JSONObject().put("query", "select POLICY_NO from so_opportunity where opportunity_id = ?").put("parameters",new JSONArray().put(oppId)));
		if(result.length() > 0){
			return result.getJSONObject(0).optString("POLICY_NO");
		}
		return "";
	}
	
	public static String getOppId(Connection con,String id,String column) throws SQLException, JSONException{
		JSONArray result = QueryUtil.executeQuery(con, new JSONObject().put("query", "select opportunity_id from so_opportunity where "+column+" = ?").put("parameters",new JSONArray().put(id)));
		if(result.length() > 0){
			return result.getJSONObject(0).optString("OPPORTUNITY_ID");
		}
		return "";
	}
	
	public static void executeInsert(Connection mainCon, JSONObject requestObj,String table) throws JSONException, SQLException{
		String insertQueryField = "";
		String insertQuery = "";
		JSONArray insertParam = new JSONArray();
		Iterator<String> keyItr = requestObj.keys();
		while(keyItr.hasNext()){
			String key = keyItr.next();
			
			if(key.equals("MODIFIED_DATE")){
				insertQuery += "CURRENT_DATE,";
			}else if( key.indexOf("DOB") > -1){
				insertQuery = insertQuery+"to_date(?,'dd/mm/yyyy'),";
				insertParam.put(requestObj.get(key));
			}
			else if(key.equals("EMAIL_TIMSTAMP") ||  key.equals("CREATED_DATE") || key.equals("SIGNED_TIMESTAMP") || key.equals("LEAD_CREATED_DTTM") || key.equals("O_TIMESTAMP") || key.equals("P_TIMESTAMP")){
				insertQuery += "CURRENT_TIMESTAMP,";
			}else if(key.equals("TRANSACTION_DATE")){
				insertQuery = insertQuery+"to_timestamp(?,'DD-mm-yyyy HH24:MI:SS'),";
				insertParam.put(requestObj.get(key));
			}
			else{					
				insertQuery = insertQuery+"?,";
				insertParam.put(requestObj.get(key));
			}
			
			insertQueryField = insertQueryField+key+",";
			
		}
		insertQueryField = insertQueryField.substring(0,insertQueryField.length()-1);
		insertQuery = insertQuery.substring(0,insertQuery.length()-1);
		String query = "insert into "+table+"("+insertQueryField+") values("+insertQuery+")";
		QueryUtil.executeQuery(mainCon, query,insertParam);
	}
	
	public static JSONArray executeSelect(Connection mainCon, JSONObject whereClause,String table) throws JSONException, SQLException{
		String selectQuery = "";
		Iterator<String> keyItr = whereClause.keys();
		while(keyItr.hasNext()){
			String key = keyItr.next();
			selectQuery = selectQuery+ key + "="+whereClause.get(key)+" and ";
		}
		selectQuery = selectQuery.substring(0,selectQuery.length()-6);
		String query = "select * from "+table+" where "+selectQuery ;
		System.out.println("Query From executeSelect : " + query);
		return QueryUtil.executeQuery(mainCon, query,new JSONArray(),false);
	}
	
	public static void executeUpdate(Connection mainCon, JSONObject requestObj,String table,JSONObject whereParams) throws JSONException, SQLException{
		String query = "update "+table+" set ";
		JSONArray insertParam = new JSONArray();
		Iterator<String> keyItr = requestObj.keys();
		String  key;
		while(keyItr.hasNext()){
			key = keyItr.next();
			
			if((key.indexOf("DOB") > -1) && !key.equals("AGE_PROOF_DOB")){
				query = query+key+"= to_date(?,'dd/mm/yyyy') ,";
				insertParam.put(requestObj.get(key));

			}else if(key.indexOf("START_DATE") > -1){
				query= query + key + "= to_date(?,'dd/mm/yyyy hh24:mi:ss') ,";
				insertParam.put(requestObj.get(key));

			}else if(key.equals("CALLBACK_DATE") || key.equals("MODIFIED_DATE") ||  key.equals("SIGNED_TIMESTAMP")){
				query += key+"= CURRENT_TIMESTAMP,";
				
			}else if(key.indexOf("OPEN_DATE") > -1 || key.equals("S_EMP_ISSDT")){
				query = query+key+"= to_date(?,'dd/mm/yyyy') ,";
				insertParam.put(requestObj.get(key));
			}else if(key.equals("CHEQ_DATE")){
				query = query+key+"= TO_TIMESTAMP(?,'DD-MM-YYYY') ,";
				insertParam.put(requestObj.get(key));
			}
			else{					
				query = query+key+"= ? ,";
				if(requestObj.get(key) == null || requestObj.get(key).equals(null)){
					insertParam.put("");
				}else{
					insertParam.put(requestObj.get(key));
				}
				

			}
					}
		query = query.substring(0,query.length()-1);
		
		keyItr = whereParams.keys();
		query += " where ";
		while(keyItr.hasNext()){
			key = keyItr.next();
			query +=   key + " = '" + whereParams.opt(key).toString() + "' and ";
		}
		
		query = query.substring(0,query.length() - 4);
		
		
		QueryUtil.executeQuery(mainCon, query,insertParam);
	}

	public static JSONArray resultSetToJSONArray(ResultSet rs) throws SQLException {

		JSONArray res = new JSONArray();
		ResultSetMetaData rsmd = rs.getMetaData();

		int j = 1;
		int columnCount = rsmd.getColumnCount();
		String value;
		String columnName, val;
		JSONObject row = null;
		try {
			while (rs.next()) {
				row = new JSONObject();
				for (j = 1; j <= columnCount; j++) {
					columnName = rsmd.getColumnName(j);
					value = rs.getString(columnName);
					// val = (value != null) ? value.getSubString(1, (int) value.length()) : "";
					val = (value != null) ? value : "";
					// logger.info(columnName + "::" + value);
					row.put(columnName, val);
					// logger.info("fetched row = "+ row.toString());
				}
				res.put(row);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
}
