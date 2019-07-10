package kr.or.ksmart.driverdb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DriverDB {
	public Connection driverDbcon() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/db05swj?" +
				 "useUnicode=true&characterEncoding=euckr";
		String user = "dbid05swj";
		String password = "dbpw05swj";
		Connection conn = null;
		conn = DriverManager.getConnection(url, user, password);
		
		System.out.println("#############################");
		System.out.println("   DriverDB.driverDbcon()");
		System.out.println("#############################");
		
		return conn;
	}
}
