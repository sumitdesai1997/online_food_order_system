package com.erd.utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import org.json.JSONObject;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/SellOnline")   
public class SellOnline extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public SellOnline() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = null;
		try {
			
			out = response.getWriter();
			response.setHeader("Content-Type", "application/json");
			
	        StringBuilder buffer = new StringBuilder();
	        BufferedReader reader = request.getReader();
	        String line;
	        while ((line = reader.readLine()) != null) {
	            buffer.append(line); 
	        }
	        String data = buffer.toString(); 
	        
			JSONObject req = new JSONObject(data);
			
			Controller control = new Controller(req);
			JSONObject resp = control.call();
			
			response.setContentType("application/json");
			out.write(resp.toString());
			out.flush();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			out.close();
		}
		
	}

}
