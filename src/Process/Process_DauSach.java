package Process;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Object.DauSach;




public class Process_DauSach {

	Connect_database cd = new Connect_database();
	
	
	
	public ArrayList<DauSach> getListDauSach() {
		Connection cn = cd.getConnection();
		ArrayList<DauSach> ls = new ArrayList<>();
		String sql = "SELECT * FROM tb_dausach;";
		try {
			PreparedStatement ps = (PreparedStatement) cn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				DauSach st = new DauSach();
				st.setMaDauSach(rs.getString("MaDauSach"));
				st.setTenSach(rs.getString("TenSach"));
				st.setSoLuong(rs.getInt("SoLuong"));
				st.setTheLoai(rs.getString("TheLoai"));
				st.setTacGia(rs.getString("TacGia"));
				st.setNXB(rs.getString("NXB"));
				st.setNamXB(rs.getInt("NamXB"));
				ls.add(st);
			}
		} catch (Exception e) {
			
		}
		return ls;
	}
	
	public ArrayList<DauSach> getListDauSachbyMaDauSach(String MaDauSach) {
		Connection cn = cd.getConnection();
		ArrayList<DauSach> ls = new ArrayList<>();
		String sql = "SELECT * FROM tb_dausach where ? = ?";
		try {
			PreparedStatement ps = (PreparedStatement) cn.prepareStatement(sql);
			ps.setString(1, "MaDauSach");
			ps.setString(2, MaDauSach);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				DauSach st = new DauSach();
				st.setMaDauSach(rs.getString("MaDauSach"));
				st.setTenSach(rs.getString("TenSach"));
				st.setSoLuong(rs.getInt("SoLuong"));
				st.setTheLoai(rs.getString("TheLoai"));
				st.setTacGia(rs.getString("TacGia"));
				st.setNXB(rs.getString("NXB"));
				st.setNamXB(rs.getInt("NamXB"));
				ls.add(st);
			}
		} catch (Exception e) {
			
		}
		return ls;
	}
	

	
	public boolean insertDauSach(String MaDauSach, String TenSach,int SoLuong, String TheLoai, String TacGia, String NXB, int NamXB) {
		Connection cn = cd.getConnection();
		String sql = "Insert into tb_dausach(MaDauSach, TenSach,SoLuong, TheLoai, TacGia, NXB, NamXB) values(?,?,?,?,?,?,?)";
		try {
			PreparedStatement ps = (PreparedStatement) cn.prepareStatement(sql);
			ps.setString(1, MaDauSach);
			ps.setString(2, TenSach);
			ps.setInt(3, SoLuong);
			ps.setString(4, TheLoai);
			ps.setString(5, TacGia);
			ps.setString(6, NXB);
			ps.setInt(7, NamXB);
			ps.executeUpdate();
			cn.close();
			JOptionPane.showMessageDialog(null, "Thêm thành công!" , "Thông báo", 1);
			return true;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Thêm thất bại!" , "Thông báo", 1);
			return false;
		}
	}
	
	public boolean updateDauSach(String MaDauSach, String TenSach,int SoLuong, String TheLoai, String TacGia, String NXB, int NamXB) {
		Connection cn = cd.getConnection();
		String sql = "update tb_dausach "
				+ "set TenSach = ?, SoLuong = ?, TheLoai = ?, TacGia = ?, NXB = ?, NamXB  = ? "
				+ "where MaDauSach = ?";
		try {
			PreparedStatement ps = (PreparedStatement) cn.prepareStatement(sql);
			ps.setString(7, MaDauSach);
			ps.setString(1, TenSach);
			ps.setInt(2, SoLuong);
			ps.setString(3, TheLoai);
			ps.setString(4, TacGia);
			ps.setString(5, NXB);
			ps.setInt(6, NamXB);
			ps.executeUpdate();
			cn.close();
			JOptionPane.showMessageDialog(null, "Cập nhật thành công!" , "Thông báo", 1);
			return true;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Cập nhật thất bại!" , "Thông báo", 1);
			return false;
		}
	}
	
	public boolean updateDauSachSoLuong(String MaDauSach) {
		Connection cn = cd.getConnection();
		String sql = "UPDATE `qltv`.`tb_dausach` SET SoLuong = SoLuong - 1 WHERE (`MaDauSach` = ?);";
		try {
			PreparedStatement ps = (PreparedStatement) cn.prepareStatement(sql);
			ps.setString(1, MaDauSach);
			ps.executeUpdate();
			cn.close();
			return true;
		} catch (Exception e) {

			return false;
		}
	}
	
	public boolean delDauSach(String MaDauSach) {
		Connection cn = cd.getConnection();
		String sql = "Delete from tb_dausach where MaDauSach = ?";
		try {

			PreparedStatement ps = (PreparedStatement) cn.prepareStatement(sql);
			ps.setString(1, MaDauSach);

			ps.executeUpdate();
			cn.close();
			JOptionPane.showMessageDialog(null, "Xóa thành công!" , "Thông báo", 1);
			return true;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Xóa thất bại!" , "Thông báo", 1);
			return false;
		}
	}
	

	
}
