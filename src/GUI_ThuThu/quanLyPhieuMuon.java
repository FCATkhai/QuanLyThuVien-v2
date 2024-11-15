/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI_ThuThu;

import Process.Process_PhieuMuon;
import Process.Process_Sach;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import Object.PhieuMuon;

/**
 *
 * @author ASUS
 */
public class quanLyPhieuMuon extends javax.swing.JPanel {


    DefaultTableModel Model = new DefaultTableModel();
    Vector<String> columns = new Vector<String>();
    Vector<Vector<Object>> rows = new Vector<>();
    Process_PhieuMuon ppm = new Process_PhieuMuon();

    public void getAllPhieuMuon() {
        Model.setRowCount(0);
        ArrayList<PhieuMuon> ls = ppm.getListPhieuMuon();
        for (int i = 0; i < ls.size(); i++) {
            PhieuMuon s = (PhieuMuon) ls.get(i);
            Vector<Object> tbRow = new Vector<>();
            tbRow.add(s.getMaPhieuMuon());
            tbRow.add(s.getNgayMuon());
            tbRow.add(s.getHanTra());
            tbRow.add(s.getMaSach());
            tbRow.add(s.getMaNguoiMuon());
            tbRow.add(s.getNgayTra());
            rows.add(tbRow);
        }
        Model.setDataVector(rows, columns);
        tablePhieuMuon.setModel(Model);
    }

    public boolean insertPhieuMuon(String MaPhieuMuon, Date NgayMuon, Date HanTra, String MaSach, String MaNguoiMuon) {
        Model.setRowCount(0);
        if(ppm.insertPhieuMuon(MaPhieuMuon, NgayMuon,HanTra, MaSach, MaNguoiMuon)) {
            ArrayList<PhieuMuon> ls = ppm.getListPhieuMuon();
            for (int i = 0; i < ls.size(); i++) {
                PhieuMuon s = (PhieuMuon) ls.get(i);
                Vector<Object> tbRow = new Vector<>();
                tbRow.add(s.getMaPhieuMuon());
                tbRow.add(s.getNgayMuon());
                tbRow.add(s.getHanTra());
                tbRow.add(s.getMaSach());
                tbRow.add(s.getMaNguoiMuon());
                tbRow.add(s.getNgayTra());
                rows.add(tbRow);
            }
            Model.setDataVector(rows, columns);
            tablePhieuMuon.setModel(Model);

            return true;
        }
        else {
            return false;
        }
    }

    public boolean updatePhieuMuon(String MaPhieuMuon, Date NgayMuon,Date HanTra, String MaSach, String MaNguoiMuon) {
        Model.setRowCount(0);
        if(ppm.updatePhieuMuon(MaPhieuMuon, NgayMuon,HanTra, MaSach, MaNguoiMuon)) {
            ArrayList<PhieuMuon> ls = ppm.getListPhieuMuon();
            for (int i = 0; i < ls.size(); i++) {
                PhieuMuon s = (PhieuMuon) ls.get(i);
                Vector<Object> tbRow = new Vector<>();
                tbRow.add(s.getMaPhieuMuon());
                tbRow.add(s.getNgayMuon());
                tbRow.add(s.getHanTra());
                tbRow.add(s.getMaSach());
                tbRow.add(s.getMaNguoiMuon());
                tbRow.add(s.getNgayTra());
                rows.add(tbRow);
            }
            Model.setDataVector(rows, columns);
            tablePhieuMuon.setModel(Model);
            return true;
        }
        else {
            System.out.println(false);
            return false;
        }

    }

    public boolean updatePMNgayTra(Date NgayTra, String MaPhieuMuon) {
        Model.setRowCount(0);
        if(ppm.updatePhieuMuonNgayTra(NgayTra, MaPhieuMuon)) {
            ArrayList<PhieuMuon> ls = ppm.getListPhieuMuon();
            for (int i = 0; i < ls.size(); i++) {
                PhieuMuon s = (PhieuMuon) ls.get(i);
                Vector<Object> tbRow = new Vector<>();
                tbRow.add(s.getMaPhieuMuon());
                tbRow.add(s.getNgayMuon());
                tbRow.add(s.getHanTra());
                tbRow.add(s.getMaSach());
                tbRow.add(s.getMaNguoiMuon());
                tbRow.add(s.getNgayTra());
                rows.add(tbRow);
            }
            Model.setDataVector(rows, columns);
            tablePhieuMuon.setModel(Model);
            return true;
        }
        else {       
            System.out.println(false);
            return false;
        }

    }

    public boolean delPhieuMuon(String MaPhieuMuon) {
        Model.setRowCount(0);
        if(ppm.delPhieuMuon(MaPhieuMuon)) {
            ArrayList<PhieuMuon> ls = ppm.getListPhieuMuon();
            for (int i = 0; i < ls.size(); i++) {
                PhieuMuon s = (PhieuMuon) ls.get(i);
                Vector<Object> tbRow = new Vector<>();
                tbRow.add(s.getMaPhieuMuon());
                tbRow.add(s.getNgayMuon());
                tbRow.add(s.getHanTra());
                tbRow.add(s.getMaSach());
                tbRow.add(s.getMaNguoiMuon());
                tbRow.add(s.getNgayTra());
                rows.add(tbRow);
            }
            Model.setDataVector(rows, columns);
            tablePhieuMuon.setModel(Model);
            return true;
        }
        else {
            return false;
        }
    }

    public boolean delPhieuMuonDaTra() {
        // Đặt lại số hàng của Model về 0 để xóa tất cả dữ liệu hiện tại trong bảng
          Model.setRowCount(0);

          // Xóa phiếu mượn đã trả thông qua phương thức delPhieuMuonDaTra()
          if (ppm.delPhieuMuonDaTra()) {
              // Lấy danh sách phiếu mượn mới nhất sau khi xóa
              ArrayList<PhieuMuon> ls = ppm.getListPhieuMuon();

              // Khởi tạo lại danh sách rows
              rows.clear();

              // Kiểm tra xem danh sách có rỗng hay không
              if (ls.isEmpty()) {
                  JOptionPane.showMessageDialog(null, "Không còn phiếu mượn nào trong danh sách!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
              } else {
                  // Duyệt qua danh sách và thêm từng phiếu mượn vào rows
                  for (PhieuMuon s : ls) {
                      Vector<Object> tbRow = new Vector<>();
                      tbRow.add(s.getMaPhieuMuon());
                      tbRow.add(s.getNgayMuon());
                      tbRow.add(s.getHanTra());
                      tbRow.add(s.getMaSach());
                      tbRow.add(s.getMaNguoiMuon());
                      tbRow.add(s.getNgayTra());
                      rows.add(tbRow);
                  }
              }

              // Đặt dữ liệu mới cho Model và cập nhật bảng
              Model.setDataVector(rows, columns);
              tablePhieuMuon.setModel(Model);
              return true;
          } else {
              JOptionPane.showMessageDialog(null, "Xóa thất bại!", "Thông báo", JOptionPane.ERROR_MESSAGE);
              return false;
          }      
    }
    public void getNguoiMuon(JComboBox ComboBox) {
        ArrayList<String> arr = ppm.getMaNguoiMuon();
        for (String mds : arr) {
            cbbMaNguoiMuon.addItem(mds);
        }
    }
    public void getSach(JComboBox ComboBox) {
        ArrayList<String> arr = ppm.getSach();
        for (String mds : arr) {
            cbbMaSach.addItem(mds);
        }
    }
    
    public quanLyPhieuMuon() {
        initComponents();
        columns.add("Mã phiếu mượn");
        columns.add("Ngày mượn");
        columns.add("Hạn trả");
        columns.add("Mã sách");
        columns.add("Mã người mượn");
        columns.add("Ngày trả");
        getNguoiMuon(cbbMaNguoiMuon);
        getSach(cbbMaSach);
        getAllPhieuMuon();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtMaPhieuMuon = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtHanTra = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtNgayMuon = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        cbbMaNguoiMuon = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        cbbMaSach = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        txtNgayTra = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnXoaPhieuDaTra = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablePhieuMuon = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jToggleButton1 = new javax.swing.JToggleButton();
        btnLamMoi = new javax.swing.JButton();
        cbbPhieuMuon = new javax.swing.JComboBox<>();
        txtSearchPhieuMuon = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        jPanel4.setLayout(new java.awt.BorderLayout());

        jPanel7.setBackground(new java.awt.Color(204, 204, 204));
        jPanel7.setPreferredSize(new java.awt.Dimension(997, 50));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setText("Quản lý phiếu mượn");
        jLabel1.setPreferredSize(new java.awt.Dimension(210, 40));
        jPanel7.add(jLabel1);

        jPanel4.add(jPanel7, java.awt.BorderLayout.PAGE_START);

        jPanel5.setPreferredSize(new java.awt.Dimension(800, 100));

        jPanel1.setPreferredSize(new java.awt.Dimension(400, 100));
        jPanel1.setLayout(new java.awt.GridLayout(0, 2, 5, 10));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel6.setText("Mã phiếu mượn");
        jPanel1.add(jLabel6);

        txtMaPhieuMuon.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jPanel1.add(txtMaPhieuMuon);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel8.setText("Hạn trả");
        jPanel1.add(jLabel8);

        txtHanTra.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jPanel1.add(txtHanTra);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel7.setText("Ngày mượn");
        jPanel1.add(jLabel7);

        txtNgayMuon.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jPanel1.add(txtNgayMuon);

        jPanel2.setPreferredSize(new java.awt.Dimension(400, 100));
        jPanel2.setLayout(new java.awt.GridLayout(0, 2, 5, 10));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel10.setText("Mã người mượn");
        jPanel2.add(jLabel10);

        jPanel2.add(cbbMaNguoiMuon);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel9.setText("Mã sách");
        jPanel2.add(jLabel9);

        jPanel2.add(cbbMaSach);

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel11.setText("Ngày trả");
        jPanel2.add(jLabel11);

        txtNgayTra.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtNgayTra.setEnabled(false);
        jPanel2.add(txtNgayTra);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(176, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(133, 133, 133))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(42, Short.MAX_VALUE))
        );

        jPanel4.add(jPanel5, java.awt.BorderLayout.CENTER);

        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 20, 5));

        btnThem.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnThem.setText("Thêm");
        btnThem.setPreferredSize(new java.awt.Dimension(100, 30));
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });
        jPanel3.add(btnThem);

        btnSua.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnSua.setText("Sửa");
        btnSua.setPreferredSize(new java.awt.Dimension(100, 30));
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });
        jPanel3.add(btnSua);

        btnXoa.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnXoa.setText("Xoá");
        btnXoa.setPreferredSize(new java.awt.Dimension(100, 30));
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });
        jPanel3.add(btnXoa);

        btnXoaPhieuDaTra.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnXoaPhieuDaTra.setText("Xoá phiếu đã trả");
        btnXoaPhieuDaTra.setPreferredSize(new java.awt.Dimension(180, 30));
        btnXoaPhieuDaTra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaPhieuDaTraActionPerformed(evt);
            }
        });
        jPanel3.add(btnXoaPhieuDaTra);

        jPanel4.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        add(jPanel4, java.awt.BorderLayout.CENTER);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setPreferredSize(new java.awt.Dimension(997, 330));

        tablePhieuMuon.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        tablePhieuMuon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã phiếu mượn", "Ngày mượn", "Hạn trả", "Mã sách", "Mã người mượn", "Ngày trả"
            }
        ));
        tablePhieuMuon.setRowHeight(25);
        tablePhieuMuon.setShowGrid(true);
        tablePhieuMuon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablePhieuMuonMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablePhieuMuon);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("Danh sách phiếu mượn");
        jLabel2.setPreferredSize(new java.awt.Dimension(300, 40));
        jPanel8.add(jLabel2);

        jToggleButton1.setText("sort");
        jToggleButton1.setPreferredSize(new java.awt.Dimension(150, 35));
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });
        jPanel8.add(jToggleButton1);

        btnLamMoi.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnLamMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/refresh (1).png"))); // NOI18N
        btnLamMoi.setPreferredSize(new java.awt.Dimension(40, 35));
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });
        jPanel8.add(btnLamMoi);

        cbbPhieuMuon.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        cbbPhieuMuon.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mã phiếu mượn", "Ngày mượn", "Hạn trả", "Mã sách", "Mã người mượn", "Ngày trả" }));
        cbbPhieuMuon.setPreferredSize(new java.awt.Dimension(150, 35));
        cbbPhieuMuon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbPhieuMuonActionPerformed(evt);
            }
        });
        jPanel8.add(cbbPhieuMuon);

        txtSearchPhieuMuon.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtSearchPhieuMuon.setPreferredSize(new java.awt.Dimension(250, 35));
        txtSearchPhieuMuon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchPhieuMuonActionPerformed(evt);
            }
        });
        jPanel8.add(txtSearchPhieuMuon);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/loupe (1).png"))); // NOI18N
        jButton2.setPreferredSize(new java.awt.Dimension(40, 35));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel8.add(jButton2);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        add(jPanel6, java.awt.BorderLayout.PAGE_END);
    }// </editor-fold>//GEN-END:initComponents

    private void tablePhieuMuonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablePhieuMuonMouseClicked
        btnThem.setEnabled(false);
        txtMaPhieuMuon.setEnabled(false);
        btnSua.setEnabled(true);
        btnXoa.setEnabled(true);
        txtNgayTra.setEnabled(true);
        int index = tablePhieuMuon.getSelectedRow();
        txtMaPhieuMuon.setText((String)(Model.getValueAt(index, 0)));
        txtNgayMuon.setText( (Model.getValueAt(index, 1)).toString());
        txtHanTra.setText( (Model.getValueAt(index, 2)).toString());
        cbbMaSach.setSelectedItem(Model.getValueAt(index, 3).toString());
        cbbMaNguoiMuon.setSelectedItem(Model.getValueAt(index, 4).toString());

        try {
            txtNgayTra.setText( (Model.getValueAt(index, 5)).toString());
        } catch (Exception e2) {

            txtNgayTra.setText(null);
        }
    }//GEN-LAST:event_tablePhieuMuonMouseClicked

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        btnThem.setEnabled(true);
        btnXoa.setEnabled(false);
        btnSua.setEnabled(false);
        txtNgayTra.setEnabled(false);
        txtMaPhieuMuon.setEnabled(true);
        txtMaPhieuMuon.setText(null);
        txtHanTra.setText(null);
        txtNgayTra.setText(null);
        txtNgayMuon.setText(null);
        cbbMaSach.setSelectedIndex(-1);
        cbbMaNguoiMuon.setSelectedIndex(-1);
        getAllPhieuMuon();
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        int n = JOptionPane.showConfirmDialog(jPanel4, "Bạn muốn xoá?", "Thông báo", JOptionPane.YES_NO_OPTION);
        if(n == JOptionPane.YES_OPTION) {

            if (delPhieuMuon(txtMaPhieuMuon.getText())) {
                JOptionPane.showMessageDialog(null, "Xóa thành công!" , "Thông báo", 1);
                Process_Sach ps = new Process_Sach();
                ps.updateSachTrangThai_Con((String)cbbMaSach.getSelectedItem());
                txtMaPhieuMuon.setEnabled(true);
                txtMaPhieuMuon.setText(null);
                txtHanTra.setText(null);
                txtNgayMuon.setText(null);
                cbbMaSach.setSelectedItem(null);
                cbbMaNguoiMuon.setSelectedItem(null);
                txtNgayTra.setText(null);
                btnSua.setEnabled(false);
            } else {
                // JOptionPane.showMessageDialog(null, "Xóa thất bại!" , "Thông báo", 1);
            }
        }

    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
           int n = JOptionPane.showConfirmDialog(jPanel4, "Bạn muốn sửa?", "Thông báo", JOptionPane.YES_NO_OPTION);
           if (n == JOptionPane.YES_OPTION) {
               // Kiểm tra dữ liệu đầu vào
               if (txtMaPhieuMuon.getText().equals("") || txtNgayMuon.getText().equals("") || txtHanTra.getText().equals("") || cbbMaSach.getSelectedIndex() == -1 || cbbMaNguoiMuon.getSelectedIndex() == -1) {
                   JOptionPane.showMessageDialog(null, "Bạn chưa nhập đủ thông tin!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                   return;
               }

               // Nếu không có ngày trả, thực hiện cập nhật thông thường
               if (txtNgayTra.getText().equals("")) {
                   if (updatePhieuMuon(txtMaPhieuMuon.getText(), Date.valueOf(txtNgayMuon.getText()), Date.valueOf(txtHanTra.getText()), (String) cbbMaSach.getSelectedItem(), (String) cbbMaNguoiMuon.getSelectedItem())) {
                       JOptionPane.showMessageDialog(null, "Cập nhật thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                   } else {
                       JOptionPane.showMessageDialog(null, "Cập nhật thất bại!", "Thông báo", JOptionPane.ERROR_MESSAGE);
                   }
               } 
               // Nếu có ngày trả, thực hiện cập nhật ngày trả và trạng thái sách
               else {
                   if (updatePMNgayTra(Date.valueOf(txtNgayTra.getText()), txtMaPhieuMuon.getText())) {
                       Process_Sach ps = new Process_Sach();
                       ps.updateSachTrangThai_Con((String) cbbMaSach.getSelectedItem());
                       JOptionPane.showMessageDialog(null, "Cập nhật ngày trả và trạng thái sách thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                   } else {
                       JOptionPane.showMessageDialog(null, "Cập nhật ngày trả thất bại!", "Thông báo", JOptionPane.ERROR_MESSAGE);
                   }
               }

               // Làm mới giao diện người dùng sau khi cập nhật
               txtMaPhieuMuon.setText(null);
               txtNgayMuon.setText(null);
               txtHanTra.setText(null);
               txtNgayTra.setText(null);
               cbbMaSach.setSelectedIndex(-1);
               cbbMaNguoiMuon.setSelectedIndex(-1);

               // Gọi phương thức để làm mới bảng dữ liệu sau khi cập nhật
               getAllPhieuMuon();
           }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt){//GEN-FIRST:event_btnThemActionPerformed
        if (txtMaPhieuMuon.getText().isEmpty() || txtNgayMuon.getText().isEmpty() ||
              cbbMaSach.getSelectedIndex() == -1 || cbbMaNguoiMuon.getSelectedIndex() == -1) {
              JOptionPane.showMessageDialog(null, "Bạn chưa nhập đủ thông tin!", "Thông báo", 1);
          } else {
              try {
                  // Kiểm tra và chuyển đổi ngày
                  Date ngayMuon = Date.valueOf(txtNgayMuon.getText());
                  Date hanTra = Date.valueOf(txtHanTra.getText());
                  String maSach = (String) cbbMaSach.getSelectedItem();
                  String maNguoiMuon = (String) cbbMaNguoiMuon.getSelectedItem();

                  // Thêm phiếu mượn vào cơ sở dữ liệu
                  if (insertPhieuMuon(txtMaPhieuMuon.getText(), ngayMuon, hanTra, maSach, maNguoiMuon)) {
                      JOptionPane.showMessageDialog(null, "Thêm thành công!", "Thông báo", 1);

                      // Cập nhật trạng thái sách
                      Process_Sach ps = new Process_Sach();
                      ps.updateSachTrangThai_DaMuon(maSach);

                      // Xóa dữ liệu giao diện
                      clearInputFields();
                  } else {
                      JOptionPane.showMessageDialog(null, "Thêm thất bại!", "Thông báo", 1);
                  }
              } catch (IllegalArgumentException e) {
                  JOptionPane.showMessageDialog(null, "Định dạng ngày không hợp lệ! (Định dạng yêu cầu: yyyy-MM-dd)", "Thông báo", 1);
              }
          }       
    }//GEN-LAST:event_btnThemActionPerformed
    private void clearInputFields() {
        txtMaPhieuMuon.setText(null);
        txtHanTra.setText(null);
        txtNgayTra.setText(null);
        txtNgayMuon.setText(null);
        cbbMaSach.setSelectedIndex(-1);
        cbbMaNguoiMuon.setSelectedIndex(-1);
    }
    private void btnXoaPhieuDaTraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaPhieuDaTraActionPerformed
        int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa phiếu mượn đã trả không?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            if (delPhieuMuonDaTra()) {
                JOptionPane.showMessageDialog(null, "Xóa thành công!", "Thông báo", 1);
                getAllPhieuMuon();  // Cập nhật lại danh sách sau khi xóa
            } else {
                JOptionPane.showMessageDialog(null, "Xóa thất bại!", "Thông báo", 1);
            }
        }  
    }//GEN-LAST:event_btnXoaPhieuDaTraActionPerformed

    private void cbbPhieuMuonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbPhieuMuonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbPhieuMuonActionPerformed

    private void txtSearchPhieuMuonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchPhieuMuonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchPhieuMuonActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
               
    }//GEN-LAST:event_jButton2ActionPerformed
private void sortTableData(boolean isAscending) {
        // Lấy dữ liệu hiện tại trong bảng và lưu vào danh sách
        DefaultTableModel model = (DefaultTableModel) tablePhieuMuon.getModel();
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
        int index = cbbPhieuMuon.getSelectedIndex();

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




    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton btnXoaPhieuDaTra;
    private javax.swing.JComboBox<String> cbbMaNguoiMuon;
    private javax.swing.JComboBox<String> cbbMaSach;
    private javax.swing.JComboBox<String> cbbPhieuMuon;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
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
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JTable tablePhieuMuon;
    private javax.swing.JTextField txtHanTra;
    private javax.swing.JTextField txtMaPhieuMuon;
    private javax.swing.JTextField txtNgayMuon;
    private javax.swing.JTextField txtNgayTra;
    private javax.swing.JTextField txtSearchPhieuMuon;
    // End of variables declaration//GEN-END:variables
}
