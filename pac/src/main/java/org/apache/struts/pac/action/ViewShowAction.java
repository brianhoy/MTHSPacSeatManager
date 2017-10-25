package org.apache.struts.pac.action;

import org.apache.struts.pac.model.ShowStore;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.sql.*;  


public class ViewShowAction extends ActionSupport {
	ArrayList<ShowStore> shows;
	
	int helloCount = 5;
	
	// todo: change everything
    public String execute() {    	
    	shows = new ArrayList<ShowStore>();
    	try {
		   Class.forName("com.mysql.jdbc.Driver");
		   
		   String hostName = "localhost";
		   String databaseName = "pac";
		   String url = "jdbc:mysql://" + hostName + "/" + databaseName;
		   
		   String user = "root";
		   String password = "password";
		   
		   Connection con = DriverManager.getConnection(url, user, password);  
		   Statement statement = con.createStatement();
		   String sqlCommand = "SELECT ID, Date, Title, Description, Image_Path FROM Shows";
	       ResultSet rs = statement.executeQuery(sqlCommand);
	       
	       while(rs.next()) {
	       		ShowStore temp = new ShowStore();
	    	   
	       		int id = rs.getInt("ID");
	       		String date = rs.getString("Date");
	       		String title = rs.getString("Title");
	       		String description = rs.getString("Description");
	       		String image_path = rs.getString("Image_Path");

	       		System.out.println("Show " + id + ": ");
	       		System.out.println(" - Date: " + date + ",");
	       		System.out.println(" - Title: " + title + ",");
	       		System.out.println(" - Description: " + description + ",");
	       		System.out.println(" - Image_path: " + image_path + ",");
	       			           
	       		temp.setName(title);
	       		temp.setDescription(description);
	       		temp.setDate(date);
	           
	           shows.add(temp);
	       }

    	} catch(Exception e) {
    		System.out.println("EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE ERROR");
    		e.printStackTrace();
    	}  
    	
		HttpServletRequest request = ServletActionContext.getRequest();  
		HttpSession session = request.getSession();  
		  
		String s = (String)session.getAttribute("login");  
    	
		if(s.equals("true")) {
			return "success_loggedin";
		}
        return "success";
    }
        
    public ArrayList<ShowStore> getShows() {
    	return shows;
    }
}
