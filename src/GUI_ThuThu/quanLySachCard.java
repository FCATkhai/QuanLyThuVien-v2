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
import Process.Process_Sach;
import Process.Process_DauSach;
import Object.Sach;
import Process.Connect_database;
import static Process.Connect_database.getConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author ASUS
 */
public class quanLySachCard extends javax.swing.JPanel {


    DefaultTableModel Model = new DefaultTableModel();
    Vector<String> columns = new Vector<String>();
    Vector<Vector<Object>> rows = new Vector<>();
    Process_Sach ps = new Process_Sach();

    public void getAllSach() {
        Model.setRowCount(0);

        ArrayList<Sach> ls = ps.getListSach();
        for (int i = 0; i < ls.size(); i++) {
            Sach s = (Sach) ls.get(i);
            Vector<Object> tbRow = new Vector<>();
            tbRow.add(s.getMaSach());
            tbRow.add(s.getTenSach());
            tbRow.add(s.getTrangThai());
            tbRow.add(s.getMaDauSach());
            rows.add(tbRow);
        }
        Model.setDataVector(rows, columns);
        tableSach.setModel(Model);
    }


    public boolean insertSach(String MaSach, String TenSach, String TrangThai, String MaDauSach) {
        Model.setRowCount(0);
        if (ps.insertSach(MaSach, TenSach, TrangThai, MaDauSach) == true) {
            ArrayList<Sach> ls = ps.getListSach();
            for (int i = 0; i < ls.size(); i++) {
                Sach s = (Sach) ls.get(i);
                Vector<Object> tbRow = new Vector<>();
                tbRow.add(s.getMaSach());
                tbRow.add(s.getTenSach());
                tbRow.add(s.getTrangThai());
                tbRow.add(s.getMaDauSach());
                rows.add(tbRow);
            }
            Model.setDataVector(rows, columns);
            tableSach.setModel(Model);
            return true;

        } else {
            return false;
        }

    }

    public void updateSach(String MaSach, String TenSach, String TrangThai, String MaDauSach) {
        if (ps.updateSach(MaSach, TenSach, TrangThai, MaDauSach) == true) {
            Model.setRowCount(0);
            ArrayList<Sach> ls = ps.getListSach();
            for (int i = 0; i < ls.size(); i++) {
                Sach s = (Sach) ls.get(i);
                Vector<Object> tbRow = new Vector<>();
                tbRow.add(s.getMaSach());
                tbRow.add(s.getTenSach());
                tbRow.add(s.getTrangThai());
                tbRow.add(s.getMaDauSach());
                rows.add(tbRow);

            }
            Model.setDataVector(rows, columns);
            tableSach.setModel(Model);
        }
    }

    public void delSach(String MaSach) {
        if (ps.delSach(MaSach) == true) {
            Model.setRowCount(0);
            ArrayList<Sach> ls = ps.getListSach();
            for (int i = 0; i < ls.size(); i++) {
                Sach s = (Sach) ls.get(i);
                Vector<Object> tbRow = new Vector<>();
                tbRow.add(s.getMaSach());
                tbRow.add(s.getTenSach());
                tbRow.add(s.getTrangThai());
                tbRow.add(s.getMaDauSach());
                rows.add(tbRow);
            }
            Model.setDataVector(rows, columns);
            tableSach.setModel(Model);
        }
    }

    public void getMaDauSach(JComboBox ComboBox) {
        ArrayList<String> arr = ps.getMaDauSach();
        for (String mds : arr) {
            cbbMaDauSach.addItem(mds);
        }
    }

    public void getTenSach(JComboBox ComboBox) {
        ArrayList<String> arr = ps.getTenSach();
        for (String mds : arr) {
            ComboBox.addItem(mds);
        }
    }


    public quanLySachCard() {
        initComponents();
        columns.add("Mã sách");
        columns.add("Tên sách");
        columns.add("Trạng thái");
        columns.add("Mã đầu sách");
        cbbMaDauSach.setSelectedIndex(-1);
        btnSua.setEnabled(false);
        btnXoa.setEnabled(false);
        btnThem.setEnabled(true);
        getMaDauSach(cbbMaDauSach);
        getAllSach();
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
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txtTenSach = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        cbbMaDauSach = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        comboBox = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        txtMaSach = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnLamMoi = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableSach = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jToggleButton1 = new javax.swing.JToggleButton();
        cbbSach = new javax.swing.JComboBox<>();
        txtSearchSach = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        jPanel4.setLayout(new java.awt.BorderLayout());

        jPanel5.setBackground(new java.awt.Color(204, 204, 204));
        jPanel5.setPreferredSize(new java.awt.Dimension(1023, 50));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setText("Quản lý sách");
        jLabel1.setPreferredSize(new java.awt.Dimension(133, 40));
        jPanel5.add(jLabel1);

        jPanel4.add(jPanel5, java.awt.BorderLayout.PAGE_START);

        jPanel2.setPreferredSize(new java.awt.Dimension(200, 191));
        jPanel2.setLayout(new java.awt.GridLayout(2, 4, 20, 10));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel7.setText("Tên sách:");
        jPanel2.add(jLabel7);

        txtTenSach.setPreferredSize(new java.awt.Dimension(150, 22));
        jPanel2.add(txtTenSach);

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel10.setText("Mã đầu sách:");
        jPanel2.add(jLabel10);

        cbbMaDauSach.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        cbbMaDauSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbMaDauSachActionPerformed(evt);
            }
        });
        jPanel2.add(cbbMaDauSach);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel9.setText("Trạng thái:");
        jPanel2.add(jLabel9);

        comboBox.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        comboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Còn", "Đã mượn" }));
        comboBox.setSelectedIndex(-1);
        jPanel2.add(comboBox);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel6.setText("Mã sách:");
        jPanel2.add(jLabel6);

        txtMaSach.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jPanel2.add(txtMaSach);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(91, 91, 91)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 768, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(244, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(71, Short.MAX_VALUE))
        );

        jPanel4.add(jPanel1, java.awt.BorderLayout.CENTER);

        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 10, 5));

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

        btnLamMoi.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnLamMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/refresh (1).png"))); // NOI18N
        btnLamMoi.setPreferredSize(new java.awt.Dimension(40, 35));
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });
        jPanel3.add(btnLamMoi);

        jPanel4.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        add(jPanel4, java.awt.BorderLayout.CENTER);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        tableSach.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        tableSach.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã sách", "Tên sách", "Trạng thái", "Mã đầu sách"
            }
        ));
        tableSach.setMinimumSize(new java.awt.Dimension(60, 200));
        tableSach.setRowHeight(25);
        tableSach.setShowGrid(true);
        tableSach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableSachMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableSach);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("Danh sách sách");
        jLabel2.setPreferredSize(new java.awt.Dimension(300, 25));
        jPanel7.add(jLabel2);

        jToggleButton1.setText("sort");
        jToggleButton1.setPreferredSize(new java.awt.Dimension(150, 35));
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });
        jPanel7.add(jToggleButton1);

        cbbSach.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        cbbSach.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mã sách", "Tên sách", "Trạng thái", "Mã đầu sách" }));
        cbbSach.setPreferredSize(new java.awt.Dimension(150, 30));
        cbbSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbSachActionPerformed(evt);
            }
        });
        jPanel7.add(cbbSach);

        txtSearchSach.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtSearchSach.setPreferredSize(new java.awt.Dimension(250, 30));
        txtSearchSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchSachActionPerformed(evt);
            }
        });
        jPanel7.add(txtSearchSach);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/loupe (1).png"))); // NOI18N
        jButton1.setPreferredSize(new java.awt.Dimension(30, 30));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel7.add(jButton1);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(167, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
                .addContainerGap())
        );

        add(jPanel6, java.awt.BorderLayout.PAGE_END);
    }// </editor-fold>//GEN-END:initComponents

    private void tableSachMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableSachMouseClicked
        btnSua.setEnabled(true);
        btnXoa.setEnabled(true);
        btnThem.setEnabled(false);
        txtMaSach.setEnabled(false);
        int index = tableSach.getSelectedRow();
        txtMaSach.setText((String)(Model.getValueAt(index, 0)));
        comboBox.setSelectedItem((String)(Model.getValueAt(index, 2)));
        cbbMaDauSach.setSelectedItem((String)(Model.getValueAt(index, 3)));

    }//GEN-LAST:event_tableSachMouseClicked

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        btnThem.setEnabled(true);
        btnSua.setEnabled(false);
        btnXoa.setEnabled(false);

        txtMaSach.setEnabled(true);
        txtMaSach.setText(null);
        comboBox.setSelectedIndex(-1);
        txtTenSach.setText(null);
        cbbMaDauSach.setSelectedIndex(-1);
        getAllSach();
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        int n = JOptionPane.showConfirmDialog(jPanel4, "Bạn muốn xóa?", "Thông báo", JOptionPane.YES_NO_OPTION);
        if (n == JOptionPane.YES_OPTION) {

            delSach(txtMaSach.getText());
            btnXoa.setEnabled(false);
            btnSua.setEnabled(false);
            txtMaSach.setEnabled(true);
            txtMaSach.setText(null);
            comboBox.setSelectedIndex(-1);
            txtTenSach.setText(null);
            cbbMaDauSach.setSelectedIndex(-1);
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        int n = JOptionPane.showConfirmDialog(jPanel4, "Bạn muốn sửa?", "Thông báo", JOptionPane.YES_NO_OPTION);
        if (n == JOptionPane.YES_OPTION) {

            updateSach(txtMaSach.getText(), (String) txtTenSach.getText(), (String) comboBox.getSelectedItem(), (String)cbbMaDauSach.getSelectedItem());
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        if (txtMaSach.getText().equals("") || txtTenSach.getText().equals("") || comboBox.getSelectedIndex() == -1 || cbbMaDauSach.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(null, "Bạn chưa nhập đủ thông tin!", "Thông báo", 1);
        } else {
            if (insertSach(txtMaSach.getText(), (String) txtTenSach.getText(), (String) comboBox.getSelectedItem(), (String)cbbMaDauSach.getSelectedItem())) {
                Process_DauSach pds = new Process_DauSach();
                pds.updateDauSachSoLuong((String)cbbMaDauSach.getSelectedItem());
                txtMaSach.setText(null);
                comboBox.setSelectedIndex(-1);
                txtTenSach.setText(null);
                cbbMaDauSach.setSelectedIndex(-1);
                JOptionPane.showMessageDialog(null, "Thêm thành công!", "Thông báo", 1);

            } else {
                JOptionPane.showMessageDialog(null, "Thêm thất bại!", "Thông báo", 1);
                getAllSach();
            }
        }
        }//GEN-LAST:event_btnThemActionPerformed

    private void cbbMaDauSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbMaDauSachActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbMaDauSachActionPerformed

    private void cbbSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbSachActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbSachActionPerformed

    private void txtSearchSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchSachActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchSachActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        DefaultTableModel Model2 = new DefaultTableModel();

        // Khởi tạo tên cột cho bảng
        Vector<String> columns2 = new Vector<>();
        columns2.add("Mã Sách");
        columns2.add("Tên Sách");
        columns2.add("Trạng Thái");
        columns2.add("Mã Đầu Sách");

        Vector<Vector<Object>> rows2 = new Vector<>();
        Connect_database cd = new Connect_database();
        String sql = null;

        if (txtSearchSach.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Bạn chưa nhập đủ thông tin!", "Thông báo", 1);
        } else {
            // Xác định câu lệnh SQL dựa trên mục tìm kiếm
            switch (cbbSach.getSelectedIndex()) {
                case 0 -> sql = "SELECT * FROM tb_sach WHERE MaSach = ?";
                case 1 -> sql = "SELECT DISTINCT * FROM tb_sach WHERE TenSach LIKE ?";
                case 2 -> sql = "SELECT * FROM tb_sach WHERE TrangThai LIKE ?";
                case 3 -> sql = "SELECT * FROM tb_sach WHERE MaDauSach = ?";
            }

            Model2.setRowCount(0);
            Connection cn = cd.getConnection();
            ArrayList<Sach> ls = new ArrayList<>();

            try {
                PreparedStatement ps = cn.prepareStatement(sql);

                // Sử dụng `LIKE` cho tìm kiếm không chính xác nếu trường `TenSach` được chọn
                if (sql.contains("LIKE")) {
                    ps.setString(1, "%" + txtSearchSach.getText() + "%");
                } else {
                    ps.setString(1, txtSearchSach.getText());
                }

                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Sach st = new Sach();
                    st.setMaSach(rs.getString("MaSach"));
                    st.setTenSach(rs.getString("TenSach"));
                    st.setTrangThai(rs.getString("TrangThai"));
                    st.setMaDauSach(rs.getString("MaDauSach"));
                    ls.add(st);
                }

                for (Sach s : ls) {
                    Vector<Object> tbRow2 = new Vector<>();
                    tbRow2.add(s.getMaSach());
                    tbRow2.add(s.getTenSach());
                    tbRow2.add(s.getTrangThai());
                    tbRow2.add(s.getMaDauSach());
                    rows2.add(tbRow2);
                }

                Model2.setDataVector(rows2, columns2);
                tableSach.setModel(Model2);

                // Dọn dẹp giao diện sau khi tìm kiếm
                cbbSach.setSelectedIndex(-1);
                txtSearchSach.setText(null);

            } catch (Exception e) {
                System.out.println(e.getMessage());
                JOptionPane.showMessageDialog(null, "Lỗi khi thực hiện tìm kiếm!", "Thông báo", 1);
            }

            if (Model2.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Không tìm thấy!", "Thông báo", 1);
            }
        }    
    }//GEN-LAST:event_jButton1ActionPerformed
private void sortTableData(boolean isAscending) {
        // Lấy dữ liệu hiện tại trong bảng và lưu vào danh sách
        DefaultTableModel model = (DefaultTableModel) tableSach.getModel();
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
        int index = cbbSach.getSelectedIndex();

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
    private javax.swing.JComboBox<String> cbbMaDauSach;
    private javax.swing.JComboBox<String> cbbSach;
    private javax.swing.JComboBox<String> comboBox;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JTable tableSach;
    private javax.swing.JTextField txtMaSach;
    private javax.swing.JTextField txtSearchSach;
    private javax.swing.JTextField txtTenSach;
    // End of variables declaration//GEN-END:variables
    }
