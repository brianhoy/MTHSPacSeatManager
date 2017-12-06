package org.apache.struts.pac.other;

import org.apache.struts.pac.model.ShowStore;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.ValueStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.sql.*;
import java.text.SimpleDateFormat;

public final class Utils {
	private static String dbUrl;
	private static String user;
	private static String password;

	private static Connection con;

	static {
		String hostName = "localhost";
		String databaseName = "pac";
		dbUrl = "jdbc:mysql://" + hostName + "/" + databaseName;

		user = "root";
		password = "password";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch(Exception e) {
			System.out.println("!!!PAC ERROR: Unable to initialize jdbc driver in Utils.");
			e.printStackTrace();
		}
		// con = DriverManager.getConnection(dbUrl, user, password);
	}

	// sorted
	public static ArrayList<java.util.Date> parseDates(String datesString) {
		ArrayList<java.util.Date> dates = new ArrayList<java.util.Date>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-dd hh:mm:ss");

		String[] stringArray = datesString.split(", ");

		try {
			for(int i = 0; i < stringArray.length; i++) {
				String strDate = stringArray[i];
				java.util.Date date = sdf.parse(strDate);
				dates.add(date);
			}
		}
		catch(java.text.ParseException e) {
			pushError("Error parsing inputted dates: " + e.getMessage());
			System.out.println("Util.parseDates error: " + e.getMessage());
			return null;
		}
		
		return dates;
	}
	
	public static String addShow(ShowStore show) {
		Connection con = null;
		PreparedStatement ps = null;

		String result = "error";

		try {
			con = DriverManager.getConnection(dbUrl, user, password);
			ps = con.prepareStatement("INSERT INTO Shows (Date, Title, Description, Image_Path) values(?,?,?,?)");

			ps.setString(1, show.getDates());
			ps.setString(2, show.getName());
			ps.setString(3, show.getDescription());
			ps.setString(4, "show_image_path.png");

			int status = ps.executeUpdate();

			if (status > 0) {
				result = "success";
			}
		} catch (Exception e) {
			System.out.println("!!!PAC ERROR: Unable to add show in Utils.");
			result = e.getMessage();
			e.printStackTrace();
		} finally {
			try { ps.close(); } catch (Exception e) {}
			try { con.close(); } catch (Exception e) {}
		}

		return result;
	}

	public static String addShowDate()
	
	public static boolean validateLogin(String username, String password) {
		if (username.equals("defuser") && password.equals("defpass")) {
			return true;
		} else {
			return false;
		}

	}

	public static ArrayList<ShowStore> getShows() {
		ArrayList<ShowStore> shows = new ArrayList<ShowStore>();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		boolean error = false;

		try {
			con = DriverManager.getConnection(dbUrl, user, password);
			ps = con.prepareStatement("SELECT ID, Date, Title, Description, Image_Path FROM Shows");
			rs = ps.executeQuery();

			while (rs.next()) {
				ShowStore temp = new ShowStore();

				int id = rs.getInt("ID");
				String date = rs.getString("Date");
				String title = rs.getString("Title");
				String description = rs.getString("Description");
				String image_path = rs.getString("Image_Path");

				temp.setName(title);
				temp.setDescription(description);
				temp.setDate(date);
				temp.setId(id);
				
				shows.add(temp);
			}
		} catch (Exception e) {
			System.out.println("!!!PAC ERROR: Unable to getShows in Utils.");
			e.printStackTrace();
			error = true;
		} finally {
			try {
				rs.close();
			} catch (Exception e) {
			}
			try {
				ps.close();
			} catch (Exception e) {
			}
			try {
				con.close();
			} catch (Exception e) {
			}
		}
		if (error) {
			return null;
		}
		return shows;
	}

	public static ShowStore getShow(int id) {
		ShowStore show = new ShowStore();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		boolean error = false;
		
		System.out.println("EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
		
		try {
			con = DriverManager.getConnection(dbUrl, user, password);
			ps = con.prepareStatement("SELECT ID, Date, Title, Description, Image_Path FROM Shows WHERE ID=" + id);
			rs = ps.executeQuery();
		
			int numShowsWithId = 0;
			
			while (rs.next()) {
				numShowsWithId++;
				
				ShowStore temp = new ShowStore();
		
				//int id = rs.getInt("ID");
				String date = rs.getString("Date");
				String title = rs.getString("Title");
				String description = rs.getString("Description");
				String image_path = rs.getString("Image_Path");
		
				temp.setName(title);
				temp.setDescription(description);
				temp.setDate(date);
				temp.setId(id);
				
				show = temp;
			}
			
			if(numShowsWithId == 0) {
				error = true;
			}
			else if(numShowsWithId != 1){
				System.out.println("!!!PAC WARNING: Multiple shows with ID " + id);
			}
		} catch (Exception e) {
			System.out.println("!!!PAC ERROR: Unable to getShows in Utils.");
			e.printStackTrace();
			error = true;
		} finally {
			try {
				rs.close();
			} catch (Exception e) {
			}
			try {
				ps.close();
			} catch (Exception e) {
			}
			try {
				con.close();
			} catch (Exception e) {
			}
		}
		if (error) {
			return null;
		}
		
		System.out.println("Successfully retrieved show with title " + show.getName());
		
		return show;
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

	public static boolean removeShow(int id) {
		Connection con = null;
		PreparedStatement ps = null;

		boolean result = false;

		try {
			con = DriverManager.getConnection(dbUrl, user, password);
			ps = con.prepareStatement("DELETE FROM Shows WHERE ID=" + id);

			int status = ps.executeUpdate();

			if (status > 0) {
				result = true;
			}
		} catch (Exception e) {
			System.out.println("!!!PAC ERROR: Unable to remove show with id " + id + " in Utils.");
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch (Exception e) {
			}
			try {
				con.close();
			} catch (Exception e) {
			}
		}

		return result;
	}

	public static boolean editShow(ShowStore show) {
		Connection con = null;
		PreparedStatement ps = null;

		boolean result = false;

		try {
			con = DriverManager.getConnection(dbUrl, user, password);
			
			String command = "UPDATE Shows SET "
					+ "Date='" + show.getDates() 
					+ "', Title='" + show.getName() 
					+ "', Description='" + show.getDescription() 
					+ "', Image_Path='show_image_path.png'" 
					+ " WHERE ID=" + show.getId() + ";";
			
			System.out.println("SQL Edit show command: \n" + command);

					
			ps = con.prepareStatement(command);

			int status = ps.executeUpdate();

			if (status > 0) {
				result = true;
			}
		} catch (Exception e) {
			System.out.println("!!!PAC ERROR: Unable to edit show in Utils.");
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch (Exception e) {
			}
			try {
				con.close();
			} catch (Exception e) {
			}
		}

		return result;
	}
}
