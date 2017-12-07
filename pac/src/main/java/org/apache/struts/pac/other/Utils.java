package org.apache.struts.pac.other;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.util.ValueStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.sql.*;
import java.text.SimpleDateFormat;

public final class Utils {
	public static ArrayList<java.util.Date> parseDates(String datesString) {
		ArrayList<java.util.Date> dates = new ArrayList<java.util.Date>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-dd hh:mm:ss");

		try {
			String charsToRemove = "[]\"";
 
			String strippedDate = stripChars(datesString, charsToRemove);
			String[] stringArray = strippedDate.split(",");
			for(int i = 0; i < stringArray.length; i++) {
				String strDate = stringArray[i];
				java.util.Date date = sdf.parse(strDate);
				dates.add(date);
			}
		}
		catch(Exception e) {
			pushError("Error parsing inputted dates: " + e.getMessage());
			System.out.println("Util.parseDates error: " + e.getMessage());
			return null;
		}
		
		return dates;
	}
	
	public static String stripChars(String input, String strip) {
	    StringBuilder result = new StringBuilder();
	    for (char c : input.toCharArray()) {
	        if (strip.indexOf(c) == -1) {
	            result.append(c);
	        }
	    }
	    return result.toString();
	}
	
	public static boolean validateLogin(String username, String password) {
		if (username.equals("defuser") && password.equals("defpass")) {
			return true;
		} else {
			return false;
		}

	}

	public static void pushError(String error) {
		ValueStack stack = ActionContext.getContext().getValueStack();
		Map<String, Object> context = new HashMap<String, Object>();

		context.put("errorMsg", error);
		stack.push(context);
	}
	public static void pushMessage(String msg) {
		ValueStack stack = ActionContext.getContext().getValueStack();
		Map<String, Object> context = new HashMap<String, Object>();

		context.put("msg", msg);
		stack.push(context);
	}
	
	public static boolean isLoggedIn() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();

		String s = (String) session.getAttribute("login");

		return s != null && !s.equals("");
	}
}
