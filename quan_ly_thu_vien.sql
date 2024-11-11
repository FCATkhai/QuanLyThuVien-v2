-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: localhost    Database: quan_ly_thu_vien
-- ------------------------------------------------------
-- Server version	8.0.39

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `librarian`
--

DROP TABLE IF EXISTS `librarian`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `librarian` (
  `username` char(30) NOT NULL,
  `password` varchar(45) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `librarian`
--

LOCK TABLES `librarian` WRITE;
/*!40000 ALTER TABLE `librarian` DISABLE KEYS */;
INSERT INTO `librarian` VALUES ('admin','12345');
/*!40000 ALTER TABLE `librarian` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_dausach`
--

DROP TABLE IF EXISTS `tb_dausach`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_dausach` (
  `MaDauSach` varchar(15) NOT NULL,
  `TenSach` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `Soluong` int NOT NULL,
  `TheLoai` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `TacGia` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `NXB` varchar(45) NOT NULL,
  `NamXB` int NOT NULL,
  PRIMARY KEY (`MaDauSach`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_dausach`
--

LOCK TABLES `tb_dausach` WRITE;
/*!40000 ALTER TABLE `tb_dausach` DISABLE KEYS */;
INSERT INTO `tb_dausach` VALUES ('DS001','Toán 10',2,'SGK','Kim Đồng','Giáo dục',2012),('DS002','Văn 10',2,'SGK','Kim Đồng','Giáo dục',2012),('DS003','Lý 12',2,'SGK','Kim Đồng','Giáo dục',2016),('DS004','Hóa 12',1,'SGK','Kim Đồng','Giáo dục',2015),('DS005','Toán rời rạc',3,'Giáo trình','Cao Tuấn Dũng','BKHT',2012),('DS006','Lập trình Java',3,'Giáo trình','Lê Thị Hoa','BTH',2009),('DS007','Lập trình C++',1,'Giáo trình','Nguyễn Thành Công','BKHXH',2015),('DS008','Lập trình Python',1,'Giáo trình','Hoàng Nam','BTNN',2002),('DS009','Lập trình C#',4,'Giáo trình','Lê  Văn Nam','THT',2010),('DS010','Trí tuệ nhân tạo',2,'Giáo trình','Thành Nam','THH',2015),('DS011','Xác xuất thống kê',3,'Giáo trình','Phạm Hoàng','TSE',2016),('DS012','Giải tích 1',1,'Giáo trình','Vũ Mạnh','TFF',2018),('DS013','Doremon',2,'Truyện tranh','Kim Đồng','THF',2012),('DS014','Naruto',0,'Truyện tranh','Kim Đồng','TSD',2018),('DS015','Connan',0,'Truyện tranh','Kim Đồng','THT',2020);
/*!40000 ALTER TABLE `tb_dausach` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_nguoimuon`
--

DROP TABLE IF EXISTS `tb_nguoimuon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_nguoimuon` (
  `MaNguoiMuon` varchar(10) NOT NULL,
  `TenNguoiMuon` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `DiaChi` varchar(45) NOT NULL,
  `Gmail` varchar(45) NOT NULL,
  `SDT` varchar(45) NOT NULL,
  PRIMARY KEY (`MaNguoiMuon`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_nguoimuon`
--

LOCK TABLES `tb_nguoimuon` WRITE;
/*!40000 ALTER TABLE `tb_nguoimuon` DISABLE KEYS */;
INSERT INTO `tb_nguoimuon` VALUES ('MN001','Trần Trọng Tuấn','Ninh Bình','trantuan@gmail.com','0349587256'),('MN002','Nguyễn Đức Trình','Nam Định','ductr@gmail.com','0987845137'),('MN003','Phạm Trung','Yến Bái','trungp@gmail.com','0987587451'),('MN004','Hồ Sỹ Sơn','Nghệ An','sontt@gmail.com','0987541632'),('MN005','Nguyễn Thắng','Nam Định','thanttt@gmail.com','0698745123'),('MN007','Nguyễn Văn Nam','Bắc Ninh','nam27@gmail.com','0986564354'),('MN008','Lê Minh Tùng','Hòa Bình','tung234@gmail.com','0978463123'),('MN009','Phạm Văn Tân','Hà Nội','tanpahm@gmail.com','0893476587'),('MN010','Nguyễn Long','Hải Dương','long@gmail.com','0234536546'),('MN011','Nguyễn Đức Tâm','Hà Nội','tanttte@gmail.com','0987865432'),('MN012','Phạm Văn Bình','Bắc Giang','vanbinh01@gmail.com','0989764532'),('MN013','Lê Ngọc Bích','Hải Phòng','bicle@gmail.com','0765231456'),('MN014','Trần Văn Hùng','Nghệ Tĩnh','hungtt@gmail.com','0954324541'),('MN015','Nguyễn Duy An','Nam Định','angng@gmail.com','0987654321'),('MN016','Vũ Văn Thưởng','Hà Nội','thungvuv@gmail.com','0234213546'),('MN017','Phạm Bá Tuấn','Hải Phòng','phatuan@gmail.com','0945324154'),('MN018','Phan Hải Long','Hà Nam','longpp@gmail.com','0394853132'),('MN019','Vũ Văn Toản','Bình Định','vantoan@gmail.com','0746324123'),('MN020','Phạm Nhật Long','Hà Đông','longnhat@gmail.com','0943251432'),('MN021','Phan Hải Nam','Ninh Bình','namna@gmail.com','09847845124'),('MN022','Lê Thành Trung','Hà Nội','trungtt@gmail.com','0287945124'),('MN22','nva','vn','nva@gmail.com','123');
/*!40000 ALTER TABLE `tb_nguoimuon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_phieumuon`
--

DROP TABLE IF EXISTS `tb_phieumuon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_phieumuon` (
  `MaPhieuMuon` varchar(15) NOT NULL,
  `NgayMuon` date NOT NULL,
  `HanTra` date NOT NULL,
  `MaSach` varchar(15) NOT NULL,
  `MaNguoiMuon` varchar(15) NOT NULL,
  `NgayTra` date DEFAULT NULL,
  PRIMARY KEY (`MaPhieuMuon`),
  KEY `MaSach_idx` (`MaSach`),
  KEY `MaNguoiMuon_idx` (`MaNguoiMuon`),
  CONSTRAINT `MaNguoiMuon` FOREIGN KEY (`MaNguoiMuon`) REFERENCES `tb_nguoimuon` (`MaNguoiMuon`),
  CONSTRAINT `MaSach` FOREIGN KEY (`MaSach`) REFERENCES `tb_sach` (`MaSach`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_phieumuon`
--

LOCK TABLES `tb_phieumuon` WRITE;
/*!40000 ALTER TABLE `tb_phieumuon` DISABLE KEYS */;
INSERT INTO `tb_phieumuon` VALUES ('P001','2022-08-12','2022-09-12','S001','MN001','2022-09-10'),('P002','2022-01-12','2022-02-12','S002','MN003',NULL),('P003','2022-06-08','2022-10-10','S006','MN003','2022-10-11'),('P004','2022-10-10','2022-12-10','S005','MN007',NULL),('P005','2022-05-05','2022-10-10','S010','MN004',NULL),('P006','2022-06-07','2022-12-12','S004','MN009',NULL),('P007','2022-04-04','2022-12-12','S023','MN003',NULL),('P008','2022-05-22','2022-10-22','S009','MN005',NULL),('P009','2023-05-22','2023-12-22','S011','MN008',NULL),('P010','2023-03-25','2023-07-20','S013','MN004','2023-05-25'),('P011','2023-01-01','2023-05-01','S008','MN019',NULL),('P012','2022-06-07','2023-01-01','S013','MN019',NULL),('P013','2022-06-07','2023-12-22','S024','MN018',NULL),('P014','2023-12-22','2023-11-01','S020','MN018','2023-10-10'),('P015','2023-03-25','2023-12-22','S020','MN001',NULL),('P016','2023-03-25','2022-08-07','S024','MN013','2022-05-07'),('P017','2022-01-12','2023-12-12','S014','MN009',NULL),('P018','2022-06-07','2023-01-01','S015','MN017','2024-11-11'),('P019','2020-03-25','2022-06-07','S012','MN017',NULL),('P020','2022-06-07','2023-01-01','S012','MN018',NULL),('P021','2022-08-07','2023-11-01','S018','MN003',NULL),('P022','2022-01-12','2023-11-01','S007','MN004',NULL),('P023','2022-06-07','2023-11-01','S017','MN007',NULL),('P024','2022-08-07','2023-01-01','S020','MN004',NULL),('P025','2024-11-10','2024-11-17','S001','MN22',NULL);
/*!40000 ALTER TABLE `tb_phieumuon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_sach`
--

DROP TABLE IF EXISTS `tb_sach`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_sach` (
  `MaSach` varchar(15) NOT NULL,
  `TenSach` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `TrangThai` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `MaDauSach` varchar(15) NOT NULL,
  PRIMARY KEY (`MaSach`),
  KEY `MaDauSach_idx` (`MaDauSach`),
  CONSTRAINT `MaDauSach` FOREIGN KEY (`MaDauSach`) REFERENCES `tb_dausach` (`MaDauSach`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_sach`
--

LOCK TABLES `tb_sach` WRITE;
/*!40000 ALTER TABLE `tb_sach` DISABLE KEYS */;
INSERT INTO `tb_sach` VALUES ('S001','Toán 10','Đã mượn','DS001'),('S002','Văn 10','Đã mượn','DS002'),('S003','Hóa 12','Còn','DS004'),('S004','Toán rời rạc','Đã mượn','DS005'),('S005','Toán rời rạc','Đã mượn','DS005'),('S006','Lập trình Java','Còn','DS006'),('S007','Văn 10','Đã mượn','DS002'),('S008','Lập trình C++','Đã mượn','DS007'),('S009','Lý 12','Đã mượn','DS003'),('S010','Toán rời rạc','Đã mượn','DS005'),('S011','Lập trình Java','Đã mượn','DS006'),('S012','Lập trình Java','Đã mượn','DS006'),('S013','Toán 10','Còn','DS001'),('S014','Lập trình C#','Đã mượn','DS009'),('S015','Doremon','Đã mượn','DS013'),('S016','Xác xuất thống kê','Còn','DS011'),('S017','Lập trình C#','Đã mượn','DS009'),('S018','Lập trình C#','Đã mượn','DS009'),('S019','Trí tuệ nhân tạo','Còn','DS010'),('S020','Giải tích 1','Đã mượn','DS012'),('S021','Lập trình C#','Còn','DS009'),('S022','Doremon','Còn','DS013'),('S023','Lập trình Python','Đã mượn','DS008'),('S024','Xác xuất thống kê','Còn','DS011'),('S025','Xác xuất thống kê','Còn','DS011'),('S026','Trí tuệ nhân tạo','Còn','DS010'),('S027','Lý 12','Còn','DS003');
/*!40000 ALTER TABLE `tb_sach` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `username` varchar(10) NOT NULL,
  `password` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`username`),
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`username`) REFERENCES `tb_nguoimuon` (`MaNguoiMuon`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('MN001','1'),('MN002','1'),('MN003','1'),('MN004','1'),('MN005','1'),('MN007','1'),('MN008','1'),('MN009','1'),('MN010','1'),('MN011','1'),('MN012','1'),('MN013','1'),('MN014','1'),('MN015','1'),('MN016','1'),('MN017','1'),('MN018','1'),('MN019','1'),('MN020','1'),('MN021','1'),('MN022','1'),('MN22','123');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-11-11 15:02:12
