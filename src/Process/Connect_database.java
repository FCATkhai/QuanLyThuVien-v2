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
	
	public  static boolean login(String user, String password, boolean isAdmin) {
            Connection cn = getConnection();
            boolean kq = false;

            String sql_login_admin = "SELECT * FROM librarian WHERE username = ? AND password = ?";
            String sql_login_user = "SELECT * FROM user WHERE username = ? AND password = ?";

            try {
                PreparedStatement ps;
                if (isAdmin) {
                    // Nếu trong bảng librarian
                    ps = cn.prepareStatement(sql_login_admin);
                } else {
                    // Nếu trong bảng users
                    ps = cn.prepareStatement(sql_login_user);
                }
                ps.setString(1, user);
                ps.setString(2, password);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    kq = true; // Đăng nhập thành công
                }

                rs.close();
                ps.close();
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            } finally {
                try {
                    if (cn != null) cn.close();
                } catch (Exception e) {
                    System.out.println("Error closing connection: " + e.getMessage());
                }
            }

            return kq;
	}
	public static void main(String[] args) {

	}
	

}
