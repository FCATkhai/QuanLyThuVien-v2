package Object;

import java.util.Date;

public class PhieuMuon {
	private String MaPhieuMuon, MaSach, MaNguoiMuon;
	private Date NgayMuon, HanTra, NgayTra;
        private Sach sach;
	public PhieuMuon() {
		super();
                this.sach = new Sach();
	}

	public PhieuMuon(String maPhieuMuon, String maSach, String maNguoiMuon, Date ngayMuon, Date hanTra, Date ngayTra) {
		super();
		MaPhieuMuon = maPhieuMuon;
		MaSach = maSach;
		MaNguoiMuon = maNguoiMuon;
		NgayMuon = ngayMuon;
		HanTra = hanTra;
		NgayTra = ngayTra;
	}

	public String getMaPhieuMuon() {
		return MaPhieuMuon;
	}

	public void setMaPhieuMuon(String maPhieuMuon) {
		MaPhieuMuon = maPhieuMuon;
	}

	public String getMaSach() {
		return MaSach;
	}

	public void setMaSach(String maSach) {
		MaSach = maSach;
	}

	public String getMaNguoiMuon() {
		return MaNguoiMuon;
	}

	public void setMaNguoiMuon(String maNguoiMuon) {
		MaNguoiMuon = maNguoiMuon;
	}

	public Date getNgayMuon() {
		return NgayMuon;
	}

	public void setNgayMuon(Date ngayMuon) {
		NgayMuon = ngayMuon;
	}

	public Date getHanTra() {
		return HanTra;
	}

	public void setHanTra(Date hanTra) {
		HanTra = hanTra;
	}

	public Date getNgayTra() {
		return NgayTra;
	}

	public void setNgayTra(Date ngayTra) {
		NgayTra = ngayTra;
	}

    // Tạo các phương thức setTenSach và setTrangThai để lấy dữ liệu từ lớp Sach
        public void setTenSach(String tenSach) {
            sach.setTenSach(tenSach);  // Lấy tên sách từ đối tượng Sach
        }

        public void setTrangThai(String trangThai) {
            sach.setTrangThai(trangThai);
        }

        public String getTenSach() {
            return sach.getTenSach();
        }

        public String getTrangThai() {
            return sach.getTrangThai();
        }

	

	
	
}
