package GUI_ThuThu;

import Object.DauSach;
import Object.NguoiMuon;
import Object.PhieuMuon;
import Object.Sach;
import Process.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Vector;

public class GUI_ThongKe extends JFrame {

	private JPanel contentPane;
	private JTable tableDauSach;
	private JTable tableSach;
	private JTable tableNguoiMuon;
	 private JTable tablePhieuMuon;
	 private JTable tableThongke;
	 private JTable tableThongtinNguoimuonsach;
	
	Connect_database cd = new Connect_database();
	
	DefaultTableModel Model1 = new DefaultTableModel();
	Vector<String> columns1 = new Vector<String>();
	Vector<Vector<Object>> rows1 = new Vector<>();
	
	DefaultTableModel Model2 = new DefaultTableModel();
	Vector<String> columns2 = new Vector<String>();
	Vector<Vector<Object>> rows2 = new Vector<>();
	
	
	DefaultTableModel Model3 = new DefaultTableModel();
	Vector<String> columns3 = new Vector<String>();
	Vector<Vector<Object>> rows3 = new Vector<>();
	
	
	DefaultTableModel Model4 = new DefaultTableModel();
	Vector<String> columns4 = new Vector<String>();
	Vector<Vector<Object>> rows4 = new Vector<>();
	
	DefaultTableModel Model5 = new DefaultTableModel();
	Vector<String> columns5 = new Vector<String>();
	Vector<Vector<Object>> rows5 = new Vector<>();
	
	DefaultTableModel Model6 = new DefaultTableModel();
	Vector<String> columns6 = new Vector<String>();
	Vector<Vector<Object>> rows6 = new Vector<>();
	
	 Process_DauSach pds = new Process_DauSach();
	 Process_Sach ps = new Process_Sach();
	 Process_NguoiMuon pmn = new Process_NguoiMuon();
	 Process_PhieuMuon pm = new Process_PhieuMuon();

	 
	 // Dau sach
		public void getAllDauSach() {

			Model1.setRowCount(0);
			ArrayList<DauSach> ls = pds.getListDauSach();
			for (int i = 0; i < ls.size(); i++) {
				DauSach s = (DauSach) ls.get(i);
				Vector<Object> tbRow1 = new Vector<>();
				tbRow1.add(s.getMaDauSach());
				tbRow1.add(s.getTenSach());
				tbRow1.add(s.getSoLuong());
				tbRow1.add(s.getTheLoai());
				tbRow1.add(s.getTacGia());
				tbRow1.add(s.getNXB());	
				tbRow1.add(s.getNamXB());
				rows1.add(tbRow1);
			}
			Model1.setDataVector(rows1, columns1);
			tableDauSach.setModel(Model1);
		}
		
		// Sach
		public void getAllSach() {
			Model2.setRowCount(0);
			ArrayList<Sach> ls = ps.getListSach();
			for (int i = 0; i < ls.size(); i++) {
				Sach s = (Sach) ls.get(i);
				Vector<Object> tbRow2 = new Vector<>();
				tbRow2.add(s.getMaSach());
				tbRow2.add(s.getTenSach());
				tbRow2.add(s.getTrangThai());
				tbRow2.add(s.getMaDauSach());
				rows2.add(tbRow2);
			}
			Model2.setDataVector(rows2, columns2);
			tableSach.setModel(Model2);
		}
		
		// Nguoi Muon
		public void getAllNguoiMuon() {

			Model3.setRowCount(0);
			ArrayList<NguoiMuon> ls = pmn.getListNguoiMuon();
			for (int i = 0; i < ls.size(); i++) {
				NguoiMuon s = (NguoiMuon) ls.get(i);
				Vector<Object> tbRow3 = new Vector<>();
				tbRow3.add(s.getMaNguoiMuon());
				tbRow3.add(s.getTenNguoiMuon());
				tbRow3.add(s.getDiaChi());
				tbRow3.add(s.getGmail());
				tbRow3.add(s.getSDT());	
				rows3.add(tbRow3);
			}
			Model3.setDataVector(rows3, columns3);
			tableNguoiMuon.setModel(Model3);
		}
		// Phieu Muon
		public void getAllPhieuMuon() {

			Model4.setRowCount(0);
			ArrayList<PhieuMuon> ls = pm.getListPhieuMuon();
			for (int i = 0; i < ls.size(); i++) {
				PhieuMuon s = (PhieuMuon) ls.get(i);
				Vector<Object> tbRow4 = new Vector<>();
				tbRow4.add(s.getMaPhieuMuon());
				tbRow4.add(s.getNgayMuon());
				tbRow4.add(s.getHanTra());
				tbRow4.add(s.getMaSach());
				tbRow4.add(s.getMaNguoiMuon());
				tbRow4.add(s.getNgayTra());
				rows4.add(tbRow4);
			}
			Model4.setDataVector(rows4, columns4);
			tablePhieuMuon.setModel(Model4);
		}
		// Thong ke
		public void Thongke(JLabel lb1, JLabel lb2, JLabel lb3, JLabel lb4) {
			Connection cn = cd.getConnection();
			String sql1 = "Select sum(Soluong)  as slsach from tb_dausach;";
			String sql2 = "Select Count(MaNguoiMuon)  as slnguoimuon from tb_nguoimuon;";
			String sql3 = "Select Count(MaPhieuMuon) as slphieumuon from tb_phieumuon;";
			String sql4 = "Select Count(MaPhieuMuon) as slphieumuonquahan from tb_phieumuon where  (HanTra) < ( SELECT CURDATE()) and   NgayTra is NULL;";
			String sql5 = "SELECT tb_nguoimuon.MaNguoiMuon, tb_nguoimuon.TenNguoiMuon, tb_sach.TenSach, tb_phieumuon.HanTra"
					+ " FROM tb_phieumuon INNER JOIN tb_nguoimuon ON tb_phieumuon.MaNguoiMuon = tb_nguoimuon.MaNguoiMuon"
					+ " INNER JOIN tb_sach ON tb_phieumuon.MaSach = tb_sach.MaSach "
					+ "INNER JOIN tb_dausach ON tb_sach.MaDauSach = tb_dausach.MaDauSach"
					+ " where tb_phieumuon.HanTra < ( SELECT CURDATE()) and NgayTra is NULL;";
			
			String sql6 = "SELECT tb_nguoimuon.MaNguoiMuon, tb_nguoimuon.TenNguoiMuon, tb_nguoimuon.SDT, tb_sach.TenSach, tb_dausach.TheLoai, tb_phieumuon.NgayMuon, tb_phieumuon.HanTra "
					+ "FROM  tb_phieumuon INNER JOIN tb_nguoimuon ON tb_phieumuon.MaNguoiMuon = tb_nguoimuon.MaNguoiMuon "
					+ "INNER JOIN  tb_sach ON tb_phieumuon.MaSach = tb_sach.MaSach "
					+ "INNER JOIN tb_dausach ON tb_sach.MaDauSach = tb_dausach.MaDauSach;";
			try {
				PreparedStatement ps1 = (PreparedStatement) cn.prepareStatement(sql1);
				ResultSet rs1 = ps1.executeQuery();
				if(rs1.next()) lb1.setText("Tổng số sách trong kho: "+Integer.toString(rs1.getInt("slsach")));
				
				PreparedStatement ps2 = (PreparedStatement) cn.prepareStatement(sql2);
				ResultSet rs2 = ps2.executeQuery();
				if(rs2.next()) lb2.setText("Tổng số khách hàng : "+Integer.toString(rs2.getInt("slnguoimuon")));
				
				PreparedStatement ps3 = (PreparedStatement) cn.prepareStatement(sql3);
				ResultSet rs3 = ps3.executeQuery();
				if(rs3.next()) lb3.setText("Tổng số phiếu mượn : "+Integer.toString(rs3.getInt("slphieumuon")));
				
				PreparedStatement ps4 = (PreparedStatement) cn.prepareStatement(sql4);
				ResultSet rs4 = ps4.executeQuery();
				if(rs4.next()) lb4.setText("Tổng số phiếu mượn quá hạn : "+Integer.toString(rs4.getInt("slphieumuonquahan")));

				PreparedStatement ps5 = (PreparedStatement) cn.prepareStatement(sql5);
				ResultSet rs5 = ps5.executeQuery();
				while(rs5.next()) {
					Vector<Object> tbRow5 = new Vector<>();
					tbRow5.add(rs5.getString("MaNguoiMuon"));
					tbRow5.add(rs5.getString("TenNguoiMuon"));
					tbRow5.add(rs5.getString("TenSach"));
					
					tbRow5.add(rs5.getDate("HanTra"));
					rows5.add(tbRow5);
				}
				Model5.setDataVector(rows5, columns5);
				tableThongke.setModel(Model5);
				
				
				PreparedStatement ps6 = (PreparedStatement) cn.prepareStatement(sql6);
				ResultSet rs6 = ps6.executeQuery();
				while(rs6.next()) {
					Vector<Object> tbRow6 = new Vector<>();
					tbRow6.add(rs6.getString("MaNguoiMuon"));
					tbRow6.add(rs6.getString("TenNguoiMuon"));
					tbRow6.add(rs6.getString("SDT"));
					tbRow6.add(rs6.getString("TenSach"));
					tbRow6.add(rs6.getString("TheLoai"));
					tbRow6.add(rs6.getDate("NgayMuon"));
					tbRow6.add(rs6.getDate("HanTra"));
					rows6.add(tbRow6);
				}
				Model6.setDataVector(rows6, columns6);
				tableThongtinNguoimuonsach.setModel(Model6);
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_ThongKe frame = new GUI_ThongKe();
					frame.setLocationRelativeTo(null);  // Giua man hinh
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUI_ThongKe() {
		setResizable(false);
		
		
		setTitle("Thống kê");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 893, 765);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(SystemColor.inactiveCaption);
		tabbedPane.setBounds(10, 10, 858, 718);
		contentPane.add(tabbedPane);
		
		JPanel panelDauSach = new JPanel();
		panelDauSach.setBackground(SystemColor.inactiveCaption);
		tabbedPane.addTab("Đầu sách", null, panelDauSach, null);
		panelDauSach.setLayout(null);
		
		JTextArea txtSearchDS = new JTextArea();
		txtSearchDS.setFont(new Font("Monospaced", Font.PLAIN, 15));
		txtSearchDS.setBounds(134, 67, 187, 22);
		panelDauSach.add(txtSearchDS);
		
		JButton btnSearchDauSach = new JButton("Tìm kiếm");
		btnSearchDauSach.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSearchDauSach.setBounds(557, 67, 105, 21);
		panelDauSach.add(btnSearchDauSach);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(29, 212, 802, 402);
		panelDauSach.add(scrollPane);
		
		tableDauSach = new JTable();
		tableDauSach.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
			}
		));
		scrollPane.setViewportView(tableDauSach);
		
		JComboBox cbbDauSach = new JComboBox();
		cbbDauSach.setModel(new DefaultComboBoxModel(new String[] {"Mã đầu sách", "Tên sách", "Số lượng", "Thể loại", "Tác giả", "Nhà xuất bản", "Năm xuất bản"}));
		cbbDauSach.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cbbDauSach.setBounds(365, 69, 120, 21);
		panelDauSach.add(cbbDauSach);
		
		JPanel panelSach = new JPanel();
		panelSach.setBackground(SystemColor.inactiveCaption);
		tabbedPane.addTab("Sách", null, panelSach, null);
		panelSach.setLayout(null);
		
		JTextArea txtSearchSach = new JTextArea();
		txtSearchSach.setBounds(120, 41, 220, 22);
		panelSach.add(txtSearchSach);
		
		JComboBox cbbSach = new JComboBox();
		cbbSach.setModel(new DefaultComboBoxModel(new String[] {"Mã sách", "Tên sách", "Trạng thái", "Mã đầu sách"}));
		cbbSach.setBounds(377, 43, 147, 21);
		panelSach.add(cbbSach);
		
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(50, 167, 745, 408);
		panelSach.add(scrollPane_1);
		
		tableSach = new JTable();
		scrollPane_1.setViewportView(tableSach);
		
		JPanel panelNguoiMuon = new JPanel();
		panelNguoiMuon.setBackground(SystemColor.inactiveCaption);
		tabbedPane.addTab("Người mượn", null, panelNguoiMuon, null);
		panelNguoiMuon.setLayout(null);
		
		JTextArea txtSearchNguoiMuon = new JTextArea();
		txtSearchNguoiMuon.setFont(new Font("Monospaced", Font.PLAIN, 15));
		txtSearchNguoiMuon.setBounds(138, 38, 169, 22);
		panelNguoiMuon.add(txtSearchNguoiMuon);
		
		JComboBox cbbNguoiMuon = new JComboBox();
		cbbNguoiMuon.setModel(new DefaultComboBoxModel(new String[] {"Mã người mượn", "Tên người mượn", "Địa chỉ", "Gmail", "SDT"}));
		cbbNguoiMuon.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cbbNguoiMuon.setBounds(371, 40, 160, 21);
		panelNguoiMuon.add(cbbNguoiMuon);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(29, 182, 802, 394);
		panelNguoiMuon.add(scrollPane_2);
		
		tableNguoiMuon = new JTable();
		scrollPane_2.setViewportView(tableNguoiMuon);
		
		JPanel panelPhieuMuon = new JPanel();
		panelPhieuMuon.setBackground(SystemColor.inactiveCaption);
		tabbedPane.addTab("Phiếu mượn", null, panelPhieuMuon, null);
		panelPhieuMuon.setLayout(null);
		
		JTextArea txtSearchPhieuMuon = new JTextArea();
		txtSearchPhieuMuon.setFont(new Font("Monospaced", Font.PLAIN, 15));
		txtSearchPhieuMuon.setBounds(124, 42, 196, 22);
		panelPhieuMuon.add(txtSearchPhieuMuon);
		
		JComboBox cbbPhieuMuon = new JComboBox();
		cbbPhieuMuon.setModel(new DefaultComboBoxModel(new String[] {"Mã phiếu mượn", "Ngày mượn", "Hạn trả", "Mã sách", "Mã người mượn", "Ngày trả"}));
		cbbPhieuMuon.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cbbPhieuMuon.setBounds(379, 44, 183, 21);
		panelPhieuMuon.add(cbbPhieuMuon);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(46, 156, 772, 423);
		panelPhieuMuon.add(scrollPane_3);
		
		tablePhieuMuon = new JTable();
		scrollPane_3.setViewportView(tablePhieuMuon);
		
		btnSearchDauSach.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String sql= null;
				if(txtSearchDS.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Bạn chưa nhập đủ thông tin!" , "Thông báo", 1);
				}
				else {
					if(cbbDauSach.getSelectedIndex() == 0) {
						 sql = "SELECT * FROM tb_dausach where MaDauSach = ?";
					}
					else if(cbbDauSach.getSelectedIndex() == 1) {
						 sql = "SELECT * FROM tb_dausach where TenSach = ?";
					}
					else if(cbbDauSach.getSelectedIndex() == 2) {
						 sql = "SELECT * FROM tb_dausach where SoLuong = ?";
					}
					else if(cbbDauSach.getSelectedIndex() == 3) {
						 sql = "SELECT * FROM tb_dausach where TheLoai = ?";
					}
					else if(cbbDauSach.getSelectedIndex() == 4) {
						 sql = "SELECT * FROM tb_dausach where TacGia = ?";
					}
					else if(cbbDauSach.getSelectedIndex() == 5) {
						 sql = "SELECT * FROM tb_dausach where NXB = ?";
					}
					else if(cbbDauSach.getSelectedIndex() == 6) {
						 sql = "SELECT * FROM tb_dausach where NamXB = ?";
					}
					Model1.setRowCount(0);
					Connection cn = cd.getConnection();
					ArrayList<DauSach> ls = new ArrayList<>();
					try {
						PreparedStatement ps = (PreparedStatement) cn.prepareStatement(sql);
						ps.setString(1, txtSearchDS.getText());
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
							for (int i = 0; i < ls.size(); i++) {
								DauSach s = (DauSach) ls.get(i);
								Vector<Object> tbRow1 = new Vector<>();
								tbRow1.add(s.getMaDauSach());
								tbRow1.add(s.getTenSach());
								tbRow1.add(s.getSoLuong());
								tbRow1.add(s.getTheLoai());
								tbRow1.add(s.getTacGia());
								tbRow1.add(s.getNXB());	
								tbRow1.add(s.getNamXB());
								rows1.add(tbRow1);
							}
							Model1.setDataVector(rows1, columns1);
							tableDauSach.setModel(Model1);

							cbbDauSach.setSelectedIndex(-1);
							txtSearchDS.setText(null);
					} catch (Exception e1) {
						
					}
					if(Model1.getRowCount() == 0) {
						JOptionPane.showMessageDialog(null, "Không tìm thấy !" , "Thông báo", 1);
					}
	
				}
				
			}
		});
		
		JButton btnSearchSach = new JButton("Tìm kiếm");
		btnSearchSach.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSearchSach.setBounds(572, 40, 124, 23);
		panelSach.add(btnSearchSach);
		btnSearchSach.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String sql= null;
				if(txtSearchSach.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Bạn chưa nhập đủ thông tin!" , "Thông báo", 1);
				}
				else {
					if(cbbSach.getSelectedIndex() == 0) {
						 sql = "SELECT * FROM tb_sach where MaSach = ?";
					}
					else if(cbbSach.getSelectedIndex() == 1) {
						 sql = "SELECT distinct * FROM tb_sach where TenSach = ?";
					}
					else if(cbbSach.getSelectedIndex() == 2) {
						 sql = "SELECT * FROM tb_sach where TrangThai = ?";
					}
					else if(cbbSach.getSelectedIndex() == 3) {
						 sql = "SELECT * FROM tb_sach where MaDauSach = ?";
					}

				Model2.setRowCount(0);
				Connection cn = cd.getConnection();
				ArrayList<Sach> ls = new ArrayList<>();
				try {
					PreparedStatement ps = (PreparedStatement) cn.prepareStatement(sql);
					ps.setString(1, txtSearchSach.getText());
					ResultSet rs = ps.executeQuery();
					while (rs.next()) {
						Sach st = new Sach();
						st.setMaSach(rs.getString("MaSach"));
						st.setTenSach(rs.getString("TenSach"));
						st.setTrangThai(rs.getString("TrangThai"));
						st.setMaDauSach(rs.getString("MaDauSach"));
						ls.add(st);
					}
						for (int i = 0; i < ls.size(); i++) {
							Sach s = (Sach) ls.get(i);
							Vector<Object> tbRow2 = new Vector<>();
							tbRow2.add(s.getMaSach());
							tbRow2.add(s.getTenSach());
							tbRow2.add(s.getTrangThai());
							tbRow2.add(s.getMaDauSach());
							rows2.add(tbRow2);
						}
						Model2.setDataVector(rows2, columns2);
						tableSach.setModel(Model2);
						cbbSach.setSelectedIndex(-1);
						txtSearchSach.setText(null);
				} catch (Exception e1) {
					
				}
				if(Model2.getRowCount() == 0) {
					JOptionPane.showMessageDialog(null, "Không tìm thấy !" , "Thông báo", 1);
				}

				}
				
			}
		});

		cbbDauSach.setSelectedIndex(-1);
		
		JButton btnLamMoiDS = new JButton("Làm mới");
		btnLamMoiDS.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnLamMoiDS.setBounds(340, 132, 105, 21);
		panelDauSach.add(btnLamMoiDS);
		
		JButton btnThoat = new JButton("Về trang chủ");
		btnThoat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUI_ThuThu_TrangChu trangChu = new GUI_ThuThu_TrangChu();
				trangChu.setLocationRelativeTo(null);  // Giua man hinh
				trangChu.setVisible(true);
				dispose();
			}
		});
		btnThoat.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnThoat.setBounds(696, 637, 135, 27);
		panelDauSach.add(btnThoat);
		
		JLabel lblNewLabel_2 = new JLabel("Danh sách đầu sách");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(40, 176, 208, 22);
		panelDauSach.add(lblNewLabel_2);
		cbbSach.setSelectedIndex(-1);
		
		JButton btnLamMoiSach = new JButton("Làm mới");
		
		btnLamMoiSach.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnLamMoiSach.setBounds(330, 105, 105, 21);
		panelSach.add(btnLamMoiSach);
		
		JButton btnThoat_1 = new JButton("Về trang chủ");
		btnThoat_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUI_ThuThu_TrangChu trangChu = new GUI_ThuThu_TrangChu();
				trangChu.setLocationRelativeTo(null);  // Giua man hinh
				trangChu.setVisible(true);
				dispose();
			}
		});
		btnThoat_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnThoat_1.setBounds(689, 634, 139, 27);
		panelSach.add(btnThoat_1);
		
		JLabel lblNewLabel_2_1 = new JLabel("Danh sách sách");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2_1.setBounds(55, 132, 208, 22);
		panelSach.add(lblNewLabel_2_1);
		cbbNguoiMuon.setSelectedIndex(-1);
		cbbPhieuMuon.setSelectedIndex(-1);
		
		JButton btnNewButton = new JButton("Tìm kiếm");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql= null;
				if(txtSearchNguoiMuon.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Bạn chưa nhập đủ thông tin!" , "Thông báo", 1);
				}
				else {
					
					if(cbbNguoiMuon.getSelectedIndex() == 0) {
						 sql = "SELECT * FROM tb_nguoimuon where MaNguoiMuon = ?";
					}
					else if(cbbNguoiMuon.getSelectedIndex() == 1) {
						 sql = "SELECT * FROM tb_nguoimuon where TenNguoiMuon = ?";
					}
					else if(cbbNguoiMuon.getSelectedIndex() == 2) {
						 sql = "SELECT * FROM tb_nguoimuon where DiaChi = ?";
					}
					else if(cbbNguoiMuon.getSelectedIndex() == 3) {
						 sql = "SELECT * FROM tb_nguoimuon where Gmail = ?";
					}
					else if(cbbNguoiMuon.getSelectedIndex() == 4) {
						 sql = "SELECT * FROM tb_nguoimuon where SDT = ?";
					}

					Connection cn = cd.getConnection();
					ArrayList<NguoiMuon> ls = new ArrayList<>();
					try {
						Model3.setRowCount(0);
						PreparedStatement ps = (PreparedStatement) cn.prepareStatement(sql);
						ps.setString(1, txtSearchNguoiMuon.getText());
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
							for (int i = 0; i < ls.size(); i++) {

								NguoiMuon s = (NguoiMuon) ls.get(i);
								Vector<Object> tbRow3 = new Vector<>();
								tbRow3.add(s.getMaNguoiMuon());
								tbRow3.add(s.getTenNguoiMuon());
								tbRow3.add(s.getDiaChi());
								tbRow3.add(s.getGmail());
								tbRow3.add(s.getSDT());	
								rows3.add(tbRow3);
							}
							Model3.setDataVector(rows3, columns3);
							tableNguoiMuon.setModel(Model3);
							
							
							cbbNguoiMuon.setSelectedIndex(-1);
							txtSearchNguoiMuon.setText(null);
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "Không tìm thấy!" , "Thông báo", 1);
					}
					if(Model3.getRowCount() == 0) {
						JOptionPane.showMessageDialog(null, "Không tìm thấy !" , "Thông báo", 1);
					}
				}
			}
			
		});
		btnNewButton.setBounds(600, 38, 122, 25);
		JButton btnSearchPhieuMuon = new JButton("Tìm kiếm");
		btnSearchPhieuMuon.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSearchPhieuMuon.setBounds(597, 43, 125, 22);
		panelPhieuMuon.add(btnSearchPhieuMuon);
		
		JButton btnLamMoiPhieuMuon = new JButton("Làm mới");
		
		btnLamMoiPhieuMuon.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnLamMoiPhieuMuon.setBounds(374, 97, 105, 21);
		panelPhieuMuon.add(btnLamMoiPhieuMuon);
		
		JButton btnThoat_3 = new JButton("Về trang chủ");
		btnThoat_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUI_ThuThu_TrangChu trangChu = new GUI_ThuThu_TrangChu();
				trangChu.setLocationRelativeTo(null);  // Giua man hinh
				trangChu.setVisible(true);
				dispose();
			}
		});
		
		
		
		
		btnThoat_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnThoat_3.setBounds(691, 601, 137, 27);
		panelPhieuMuon.add(btnThoat_3);
		
		JLabel lblNewLabel_2_3 = new JLabel("Danh sách phiếu mượn");
		lblNewLabel_2_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2_3.setBounds(54, 124, 208, 22);
		panelPhieuMuon.add(lblNewLabel_2_3);
		btnSearchPhieuMuon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql= null;
				if(txtSearchPhieuMuon.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Bạn chưa nhập đủ thông tin!" , "Thông báo", 1);
				}
				else {
						
					
					if(cbbPhieuMuon.getSelectedIndex() == 0) {
						 sql = "SELECT * FROM tb_phieumuon where MaPhieuMuon = ?";
					}
					else if(cbbPhieuMuon.getSelectedIndex() == 1) {
						 sql = "SELECT * FROM tb_phieumuon where NgayMuon = ?";
					}
					else if(cbbPhieuMuon.getSelectedIndex() == 2) {
						 sql = "SELECT * FROM tb_phieumuon where HanTra = ?";
					}
					else if(cbbPhieuMuon.getSelectedIndex() == 3) {
						 sql = "SELECT * FROM tb_phieumuon where MaSach = ?";
					}
					else if(cbbPhieuMuon.getSelectedIndex() == 4) {
						 sql = "SELECT * FROM tb_phieumuon where MaNguoiMuon = ?";
					}
					else if(cbbPhieuMuon.getSelectedIndex() == 5) {
						 sql = "SELECT * FROM tb_phieumuon where NgayTra = ?";
					}

					Model4.setRowCount(0);
					Connection cn = cd.getConnection();
					ArrayList<PhieuMuon> ls = new ArrayList<>();
					try {
						PreparedStatement ps = (PreparedStatement) cn.prepareStatement(sql);
						ps.setString(1, txtSearchPhieuMuon.getText());
						ResultSet rs = ps.executeQuery();
						while (rs.next()) {
							PhieuMuon st = new PhieuMuon();
							st.setMaPhieuMuon(rs.getString("MaPhieuMuon"));
							st.setNgayMuon(rs.getDate("NgayMuon"));
							st.setHanTra(rs.getDate("HanTra"));
							st.setMaSach(rs.getString("MaSach"));
							st.setMaNguoiMuon(rs.getString("MaNguoiMuon"));
							st.setNgayTra(rs.getDate("NgayTra"));
							ls.add(st);
						}
							for (int i = 0; i < ls.size(); i++) {
								PhieuMuon s = (PhieuMuon) ls.get(i);
								Vector<Object> tbRow4 = new Vector<>();
								tbRow4.add(s.getMaPhieuMuon());
								tbRow4.add(s.getNgayMuon());
								tbRow4.add(s.getHanTra());
								tbRow4.add(s.getMaSach());
								tbRow4.add(s.getMaNguoiMuon());
								tbRow4.add(s.getNgayTra());
								rows4.add(tbRow4);
							}
							Model4.setDataVector(rows4, columns4);
							tablePhieuMuon.setModel(Model4);
							cbbPhieuMuon.setSelectedIndex(-1);
							txtSearchPhieuMuon.setText(null);
					}
				 catch (Exception e1) {
					//JOptionPane.showMessageDialog(null, "Không tìm thấy!" , "Thông báo", 1);
				}
					if(Model4.getRowCount() == 0) {
						JOptionPane.showMessageDialog(null, "Không tìm thấy !" , "Thông báo", 1);
					}
			}
		}
		
	});
		panelNguoiMuon.add(btnNewButton);
		
		JButton btnLamMoiNguoiMuon = new JButton("Làm mới");
		btnLamMoiNguoiMuon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtSearchNguoiMuon.setText(null);
				cbbNguoiMuon.setSelectedItem(-1);
				getAllNguoiMuon();
			}
		});
		btnLamMoiNguoiMuon.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnLamMoiNguoiMuon.setBounds(357, 84, 105, 21);
		panelNguoiMuon.add(btnLamMoiNguoiMuon);
		
		JButton btnThoat_2 = new JButton("Về trang chủ");
		btnThoat_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUI_ThuThu_TrangChu trangChu = new GUI_ThuThu_TrangChu();
				trangChu.setLocationRelativeTo(null);  // Giua man hinh
				trangChu.setVisible(true);
				dispose();
			}
		});
		btnThoat_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnThoat_2.setBounds(700, 602, 122, 25);
		panelNguoiMuon.add(btnThoat_2);
		
		JLabel lblNewLabel_2_2 = new JLabel("Danh sách người mượn");
		lblNewLabel_2_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2_2.setBounds(30, 140, 208, 22);
		panelNguoiMuon.add(lblNewLabel_2_2);
		
		JPanel panelThongKe = new JPanel();
		panelThongKe.setBackground(SystemColor.inactiveCaption);
		tabbedPane.addTab("Thống kê", null, panelThongKe, null);
		panelThongKe.setLayout(null);
		
		JLabel lblTongsoSach = new JLabel("1");
		lblTongsoSach.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTongsoSach.setBounds(80, 36, 203, 24);
		panelThongKe.add(lblTongsoSach);
		JLabel lblTongsoNguoiMuon = new JLabel("2");
		lblTongsoNguoiMuon.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTongsoNguoiMuon.setBounds(80, 80, 252, 24);
		panelThongKe.add(lblTongsoNguoiMuon);
		
		JLabel lblTongSoPhieuMuon = new JLabel("3");
		lblTongSoPhieuMuon.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTongSoPhieuMuon.setBounds(431, 31, 264, 34);
		panelThongKe.add(lblTongSoPhieuMuon);
		
		JLabel lblTongSoPhieuMuonQuaHan = new JLabel("4");
		lblTongSoPhieuMuonQuaHan.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTongSoPhieuMuonQuaHan.setBounds(431, 75, 298, 34);
		panelThongKe.add(lblTongSoPhieuMuonQuaHan);
		

		
		
		JButton btnThoat_4 = new JButton("Về trang chủ");
		btnThoat_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUI_ThuThu_TrangChu trangChu = new GUI_ThuThu_TrangChu();
				trangChu.setLocationRelativeTo(null);  // Giua man hinh
				trangChu.setVisible(true);
				dispose();
			}
		});
		btnThoat_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnThoat_4.setBounds(693, 660, 137, 21);
		panelThongKe.add(btnThoat_4);
		
		JLabel lblNewLabel = new JLabel("Thông tin phiếu mượn quá hạn");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(66, 357, 342, 19);
		panelThongKe.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Thông tin người mượn sách");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(66, 130, 310, 21);
		panelThongKe.add(lblNewLabel_1);
		
		btnLamMoiDS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtSearchDS.setText(null);
				cbbDauSach.setSelectedIndex(-1);
				getAllDauSach();
			}
		});
		btnLamMoiSach.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtSearchSach.setText(null);
				cbbSach.setSelectedIndex(-1);
				getAllSach();
			}
		});
		btnLamMoiPhieuMuon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtSearchPhieuMuon.setText(null);
				cbbPhieuMuon.setSelectedIndex(-1);
				getAllPhieuMuon();
			}
		});
		
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(40, 401, 777, 233);
		panelThongKe.add(scrollPane_4);
		tableThongke = new JTable();
		scrollPane_4.setViewportView(tableThongke);

		JScrollPane scrollPane_5 = new JScrollPane();
		scrollPane_5.setBounds(40, 161, 768, 186);
		panelThongKe.add(scrollPane_5);
		tableThongtinNguoimuonsach = new JTable();
		scrollPane_5.setViewportView(tableThongtinNguoimuonsach);
		
		
		columns1.add("Mã đầu sách");
		columns1.add("Tên sách");
		columns1.add("Số lượng");
		columns1.add("Thể loại");
		columns1.add("Tác giả");
		columns1.add("Nhà xuất bản");
		columns1.add("Năm xuất bản");
		
		columns2.add("Mã sách");
		columns2.add("Tên sách");
		columns2.add("Trạng thái");
		columns2.add("Mã đầu sách");
		
		
		columns3.add("Mã người mượn");
		columns3.add("Tên người mượn");
		columns3.add("Địa chỉ");
		columns3.add("Gmail");
		columns3.add("SDT");
		
		columns4.add("Mã phiếu mượn");
		columns4.add("Ngày mượn");
		columns4.add("Hạn trả");
		columns4.add("Mã sách");
		columns4.add("Mã người mượn");
		columns4.add("Ngày trả");
		
		columns5.add("Mã người mượn");
		columns5.add("Tên người mượn");
		columns5.add("Tên sách");
		columns5.add("Hạn trả");
		
		columns6.add("Mã người mượn");
		columns6.add("Tên người mượn");
		columns6.add("SDT");
		columns6.add("Tên sách");
		columns6.add("Thể loại");
		columns6.add("Ngày mượn");
		columns6.add("Hạn trả");
		
		
		getAllDauSach();
		getAllSach();
		getAllNguoiMuon();
		getAllPhieuMuon();
		Thongke(lblTongsoSach, lblTongsoNguoiMuon, lblTongSoPhieuMuon, lblTongSoPhieuMuonQuaHan);
		


	}
}
