use quan_ly_thu_vien;


-- trigger dang_ky_nguoi_muon
DELIMITER $$
CREATE TRIGGER dang_ky_nguoi_muon
BEFORE INSERT ON tb_nguoimuon
FOR EACH ROW
BEGIN
    IF NEW.MaNguoiMuon IS NULL THEN
        SET NEW.MaNguoiMuon = CONCAT('MN', (SELECT COUNT(*) + 1 FROM tb_nguoimuon));
    END IF;
END$$
DELIMITER ;

-- trigger xoa nguoi muon
DROP TRIGGER IF EXISTS xoa_nguoi_muon;
DELIMITER $$
CREATE TRIGGER xoa_nguoi_muon
BEFORE DELETE ON tb_nguoimuon
FOR EACH ROW
BEGIN
    DELETE FROM tb_phieumuon WHERE MaNguoiMuon = OLD.MaNguoiMuon;
    DELETE FROM user WHERE username = OLD.MaNguoiMuon;
END$$
DELIMITER ;

-- trigger tao_phieu_muon
drop trigger tao_phieu_muon;
DELIMITER $$
CREATE TRIGGER tao_phieu_muon
BEFORE INSERT ON tb_phieumuon
FOR EACH ROW
BEGIN
    IF NEW.MaPhieuMuon IS NULL THEN
        SET NEW.MaPhieuMuon = CONCAT('P', (SELECT COUNT(*) + 1 FROM tb_phieumuon));
    END IF;
    IF NEW.NgayMuon IS NULL THEN
		SET NEW.NgayMuon = CURDATE();
    END IF;
    IF NEW.HanTra IS NULL THEN
		SET NEW.HanTra = DATE_ADD(CURDATE(), INTERVAL 7 DAY);
    END IF;
    IF NEW.NgayMuon > NEW.NgayTra THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Ngày trả phải nhỏ hơn ngày mượn';
    END IF;
     IF NEW.NgayMuon > NEW.HanTra THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Ngày trả phải nhỏ hơn hạn tra';
    END IF;
END$$
DELIMITER ;


-- trigger update_phieu_muon
DELIMITER $$
CREATE TRIGGER update_phieu_muon
BEFORE UPDATE ON tb_phieumuon
FOR EACH ROW
BEGIN
    IF NEW.NgayTra IS NULL THEN
        SET NEW.NgayTra = CURDATE();
    END IF;
END$$
DELIMITER ;


-- trigger kiem tra xoa sach
drop trigger if exists kiem_tra_xoa_sach;
DELIMITER $$
CREATE TRIGGER kiem_tra_xoa_sach
BEFORE DELETE ON tb_sach
FOR EACH ROW
BEGIN
	IF OLD.TrangThai = 'Đã mượn' THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Không thể xóa sách vì sách đang được mượn';
    END IF;
END$$
DELIMITER ;


-- trigger CheckNgayTraPhieuMuon

DELIMITER $$
CREATE TRIGGER CheckNgayTraPhieuMuon
BEFORE DELETE ON tb_phieumuon
FOR EACH ROW
BEGIN
    IF OLD.NgayTra IS NULL THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Không thể xóa phiếu mượn vì sách vẫn chưa được trả';
    END IF;
END$$
DELIMITER ;

-- trigger tang so luong sach
drop trigger if exists tang_soluong_sach;
DELIMITER $$
CREATE TRIGGER tang_soluong_sach
AFTER INSERT ON tb_sach
FOR EACH ROW
BEGIN
	UPDATE tb_dausach
    SET Soluong = Soluong + 1
    WHERE MaDauSach = NEW.MaDauSach;
END$$
DELIMITER ;

-- trigger giam so luong sach
drop trigger if exists giam_soluong_sach;
DELIMITER $$
CREATE TRIGGER giam_soluong_sach
AFTER DELETE ON tb_sach
FOR EACH ROW
BEGIN
	UPDATE tb_dausach
    SET Soluong = Soluong - 1
    WHERE MaDauSach = OLD.MaDauSach;
END$$
DELIMITER ;

--           procedure
-- #############################
DROP PROCEDURE IF EXISTS getSachDaMuonByMaNguoiMuon;
DELIMITER $$
CREATE PROCEDURE getSachDaMuonByMaNguoiMuon(IN MaNguoiMuon_IN VARCHAR(10))
BEGIN
	select t2.MaPhieuMuon, t1.MaSach as t1_MaSach, t1.TenSach, t2.NgayMuon, t2.HanTra, t2.NgayTra 
    from tb_sach t1, tb_phieumuon t2 
    where t1.MaSach = t2.MaSach and t2.MaNguoiMuon=MaNguoiMuon_IN;
END$$
DELIMITER ;

--           function
-- #############################
DROP FUNCTION IF EXISTS GetTotalBooks;
DELIMITER $$
CREATE FUNCTION GetTotalBooks() 
RETURNS INT
READS SQL DATA
BEGIN
    DECLARE TotalBooks INT;

    SELECT COUNT(*)
    INTO TotalBooks
    FROM tb_sach;

    RETURN TotalBooks;
END$$
DELIMITER ;




-- CALL getSachDaMuonByMaNguoiMuon('MN001'); 

-- drop table user;
-- CREATE TABLE user (
-- 	username VARCHAR(10) PRIMARY KEY,
--     password VARCHAR(45)
--     
--     -- FOREIGN KEY(username) REFERENCES tb_nguoimuon(MaNguoiMuon)
-- );

-- INSERT INTO user (username, password) VALUES
--     ('MN001', '1'),
--     ('MN002', '1'),
--     ('MN003', '1'),
--     ('MN004', '1'),
--     ('MN005', '1'),
--     ('MN007', '1'),
--     ('MN008', '1'),
--     ('MN009', '1'),
--     ('MN010', '1'),
--     ('MN011', '1'),
--     ('MN012', '1'),
--     ('MN013', '1'),
--     ('MN014', '1'),
--     ('MN015', '1'),
--     ('MN016', '1'),
--     ('MN017', '1'),
--     ('MN018', '1'),
--     ('MN019', '1'),
--     ('MN020', '1'),
--     ('MN021', '1'),
--     ('MN022', '1');
--     
-- ALTER TABLE user
-- ADD FOREIGN KEY(username) REFERENCES tb_nguoimuon(MaNguoiMuon);


-- INSERT INTO `tb_phieumuon` VALUES 
-- ('P001','2022-08-12','2022-09-12','S001','MN001','2022-09-10'),
-- ('P003','2022-06-08','2022-10-10','S006','MN003','2022-10-11'),
-- ('P010','2023-03-25','2023-07-20','S013','MN004','2023-05-25'),
-- ('P014','2023-12-22','2023-11-01','S020','MN018','2023-10-10'),
-- ('P016','2023-03-25','2022-08-07','S024','MN013','2022-05-07');

-- Select count(*) as slmuon from tb_phieumuon t1, tb_sach t2 where t1.MaSach = t2.MaSach and t1.NgayTra is NULL and t2.TrangThai='Đã mượn' and t1.MaNguoiMuon='MN017';


-- INSERT INTO `tb_phieumuon` (`MaPhieuMuon`, `NgayMuon`, `HanTra`, `MaSach`, `MaNguoiMuon`) VALUES (null, null, null, 'S001', 'MN001');
-- DELETE FROM tb_phieumuon WHERE MaPhieuMuon='P25';


-- select t2.MaPhieuMuon, t1.MaSach as t1_MaSach, t1.TenSach, t2.NgayMuon, t2.HanTra, t2.NgayTra from tb_sach t1, tb_phieumuon t2 where t1.MaSach = t2.MaSach and t2.MaNguoiMuon='MN017';
