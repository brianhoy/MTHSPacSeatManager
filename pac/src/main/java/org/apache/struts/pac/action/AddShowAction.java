package org.apache.struts.pac.action;

import org.apache.struts.pac.model.ShowStore;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.sql.*;  


public class AddShowAction extends ActionSupport {
	private ShowStore show;
	int status = 0;
	
    public String execute() {    	
    	try {
			System.out.println("Adding show to database.");
			System.out.println(" - Title: " + show.getName() + ",");
			System.out.println(" - Description: " + show.getDescription() + ",");
			System.out.println(" - Date: " + show.getDate() + ",");
			System.out.println(" - Image_path: " + "none" + ",");

    		
		   Class.forName("com.mysql.jdbc.Driver");
		   
		   String hostName = "localhost";
		   String databaseName = "pac";
		   String url = "jdbc:mysql://" + hostName + "/" + databaseName;
		   
		   String user = "root";
		   String password = "password";
		   
		   Connection con = DriverManager.getConnection(url, user, password);  
		   //Statement statement = con.createStatement();
		   PreparedStatement ps = con.prepareStatement("INSERT INTO Shows (Date, Title, Description, Image_Path) values(?,?,?,?)");
		   
		   ps.setString(1, show.getDate());
		   ps.setString(2, show.getName());
		   ps.setString(3, show.getDescription());
		   ps.setString(4, "show_image_path.png");

		   status = ps.executeUpdate();
		   
		   if(status > 0) {
			   return "success";
		   }
    	} 
    	catch(Exception e) {
    		e.printStackTrace();
    	}  
    	
        return "error";
    }
        
    public ShowStore getShow() {
    	return show;
    }
    public void setShow(ShowStore show) {
    	this.show = show;
    }
}