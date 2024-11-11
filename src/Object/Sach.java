package Object;

public class Sach {
	private String MaSach, TenSach, TrangThai, MaDauSach;

	public Sach() {
		super();
	}

	public Sach(String maSach, String tenSach, String trangThai, String maDauSach) {
		super();
		MaSach = maSach;
		TenSach = tenSach;
		TrangThai = trangThai;
		MaDauSach = maDauSach;
	}

	public String getMaSach() {
		return MaSach;
	}

	public void setMaSach(String maSach) {
		MaSach = maSach;
	}

	public String getTenSach() {
		return TenSach;
	}

	public void setTenSach(String tenSach) {
		TenSach = tenSach;
	}

	public String getTrangThai() {
		return TrangThai;
	}

	public void setTrangThai(String trangThai) {
		TrangThai = trangThai;
	}

	public String getMaDauSach() {
		return MaDauSach;
	}

	public void setMaDauSach(String maDauSach) {
		MaDauSach = maDauSach;
	}
}
