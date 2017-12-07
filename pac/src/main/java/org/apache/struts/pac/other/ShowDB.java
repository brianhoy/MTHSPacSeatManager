package org.apache.struts.pac.other;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.struts.pac.model.Show;
import org.apache.struts.pac.model.ShowDate;

public final class ShowDB {
	public static String addShow(Show show, ArrayList<ShowDate> dates) {
		Connection con = null;
		PreparedStatement ps = null;

		String result = "error";
		int lastInsertedId = -1;

		try {
			con = DriverManager.getConnection(DbInf.getDbUrl(), DbInf.getUser(), DbInf.getPassword());
			
			
			String command = "INSERT INTO Shows (Title, Description, ImagePath, FirstDate, LastDate, Duration) values(?,?,?,?,?,?)";
			
			ps = con.prepareStatement(command, Statement.RETURN_GENERATED_KEYS);

			java.sql.Date lastDate = null;
			if(show.getLastDate() != null) lastDate = new java.sql.Date(show.getLastDate().getTime());
		
			ps.setString(1, show.getTitle());
			ps.setString(2, show.getDescription());
			ps.setString(3, show.getImagePath());
			ps.setDate(4, new java.sql.Date(show.getFirstDate().getTime()));
			ps.setDate(5, lastDate);
			if(show.getDuration() == null) {
				ps.setInt(6,  -1);
			}
			else {
				ps.setInt(6, (int)show.getDuration().toMinutes());
			}
			
			int status = ps.executeUpdate();
			
			if (status > 0) {
				
				ResultSet rs = ps.getGeneratedKeys();
				if(rs.next()) {
					lastInsertedId = rs.getInt(1);
				}
			}
		} catch (Exception e) {
			System.out.println("!!!PAC ERROR: Unable to add show in Utils.");
			result = e.getMessage();
			e.printStackTrace();
		} finally {
			try { ps.close(); } catch (Exception e) {}
			try { con.close(); } catch (Exception e) {}
		}

		System.out.println("Trying to add show dates with lastInsertedId " + lastInsertedId);
		if (lastInsertedId != -1) {
			if(addShowDates(lastInsertedId, dates).equals("success")) {
				result = "success";
			}
		}
		
		return result;
	}
	public static boolean editShow(Show show) {
		Connection con = null;
		PreparedStatement ps = null;

		boolean result = false;

		try {
			con = DriverManager.getConnection(DbInf.getDbUrl(), DbInf.getUser(), DbInf.getPassword());
						
			String command = "UPDATE Shows SET Title = ?, Description = ?, ImagePath = ?, FirstDate = ?, LastDate = ?, Duration = ? WHERE ShowID = ?";
			ps = con.prepareStatement(command);

			System.out.println("SQL Edit show command: \n" + command);

			ps.setString(1, show.getTitle());
			ps.setString(2, show.getDescription());
			ps.setString(3, show.getImagePath());
			ps.setDate(4, new java.sql.Date(show.getFirstDate().getTime()));
			ps.setDate(5, new java.sql.Date(show.getLastDate().getTime()));
			ps.setInt(6, (int)show.getDuration().toMinutes());
			ps.setInt(7, show.getShowId());


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
	public static boolean removeShow(int id) {
		Connection con = null;
		PreparedStatement ps = null;

		boolean result = false;

		try {
			con = DriverManager.getConnection(DbInf.getDbUrl(), DbInf.getUser(), DbInf.getPassword());
			
			ps = con.prepareStatement("DELETE FROM ShowDates WHERE ShowID = ?");
			ps.setInt(1, id);
			
			int status = ps.executeUpdate();

			if (status > 0) {
			}
			ps = con.prepareStatement("DELETE FROM Shows WHERE ShowID = ?");
			ps.setInt(1, id);

			status = ps.executeUpdate();

			if (status > 0) {
				result = true;
			}
		} catch (Exception e) {
			System.out.println("!!!PAC ERROR: Unable to remove show with id " + id + " in Utils.");
			Utils.pushMessage(e.getMessage());
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

	public static String addShowDate(int showId, ShowDate date) {
		ArrayList<ShowDate> dates = new ArrayList<ShowDate>();
		dates.add(date);
		return addShowDates(showId, dates);
	}
	public static String addShowDates(int showId, ArrayList<ShowDate> dates) {
		Connection con = null;
		PreparedStatement ps = null;
		String result = "success";
		
		try {
			con = DriverManager.getConnection(DbInf.getDbUrl(), DbInf.getUser(), DbInf.getPassword());
			
			
			String command = "INSERT INTO ShowDates (ShowID, Date) values(?,?)";
			
			ps = con.prepareStatement(command, Statement.RETURN_GENERATED_KEYS);

			for(ShowDate date : dates) {
				ps.setInt(1, showId);
				ps.setDate(2, new java.sql.Date(date.getDate().getTime()));
				
				ps.addBatch();
			}
			
			int[] statuses = ps.executeBatch();
			
			for(int status : statuses) {
				if(status <= 0) {
					result = "error";
				}
			}
		} catch (Exception e) {
			System.out.println("!!!PAC ERROR: Unable to add show dates in Utils.");
			result = e.getMessage();
			e.printStackTrace();
		} finally {
			try { ps.close(); } catch (Exception e) {}
			try { con.close(); } catch (Exception e) {}
		}
		
		return result;
	}
	public static boolean editShowDate(int showDateId, ShowDate newDate) {
		return false;
	}
	public static boolean removeShowDate(int showDateId) { return false; }

	public static ArrayList<Show> getShows() {
		ArrayList<Show> shows = new ArrayList<Show>();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		boolean error = false;

		try {
			con = DriverManager.getConnection(DbInf.getDbUrl(), DbInf.getUser(), DbInf.getPassword());
			ps = con.prepareStatement("SELECT ShowID, Title, Description, ImagePath, FirstDate, LastDate, Duration FROM Shows");
			rs = ps.executeQuery();

			while (rs.next()) {
				Show temp = new Show();

				temp.setShowId(rs.getInt(1));
				temp.setTitle(rs.getString(2));
				temp.setDescription(rs.getString(3));
				temp.setImagePath(rs.getString(4));
				temp.setFirstDate(rs.getDate(5));
				temp.setLastDate(rs.getDate(6));
				temp.setDuration(java.time.Duration.ofMinutes(rs.getInt(7)));
								
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
	public static Show getShow(int id) {
		Show show = new Show();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		boolean error = false;
		
		System.out.println("EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
		
		try {
			con = DriverManager.getConnection(DbInf.getDbUrl(), DbInf.getUser(), DbInf.getPassword());
			ps = con.prepareStatement("SELECT ShowID, Title, Description, ImagePath, FirstDate, LastDate, Duration FROM Shows WHERE ShowID=" + id);
			rs = ps.executeQuery();
		
			int numShowsWithId = 0;
			
			while (rs.next()) {
				numShowsWithId++;
				
				Show temp = new Show();
		
				temp.setShowId(rs.getInt(1));
				temp.setTitle(rs.getString(2));
				temp.setDescription(rs.getString(3));
				temp.setImagePath(rs.getString(4));
				temp.setFirstDate(rs.getDate(5));
				temp.setLastDate(rs.getDate(6));
				temp.setDuration(java.time.Duration.ofMinutes(rs.getInt(7)));
				
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
		
		System.out.println("Successfully retrieved show with title " + show.getTitle());
		
		return show;
	}
}
