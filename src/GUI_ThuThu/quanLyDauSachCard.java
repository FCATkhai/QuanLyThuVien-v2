/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI_ThuThu;

import Process.Process_DauSach;

import java.util.ArrayList;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import Object.DauSach;
import Process.Connect_database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author ASUS
 */
public class quanLyDauSachCard extends javax.swing.JPanel {

    
    DefaultTableModel Model = new DefaultTableModel();
    Vector<String> columns = new Vector<String>();
    Vector<Vector<Object>> rows = new Vector<>();
    Process_DauSach pds = new Process_DauSach();

    public void getAllDauSach() {
        Model.setRowCount(0);
        ArrayList<DauSach> ls = pds.getListDauSach();
        for (int i = 0; i < ls.size(); i++) {
            DauSach s = (DauSach) ls.get(i);
            Vector<Object> tbRow = new Vector<>();
            tbRow.add(s.getMaDauSach());
            tbRow.add(s.getTenSach());
            tbRow.add(s.getSoLuong());
            tbRow.add(s.getTheLoai());
            tbRow.add(s.getTacGia());
            tbRow.add(s.getNXB());
            tbRow.add(s.getNamXB());
            rows.add(tbRow);
        }
        Model.setDataVector(rows, columns);
        tableDauSach.setModel(Model);
    }

    public void insertDauSach(String MaDauSach, String TenSach, int SoLuong, String TheLoai, String TacGia, String NXB, int NamXB) {
        Model.setRowCount(0);
        if (pds.insertDauSach(MaDauSach, TenSach, SoLuong, TheLoai, TacGia, NXB, NamXB)== true) {
            getAllDauSach();
        }
        else {
            getAllDauSach();
        }
    }

    public void updateDauSach(String MaDauSach, String TenSach, int SoLuong, String TheLoai, String TacGia, String NXB, int NamXB) {
        Model.setRowCount(0);
        if (pds.updateDauSach(MaDauSach, TenSach, SoLuong, TheLoai, TacGia, NXB, NamXB)== true) {
            getAllDauSach();
        }
    }

    public void delDauSach(String MaDauSach) {
        Model.setRowCount(0);
        if (pds.delDauSach(MaDauSach) == true) {
            ArrayList<DauSach> ls = pds.getListDauSach();
            for (int i = 0; i < ls.size(); i++) {
                DauSach s = (DauSach) ls.get(i);
                Vector<Object> tbRow = new Vector<>();
                tbRow.add(s.getMaDauSach());
                tbRow.add(s.getTenSach());
                tbRow.add(s.getSoLuong());
                tbRow.add(s.getTheLoai());
                tbRow.add(s.getTacGia());
                tbRow.add(s.getNXB());
                tbRow.add(s.getNamXB());
                rows.add(tbRow);
            }
            Model.setDataVector(rows, columns);
            tableDauSach.setModel(Model);
        }
    }
    
    
    public quanLyDauSachCard() {
        initComponents();
        columns.add("Mã đầu sách");
        columns.add("Tên sách");
        columns.add("Số lượng");
        columns.add("Thể loại");
        columns.add("Tác giả");
        columns.add("Nhà xuất bản");
        columns.add("Năm xuất bản");
        btnXoa.setEnabled(false);
        btnSua.setEnabled(false);
        getAllDauSach();
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

        jPanel10 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        txtSoLuong = new javax.swing.JTextField();
        txtTenSach = new javax.swing.JTextField();
        txtMaDauSach = new javax.swing.JTextField();
        txtNXB = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        txtNamXB = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtTacGia = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtTheLoai = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnLamMoi = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableDauSach = new javax.swing.JTable();
        jPanel11 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jToggleButton1 = new javax.swing.JToggleButton();
        cbbDauSach = new javax.swing.JComboBox<>();
        txtSearchDS = new javax.swing.JTextField();
        btnSearchDauSach = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        jPanel10.setBackground(new java.awt.Color(204, 204, 204));
        jPanel10.setPreferredSize(new java.awt.Dimension(1027, 50));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setText("Quản Lý Đầu Sách");
        jLabel1.setPreferredSize(new java.awt.Dimension(194, 30));
        jPanel10.add(jLabel1);

        add(jPanel10, java.awt.BorderLayout.PAGE_START);

        jPanel4.setLayout(new java.awt.BorderLayout());

        jPanel2.setPreferredSize(new java.awt.Dimension(823, 290));
        jPanel2.setLayout(new java.awt.GridBagLayout());

        jPanel9.setLayout(new java.awt.GridLayout(1, 2, 40, 0));

        jPanel7.setLayout(new java.awt.GridLayout(0, 2, 10, 0));

        jPanel5.setLayout(new java.awt.GridLayout(0, 1, 0, 20));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel8.setText("Số lượng:");
        jPanel5.add(jLabel8);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel7.setText("Tên đầu sách:");
        jPanel5.add(jLabel7);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel6.setText("Mã đầu sách:");
        jPanel5.add(jLabel6);

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel11.setText("Nhà xuất bản:");
        jLabel11.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel5.add(jLabel11);

        jPanel7.add(jPanel5);

        jPanel3.setLayout(new java.awt.GridLayout(0, 1, 0, 10));

        txtSoLuong.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jPanel3.add(txtSoLuong);

        txtTenSach.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtTenSach.setMinimumSize(new java.awt.Dimension(100, 31));
        txtTenSach.setPreferredSize(new java.awt.Dimension(100, 31));
        jPanel3.add(txtTenSach);

        txtMaDauSach.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtMaDauSach.setPreferredSize(new java.awt.Dimension(64, 12));
        jPanel3.add(txtMaDauSach);

        txtNXB.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jPanel3.add(txtNXB);

        jPanel7.add(jPanel3);

        jPanel9.add(jPanel7);

        jPanel8.setLayout(new java.awt.GridLayout(3, 2, 10, 30));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel12.setText("Năm xuất bản:");
        jLabel12.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel8.add(jLabel12);

        txtNamXB.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jPanel8.add(txtNamXB);

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel10.setText("Tác giả:");
        jLabel10.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel8.add(jLabel10);

        txtTacGia.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jPanel8.add(txtTacGia);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel9.setText("Thể loại:");
        jLabel9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel8.add(jLabel9);

        txtTheLoai.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jPanel8.add(txtTheLoai);

        jPanel9.add(jPanel8);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 310;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 80, 27, 117);
        jPanel2.add(jPanel9, gridBagConstraints);

        jPanel4.add(jPanel2, java.awt.BorderLayout.CENTER);

        jPanel1.setPreferredSize(new java.awt.Dimension(671, 50));
        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 20, 10));

        btnThem.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnThem.setText("Thêm");
        btnThem.setPreferredSize(new java.awt.Dimension(100, 30));
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });
        jPanel1.add(btnThem);

        btnSua.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnSua.setText("Sửa");
        btnSua.setPreferredSize(new java.awt.Dimension(100, 30));
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });
        jPanel1.add(btnSua);

        btnXoa.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnXoa.setText("Xoá");
        btnXoa.setPreferredSize(new java.awt.Dimension(100, 30));
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });
        jPanel1.add(btnXoa);

        btnLamMoi.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnLamMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/refresh (1).png"))); // NOI18N
        btnLamMoi.setPreferredSize(new java.awt.Dimension(40, 35));
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });
        jPanel1.add(btnLamMoi);

        jPanel4.add(jPanel1, java.awt.BorderLayout.PAGE_END);

        add(jPanel4, java.awt.BorderLayout.CENTER);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setPreferredSize(new java.awt.Dimension(1303, 340));

        tableDauSach.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        tableDauSach.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã đầu sách", "Tên sách", "Số lượng", "Thể loại", "Tác giả", "Nhà xuất bản", "Năm xuất bản"
            }
        ));
        tableDauSach.setRowHeight(25);
        tableDauSach.setShowGrid(true);
        tableDauSach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableDauSachMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableDauSach);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("Danh sách đầu sách");
        jLabel2.setPreferredSize(new java.awt.Dimension(300, 25));
        jPanel11.add(jLabel2);

        jToggleButton1.setText("sort");
        jToggleButton1.setPreferredSize(new java.awt.Dimension(150, 35));
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });
        jPanel11.add(jToggleButton1);

        cbbDauSach.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        cbbDauSach.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mã đầu sách", "Tên sách", "Số lượng", "Thể loại", "Tác giả", "Nhà xuất bản", "Năm xuất bản" }));
        cbbDauSach.setPreferredSize(new java.awt.Dimension(150, 30));
        jPanel11.add(cbbDauSach);

        txtSearchDS.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtSearchDS.setToolTipText("");
        txtSearchDS.setPreferredSize(new java.awt.Dimension(250, 30));
        txtSearchDS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchDSActionPerformed(evt);
            }
        });
        jPanel11.add(txtSearchDS);

        btnSearchDauSach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/loupe (1).png"))); // NOI18N
        btnSearchDauSach.setPreferredSize(new java.awt.Dimension(30, 30));
        btnSearchDauSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchDauSachActionPerformed(evt);
            }
        });
        jPanel11.add(btnSearchDauSach);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap(359, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        add(jPanel6, java.awt.BorderLayout.PAGE_END);
    }// </editor-fold>//GEN-END:initComponents

    private void tableDauSachMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableDauSachMouseClicked
        btnSua.setEnabled(true);
        btnXoa.setEnabled(true);
        btnThem.setEnabled(false);
        txtMaDauSach.setEnabled(false);
        int index = tableDauSach.getSelectedRow();
        txtMaDauSach.setText((String)(Model.getValueAt(index, 0)));
        txtTenSach.setText((String)(Model.getValueAt(index, 1)));
        txtSoLuong.setText(String.valueOf(Model.getValueAt(index, 2)));
        txtTheLoai.setText((String)(Model.getValueAt(index, 3)));
        txtTacGia.setText((String)(Model.getValueAt(index, 4)));
        txtNXB.setText((String)(Model.getValueAt(index, 5)));
        txtNamXB.setText(String.valueOf(Model.getValueAt(index, 6)));
    }//GEN-LAST:event_tableDauSachMouseClicked

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        if(txtMaDauSach.getText().equals("") || txtSoLuong.getText().equals("") || txtTacGia.getText().equals("") ||txtTheLoai.getText().equals("") || txtNamXB.getText().equals("") || txtNXB.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Bạn chưa nhập đủ thông tin!" , "Thông báo", 1);
        }
        else
        {
            insertDauSach(txtMaDauSach.getText(), txtTenSach.getText(), Integer.parseInt(txtSoLuong.getText()), txtTheLoai.getText(), txtTacGia.getText(), txtNXB.getText(), Integer.parseInt(txtNamXB.getText()));
            txtTenSach.setText("");
            txtTacGia.setText("");
            txtMaDauSach.setText("");
            txtSoLuong.setText("");
            txtTheLoai.setText("");
            txtNamXB.setText(null);
            txtNXB.setText(null);
            btnSua.setEnabled(false);
            btnXoa.setEnabled(false);
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        int n = JOptionPane.showConfirmDialog(jPanel4, "Bạn muốn xóa?", "Thông báo", JOptionPane.YES_NO_OPTION);
        if(n == JOptionPane.YES_OPTION) {
            delDauSach(txtMaDauSach.getText());
            txtMaDauSach.setEnabled(true);
            txtTenSach.setText(null);
            txtTacGia.setText(null);
            txtMaDauSach.setText(null);
            txtSoLuong.setText(null);
            txtTheLoai.setText(null);
            txtTenSach.setText(null);
            txtNamXB.setText(null);
            txtNXB.setText(null);
            btnXoa.setEnabled(false);
            btnSua.setEnabled(false);
            btnThem.setEnabled(true);
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        btnSua.setEnabled(false);
        btnXoa.setEnabled(false);
        btnThem.setEnabled(true);

        txtMaDauSach.setEnabled(true);
        txtTenSach.setText(null);
        txtTacGia.setText(null);
        txtMaDauSach.setText(null);
        txtSoLuong.setText(null);
        txtTheLoai.setText(null);
        txtTenSach.setText(null);
        txtNamXB.setText(null);
        txtNXB.setText(null);

        getAllDauSach();
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void txtSearchDSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchDSActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchDSActionPerformed

    private void btnSearchDauSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchDauSachActionPerformed
        // TODO add your handling code here:
        Connect_database cd = new Connect_database();
        DefaultTableModel Model1 = new DefaultTableModel();

        // Khởi tạo tên cột
        Vector<String> columns1 = new Vector<>();
        columns1.add("Mã Đầu Sách");
        columns1.add("Tên Sách");
        columns1.add("Số Lượng");
        columns1.add("Thể Loại");
        columns1.add("Tác Giả");
        columns1.add("NXB");
        columns1.add("Năm XB");

        Vector<Vector<Object>> rows1 = new Vector<>();
        String sql = null;

        if (txtSearchDS.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Bạn chưa nhập đủ thông tin!", "Thông báo", 1);
        } else {
            switch (cbbDauSach.getSelectedIndex()) {
                case 0 -> sql = "SELECT * FROM tb_dausach WHERE MaDauSach = ?";
                case 1 -> sql = "SELECT * FROM tb_dausach WHERE TenSach LIKE ?";
                case 2 -> sql = "SELECT * FROM tb_dausach WHERE SoLuong = ?";
                case 3 -> sql = "SELECT * FROM tb_dausach WHERE TheLoai LIKE ?";
                case 4 -> sql = "SELECT * FROM tb_dausach WHERE TacGia LIKE ?";
                case 5 -> sql = "SELECT * FROM tb_dausach WHERE NXB = ?";
                case 6 -> sql = "SELECT * FROM tb_dausach WHERE NamXB = ?";
            }

            Model1.setRowCount(0);
            Connection cn = cd.getConnection();
            ArrayList<DauSach> ls = new ArrayList<>();

            try {
                PreparedStatement ps = cn.prepareStatement(sql);
                if (sql.contains("LIKE")) {
                    ps.setString(1, "%" + txtSearchDS.getText() + "%");
                } else {
                    ps.setString(1, txtSearchDS.getText());
                }
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

                for (DauSach s : ls) {
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

                // Dọn dẹp sau khi tìm kiếm
                cbbDauSach.setSelectedIndex(-1);
                txtSearchDS.setText(null);

            } catch (Exception e1) {
                System.out.println(e1.getMessage());
            }

            if (Model1.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Không tìm thấy!", "Thông báo", 1);
            }
        }
    }//GEN-LAST:event_btnSearchDauSachActionPerformed
    private void sortTableData(boolean isAscending) {
        // Lấy dữ liệu hiện tại trong bảng và lưu vào danh sách
        DefaultTableModel model = (DefaultTableModel) tableDauSach.getModel();
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

        // Lấy chỉ số cột cần sắp xếp từ combo box
        int index = cbbDauSach.getSelectedIndex();

        try {
            // Sắp xếp theo String (đối với ngày tháng dạng String)
            data.sort((row1, row2) -> {
                String value1 = (String) row1.get(index); // Lấy giá trị cột tại index
                String value2 = (String) row2.get(index);

                // So sánh hai giá trị String (ngày tháng)
                if (isAscending) {
                    return value1.compareTo(value2); // Sắp xếp tăng dần
                } else {
                    return value2.compareTo(value1); // Sắp xếp giảm dần
                }
            });

        } catch (Exception e) {
            // Nếu dữ liệu không phải String (ví dụ kiểu int), thử so sánh theo kiểu int
            data.sort((row1, row2) -> {
                try {
                    // Thử lấy giá trị int từ cột
                    int value1 = (int) row1.get(index); 
                    int value2 = (int) row2.get(index);

                    // So sánh hai giá trị int
                    if (isAscending) {
                        return Integer.compare(value1, value2); // Sắp xếp tăng dần
                    } else {
                        return Integer.compare(value2, value1); // Sắp xếp giảm dần
                    }
                } catch (Exception ex) {
                    // Nếu không phải kiểu int, xử lý ngoại lệ (giả sử là kiểu String hoặc Date khác)
                    return 0; // Nếu có lỗi thì không thực hiện sắp xếp
                }
            });
        }

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

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {
        int n = JOptionPane.showConfirmDialog(jPanel4, "Bạn muốn sửa?", "Thông báo", JOptionPane.YES_NO_OPTION);
        if(n == JOptionPane.YES_OPTION) {
            updateDauSach(txtMaDauSach.getText(), txtTenSach.getText(), Integer.parseInt(txtSoLuong.getText()), txtTheLoai.getText(), txtTacGia.getText(), txtNXB.getText(), Integer.parseInt(txtNamXB.getText()));
        }
        
    }                                        


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnSearchDauSach;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> cbbDauSach;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JTable tableDauSach;
    private javax.swing.JTextField txtMaDauSach;
    private javax.swing.JTextField txtNXB;
    private javax.swing.JTextField txtNamXB;
    private javax.swing.JTextField txtSearchDS;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTacGia;
    private javax.swing.JTextField txtTenSach;
    private javax.swing.JTextField txtTheLoai;
    // End of variables declaration//GEN-END:variables
}
