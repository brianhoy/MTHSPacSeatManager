package org.apache.struts.pac.other;

public final class DbInf {
	private static String dbUrl;
	private static String user;
	private static String password;

	static {
		String hostName = "localhost";
		String databaseName = "pac";
		dbUrl = "jdbc:mysql://" + hostName + "/" + databaseName;
		user = "root";
		password = "password";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch(Exception e) {
			System.out.println("!!!PAC ERROR: Unable to initialize jdbc driver in DatabaseInfo.");
			e.printStackTrace();
		}
	}

	public static String getDbUrl() {
		return DbInf.dbUrl;
	}
	public static String getUser() {
		return DbInf.user;
	}
	public static String getPassword() {
		return DbInf.password;
	}
}
