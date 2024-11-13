package Process;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class Connect_database {
	public static Connection cn;
	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/quan_ly_thu_vien","root","1234567890");
		}
		catch(ClassNotFoundException | SQLException e)
		{
			System.out.println("Error connection");
		}
		return cn;
	}
	
	public  static boolean login(String user, String password) {
		Connection cn = getConnection();
		boolean kq = false;
		String sql_login = "select * from librarian where username = ? and password = ?;";
		try {
			PreparedStatement ps = cn.prepareStatement(sql_login);
			ps.setString(1, user);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				kq = true;
			}
		} catch (Exception e) {
                        System.out.println(e.getMessage());
			return false;
		}
		return kq;
		

	}
	public static void main(String[] args) {

	}
	

}
