package Process;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Object.Sach;
public class Process_Sach {
Connect_database cd = new Connect_database();
	
	
	
	public ArrayList<Sach> getListSach() {
		Connection cn = cd.getConnection();
		ArrayList<Sach> ls = new ArrayList<>();
		String sql = "SELECT * FROM tb_sach;";
		try {
			PreparedStatement ps = (PreparedStatement) cn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Sach st = new Sach();
				st.setMaSach(rs.getString("MaSach"));
				st.setTenSach(rs.getString("TenSach"));
				st.setTrangThai(rs.getString("TrangThai"));
				st.setMaDauSach(rs.getString("MaDauSach"));
				ls.add(st);
			}
		} catch (Exception e) {
			
		}
		return ls;
	}
	
	
	public boolean insertSach(String MaSach, String TenSach, String TrangThai, String MaDauSach) {
		Connection cn = cd.getConnection();
		String sql = "Insert into tb_sach(MaSach, TenSach,TrangThai, MaDauSach) values(?,?,?,?)";
		try {
			PreparedStatement ps = (PreparedStatement) cn.prepareStatement(sql);
			ps.setString(1, MaSach);
			ps.setString(2, TenSach);
			ps.setString(3, TrangThai);
			ps.setString(4, MaDauSach);
			ps.executeUpdate();
			cn.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public boolean updateSach(String MaSach, String TenSach, String TrangThai, String MaDauSach) {
		Connection cn = cd.getConnection();
		String sql = "update tb_sach "
				+ "set TenSach = ?, TrangThai = ?, MaDauSach = ? "
				+ "where MaSach = ?";
		try {
			PreparedStatement ps = (PreparedStatement) cn.prepareStatement(sql);
			ps.setString(4, MaSach);
			ps.setString(1, TenSach);
			ps.setString(2, TrangThai);
			ps.setString(3, MaDauSach);
			ps.executeUpdate();
			cn.close();
			JOptionPane.showMessageDialog(null, "Sửa thành công!" , "Thông báo", 1);
			return true;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Sửa thất bại!" , "Thông báo", 1);
			return false;
		}
	}
	
	
	
	public boolean updateSachTrangThai(String MaSach) {
		Connection cn = cd.getConnection();
		String sql = "UPDATE `tb_sach` SET `TrangThai` = 'Đã mượn' WHERE (`MaSach` = ?);";
		try {
			PreparedStatement ps = (PreparedStatement) cn.prepareStatement(sql);
			ps.setString(1, MaSach);
			ps.executeUpdate();
			cn.close();
			return true;
		} catch (Exception e) {

			return false;
		}
	}
	
	public boolean updateSachTrangThai2(String MaSach) {
		Connection cn = cd.getConnection();
		String sql = "UPDATE `tb_sach` SET `TrangThai` = 'Còn' WHERE (`MaSach` = ?);";
		try {
			PreparedStatement ps = (PreparedStatement) cn.prepareStatement(sql);
			ps.setString(1, MaSach);
			ps.executeUpdate();
			cn.close();
			return true;
		} catch (Exception e) {

			return false;
		}
	}
	
	public boolean delSach(String MaSach) {
		Connection cn = cd.getConnection();
		String sql = "delete from tb_sach where MaSach = ?";
		try {
			PreparedStatement ps = (PreparedStatement) cn.prepareStatement(sql);
			ps.setString(1, MaSach);
			ps.executeUpdate();
			ps.close();
			return true;
			
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Xóa thất bại!" , "Thông báo", 1);
			return false;
		}
	}
	
	public ArrayList<String> getMaDauSach(){
		Connection cn = cd.getConnection();
		ArrayList< String> arr = new ArrayList<>();
				
		String sql = "Select distinct MaDauSach from tb_DauSach order by MaDauSach";
		try {
			PreparedStatement ps = (PreparedStatement) cn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				arr.add(rs.getString("MaDauSach"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arr;
	}
	
	public String getMaDauSach2(String TenSach){
		Connection cn = cd.getConnection();
		String kqString = null;		
		String sql = "Select distinct MaDauSach from tb_DauSach where TenSach = ?";
		try {
			PreparedStatement ps = (PreparedStatement) cn.prepareStatement(sql);
			ps.setString(1, TenSach);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				
			kqString =	rs.getString("MaDauSach");
			
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return kqString;

	}
	public ArrayList<String> getTenSach() {
	    ArrayList<String> tenSachList = new ArrayList<>();
	    try (Connection con = cd.getConnection();
             PreparedStatement stmt = con.prepareStatement("SELECT DISTINCT TenSach FROM tb_DauSach ORDER BY TenSach")) {
	        ResultSet rs = stmt.executeQuery();
	        while (rs.next()) {
	            tenSachList.add(rs.getString("TenSach"));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return tenSachList;
	}

}
