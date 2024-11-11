/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI_ThuThu;

import Process.Process_PhieuMuon;
import Process.Process_Sach;

import java.awt.event.ActionEvent;
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
        table.setModel(Model);
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
            table.setModel(Model);

            return true;
        }
        else {
            return false;
        }
    }

    public void updatePhieuMuon(String MaPhieuMuon, Date NgayMuon,Date HanTra, String MaSach, String MaNguoiMuon) {
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
            table.setModel(Model);
        }
        else {
            System.out.println(false);
        }

    }

    public void updatePMNgayTra(Date NgayTra, String MaPhieuMuon) {
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
            table.setModel(Model);
        }
        else {
            System.out.println(false);
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
            table.setModel(Model);
            return true;
        }
        else {
            return false;
        }
    }

    public boolean delPhieuMuonDaTra() {
        Model.setRowCount(0);
        if(ppm.delPhieuMuonDaTra()) {
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
            table.setModel(Model);
            return true;

        }
        else {
            return false;
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
        jLabel6 = new javax.swing.JLabel();
        txtMaPhieuMuon = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtNgayMuon = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtHanTra = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtMaSach = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtNgayTra = new javax.swing.JTextField();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnLamMoi = new javax.swing.JButton();
        txtMaNguoiMuon = new javax.swing.JTextField();
        btnXoaPhieuDaTra = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel6.setText("Mã phiếu mượn");

        txtMaPhieuMuon.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel7.setText("Ngày mượn");

        txtNgayMuon.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel8.setText("Hạn trả");

        txtHanTra.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel9.setText("Mã sách");

        txtMaSach.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel10.setText("Mã người mượn");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel11.setText("Ngày trả");

        txtNgayTra.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtNgayTra.setEnabled(false);

        btnThem.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnXoa.setText("Xoá");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnLamMoi.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnLamMoi.setText("Làm mới");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        txtMaNguoiMuon.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        btnXoaPhieuDaTra.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnXoaPhieuDaTra.setText("Xoá phiếu đã trả");
        btnXoaPhieuDaTra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaPhieuDaTraActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtHanTra, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtNgayMuon)
                        .addComponent(txtMaPhieuMuon, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(159, 159, 159)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(69, 69, 69)
                        .addComponent(txtMaSach, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(76, 76, 76)
                                .addComponent(txtNgayTra, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtMaNguoiMuon, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(71, Short.MAX_VALUE)
                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(74, 74, 74)
                .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(110, 110, 110)
                .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(82, 82, 82)
                .addComponent(btnLamMoi)
                .addGap(73, 73, 73)
                .addComponent(btnXoaPhieuDaTra)
                .addGap(50, 50, 50))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(txtMaSach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMaNguoiMuon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(txtNgayTra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtMaPhieuMuon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtNgayMuon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtHanTra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(27, 27, 27)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnXoa)
                        .addComponent(btnLamMoi)
                        .addComponent(btnXoaPhieuDaTra))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnThem, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btnSua)))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        table.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        table.setModel(new javax.swing.table.DefaultTableModel(
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
        table.setRowHeight(25);
        table.setShowGrid(true);
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("Danh sách phiếu mượn");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 965, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(62, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        btnThem.setEnabled(false);
        txtMaPhieuMuon.setEnabled(false);
        btnSua.setEnabled(true);
        btnXoa.setEnabled(true);
        txtNgayTra.setEnabled(true);
        int index = table.getSelectedRow();
        txtMaPhieuMuon.setText((String)(Model.getValueAt(index, 0)));
        txtNgayMuon.setText( (Model.getValueAt(index, 1)).toString());
        txtHanTra.setText( (Model.getValueAt(index, 2)).toString());
        txtMaSach.setText(Model.getValueAt(index, 3).toString());
        txtMaNguoiMuon.setText(Model.getValueAt(index, 4).toString());

        try {
            txtNgayTra.setText( (Model.getValueAt(index, 5)).toString());
        } catch (Exception e2) {

            txtNgayTra.setText(null);
        }
    }//GEN-LAST:event_tableMouseClicked

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
        txtMaSach.setText(null);
        txtMaNguoiMuon.setText(null);
        getAllPhieuMuon();
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        int n = JOptionPane.showConfirmDialog(jPanel4, "Bạn muốn xoá?", "Thông báo", JOptionPane.YES_NO_OPTION);
        if(n == JOptionPane.YES_OPTION) {

            if (delPhieuMuon(txtMaPhieuMuon.getText())) {
                JOptionPane.showMessageDialog(null, "Xóa thành công!" , "Thông báo", 1);
                Process_Sach ps = new Process_Sach();
                ps.updateSachTrangThai2(txtMaSach.getText());
                txtMaPhieuMuon.setEnabled(true);
                txtMaPhieuMuon.setText(null);
                txtHanTra.setText(null);
                txtNgayMuon.setText(null);
                txtMaSach.setText(null);
                txtMaNguoiMuon.setText(null);
                txtNgayTra.setText(null);
                btnSua.setEnabled(false);
            } else {
                JOptionPane.showMessageDialog(null, "Xóa thất bại!" , "Thông báo", 1);
            }
        }

    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        int n = JOptionPane.showConfirmDialog(jPanel4, "Bạn muốn sửa?", "Thông báo", JOptionPane.YES_NO_OPTION);
        if(n == JOptionPane.YES_OPTION) {

            if(txtNgayTra.getText().equals(""))
            {
                updatePhieuMuon(txtMaPhieuMuon.getText(), Date.valueOf(txtNgayMuon.getText()),Date.valueOf(txtHanTra.getText()), txtMaSach.getText(), txtMaNguoiMuon.getText());
            }
            else {
                updatePMNgayTra(Date.valueOf(txtNgayTra.getText()), txtMaPhieuMuon.getText());
                Process_Sach ps = new Process_Sach();
                ps.updateSachTrangThai2(txtMaSach.getText());

            }

    }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt){//GEN-FIRST:event_btnThemActionPerformed
            if (txtMaPhieuMuon.getText().equals("") || txtNgayMuon.getText().equals("") || txtMaSach.getText().equals("") || txtMaNguoiMuon.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Bạn chưa nhập đủ thông tin!", "Thông báo", 1);
            } else {
                if (insertPhieuMuon(txtMaPhieuMuon.getText(), Date.valueOf(txtNgayMuon.getText()), Date.valueOf(txtHanTra.getText()), txtMaSach.getText(), txtMaNguoiMuon.getText())) {
                    JOptionPane.showMessageDialog(null, "Thêm thành công!", "Thông báo", 1);
                    Process_Sach ps = new Process_Sach();
                    ps.updateSachTrangThai(txtMaSach.getText());
                    txtMaPhieuMuon.setText(null);
                    txtHanTra.setText(null);
                    txtNgayTra.setText(null);
                    txtNgayMuon.setText(null);
                    txtMaSach.setText(null);
                    txtMaNguoiMuon.setText(null);
                } else {
                    JOptionPane.showMessageDialog(null, "Thêm thất bại!", "Thông báo", 1);
                    getAllPhieuMuon();
                }
            }

    }//GEN-LAST:event_btnThemActionPerformed

    private void btnXoaPhieuDaTraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaPhieuDaTraActionPerformed

            if(delPhieuMuonDaTra()) {
                JOptionPane.showMessageDialog(null, "Xóa thành công!" , "Thông báo", 1);
                getAllPhieuMuon();
            }
            else {
                JOptionPane.showMessageDialog(null, "Xóa thất bại!" , "Thông báo", 1);
            }

    }//GEN-LAST:event_btnXoaPhieuDaTraActionPerformed




    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton btnXoaPhieuDaTra;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table;
    private javax.swing.JTextField txtHanTra;
    private javax.swing.JTextField txtMaNguoiMuon;
    private javax.swing.JTextField txtMaPhieuMuon;
    private javax.swing.JTextField txtMaSach;
    private javax.swing.JTextField txtNgayMuon;
    private javax.swing.JTextField txtNgayTra;
    // End of variables declaration//GEN-END:variables
}
