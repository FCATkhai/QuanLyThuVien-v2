package Object;

public class NguoiMuon {
	private String MaNguoiMuon, TenNguoiMuon, DiaChi,Gmail,SDT;

	public NguoiMuon() {
		super();
	}

	public NguoiMuon(String maNguoiMuon, String tenNguoiMuon, String diaChi, String gmail, String sDT) {
		super();
		MaNguoiMuon = maNguoiMuon;
		TenNguoiMuon = tenNguoiMuon;
		DiaChi = diaChi;
		Gmail = gmail;
		SDT = sDT;
	}

	public String getMaNguoiMuon() {
		return MaNguoiMuon;
	}

	public void setMaNguoiMuon(String maNguoiMuon) {
		MaNguoiMuon = maNguoiMuon;
	}

	public String getTenNguoiMuon() {
		return TenNguoiMuon;
	}

	public void setTenNguoiMuon(String tenNguoiMuon) {
		TenNguoiMuon = tenNguoiMuon;
	}

	public String getDiaChi() {
		return DiaChi;
	}

	public void setDiaChi(String diaChi) {
		DiaChi = diaChi;
	}

	public String getGmail() {
		return Gmail;
	}

	public void setGmail(String gmail) {
		Gmail = gmail;
	}

	public String getSDT() {
		return SDT;
	}

	public void setSDT(String sDT) {
		SDT = sDT;
	}
}
