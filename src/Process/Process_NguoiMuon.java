package Process;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;

import Object.NguoiMuon;
import Object.Sach;


public class Process_NguoiMuon {

Connect_database cd = new Connect_database();
	
	
	
	public ArrayList<NguoiMuon> getListNguoiMuon() {
		Connection cn = cd.getConnection();
		ArrayList<NguoiMuon> ls = new ArrayList<>();
		String sql = "SELECT * FROM tb_nguoimuon;";
		try {
			PreparedStatement ps = (PreparedStatement) cn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				NguoiMuon st = new NguoiMuon();
				st.setMaNguoiMuon(rs.getString("MaNguoiMuon"));
				st.setTenNguoiMuon(rs.getString("TenNguoiMuon"));
				st.setDiaChi(rs.getString("DiaChi"));
				st.setGmail(rs.getString("Gmail"));
				st.setSDT(rs.getString("SDT"));
				ls.add(st);
			}
		} catch (Exception e) {
			
		}
		return ls;
	}

	
	public boolean insertNguoiMuon(String MaNguoiMuon, String TenNguoiMuon, String DiaChi, String Gmail, String SDT) {
		Connection cn = cd.getConnection();
		String sql = "insert into tb_nguoimuon(MaNguoiMuon, TenNguoiMuon, DiaChi, Gmail, SDT) values (?,?,?,?,?)";
		try {
			PreparedStatement ps = (PreparedStatement) cn.prepareStatement(sql);
			ps.setString(1, MaNguoiMuon);
			ps.setString(2, TenNguoiMuon);
			ps.setString(3, DiaChi);
			ps.setString(4, Gmail);
			ps.setString(5, SDT);
			ps.executeUpdate();
			ps.close();
			JOptionPane.showMessageDialog(null, "Đã thêm khách hàng thành công!" , "Thông báo", 1);
			return true;
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Thêm thất bại!" , "Thông báo", 1);
			return false;
		}
	}
	
	public boolean updateNguoiMuon(String MaNguoiMuon, String TenNguoiMuon, String DiaChi, String Gmai, String SDT) {
		Connection cn = cd.getConnection();
		String sql = "update tb_nguoimuon set TenNguoiMuon = ?, DiaChi = ?, Gmail = ?, SDT = ? where MaNguoiMuon = ?";
		try {
			PreparedStatement ps = (PreparedStatement) cn.prepareStatement(sql);
			ps.setString(5, MaNguoiMuon);
			ps.setString(1, TenNguoiMuon);
			ps.setString(2, DiaChi);
			ps.setString(3, Gmai);
			ps.setString(4, SDT);
			ps.executeUpdate();
			ps.close();
			JOptionPane.showMessageDialog(null, "Cập nhật thành công!" , "Thông báo", 1);
			return true;
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Cập nhật thất bại!" , "Thông báo", 1);
			return false;
		}
	}
	
	public boolean delNguoiMuon(String MaNguoiMuon) {
		Connection cn = cd.getConnection();
		String sql = "delete from tb_nguoimuon where MaNguoiMuon = ?";
		try {
			PreparedStatement ps = (PreparedStatement) cn.prepareStatement(sql);
			ps.setString(1, MaNguoiMuon);
			ps.executeUpdate();
			ps.close();
			JOptionPane.showMessageDialog(null, "Xóa thành công!" , "Thông báo", 1);
			return true;
		}catch (Exception e) {
		System.out.println(e);
			JOptionPane.showMessageDialog(null, "Xóa thất bại!" , "Thông báo", 1);
			return false;
		}
	}

	public NguoiMuon getTTNguoiMuon(String MaNguoiMuon) {
		Connection cn = cd.getConnection();
		String sql = "SELECT * FROM tb_nguoimuon WHERE MaNguoiMuon=?; ";
		try {
			PreparedStatement ps = (PreparedStatement) cn.prepareStatement(sql);
			ps.setString(1, MaNguoiMuon);
			ResultSet rs = ps.executeQuery();
			NguoiMuon st = new NguoiMuon();
			while (rs.next()) {
				st.setMaNguoiMuon(rs.getString("MaNguoiMuon"));
				st.setTenNguoiMuon(rs.getString("TenNguoiMuon"));
				st.setDiaChi(rs.getString("DiaChi"));
				st.setGmail(rs.getString("Gmail"));
				st.setSDT(rs.getString("SDT"));
			}
			return st;
		}catch (Exception e) {
			return null;
		}
	}

	public ArrayList<HashMap<String, String>> getListSachDaMuon(String MaNguoiMuon) {
		Connection cn = cd.getConnection();
		ArrayList<HashMap<String, String>> ls = new ArrayList<>();
		String sql = "select t2.MaPhieuMuon, t1.MaSach as t1_MaSach, t1.TenSach, t2.NgayMuon, t2.HanTra, t2.NgayTra from tb_sach t1, tb_phieumuon t2 where t1.MaSach = t2.MaSach and t2.MaNguoiMuon=?;";
		try {
			PreparedStatement ps = (PreparedStatement) cn.prepareStatement(sql);
			ps.setString(1, MaNguoiMuon);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				HashMap<String, String> entry = new HashMap<>();
				entry.put("MaPhieuMuon", rs.getString("MaPhieuMuon"));
				entry.put("MaSach", rs.getString("t1_MaSach"));
				entry.put("TenSach", rs.getString("TenSach"));
				entry.put("NgayMuon", String.valueOf(rs.getDate("NgayMuon")));
				entry.put("HanTra", String.valueOf(rs.getDate("HanTra")));
				entry.put("NgayTra", String.valueOf(rs.getDate("NgayTra")));
				if (rs.getString("NgayTra") == null) {
					entry.put("TrangThai", "Chưa trả");
				} else {
					entry.put("TrangThai", "Đã trả");
				}
				ls.add(entry);
			}
		} catch (Exception e) {

		}
		return ls;
	}
}
