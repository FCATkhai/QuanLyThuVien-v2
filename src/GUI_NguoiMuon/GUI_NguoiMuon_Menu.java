/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI_NguoiMuon;

import GUI_ThuThu.*;
import Main.GUI_ChooseLogin;
import Object.DauSach;
import java.awt.CardLayout;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Vector;

import Process.Process_NguoiMuon;
import Object.NguoiMuon;
import Object.PhieuMuon;
import Object.Sach;
import Process.Connect_database;
import Process.Process_Sach;
import Process.Process_PhieuMuon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author ASUS
 */
public final class GUI_NguoiMuon_Menu extends javax.swing.JFrame {

    /**
     * Creates new form HomePage
     */
    String MaNguoiMuon = null;
    CardLayout cardLayout;
    DefaultTableModel Model1 = new DefaultTableModel();
    Vector<String> columnsMuon = new Vector<String>();
    Vector<Vector<Object>> rowsMuon = new Vector<>();

    DefaultTableModel Model2 = new DefaultTableModel();
    Vector<String> columnsTra = new Vector<String>();
    Vector<Vector<Object>> rowsTra = new Vector<>();
    Connect_database cd = new Connect_database();

    Process_Sach ps = new Process_Sach();
    Process_NguoiMuon pnm = new Process_NguoiMuon();
    Process_PhieuMuon ppm = new Process_PhieuMuon();


    public void getTTNguoiMuon(String MaNguoiMuon) {
        NguoiMuon TTNguoiMuon = pnm.getTTNguoiMuon(MaNguoiMuon);
        labelXinChao.setText("Xin chào, " + TTNguoiMuon.getTenNguoiMuon());
        lableMaNguoiMuon.setText(TTNguoiMuon.getMaNguoiMuon());
        labelHoVaTen.setText(TTNguoiMuon.getTenNguoiMuon());
        labelDiaChi.setText(TTNguoiMuon.getDiaChi());
        labelGmail.setText(TTNguoiMuon.getGmail());
        labelSDT.setText(TTNguoiMuon.getSDT());
    }

    public void getSLSach() throws SQLException {

        Connection cn = cd.getConnection();
        try{
            String sql1 = "Select sum(Soluong)  as slsach from tb_dausach;";
            PreparedStatement ps1 = (PreparedStatement) cn.prepareStatement(sql1);
            ResultSet rs1 = ps1.executeQuery();
            if(rs1.next()) labelSLSach.setText(Integer.toString(rs1.getInt("slsach")));           
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

    }

    public void getSLSachDaMuon(String MaNguoiMuon) {
        Connection cn = null;
        PreparedStatement ps2 = null;
        ResultSet rs1 = null;

        try {
            cn = cd.getConnection();  // Assuming cd.getConnection() establishes the connection

            // Updated SQL query (simplified without JOIN, just counting borrowed books for a user)
            String sql2 = "SELECT COUNT(*) AS slmuon FROM tb_phieumuon WHERE MaNguoiMuon = ?";

            ps2 = cn.prepareStatement(sql2);
            ps2.setString(1, MaNguoiMuon);  // Set the user ID (MaNguoiMuon)

            rs1 = ps2.executeQuery();
            System.out.println(rs1.getRow());
            if (rs1.next()) {
                // Update the label with the count of borrowed books (as an integer converted to String)
                labelSLSachDaMuon.setText(Integer.toString(rs1.getInt("slmuon")));
            }

        } catch (SQLException ex) {
            // Error handling
            System.out.println("Error occurred while retrieving the borrowed books count: " + ex.getMessage());
        } finally {
            // Close resources in finally block to prevent resource leaks
            try {
                if (rs1 != null) rs1.close();
                if (ps2 != null) ps2.close();
                if (cn != null) cn.close();
            } catch (SQLException ex) {
                System.out.println("Error closing resources: " + ex.getMessage());
            }
        }    
    }
    public boolean insertPhieuMuon(String MaSach, String MaNguoiMuon) {
        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String MaPhieuMuon = null;
        try {
            cn = cd.getConnection();
            String sqlMax = "SELECT MAX(MaPhieuMuon) AS lastPhieuMuon FROM tb_phieumuon";
            ps = cn.prepareStatement(sqlMax);
            rs = ps.executeQuery();

            if (rs.next()) {
                String lastPhieuMuon = rs.getString("lastPhieuMuon");
                if (lastPhieuMuon == null || lastPhieuMuon.isEmpty()) {
                    MaPhieuMuon = "P001";
                } else {
                    String numericPart = lastPhieuMuon.substring(1);
                    int nextNumber = Integer.parseInt(numericPart) + 1;
                    MaPhieuMuon = "P" + String.format("%03d", nextNumber);
                }
            }

            String sqlInsert = "INSERT INTO tb_phieumuon (MaPhieuMuon, NgayMuon, HanTra, MaSach, MaNguoiMuon) "
                             + "VALUES (?, NOW(), DATE_ADD(NOW(), INTERVAL 7 DAY), ?, ?)";
            ps = cn.prepareStatement(sqlInsert);
            ps.setString(1, MaPhieuMuon);
            ps.setString(2, MaSach);
            ps.setString(3, MaNguoiMuon);
            ps.executeUpdate();

            

            return true;
        } catch (SQLException e) {
            System.out.println("Error while inserting PhieuMuon: " + e.getMessage());
            return false;
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (cn != null) cn.close();
            } catch (SQLException e) {
                System.out.println("Error closing resources: " + e.getMessage());
            }
        }
    }

    // Muon Card
    public void getTableMuon() {
        ArrayList<Sach> ls = ps.getListSach();
        Model1.setRowCount(0);
        for (int i = 0; i < ls.size(); i++) {
            Sach s = ls.get(i);
            Vector<Object> tbRow = new Vector<>();
            tbRow.add(s.getMaSach());
            tbRow.add(s.getTenSach());
            tbRow.add(s.getTrangThai());
            tbRow.add(s.getMaDauSach());
            rowsMuon.add(tbRow);
        }
        Model1.setDataVector(rowsMuon, columnsMuon);
        tableMuon.setModel(Model1);
    }

    

    // Tra sach card
    public void getTableSachDaMuon() {
        Model2.setRowCount(0);
        ArrayList<HashMap<String, String>> ls = pnm.getListSachDaMuon(this.MaNguoiMuon);
        for (int i = 0; i < ls.size(); i++) {
            HashMap<String, String> entry = ls.get(i);
            Vector<Object> tbRow = new Vector<>();
            tbRow.add(entry.get("MaPhieuMuon"));
            tbRow.add(entry.get("MaSach"));
            tbRow.add(entry.get("TenSach"));
            tbRow.add(entry.get("NgayMuon"));
            tbRow.add(entry.get("HanTra"));
            tbRow.add(entry.get("NgayTra"));
            tbRow.add(entry.get("TrangThai"));

            rowsTra.add(tbRow);
        }
        Model2.setDataVector(rowsTra, columnsTra);
        tableTra.setModel(Model2);
    }

    public GUI_NguoiMuon_Menu(String MaNguoiMuon) throws SQLException {   
        initComponents();
        this.MaNguoiMuon = MaNguoiMuon;
        getTTNguoiMuon(MaNguoiMuon);
        getSLSach();
        getSLSachDaMuon(MaNguoiMuon);
        btnMuon.setEnabled(false);
        columnsMuon.add("Mã Sách");
        columnsMuon.add("Tên sách");
        columnsMuon.add("Trạng thái");
        columnsMuon.add("Mã đầu sách");
        getTableMuon();
        columnsTra.add("Mã Phiếu mượn");
        columnsTra.add("Mã Sách");
        columnsTra.add("Tên Sách");
        columnsTra.add("Ngày mượn");
        columnsTra.add("Hạn trả");
        columnsTra.add("Ngày trả");
        columnsTra.add("Trạng thái");
        getTableSachDaMuon();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel17 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        UICards = new javax.swing.JPanel();
        homeCard = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        labelSLSach = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        labelSLSachDaMuon = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        lableMaNguoiMuon = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        labelHoVaTen = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        labelDiaChi = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        labelGmail = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        labelSDT = new javax.swing.JLabel();
        borrowCard = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        labelMaSach = new javax.swing.JLabel();
        labelTenSach = new javax.swing.JLabel();
        labelTrangThai = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableMuon = new javax.swing.JTable();
        jPanel18 = new javax.swing.JPanel();
        btnMuon = new javax.swing.JButton();
        jToggleButton2 = new javax.swing.JToggleButton();
        btnReset = new javax.swing.JButton();
        cbbDauSach = new javax.swing.JComboBox<>();
        txtSearchS = new javax.swing.JTextField();
        btnSearchS = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        returnCard = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        labelMaSach_TraSach = new javax.swing.JLabel();
        labelTenSach_TraSach = new javax.swing.JLabel();
        labelTrangThai_TraSach = new javax.swing.JLabel();
        labelNgayMuon_TraSach = new javax.swing.JLabel();
        labelHanTra_TraSach = new javax.swing.JLabel();
        labelNgayTra_TraSach = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        labelMaPhieuMuon_Tra = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableTra = new javax.swing.JTable();
        jLabel25 = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        btnTra = new javax.swing.JButton();
        jToggleButton1 = new javax.swing.JToggleButton();
        btnReset_Tra = new javax.swing.JButton();
        cbbS_tra = new javax.swing.JComboBox<>();
        textSearchS2 = new javax.swing.JTextField();
        btnSearch1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        labelXinChao = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1350, 800));
        setResizable(false);
        setSize(new java.awt.Dimension(1350, 800));

        jSplitPane1.setPreferredSize(new java.awt.Dimension(500, 1200));

        jPanel1.setBackground(new java.awt.Color(66, 61, 61));
        jPanel1.setPreferredSize(new java.awt.Dimension(250, 1035));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel15.setBackground(new java.awt.Color(66, 61, 61));
        jPanel15.setPreferredSize(new java.awt.Dimension(200, 300));
        jPanel15.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, -5, 5));

        jButton1.setBackground(new java.awt.Color(102, 102, 102));
        jButton1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jButton1.setText("Trang chủ");
        jButton1.setMaximumSize(new java.awt.Dimension(200, 50));
        jButton1.setPreferredSize(new java.awt.Dimension(200, 50));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel15.add(jButton1);

        jButton2.setBackground(new java.awt.Color(102, 102, 102));
        jButton2.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jButton2.setText("Mượn sách");
        jButton2.setMaximumSize(new java.awt.Dimension(200, 50));
        jButton2.setPreferredSize(new java.awt.Dimension(200, 50));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel15.add(jButton2);

        jButton3.setBackground(new java.awt.Color(102, 102, 102));
        jButton3.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jButton3.setText("Trả sách");
        jButton3.setMaximumSize(new java.awt.Dimension(200, 50));
        jButton3.setPreferredSize(new java.awt.Dimension(200, 50));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel15.add(jButton3);

        jPanel17.setBackground(new java.awt.Color(66, 61, 61));
        jPanel17.setPreferredSize(new java.awt.Dimension(100, 200));

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );

        jPanel15.add(jPanel17);

        jPanel16.setBackground(new java.awt.Color(66, 61, 61));
        jPanel16.setLayout(new java.awt.GridBagLayout());

        jButton5.setBackground(new java.awt.Color(102, 102, 102));
        jButton5.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jButton5.setText("Đăng xuất");
        jButton5.setMaximumSize(new java.awt.Dimension(200, 50));
        jButton5.setPreferredSize(new java.awt.Dimension(200, 50));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel16.add(jButton5, new java.awt.GridBagConstraints());

        jPanel15.add(jPanel16);

        jPanel1.add(jPanel15, java.awt.BorderLayout.CENTER);

        jSplitPane1.setLeftComponent(jPanel1);

        UICards.setBackground(new java.awt.Color(255, 255, 255));
        UICards.setPreferredSize(new java.awt.Dimension(600, 700));
        UICards.setLayout(new java.awt.CardLayout());

        homeCard.setBackground(new java.awt.Color(255, 255, 255));
        homeCard.setLayout(new java.awt.BorderLayout());

        jPanel14.setLayout(new java.awt.GridBagLayout());

        jPanel10.setBackground(new java.awt.Color(0, 0, 0));
        jPanel10.setLayout(new java.awt.GridLayout(0, 2, 1, 1));

        jPanel12.setLayout(new java.awt.GridBagLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("Tổng số lượng sách");
        jLabel2.setToolTipText("");
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.gridheight = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.ipadx = 399;
        gridBagConstraints.ipady = 49;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(79, 0, 84, 33);
        jPanel12.add(jLabel2, gridBagConstraints);

        jPanel10.add(jPanel12);

        jPanel11.setPreferredSize(new java.awt.Dimension(200, 112));
        java.awt.GridBagLayout jPanel11Layout = new java.awt.GridBagLayout();
        jPanel11Layout.columnWidths = new int[] {100};
        jPanel11.setLayout(jPanel11Layout);

        jLabel4.setBackground(new java.awt.Color(255, 255, 102));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setText("Số lượng sách đã mượn");
        jLabel4.setToolTipText("");
        jLabel4.setFocusable(false);
        jLabel4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel4.setPreferredSize(new java.awt.Dimension(100, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.gridheight = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.ipadx = 400;
        gridBagConstraints.ipady = 50;
        jPanel11.add(jLabel4, gridBagConstraints);

        jPanel10.add(jPanel11);

        jPanel3.setBackground(new java.awt.Color(204, 255, 204));
        jPanel3.setPreferredSize(new java.awt.Dimension(100, 100));
        jPanel3.setLayout(new java.awt.GridBagLayout());

        labelSLSach.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        labelSLSach.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelSLSach.setText("0");
        labelSLSach.setToolTipText("");
        labelSLSach.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        labelSLSach.setPreferredSize(new java.awt.Dimension(50, 50));
        jPanel3.add(labelSLSach, new java.awt.GridBagConstraints());

        jPanel10.add(jPanel3);

        jPanel5.setBackground(new java.awt.Color(255, 255, 204));
        jPanel5.setPreferredSize(new java.awt.Dimension(100, 100));
        jPanel5.setLayout(new java.awt.GridBagLayout());

        labelSLSachDaMuon.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        labelSLSachDaMuon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelSLSachDaMuon.setText("0");
        labelSLSachDaMuon.setToolTipText("");
        labelSLSachDaMuon.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        labelSLSachDaMuon.setPreferredSize(new java.awt.Dimension(50, 50));
        jPanel5.add(labelSLSachDaMuon, new java.awt.GridBagConstraints());

        jPanel10.add(jPanel5);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = -784;
        gridBagConstraints.ipady = -351;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(55, 465, 72, 452);
        jPanel14.add(jPanel10, gridBagConstraints);

        homeCard.add(jPanel14, java.awt.BorderLayout.PAGE_START);

        jPanel9.setLayout(new java.awt.GridBagLayout());

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Thông tin người mượn");
        jLabel11.setPreferredSize(new java.awt.Dimension(388, 100));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 942;
        gridBagConstraints.ipady = 52;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel9.add(jLabel11, gridBagConstraints);

        jPanel13.setLayout(new java.awt.GridLayout(0, 2, 2, 2));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel7.setText("Mã người mượn:");
        jPanel13.add(jLabel7);

        lableMaNguoiMuon.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lableMaNguoiMuon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lableMaNguoiMuon.setText("null");
        jPanel13.add(lableMaNguoiMuon);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel6.setText("Họ và tên:");
        jPanel13.add(jLabel6);

        labelHoVaTen.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        labelHoVaTen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelHoVaTen.setText("null");
        jPanel13.add(labelHoVaTen);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel8.setText("Địa chỉ:");
        jPanel13.add(jLabel8);

        labelDiaChi.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        labelDiaChi.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelDiaChi.setText("null");
        jPanel13.add(labelDiaChi);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel9.setText("Gmail:");
        jPanel13.add(jLabel9);

        labelGmail.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        labelGmail.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelGmail.setText("null");
        jPanel13.add(labelGmail);

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel10.setText("Số điện thoại:");
        jPanel13.add(jLabel10);

        labelSDT.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        labelSDT.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelSDT.setText("null");
        jPanel13.add(labelSDT);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 637;
        gridBagConstraints.ipady = 107;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(12, 95, 397, 0);
        jPanel9.add(jPanel13, gridBagConstraints);

        homeCard.add(jPanel9, java.awt.BorderLayout.CENTER);

        UICards.add(homeCard, "homeCard");

        borrowCard.setBackground(new java.awt.Color(255, 255, 255));
        borrowCard.setPreferredSize(new java.awt.Dimension(1000, 701));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("Mã sách:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setText("Tên sách:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel5.setText("Trạng thái:");

        labelMaSach.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        labelMaSach.setText("...");

        labelTenSach.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        labelTenSach.setText("...");
        labelTenSach.setToolTipText("");

        labelTrangThai.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        labelTrangThai.setText("...");

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        tableMuon.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        tableMuon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "", "Title 2", "Title 3", "Title 4"
            }
        ));
        tableMuon.setRowHeight(25);
        tableMuon.setShowGrid(true);
        tableMuon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMuonMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableMuon);

        btnMuon.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnMuon.setText("Mượn");
        btnMuon.setEnabled(false);
        btnMuon.setPreferredSize(new java.awt.Dimension(150, 35));
        btnMuon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMuonActionPerformed(evt);
            }
        });
        jPanel18.add(btnMuon);

        jToggleButton2.setText("sort");
        jToggleButton2.setPreferredSize(new java.awt.Dimension(100, 35));
        jToggleButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton2ActionPerformed(evt);
            }
        });
        jPanel18.add(jToggleButton2);

        btnReset.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnReset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/refresh (1).png"))); // NOI18N
        btnReset.setPreferredSize(new java.awt.Dimension(40, 35));
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });
        jPanel18.add(btnReset);

        cbbDauSach.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mã Đầu sách", "Mã Sách", "Tên Sách", "Trạng thái", " " }));
        cbbDauSach.setPreferredSize(new java.awt.Dimension(150, 35));
        jPanel18.add(cbbDauSach);

        txtSearchS.setPreferredSize(new java.awt.Dimension(250, 35));
        jPanel18.add(txtSearchS);

        btnSearchS.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnSearchS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/loupe (1).png"))); // NOI18N
        btnSearchS.setText("Tìm");
        btnSearchS.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        btnSearchS.setPreferredSize(new java.awt.Dimension(100, 35));
        btnSearchS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchSActionPerformed(evt);
            }
        });
        jPanel18.add(btnSearchS);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, 828, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 828, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(445, Short.MAX_VALUE))
        );

        jLabel20.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel20.setText("Chức năng mượn sách");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(374, 374, 374)
                        .addComponent(jLabel20))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(labelTenSach, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(labelMaSach, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(labelTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(244, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(labelMaSach))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(labelTenSach))
                .addGap(22, 22, 22)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(labelTrangThai))
                .addGap(18, 18, 18)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );

        javax.swing.GroupLayout borrowCardLayout = new javax.swing.GroupLayout(borrowCard);
        borrowCard.setLayout(borrowCardLayout);
        borrowCardLayout.setHorizontalGroup(
            borrowCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(borrowCardLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        borrowCardLayout.setVerticalGroup(
            borrowCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(borrowCardLayout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(796, 796, 796))
        );

        UICards.add(borrowCard, "borrowCard");

        returnCard.setBackground(new java.awt.Color(255, 255, 255));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel13.setText("Mã sách");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel14.setText("Tên sách");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel15.setText("Ngày mượn");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel16.setText("Hạn trả");

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel17.setText("Ngày trả");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel18.setText("Trạng thái");

        labelMaSach_TraSach.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        labelMaSach_TraSach.setText("...");

        labelTenSach_TraSach.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        labelTenSach_TraSach.setText("...");

        labelTrangThai_TraSach.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        labelTrangThai_TraSach.setText("...");

        labelNgayMuon_TraSach.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        labelNgayMuon_TraSach.setText("...");

        labelHanTra_TraSach.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        labelHanTra_TraSach.setText("....");

        labelNgayTra_TraSach.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        labelNgayTra_TraSach.setText("....");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel12.setText("Mã phiếu mượn");

        labelMaPhieuMuon_Tra.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        labelMaPhieuMuon_Tra.setText("...");

        jLabel22.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel22.setText("Chức năng trả sách");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(308, 308, 308)
                        .addComponent(jLabel22))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(152, 152, 152)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel18)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(labelTrangThai_TraSach, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel12))
                                .addGap(27, 27, 27)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(labelMaSach_TraSach, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(labelTenSach_TraSach, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(labelMaPhieuMuon_Tra, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(jLabel16)
                            .addComponent(jLabel17))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(labelNgayMuon_TraSach, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelHanTra_TraSach, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelNgayTra_TraSach, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel22)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel7Layout.createSequentialGroup()
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel15)
                                    .addComponent(jLabel12)
                                    .addComponent(labelMaPhieuMuon_Tra))
                                .addGroup(jPanel7Layout.createSequentialGroup()
                                    .addGap(56, 56, 56)
                                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel16)
                                        .addComponent(labelMaSach_TraSach))))
                            .addGap(33, 33, 33)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel17)
                                .addComponent(labelTenSach_TraSach)))
                        .addGroup(jPanel7Layout.createSequentialGroup()
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(labelNgayMuon_TraSach)
                                .addGroup(jPanel7Layout.createSequentialGroup()
                                    .addGap(56, 56, 56)
                                    .addComponent(labelHanTra_TraSach)))
                            .addGap(33, 33, 33)
                            .addComponent(labelNgayTra_TraSach)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(jLabel13)
                        .addGap(31, 31, 31)
                        .addComponent(jLabel14)
                        .addGap(33, 33, 33)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(labelTrangThai_TraSach))))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        tableTra.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        tableTra.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tableTra.setRowHeight(25);
        tableTra.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableTraMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tableTra);

        jLabel25.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel25.setText("Danh sách những quyển sách đã mượn");

        btnTra.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnTra.setText("Trả sách");
        btnTra.setEnabled(false);
        btnTra.setPreferredSize(new java.awt.Dimension(150, 35));
        btnTra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTraActionPerformed(evt);
            }
        });
        jPanel19.add(btnTra);

        jToggleButton1.setText("sort");
        jToggleButton1.setPreferredSize(new java.awt.Dimension(150, 35));
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });
        jPanel19.add(jToggleButton1);

        btnReset_Tra.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnReset_Tra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/refresh (1).png"))); // NOI18N
        btnReset_Tra.setPreferredSize(new java.awt.Dimension(40, 35));
        btnReset_Tra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReset_TraActionPerformed(evt);
            }
        });
        jPanel19.add(btnReset_Tra);

        cbbS_tra.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mã Đầu sách", "Mã Sách", "Tên Sách", "Trạng thái", "Ngày mượn", "Ngày trả", "Hạn trả", " ", " ", " " }));
        cbbS_tra.setPreferredSize(new java.awt.Dimension(150, 35));
        jPanel19.add(cbbS_tra);

        textSearchS2.setPreferredSize(new java.awt.Dimension(250, 35));
        jPanel19.add(textSearchS2);

        btnSearch1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnSearch1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/loupe (1).png"))); // NOI18N
        btnSearch1.setText("Tìm");
        btnSearch1.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        btnSearch1.setPreferredSize(new java.awt.Dimension(100, 35));
        btnSearch1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearch1ActionPerformed(evt);
            }
        });
        jPanel19.add(btnSearch1);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane2)
                        .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel25))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel25)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(1286, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout returnCardLayout = new javax.swing.GroupLayout(returnCard);
        returnCard.setLayout(returnCardLayout);
        returnCardLayout.setHorizontalGroup(
            returnCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        returnCardLayout.setVerticalGroup(
            returnCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(returnCardLayout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        UICards.add(returnCard, "returnCard");

        jSplitPane1.setRightComponent(UICards);

        getContentPane().add(jSplitPane1, java.awt.BorderLayout.CENTER);

        jPanel2.setBackground(new java.awt.Color(138, 25, 18));

        labelXinChao.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        labelXinChao.setForeground(new java.awt.Color(255, 255, 255));
        labelXinChao.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        labelXinChao.setText("Xin chào, Nguyen Van A ");
        labelXinChao.setPreferredSize(new java.awt.Dimension(266, 50));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(1229, Short.MAX_VALUE)
                .addComponent(labelXinChao, javax.swing.GroupLayout.PREFERRED_SIZE, 755, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(76, 76, 76))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelXinChao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, java.awt.BorderLayout.PAGE_START);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        UICards.removeAll();
        getSLSachDaMuon(MaNguoiMuon);
        UICards.add(homeCard);
        UICards.revalidate();
        UICards.repaint();    
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        UICards.removeAll();
        UICards.add(borrowCard);
        UICards.revalidate();
        UICards.repaint(); 
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        UICards.removeAll();
        UICards.add(returnCard);
        UICards.revalidate();
        UICards.repaint(); 
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        GUI_ChooseLogin login = new GUI_ChooseLogin();
        
        login.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void tableMuonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMuonMouseClicked
           int index = tableMuon.getSelectedRow();  // Lấy chỉ số của hàng được chọn
          if (index >= 0) {  // Kiểm tra nếu chỉ số hợp lệ (không phải -1 khi không chọn hàng)
              labelMaSach.setText((String)(Model1.getValueAt(index, 0)));
              labelTenSach.setText((String)(Model1.getValueAt(index, 1)));
              labelTrangThai.setText((String)(Model1.getValueAt(index, 2)));

              // Kiểm tra trạng thái của sách và quyết định bật hay tắt nút Mượn
              if ("Còn".equals(labelTrangThai.getText().trim())) {
                  btnMuon.setEnabled(true);
              } else {
                  btnMuon.setEnabled(false);
              }
          }  
    }//GEN-LAST:event_tableMuonMouseClicked

    private void btnSearchSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchSActionPerformed
         String searchText = txtSearchS.getText().trim();  // Lấy nội dung ô tìm kiếm và loại bỏ khoảng trắng thừa
         if (searchText.isEmpty()) {
             JOptionPane.showMessageDialog(null, "Bạn chưa nhập đủ thông tin!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
             return;
         }

         // Khởi tạo Model1 và các cột của bảng
         
         Vector<String> columns1 = new Vector<>();
         columns1.add("Mã Sách");
         columns1.add("Tên Sách");
         columns1.add("Trạng thái");
         columns1.add("Mã Đầu Sách");

         Vector<Vector<Object>> rows1 = new Vector<>();
         String sql = null;

         // Tạo câu lệnh SQL dựa vào lựa chọn trong combo box
         switch (cbbDauSach.getSelectedIndex()) {
             case 0 -> sql = "SELECT * FROM tb_sach WHERE MaDauSach LIKE ?";
             case 1 -> sql = "SELECT * FROM tb_sach WHERE MaSach LIKE ?";
             case 2 -> sql = "SELECT * FROM tb_sach WHERE TenSach LIKE ?";
             case 3 -> sql = "SELECT * FROM tb_sach WHERE TrangThai LIKE ?";
             default -> sql = null;
         }

         // Nếu có câu lệnh SQL hợp lệ
         if (sql != null) {
             // Kết nối đến cơ sở dữ liệu
             Connection cn = cd.getConnection();
             ArrayList<Sach> ls = new ArrayList<>();

             try {
                 PreparedStatement ps = cn.prepareStatement(sql);
                 ps.setString(1, "%" + searchText + "%");
                 ResultSet rs = ps.executeQuery();

                 // Xử lý kết quả truy vấn
                 while (rs.next()) {
                     Sach st = new Sach();
                     st.setMaDauSach(rs.getString("MaDauSach"));
                     st.setMaSach(rs.getString("MaSach"));
                     st.setTenSach(rs.getString("TenSach"));
                     st.setTrangThai(rs.getString("TrangThai"));
                     ls.add(st);
                 }

                 // Thêm dữ liệu vào Model1
                 for (Sach s : ls) {
                     Vector<Object> tbRow1 = new Vector<>();
                     tbRow1.add(s.getMaSach());
                     tbRow1.add(s.getTenSach());
                     tbRow1.add(s.getTrangThai());
                     tbRow1.add(s.getMaDauSach());
                     rows1.add(tbRow1);
                 }

                 // Cập nhật dữ liệu vào Model1 và bảng
                 Model1.setDataVector(rows1, columns1);
                 tableMuon.setModel(Model1);

                 // Dọn dẹp sau khi tìm kiếm
                 cbbDauSach.setSelectedIndex(-1);
                 txtSearchS.setText("");

                 // Nếu không có kết quả tìm kiếm
                 if (Model1.getRowCount() == 0) {
                     JOptionPane.showMessageDialog(null, "Không tìm thấy!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                 }

             } catch (SQLException e1) {
                 System.out.println("Lỗi tìm kiếm: " + e1.getMessage());
             }
         }  
    }//GEN-LAST:event_btnSearchSActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        labelMaSach.setText(null);
        labelTenSach.setText(null);
        labelTrangThai.setText(null);
        btnMuon.setEnabled(false);
        getTableMuon();
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnMuonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMuonActionPerformed
       int n = JOptionPane.showConfirmDialog(jPanel6, "Bạn muốn mượn sách?", "Thông báo", JOptionPane.YES_NO_OPTION);

       if (n == JOptionPane.YES_OPTION) {
           // Check if MaSach and MaNguoiMuon are valid (not empty or null)
           String maSach = labelMaSach.getText();
           String maNguoiMuon = this.MaNguoiMuon;

           if (maSach == null || maSach.isEmpty() || maNguoiMuon == null || maNguoiMuon.isEmpty()) {
               JOptionPane.showMessageDialog(null, "Thông tin không hợp lệ! Vui lòng chọn sách và người mượn.", "Lỗi", JOptionPane.ERROR_MESSAGE);
               return;
           }

           // Attempt to insert the loan record
           if (insertPhieuMuon(maSach, maNguoiMuon)) {
               // Update the book status after successful loan
               Process_Sach ps = new Process_Sach();
               ps.updateSachTrangThai(maSach);

               // Clear the labels for the next operation
               labelMaSach.setText(null);
               labelTenSach.setText(null);
               labelTrangThai.setText(null);

               // Disable the button to prevent multiple borrowing attempts for the same book
               btnMuon.setEnabled(false);

               // Show success message
               JOptionPane.showMessageDialog(null, "Mượn sách thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
               getTableMuon();
           } else {
               // If insertion failed, show error message
               JOptionPane.showMessageDialog(null, "Mượn sách thất bại! Vui lòng thử lại.", "Thông báo", JOptionPane.ERROR_MESSAGE);
           }
       }
    }//GEN-LAST:event_btnMuonActionPerformed

    private void tableTraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableTraMouseClicked
    int index = tableTra.getSelectedRow();  // Lấy chỉ số dòng được chọn
        if (index != -1) {  // Kiểm tra xem có dòng nào được chọn không
            // Lấy và hiển thị thông tin từ bảng vào các label
            labelMaPhieuMuon_Tra.setText((String)(Model2.getValueAt(index, 0)));
            labelMaSach_TraSach.setText((String)(Model2.getValueAt(index, 1)));
            labelTenSach_TraSach.setText((String)(Model2.getValueAt(index, 2)));
            labelNgayMuon_TraSach.setText((String)(Model2.getValueAt(index, 3)));
            labelHanTra_TraSach.setText((String)(Model2.getValueAt(index, 4)));
            labelNgayTra_TraSach.setText((String)(Model2.getValueAt(index, 5)));
            labelTrangThai_TraSach.setText((String)(Model2.getValueAt(index, 6)));

            // Kiểm tra trạng thái sách và cập nhật nút "Trả sách"
            System.out.println(labelMaSach_TraSach.getText());
            String trangThai = labelTrangThai_TraSach.getText();
            System.out.println(labelTrangThai_TraSach.getText());
            if ("Chưa trả".equals(trangThai)) {
                btnTra.setEnabled(true);  // Nếu sách chưa trả, kích hoạt nút "Trả sách"
            } else {
                btnTra.setEnabled(false);  // Nếu sách đã trả, vô hiệu hóa nút "Trả sách"
            }
        }
    }//GEN-LAST:event_tableTraMouseClicked

    private void btnTraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTraActionPerformed
        int n = JOptionPane.showConfirmDialog(jPanel8, "Bạn muốn trả sách?", "Thông báo", JOptionPane.YES_NO_OPTION);
        if(n == JOptionPane.YES_OPTION) {
            // Thực hiện cập nhật ngày trả sách
            if (ppm.updatePhieuMuonNgayTraUser(labelMaPhieuMuon_Tra.getText())) {
                JOptionPane.showMessageDialog(null, "Trả sách thành công!", "Thông báo", 1);

                // Cập nhật trạng thái sách thành "Còn" sau khi trả sách
                Process_Sach ps = new Process_Sach();
                System.out.println(labelMaSach_TraSach.getText());
                if (ps.updateSachTrangThai2(labelMaSach_TraSach.getText())) {
                    System.out.println("Trạng thái sách đã được cập nhật thành 'Còn'.");
                } else {
                    System.out.println("Cập nhật trạng thái sách thất bại.");
                }
                
                // Làm sạch các trường thông tin trên giao diện
                labelMaPhieuMuon_Tra.setText(null);
                labelMaSach_TraSach.setText(null);
                labelTenSach_TraSach.setText(null);
                labelTrangThai_TraSach.setText(null);
                labelNgayMuon_TraSach.setText(null);
                labelHanTra_TraSach.setText(null);
                labelNgayTra_TraSach.setText(null);
                labelNgayTra_TraSach.setText(null);
                
                // Cập nhật lại bảng sách đã mượn
                getTableSachDaMuon();
            } else {
                JOptionPane.showMessageDialog(null, "Trả sách thất bại!", "Thông báo", 1);
            }
        }
    }//GEN-LAST:event_btnTraActionPerformed

    private void btnReset_TraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReset_TraActionPerformed
        labelMaPhieuMuon_Tra.setText(null);
        labelMaSach_TraSach.setText(null);
        labelTenSach_TraSach.setText(null);
        labelTrangThai_TraSach.setText(null);
        labelNgayMuon_TraSach.setText(null);
        labelHanTra_TraSach.setText(null);
        labelNgayTra_TraSach.setText(null);
        labelNgayTra_TraSach.setText(null);

        btnTra.setEnabled(false);
        getTableSachDaMuon();
    }//GEN-LAST:event_btnReset_TraActionPerformed

    private void btnSearch1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearch1ActionPerformed
        String searchText = textSearchS2.getText().trim();  // Lấy nội dung ô tìm kiếm và loại bỏ khoảng trắng thừa

            // Kiểm tra nếu không nhập thông tin tìm kiếm
            if ("".equals(searchText)) {
                JOptionPane.showMessageDialog(null, "Bạn chưa nhập đủ thông tin!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            // Khởi tạo Model1 và các cột của bảng
            DefaultTableModel Model1 = new DefaultTableModel();
            Vector<String> columns1 = new Vector<>();
            columns1.add("Mã Phiếu Mượn");
            columns1.add("Mã Sách");
            columns1.add("Tên Sách");
            columns1.add("Ngày Mượn");
            columns1.add("Hạn Trả");
            columns1.add("Ngày Trả");
            columns1.add("Trạng Thái");
            Vector<Vector<Object>> rows1 = new Vector<>();

            // Tạo câu lệnh SQL dựa vào lựa chọn trong combo box
            String sql = null;
            switch (cbbS_tra.getSelectedIndex()) {
                case 0: // Tìm kiếm theo Mã Đầu Sách
                    sql = "SELECT t2.MaPhieuMuon, t1.MaSach, t1.TenSach, t2.NgayMuon, t2.HanTra, t2.NgayTra, t1.TrangThai " +
                          "FROM tb_sach t1 JOIN tb_phieumuon t2 ON t1.MaSach = t2.MaSach WHERE t1.MaDauSach LIKE ? AND t2.MaNguoiMuon = ?";
                    break;
                case 1: // Tìm kiếm theo Mã Sách
                    sql = "SELECT t2.MaPhieuMuon, t1.MaSach, t1.TenSach, t2.NgayMuon, t2.HanTra, t2.NgayTra, t1.TrangThai " +
                          "FROM tb_sach t1 JOIN tb_phieumuon t2 ON t1.MaSach = t2.MaSach WHERE t1.MaSach LIKE ? AND t2.MaNguoiMuon = ?";
                    break;
                case 2: // Tìm kiếm theo Tên Sách
                    sql = "SELECT t2.MaPhieuMuon, t1.MaSach, t1.TenSach, t2.NgayMuon, t2.HanTra, t2.NgayTra, t1.TrangThai " +
                          "FROM tb_sach t1 JOIN tb_phieumuon t2 ON t1.MaSach = t2.MaSach WHERE t1.TenSach LIKE ? AND t2.MaNguoiMuon = ?";
                    break;
                case 3: // Tìm kiếm theo Trạng Thái
                    sql = "SELECT t2.MaPhieuMuon, t1.MaSach, t1.TenSach, t2.NgayMuon, t2.HanTra, t2.NgayTra, t1.TrangThai " +
                          "FROM tb_sach t1 JOIN tb_phieumuon t2 ON t1.MaSach = t2.MaSach WHERE t2.TrangThai LIKE ? AND t2.MaNguoiMuon = ?";
                    break;
                case 4: // Tìm kiếm theo Ngày Mượn
                    sql = "SELECT t2.MaPhieuMuon, t1.MaSach, t1.TenSach, t2.NgayMuon, t2.HanTra, t2.NgayTra, t1.TrangThai " +
                          "FROM tb_sach t1 JOIN tb_phieumuon t2 ON t1.MaSach = t2.MaSach WHERE t2.NgayMuon LIKE ? AND t2.MaNguoiMuon = ?";
                    break;
                case 5: // Tìm kiếm theo Ngày Trả
                    sql = "SELECT t2.MaPhieuMuon, t1.MaSach, t1.TenSach, t2.NgayMuon, t2.HanTra, t2.NgayTra, t1.TrangThai " +
                          "FROM tb_sach t1 JOIN tb_phieumuon t2 ON t1.MaSach = t2.MaSach WHERE t2.NgayTra LIKE ? AND t2.MaNguoiMuon = ?";
                    break;
                case 6: // Tìm kiếm theo Hạn Trả
                    sql = "SELECT t2.MaPhieuMuon, t1.MaSach, t1.TenSach, t2.NgayMuon, t2.HanTra, t2.NgayTra, t1.TrangThai " +
                          "FROM tb_sach t1 JOIN tb_phieumuon t2 ON t1.MaSach = t2.MaSach WHERE t2.HanTra LIKE ? AND t2.MaNguoiMuon = ?";
                    break;
                default:
                    sql = null;
            }

            if (sql != null) {
                // Kết nối đến cơ sở dữ liệu
                Connection cn = cd.getConnection();
                ArrayList<PhieuMuon> ls = new ArrayList<>();

                try {
                    PreparedStatement ps = cn.prepareStatement(sql);

                    // Nếu tìm kiếm theo ngày mượn, ngày trả, hoặc hạn trả
                    if (cbbS_tra.getSelectedIndex() == 5 || cbbS_tra.getSelectedIndex() == 6 || cbbS_tra.getSelectedIndex() == 7) {
                        ps.setString(1, "%" + searchText + "%");
                        ps.setString(2, MaNguoiMuon);  // Mã người mượn
                    } else {
                        ps.setString(1, "%" + searchText + "%");  // Điều kiện tìm kiếm theo văn bản
                        ps.setString(2, MaNguoiMuon);  // Mã người mượn
                    }

                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        PhieuMuon pm = new PhieuMuon();
                        pm.setMaPhieuMuon(rs.getString("MaPhieuMuon"));
                        pm.setMaSach(rs.getString("MaSach"));
                        pm.setTenSach(rs.getString("TenSach"));
                        pm.setNgayMuon(rs.getDate("NgayMuon"));
                        pm.setHanTra(rs.getDate("HanTra"));
                        pm.setNgayTra(rs.getDate("NgayTra"));
                        pm.setTrangThai(rs.getString("TrangThai"));
                        ls.add(pm);
                    }

                    // Thêm dữ liệu vào Model1
                    for (PhieuMuon pm : ls) {
                        Vector<Object> tbRow1 = new Vector<>();
                        tbRow1.add(pm.getMaPhieuMuon());
                        tbRow1.add(pm.getMaSach());
                        tbRow1.add(pm.getTenSach());
                        tbRow1.add(pm.getNgayMuon());
                        tbRow1.add(pm.getHanTra());
                        tbRow1.add(pm.getNgayTra());
                        tbRow1.add(pm.getTrangThai());
                        rows1.add(tbRow1);
                    }

                    // Cập nhật dữ liệu vào Model1 và bảng
                    Model1.setDataVector(rows1, columns1);
                    tableTra.setModel(Model1);

                    // Dọn dẹp sau khi tìm kiếm
                    cbbS_tra.setSelectedIndex(-1);
                    textSearchS2.setText("");

                    // Nếu không có kết quả tìm kiếm
                    if (Model1.getRowCount() == 0) {
                        JOptionPane.showMessageDialog(null, "Không tìm thấy!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (SQLException e1) {
                    System.out.println("Lỗi tìm kiếm: " + e1.getMessage());
                }
            }          
    }//GEN-LAST:event_btnSearch1ActionPerformed
    private void sortTableData(boolean isAscending) {
        // Lấy dữ liệu hiện tại trong bảng và lưu vào danh sách
        DefaultTableModel model = (DefaultTableModel) tableTra.getModel();
        int rowCount = model.getRowCount();
        ArrayList<Vector<Object>> data = new ArrayList<>();

        // Lưu các dòng dữ liệu từ bảng vào danh sách
        for (int i = 0; i < rowCount; i++) {
            Vector<Object> rowData = new Vector<>();
            for (int j = 0; j < model.getColumnCount(); j++) {
                rowData.add(model.getValueAt(i, j));
            }
            data.add(rowData);
        }

        // Sắp xếp danh sách theo cột mong muốn (ví dụ, cột "Ngày Mượn" index 3)
        int index = cbbS_tra.getSelectedIndex();
        data.sort((row1, row2) -> {
            String date1 = (String) row1.get(index); // Ngày Mượn cột index 3
            String date2 =  (String)row2.get(index);

            if (isAscending) {
                return date1.compareTo(date2); // Sắp xếp tăng dần
            } else {
                return date2.compareTo(date1); // Sắp xếp giảm dần
            }
        });

        // Xóa tất cả dữ liệu trong bảng và thêm lại theo thứ tự đã sắp xếp
        model.setRowCount(0); // Xóa dữ liệu hiện tại trong bảng
        for (Vector<Object> row : data) {
            model.addRow(row); // Thêm lại dữ liệu đã sắp xếp
        }
    }
    private void sortTableData1(boolean isAscending) {
        // Lấy dữ liệu hiện tại trong bảng và lưu vào danh sách
        DefaultTableModel model = (DefaultTableModel) tableMuon.getModel();
        int rowCount = model.getRowCount();
        ArrayList<Vector<Object>> data = new ArrayList<>();

        // Lưu các dòng dữ liệu từ bảng vào danh sách
        for (int i = 0; i < rowCount; i++) {
            Vector<Object> rowData = new Vector<>();
            for (int j = 0; j < model.getColumnCount(); j++) {
                rowData.add(model.getValueAt(i, j));
            }
            data.add(rowData);
        }

        // Sắp xếp danh sách theo cột mong muốn (ví dụ, cột "Ngày Mượn" index 3)
        int index = cbbDauSach.getSelectedIndex();
        data.sort((row1, row2) -> {
            String date1 = (String) row1.get(index); // Ngày Mượn cột index 3
            String date2 =  (String)row2.get(index);

            if (isAscending) {
                return date1.compareTo(date2); // Sắp xếp tăng dần
            } else {
                return date2.compareTo(date1); // Sắp xếp giảm dần
            }
        });

        // Xóa tất cả dữ liệu trong bảng và thêm lại theo thứ tự đã sắp xếp
        model.setRowCount(0); // Xóa dữ liệu hiện tại trong bảng
        for (Vector<Object> row : data) {
            model.addRow(row); // Thêm lại dữ liệu đã sắp xếp
        }
    }
    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        // Kiểm tra trạng thái của toggle button
        boolean isAscending = jToggleButton1.isSelected();

        if (isAscending) {
            jToggleButton1.setText("Tăng dần");
        } else {
            jToggleButton1.setText("Giảm dần");
        }

        // Gọi hàm sắp xếp lại bảng dữ liệu
        sortTableData(isAscending);
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private void jToggleButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton2ActionPerformed
        // TODO add your handling code here:
                // Kiểm tra trạng thái của toggle button
        boolean isAscending = jToggleButton2.isSelected();

        if (isAscending) {
            jToggleButton2.setText("Tăng dần");
        } else {
            jToggleButton2.setText("Giảm dần");
        }

        // Gọi hàm sắp xếp lại bảng dữ liệu
        sortTableData1(isAscending);
    }//GEN-LAST:event_jToggleButton2ActionPerformed

    /**
     *
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUI_ChooseLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI_ChooseLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI_ChooseLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI_ChooseLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

                try {
                    new GUI_NguoiMuon_Menu("").setVisible(true);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel UICards;
    private javax.swing.JPanel borrowCard;
    private javax.swing.JButton btnMuon;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnReset_Tra;
    private javax.swing.JButton btnSearch1;
    private javax.swing.JButton btnSearchS;
    private javax.swing.JButton btnTra;
    private javax.swing.JComboBox<String> cbbDauSach;
    private javax.swing.JComboBox<String> cbbS_tra;
    private javax.swing.JPanel homeCard;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JToggleButton jToggleButton2;
    private javax.swing.JLabel labelDiaChi;
    private javax.swing.JLabel labelGmail;
    private javax.swing.JLabel labelHanTra_TraSach;
    private javax.swing.JLabel labelHoVaTen;
    private javax.swing.JLabel labelMaPhieuMuon_Tra;
    private javax.swing.JLabel labelMaSach;
    private javax.swing.JLabel labelMaSach_TraSach;
    private javax.swing.JLabel labelNgayMuon_TraSach;
    private javax.swing.JLabel labelNgayTra_TraSach;
    private javax.swing.JLabel labelSDT;
    private javax.swing.JLabel labelSLSach;
    private javax.swing.JLabel labelSLSachDaMuon;
    private javax.swing.JLabel labelTenSach;
    private javax.swing.JLabel labelTenSach_TraSach;
    private javax.swing.JLabel labelTrangThai;
    private javax.swing.JLabel labelTrangThai_TraSach;
    private javax.swing.JLabel labelXinChao;
    private javax.swing.JLabel lableMaNguoiMuon;
    private javax.swing.JPanel returnCard;
    private javax.swing.JTable tableMuon;
    private javax.swing.JTable tableTra;
    private javax.swing.JTextField textSearchS2;
    private javax.swing.JTextField txtSearchS;
    // End of variables declaration//GEN-END:variables
}
