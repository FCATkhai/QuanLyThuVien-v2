/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI_ThuThu;

import Process.Process_NguoiMuon;

import java.util.ArrayList;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import Object.NguoiMuon;
import Process.Connect_database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author ASUS
 */
public class quanLyNguoiMuonCard extends javax.swing.JPanel {

    
    DefaultTableModel Model = new DefaultTableModel();
    Vector<String> columns = new Vector<String>();
    Vector<Vector<Object>> rows = new Vector<>();
    Process_NguoiMuon pmn = new Process_NguoiMuon();

    public void getAllNguoiMuon() {
        Model.setRowCount(0);

        ArrayList<NguoiMuon> ls = pmn.getListNguoiMuon();
        for (int i = 0; i < ls.size(); i++) {
            NguoiMuon s = (NguoiMuon) ls.get(i);
            Vector<Object> tbRow = new Vector<>();
            tbRow.add(s.getMaNguoiMuon());
            tbRow.add(s.getTenNguoiMuon());
            tbRow.add(s.getDiaChi());
            tbRow.add(s.getGmail());
            tbRow.add(s.getSDT());
            
            rows.add(tbRow);
        }
        Model.setDataVector(rows, columns);
        tableNguoiMuon.setModel(Model);
    }
    public void insertNguoiMuon(String MaNguoiMuon, String TenNguoiMuon, String DiaChi, String Gmail, String SDT) {
        Model.setRowCount(0);
        if(pmn.insertNguoiMuon(MaNguoiMuon.trim(), TenNguoiMuon.trim(), DiaChi.trim(), Gmail.trim(), SDT.trim()) == true) {
            ArrayList<NguoiMuon> ls = pmn.getListNguoiMuon();
            for (int i = 0; i < ls.size(); i++) {
                NguoiMuon s = (NguoiMuon) ls.get(i);
                Vector<Object> tbRow = new Vector<>();
                tbRow.add(s.getMaNguoiMuon());
                tbRow.add(s.getTenNguoiMuon());
                tbRow.add(s.getDiaChi());
                tbRow.add(s.getGmail());
                tbRow.add(s.getSDT());
                rows.add(tbRow);
            }
            Model.setDataVector(rows, columns);
            tableNguoiMuon.setModel(Model);
        }
        else {
            getAllNguoiMuon();
        }
    }
    private void sortTableData(boolean isAscending) {
        // Lấy dữ liệu hiện tại trong bảng và lưu vào danh sách
        DefaultTableModel model = (DefaultTableModel) tableNguoiMuon.getModel();
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
        int index = cbbNguoiMuon.getSelectedIndex();

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
    public void updateNguoiMuon(String MaNguoiMuon, String TenNguoiMuon, String DiaChi,String Gmail, String SDT) {
        Model.setRowCount(0);
        if(pmn.updateNguoiMuon(MaNguoiMuon, TenNguoiMuon, DiaChi, Gmail, SDT) == true) {
            ArrayList<NguoiMuon> ls = pmn.getListNguoiMuon();
            for(int i = 0; i< ls.size(); i++) {
                NguoiMuon s = (NguoiMuon) ls.get(i);
                Vector<Object> tbRow = new Vector<>();
                tbRow.add(s.getMaNguoiMuon());
                tbRow.add(s.getTenNguoiMuon());
                tbRow.add(s.getDiaChi());
                tbRow.add(s.getGmail());
                tbRow.add(s.getSDT());
                rows.add(tbRow);
            }
            Model.setDataVector(rows, columns);
            tableNguoiMuon.setModel(Model);
        }
    }
    public void delNguoiMuon(String MaNguoiMuon) {
        Model.setRowCount(0);
        if(pmn.delNguoiMuon(MaNguoiMuon) == true) {
            ArrayList<NguoiMuon> ls = pmn.getListNguoiMuon();
            for(int i = 0; i<ls.size(); i++) {
                NguoiMuon s = (NguoiMuon) ls.get(i);
                Vector<Object> tbRow = new Vector<>();
                tbRow.add(s.getMaNguoiMuon());
                tbRow.add(s.getTenNguoiMuon());
                tbRow.add(s.getDiaChi());
                tbRow.add(s.getGmail());
                tbRow.add(s.getSDT());
                rows.add(tbRow);
            }
            Model.setDataVector(rows, columns);
            tableNguoiMuon.setModel(Model);
        }

    }
    
    
    public quanLyNguoiMuonCard() {
        initComponents();
        columns.add("Mã người mượn");
        columns.add("Tên người mượn");
        columns.add("Địa chỉ");
        columns.add("Email");
        columns.add("SDT");
        btnSua.setEnabled(false);
        btnXoa.setEnabled(false);
        btnThem.setEnabled(true);
        getAllNguoiMuon();
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
        jPanel9 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtMaNguoiMuon = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtGmail = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtTenNguoiMuon = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDiaChi = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnLamMoi = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableNguoiMuon = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jToggleButton1 = new javax.swing.JToggleButton();
        cbbNguoiMuon = new javax.swing.JComboBox<>();
        txtSearchNguoiMuon = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        jPanel4.setLayout(new java.awt.BorderLayout());

        jPanel9.setBackground(new java.awt.Color(204, 204, 204));
        jPanel9.setPreferredSize(new java.awt.Dimension(1071, 50));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setText("Quản lý người mượn sách");
        jPanel9.add(jLabel1);

        jPanel4.add(jPanel9, java.awt.BorderLayout.PAGE_START);

        jPanel3.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jPanel3.setPreferredSize(new java.awt.Dimension(824, 300));
        jPanel3.setLayout(new java.awt.BorderLayout(10, 10));

        jPanel5.setPreferredSize(new java.awt.Dimension(100, 80));
        jPanel5.setLayout(new java.awt.GridLayout(0, 4, 20, 20));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel6.setText("Mã người mượn:");
        jLabel6.setPreferredSize(new java.awt.Dimension(90, 30));
        jPanel5.add(jLabel6);

        txtMaNguoiMuon.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jPanel5.add(txtMaNguoiMuon);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel9.setText("Email:");
        jLabel9.setPreferredSize(new java.awt.Dimension(90, 30));
        jPanel5.add(jLabel9);

        txtGmail.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtGmail.setPreferredSize(new java.awt.Dimension(100, 31));
        jPanel5.add(txtGmail);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel7.setText("Tên người mượn:");
        jLabel7.setPreferredSize(new java.awt.Dimension(90, 30));
        jPanel5.add(jLabel7);

        txtTenNguoiMuon.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jPanel5.add(txtTenNguoiMuon);

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel10.setText("Số điện thoại:");
        jLabel10.setMaximumSize(new java.awt.Dimension(50, 25));
        jLabel10.setPreferredSize(new java.awt.Dimension(30, 30));
        jPanel5.add(jLabel10);

        txtSDT.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jPanel5.add(txtSDT);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1161, Short.MAX_VALUE)
            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 1021, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(128, Short.MAX_VALUE)))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel8Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)))
        );

        jPanel3.add(jPanel8, java.awt.BorderLayout.PAGE_START);

        jPanel7.setPreferredSize(new java.awt.Dimension(500, 150));
        jPanel7.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEADING, 13, 0));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel8.setText("Địa chỉ:");
        jLabel8.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel8.setPreferredSize(new java.awt.Dimension(240, 70));
        jPanel7.add(jLabel8);

        jScrollPane2.setPreferredSize(new java.awt.Dimension(600, 70));

        txtDiaChi.setColumns(20);
        txtDiaChi.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtDiaChi.setRows(5);
        jScrollPane2.setViewportView(txtDiaChi);

        jPanel7.add(jScrollPane2);

        jPanel3.add(jPanel7, java.awt.BorderLayout.CENTER);

        jPanel4.add(jPanel3, java.awt.BorderLayout.CENTER);

        jPanel2.setPreferredSize(new java.awt.Dimension(806, 50));
        jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 20, 5));

        btnThem.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnThem.setText("Thêm");
        btnThem.setPreferredSize(new java.awt.Dimension(100, 30));
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });
        jPanel2.add(btnThem);

        btnSua.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnSua.setText("Sửa");
        btnSua.setPreferredSize(new java.awt.Dimension(100, 30));
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });
        jPanel2.add(btnSua);

        btnXoa.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnXoa.setText("Xoá");
        btnXoa.setPreferredSize(new java.awt.Dimension(100, 30));
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });
        jPanel2.add(btnXoa);

        btnLamMoi.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnLamMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/refresh (1).png"))); // NOI18N
        btnLamMoi.setPreferredSize(new java.awt.Dimension(40, 35));
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });
        jPanel2.add(btnLamMoi);

        jPanel4.add(jPanel2, java.awt.BorderLayout.PAGE_END);

        add(jPanel4, java.awt.BorderLayout.CENTER);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setPreferredSize(new java.awt.Dimension(1016, 350));

        tableNguoiMuon.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        tableNguoiMuon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã người mượn", "Tên người mượn", "Địa chỉ", "Email", "SDT"
            }
        ));
        tableNguoiMuon.setRowHeight(25);
        tableNguoiMuon.setShowGrid(true);
        tableNguoiMuon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableNguoiMuonMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableNguoiMuon);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("Danh sách người mượn");
        jLabel2.setPreferredSize(new java.awt.Dimension(400, 25));
        jPanel1.add(jLabel2);

        jToggleButton1.setText("sort");
        jToggleButton1.setPreferredSize(new java.awt.Dimension(150, 35));
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jToggleButton1);

        cbbNguoiMuon.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mã người mượn", "Tên người mượn", "Địa chỉ", "Email", "SDT" }));
        cbbNguoiMuon.setPreferredSize(new java.awt.Dimension(150, 30));
        cbbNguoiMuon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbNguoiMuonActionPerformed(evt);
            }
        });
        jPanel1.add(cbbNguoiMuon);

        txtSearchNguoiMuon.setPreferredSize(new java.awt.Dimension(250, 30));
        txtSearchNguoiMuon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchNguoiMuonActionPerformed(evt);
            }
        });
        jPanel1.add(txtSearchNguoiMuon);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/loupe (1).png"))); // NOI18N
        jButton1.setPreferredSize(new java.awt.Dimension(30, 30));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(45, 45, 45))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        add(jPanel6, java.awt.BorderLayout.PAGE_END);
    }// </editor-fold>//GEN-END:initComponents

    private void tableNguoiMuonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableNguoiMuonMouseClicked
        btnSua.setEnabled(true);
        btnXoa.setEnabled(true);
        btnThem.setEnabled(false);
        txtMaNguoiMuon.setEnabled(false);
        int index = tableNguoiMuon.getSelectedRow();

        txtMaNguoiMuon.setText((String)(Model.getValueAt(index, 0)));
        txtTenNguoiMuon.setText((String)(Model.getValueAt(index, 1)));
        txtDiaChi.setText((String)(Model.getValueAt(index, 2)));
        txtGmail.setText((String)(Model.getValueAt(index, 3)));
        txtSDT.setText((String)(Model.getValueAt(index, 4)));
    }//GEN-LAST:event_tableNguoiMuonMouseClicked

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        if(txtMaNguoiMuon.getText().equals("")  || txtTenNguoiMuon.getText().equals("") || txtDiaChi.getText().equals("") || txtGmail.getText().equals("") || txtSDT.getText().equals(""))
        {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đủ thông tin" , "Thông báo", 1);
        }
        else {
            insertNguoiMuon(txtMaNguoiMuon.getText(), txtTenNguoiMuon.getText(), txtDiaChi.getText(), txtGmail.getText(), txtSDT.getText());
            txtDiaChi.setText("");
            txtGmail.setText("");
            txtSDT.setText("");
            txtTenNguoiMuon.setText("");
            txtMaNguoiMuon.setText("");
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        int n = JOptionPane.showConfirmDialog(jPanel4, "Bạn muốn xóa?", "Thông báo", JOptionPane.YES_NO_OPTION);
        if(n == JOptionPane.YES_OPTION) {
            delNguoiMuon(txtMaNguoiMuon.getText());
            txtMaNguoiMuon.setEnabled(true);
            txtGmail.setText(null);
            txtDiaChi.setText(null);
            txtMaNguoiMuon.setText(null);
            txtSDT.setText(null);
            txtTenNguoiMuon.setText(null);
            btnSua.setEnabled(false);
            btnXoa.setEnabled(false);
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        btnThem.setEnabled(true);
        btnSua.setEnabled(false);
        btnXoa.setEnabled(false);
        txtMaNguoiMuon.setEnabled(true);
        txtGmail.setText(null);
        txtDiaChi.setText(null);
        txtMaNguoiMuon.setText(null);
        txtSDT.setText(null);
        txtTenNguoiMuon.setText(null);
        getAllNguoiMuon();
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void txtSearchNguoiMuonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchNguoiMuonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchNguoiMuonActionPerformed

    private void cbbNguoiMuonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbNguoiMuonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbNguoiMuonActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
         Connect_database cd = new Connect_database();
        DefaultTableModel Model3 = new DefaultTableModel();

        // Khởi tạo tên cột cho bảng
        Vector<String> columns3 = new Vector<>();
        columns3.add("Mã Người Mượn");
        columns3.add("Tên Người Mượn");
        columns3.add("Địa Chỉ");
        columns3.add("Gmail");
        columns3.add("SĐT");

        Vector<Vector<Object>> rows3 = new Vector<>();
        String sql = null;

        if (txtSearchNguoiMuon.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Bạn chưa nhập đủ thông tin!", "Thông báo", 1);
        } else {
            // Thiết lập câu lệnh SQL dựa trên lựa chọn tìm kiếm
            switch (cbbNguoiMuon.getSelectedIndex()) {
                case 0 -> sql = "SELECT * FROM tb_nguoimuon WHERE MaNguoiMuon = ?";
                case 1 -> sql = "SELECT * FROM tb_nguoimuon WHERE TenNguoiMuon LIKE ?";
                case 2 -> sql = "SELECT * FROM tb_nguoimuon WHERE DiaChi LIKE ?";
                case 3 -> sql = "SELECT * FROM tb_nguoimuon WHERE Gmail LIKE ?";
                case 4 -> sql = "SELECT * FROM tb_nguoimuon WHERE SDT = ?";
            }

            Model3.setRowCount(0);
            Connection cn = cd.getConnection();
            ArrayList<NguoiMuon> ls = new ArrayList<>();

            try {
                PreparedStatement ps = cn.prepareStatement(sql);

                // Sử dụng `LIKE` cho tìm kiếm không chính xác
                if (sql.contains("LIKE")) {
                    ps.setString(1, "%" + txtSearchNguoiMuon.getText() + "%");
                } else {
                    ps.setString(1, txtSearchNguoiMuon.getText());
                }

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

                for (NguoiMuon s : ls) {
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

                // Dọn dẹp giao diện sau khi tìm kiếm
                cbbNguoiMuon.setSelectedIndex(-1);
                txtSearchNguoiMuon.setText(null);

            } catch (Exception e) {
                System.out.println(e.getMessage());
                JOptionPane.showMessageDialog(null, "Lỗi khi thực hiện tìm kiếm!", "Thông báo", 1);
            }

            if (Model3.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Không tìm thấy!", "Thông báo", 1);
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

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
            updateNguoiMuon(txtMaNguoiMuon.getText(), txtTenNguoiMuon.getText(), txtDiaChi.getText(), txtGmail.getText(), txtSDT.getText());
        }
        
    }                                        


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> cbbNguoiMuon;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
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
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JTable tableNguoiMuon;
    private javax.swing.JTextArea txtDiaChi;
    private javax.swing.JTextField txtGmail;
    private javax.swing.JTextField txtMaNguoiMuon;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtSearchNguoiMuon;
    private javax.swing.JTextField txtTenNguoiMuon;
    // End of variables declaration//GEN-END:variables
}
