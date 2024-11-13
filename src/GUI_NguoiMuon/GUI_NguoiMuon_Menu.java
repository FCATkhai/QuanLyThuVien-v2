/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI_NguoiMuon;

import GUI_ThuThu.*;
import Main.GUI_ChooseLogin;
import java.awt.CardLayout;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Vector;

import Process.Process_NguoiMuon;
import Object.NguoiMuon;
import Object.Sach;
import Process.Connect_database;
import Process.Process_Sach;
import Process.Process_PhieuMuon;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ASUS
 */
public class GUI_NguoiMuon_Menu extends javax.swing.JFrame {

    /**
     * Creates new form HomePage
     */
    String MaNguoiMuon;
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
        labelXinChao.setText("Xin chào " + TTNguoiMuon.getTenNguoiMuon());
        lableMaNguoiMuon.setText(TTNguoiMuon.getMaNguoiMuon());
        labelHoVaTen.setText(TTNguoiMuon.getTenNguoiMuon());
        labelDiaChi.setText(TTNguoiMuon.getDiaChi());
        labelGmail.setText(TTNguoiMuon.getGmail());
        labelSDT.setText(TTNguoiMuon.getSDT());
    }

    public void getSLSach() throws SQLException {

        Connection cn = cd.getConnection();
        String sql1 = "Select sum(Soluong)  as slsach from tb_dausach;";
        PreparedStatement ps1 = (PreparedStatement) cn.prepareStatement(sql1);
        ResultSet rs1 = ps1.executeQuery();
        if(rs1.next()) labelSLSach.setText(Integer.toString(rs1.getInt("slsach")));
    }

    public void getSLSachDaMuon() throws SQLException {
        Connection cn = cd.getConnection();
        String sql2 = "Select count(*) as slmuon from tb_phieumuon t1, tb_sach t2 where t1.MaSach = t2.MaSach and t1.NgayTra is NULL and t2.TrangThai='Đã mượn' and t1.MaNguoiMuon=?;";
        PreparedStatement ps2 = (PreparedStatement) cn.prepareStatement(sql2);
        ps2.setString(1, this.MaNguoiMuon);
        ResultSet rs1 = ps2.executeQuery();
        if(rs1.next()) labelSLSachDaMuon.setText(Integer.toString(rs1.getInt("slmuon")));
    }
    // Muon Card
    public void getTableMuon() {
        Model1.setRowCount(0);

        ArrayList<Sach> ls = ps.getListSach();
        for (int i = 0; i < ls.size(); i++) {
            Sach s = (Sach) ls.get(i);
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

    public boolean insertPhieuMuon(String MaSach, String MaNguoiMuon) {
        Model1.setRowCount(0);

        if(ppm.insertPhieuMuon(null, null, null, MaSach, MaNguoiMuon)) {


            return true;
        }
        else {
            return false;
        }
    }
    public boolean updatePMNgayTra(String MaPhieuMuon) {
        if(ppm.updatePhieuMuonNgayTra(null, MaPhieuMuon)) {
            return true;
        }
        else {
            return false;
        }

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
            // TODO: SUA LOI CHO NAY
//            System.out.println((entry.get("NgayTra")) != null);
            tbRow.add(entry.get("NgayTra"));
//            tbRow.add(Objects.equals(entry.get("NgayTra"), null) ? "hello" : "hello1");
            tbRow.add(entry.get("TrangThai"));

            rowsTra.add(tbRow);
        }
        Model2.setDataVector(rowsTra, columnsTra);
        tableTra.setModel(Model2);
    }

    public GUI_NguoiMuon_Menu(String MaNguoiMuon) throws SQLException {
        initComponents();

        columnsMuon.add("Mã sách");
        columnsMuon.add("Tên sách");
        columnsMuon.add("Trạng thái");
        columnsMuon.add("Mã đầu sách");

        columnsTra.add("Mã phiếu mượn");
        columnsTra.add("Mã sách");
        columnsTra.add("Tên sách");
        columnsTra.add("Ngày mượn");
        columnsTra.add("Hạn Trả");
        columnsTra.add("Ngày Trả");
        columnsTra.add("Trạng thái");

        this.MaNguoiMuon = MaNguoiMuon;
        this.setSize(1280, 720);
        getTTNguoiMuon(this.MaNguoiMuon);
        getSLSach();
        getSLSachDaMuon();
        getTableMuon();
        getTableSachDaMuon();
        cardLayout = (CardLayout)(UICards.getLayout());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        UICards = new javax.swing.JPanel();
        homeCard = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        lableMaNguoiMuon = new javax.swing.JLabel();
        labelHoVaTen = new javax.swing.JLabel();
        labelGmail = new javax.swing.JLabel();
        labelDiaChi = new javax.swing.JLabel();
        labelSDT = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        labelSLSach = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        labelSLSachDaMuon = new javax.swing.JLabel();
        borrowCard = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        btnMuon = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        btnSearch = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        labelMaSach = new javax.swing.JLabel();
        labelTenSach = new javax.swing.JLabel();
        labelTrangThai = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableMuon = new javax.swing.JTable();
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
        btnTra = new javax.swing.JButton();
        btnReset_Tra = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        labelMaPhieuMuon_Tra = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableTra = new javax.swing.JTable();
        jLabel25 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        labelXinChao = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(new java.awt.Dimension(1280, 720));

        jPanel1.setBackground(new java.awt.Color(66, 61, 61));

        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton1.setText("Trang chủ");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton3.setText("Trả sách");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton5.setText("Đăng xuất");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton2.setText("Mượn sách");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jButton5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(101, 101, 101)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addGap(18, 18, 18)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton5)
                .addGap(409, 409, 409))
        );

        jSplitPane1.setLeftComponent(jPanel1);

        UICards.setBackground(new java.awt.Color(255, 255, 255));
        UICards.setPreferredSize(new java.awt.Dimension(800, 1081));
        UICards.setLayout(new java.awt.CardLayout());

        homeCard.setBackground(new java.awt.Color(255, 255, 255));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel6.setText("Họ và tên:");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel7.setText("Mã người mượn:");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel8.setText("Địa chỉ:");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel9.setText("Gmail:");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel10.setText("Số điện thoại:");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel11.setText("Thông tin người mượn");

        lableMaNguoiMuon.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lableMaNguoiMuon.setText("jLabel7");

        labelHoVaTen.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        labelHoVaTen.setText("jLabel8");

        labelGmail.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        labelGmail.setText("jLabel10");

        labelDiaChi.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        labelDiaChi.setText("jLabel9");

        labelSDT.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        labelSDT.setText("jLabel11");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("Tổng số lượng sách");

        jPanel3.setBackground(new java.awt.Color(192, 192, 192));

        labelSLSach.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        labelSLSach.setText("198");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(labelSLSach, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(37, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(labelSLSach)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setText("Số lượng sách đã mượn");

        jPanel5.setBackground(new java.awt.Color(192, 192, 192));

        labelSLSachDaMuon.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        labelSLSachDaMuon.setText("0");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(35, Short.MAX_VALUE)
                .addComponent(labelSLSachDaMuon, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(labelSLSachDaMuon)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout homeCardLayout = new javax.swing.GroupLayout(homeCard);
        homeCard.setLayout(homeCardLayout);
        homeCardLayout.setHorizontalGroup(
            homeCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, homeCardLayout.createSequentialGroup()
                .addContainerGap(296, Short.MAX_VALUE)
                .addGroup(homeCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(homeCardLayout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(217, 217, 217)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(homeCardLayout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(149, 149, 149)
                        .addComponent(jLabel4)))
                .addGap(424, 424, 424))
            .addGroup(homeCardLayout.createSequentialGroup()
                .addGap(167, 167, 167)
                .addGroup(homeCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(homeCardLayout.createSequentialGroup()
                        .addGroup(homeCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel9)
                            .addComponent(jLabel8)
                            .addComponent(jLabel7)
                            .addComponent(jLabel6))
                        .addGap(58, 58, 58)
                        .addGroup(homeCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lableMaNguoiMuon)
                            .addComponent(labelHoVaTen)
                            .addComponent(labelDiaChi)
                            .addComponent(labelGmail)
                            .addComponent(labelSDT))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, homeCardLayout.createSequentialGroup()
                        .addGap(127, 127, 127)
                        .addComponent(jLabel11)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        homeCardLayout.setVerticalGroup(
            homeCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(homeCardLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(homeCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(homeCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(132, 132, 132)
                .addComponent(jLabel11)
                .addGap(21, 21, 21)
                .addGroup(homeCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(lableMaNguoiMuon))
                .addGap(18, 18, 18)
                .addGroup(homeCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(labelHoVaTen))
                .addGap(29, 29, 29)
                .addGroup(homeCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(labelDiaChi))
                .addGap(32, 32, 32)
                .addGroup(homeCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(labelGmail))
                .addGap(31, 31, 31)
                .addGroup(homeCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(labelSDT))
                .addContainerGap(473, Short.MAX_VALUE))
        );

        UICards.add(homeCard, "homeCard");

        borrowCard.setBackground(new java.awt.Color(255, 255, 255));
        borrowCard.setPreferredSize(new java.awt.Dimension(1000, 701));

        btnMuon.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnMuon.setText("Mượn");
        btnMuon.setEnabled(false);
        btnMuon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMuonActionPerformed(evt);
            }
        });

        btnReset.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnReset.setText("Làm mới");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        btnSearch.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnSearch.setText("Tìm kiếm");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

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

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(labelTrangThai)
                            .addComponent(labelTenSach, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
                            .addComponent(labelMaSach, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(18, 18, 18)
                .addComponent(btnSearch)
                .addGap(94, 94, 94)
                .addComponent(btnMuon)
                .addGap(77, 77, 77)
                .addComponent(btnReset)
                .addContainerGap(300, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(labelMaSach))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(labelTenSach))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(labelTrangThai))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnReset)
                    .addComponent(btnMuon)
                    .addComponent(btnSearch)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16))
        );

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
                "Title 1", "Title 2", "Title 3", "Title 4"
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

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(85, 85, 85)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 828, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(169, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(451, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout borrowCardLayout = new javax.swing.GroupLayout(borrowCard);
        borrowCard.setLayout(borrowCardLayout);
        borrowCardLayout.setHorizontalGroup(
            borrowCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(borrowCardLayout.createSequentialGroup()
                .addGroup(borrowCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );
        borrowCardLayout.setVerticalGroup(
            borrowCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(borrowCardLayout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
        labelTrangThai_TraSach.setText("jLabel21");

        labelNgayMuon_TraSach.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        labelNgayMuon_TraSach.setText("...");

        labelHanTra_TraSach.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        labelHanTra_TraSach.setText("....");

        labelNgayTra_TraSach.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        labelNgayTra_TraSach.setText("....");

        btnTra.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnTra.setText("Trả sách");
        btnTra.setEnabled(false);
        btnTra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTraActionPerformed(evt);
            }
        });

        btnReset_Tra.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnReset_Tra.setText("Làm mới");
        btnReset_Tra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReset_TraActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel12.setText("Mã phiếu mượn");

        labelMaPhieuMuon_Tra.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        labelMaPhieuMuon_Tra.setText("...");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(62, 62, 62)
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
                            .addComponent(labelNgayTra_TraSach, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(492, 492, 492)
                        .addComponent(btnTra)
                        .addGap(58, 58, 58)
                        .addComponent(btnReset_Tra)))
                .addContainerGap(504, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnTra)
                            .addComponent(btnReset_Tra))
                        .addGap(24, 24, 24))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(jLabel13)
                        .addGap(31, 31, 31)
                        .addComponent(jLabel14)
                        .addGap(33, 33, 33)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(labelTrangThai_TraSach))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
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

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel25))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1000, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(210, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel25)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(525, Short.MAX_VALUE))
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
                .addGap(28, 28, 28)
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        UICards.add(returnCard, "returnCard");

        jSplitPane1.setRightComponent(UICards);

        getContentPane().add(jSplitPane1, java.awt.BorderLayout.CENTER);

        jPanel2.setBackground(new java.awt.Color(138, 25, 18));

        labelXinChao.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        labelXinChao.setForeground(new java.awt.Color(255, 255, 255));
        labelXinChao.setText("Xin chào Nguyễn văn A");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(1104, Short.MAX_VALUE)
                .addComponent(labelXinChao, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelXinChao)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, java.awt.BorderLayout.PAGE_START);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        cardLayout.show(UICards, "homeCard");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        cardLayout.show(UICards, "borrowCard");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        cardLayout.show(UICards, "returnCard");
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        GUI_ChooseLogin login = new GUI_ChooseLogin();
        
        login.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void tableMuonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMuonMouseClicked

        int index = tableMuon.getSelectedRow();
        labelMaSach.setText((String)(Model1.getValueAt(index, 0)));
        labelTenSach.setText((String)(Model1.getValueAt(index, 1)));
        labelTrangThai.setText((String)(Model1.getValueAt(index, 2)));

        if (labelTrangThai.getText().equals("Còn")) {
            btnMuon.setEnabled(true);
        } else {
            btnMuon.setEnabled(false);
        }

    }//GEN-LAST:event_tableMuonMouseClicked

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        labelMaSach.setText(null);
        labelTenSach.setText(null);
        labelTrangThai.setText(null);

        btnMuon.setEnabled(false);
        getTableMuon();
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnMuonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMuonActionPerformed
        int n = JOptionPane.showConfirmDialog(jPanel6, "Bạn muốn mượn sách?", "Thông báo", JOptionPane.YES_NO_OPTION);
        if(n == JOptionPane.YES_OPTION) {
            if (insertPhieuMuon(labelMaSach.getText(), this.MaNguoiMuon)) {
                JOptionPane.showMessageDialog(null, "Mượn sách thành công!", "Thông báo", 1);
                Process_Sach ps = new Process_Sach();
                ps.updateSachTrangThai(labelMaSach.getText());
                labelMaSach.setText(null);
                labelTenSach.setText(null);
                labelTrangThai.setText(null);

                btnMuon.setEnabled(false);
            } else {
                JOptionPane.showMessageDialog(null, "Mượn sách thất bại!", "Thông báo", 1);
            }
            getTableMuon();
        }
    }//GEN-LAST:event_btnMuonActionPerformed

    private void tableTraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableTraMouseClicked
        int index = tableTra.getSelectedRow();
        labelMaPhieuMuon_Tra.setText((String)(Model2.getValueAt(index, 0)));
        labelMaSach_TraSach.setText((String)(Model2.getValueAt(index, 1)));
        labelTenSach_TraSach.setText((String)(Model2.getValueAt(index, 2)));
        labelNgayMuon_TraSach.setText((String)(Model2.getValueAt(index, 3)));
        labelHanTra_TraSach.setText((String)(Model2.getValueAt(index, 4)));
        labelNgayTra_TraSach.setText((String)(Model2.getValueAt(index, 5)));
        labelTrangThai_TraSach.setText((String)(Model2.getValueAt(index, 6)));

        if (labelTrangThai_TraSach.getText().equals("Chưa trả")) {
            btnTra.setEnabled(true);
        } else {
            btnTra.setEnabled(false);
        }
    }//GEN-LAST:event_tableTraMouseClicked

    private void btnTraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTraActionPerformed
        int n = JOptionPane.showConfirmDialog(jPanel8, "Bạn muốn trả sách?", "Thông báo", JOptionPane.YES_NO_OPTION);
        if(n == JOptionPane.YES_OPTION) {
            if (updatePMNgayTra(labelMaPhieuMuon_Tra.getText())) {
                JOptionPane.showMessageDialog(null, "Trả sách thành công!", "Thông báo", 1);
                Process_Sach ps = new Process_Sach();
                ps.updateSachTrangThai2(labelMaSach.getText());

                labelMaPhieuMuon_Tra.setText(null);
                labelMaSach_TraSach.setText(null);
                labelTenSach_TraSach.setText(null);
                labelTrangThai_TraSach.setText(null);
                labelNgayMuon_TraSach.setText(null);
                labelHanTra_TraSach.setText(null);
                labelNgayTra_TraSach.setText(null);
                labelNgayTra_TraSach.setText(null);

                btnTra.setEnabled(false);
            } else {
                JOptionPane.showMessageDialog(null, "Trả sách thất bại!", "Thông báo", 1);
            }
            getTableSachDaMuon();
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
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnTra;
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
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JTextField jTextField1;
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
    // End of variables declaration//GEN-END:variables
}
